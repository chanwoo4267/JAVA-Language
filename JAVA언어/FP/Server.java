package project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

class Book implements Comparable<Book>{
	private String name;
	private String author;
	private String borrower;
	Book(String name, String author, String borrower) {
		this.name = name;
		this.author = author;
		this.borrower = borrower;
	}
	public String getName() {
		return this.name;
	}
	public String getAuthor() {
		return this.author;
	}
	public String getBorrower() {
		return this.borrower;
	}
	public void Borrow(String aname) {
		this.borrower = aname;
	}
	@Override 
	public int compareTo(Book o) {
		String t1 = this.name.toLowerCase();
		String t2 = o.getName().toLowerCase();
		if (t1.compareTo(t2) > 0)
			return 1;
		else if (t1.compareTo(t2) < 0)
			return -1;
		else
			return 0;
	}
}

public class Server implements Runnable {
	
	ServerSocket serverSocket;
	Thread[] threadArr;
	ArrayList<Book> booklist;
	String Username = null;
	
	static void DataWriter(ArrayList<Book> booklist) {

		File file = new File("books.txt");
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(file,false);
			for(int i=0; i<booklist.size(); i++) {
				Book t = booklist.get(i);
				String tt = t.getName() + "\t" + t.getAuthor() + "\t" + t.getBorrower() + "\n";
				writer.write(tt);
				writer.flush();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) writer.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	static ArrayList<Book> DataReader() {
		
		ArrayList<Book> booklist = new ArrayList<Book>();
		try {
				BufferedReader br = new BufferedReader(new FileReader("books.txt"));
				while(true)
				{
					String line = br.readLine();
					if (line == null) break;
					String[] arr = line.split("\t", 3);
					booklist.add(new Book(arr[0],arr[1],arr[2]));
				}
				br.close();
			} catch (IOException e)
			{
				return booklist;
			}
		return booklist;
	}
	
	/*static String getTime() {
		String name = Thread.currentThread().getName();
		SimpleDateFormat f = new SimpleDateFormat("[hh:mm:ss]");
		return f.format(new Date()) + " " + name + ": ";
	}*/
	
	//서버 생성자
	public Server(int num,int port)
	{
		try {
			serverSocket = new ServerSocket(port);
			threadArr = new Thread[num];
			booklist = DataReader();
			Collections.sort(booklist);
			DataWriter(booklist);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		for(int i=0; i<threadArr.length; i++) {
			threadArr[i] = new Thread(this);
			threadArr[i].start();
		}
	}
	
	public int add(String input)
	{
		String[] arr = input.split("\t",3);
		Book book = new Book(arr[0],arr[1],arr[2]);
		int i;	
		for(i=0; i<booklist.size(); i++)
		{
			if ((arr[0].toLowerCase()).equals(booklist.get(i).getName().toLowerCase())) {
					break;
			}
		}
		if (i == booklist.size()){
			booklist.add(book);
			return 1;
		}
		return 0;
	}
	
	public String returnf(String input,String name)
	{
		int i;	
		for(i=0; i<booklist.size(); i++)
		{
			if ((input.toLowerCase()).equals(booklist.get(i).getName().toLowerCase())) {
					if (booklist.get(i).getBorrower().equals(name)) {
						booklist.get(i).Borrow("-");
						return booklist.get(i).getName();
					}
			}
		}
		return null;
	}
	
	public String borrow(String input,String name)
	{
		int i;	
		for(i=0; i<booklist.size(); i++)
		{
			if ((input.toLowerCase()).equals(booklist.get(i).getName().toLowerCase())) {
					if (booklist.get(i).getBorrower().equals("-")) {
						booklist.get(i).Borrow(name);
						return booklist.get(i).getName();
					}
			}
		}
		return null;
	}
	
	public void run() {
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				//System.out.println(socket.getInetAddress().getHostAddress() + " connected.");
				OutputStream out = socket.getOutputStream();
				InputStream in = socket.getInputStream();
				
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line = null;
                String input = null;
                int flag;
                String flagstr;

                while((line = br.readLine()) != null){
                		if (Username == null) {
                			Username = line;
                		}
                		else if (line.equals("add")) {
                			input = br.readLine();
                			flag = add(input);
                			if (flag == 0)
                				pw.println("The book already exists in the list.");
                			else 
                				pw.println("A new book added to the list.");
                			pw.flush();
                			Collections.sort(booklist);
                			DataWriter(booklist);
                		}		
                		else if (line.equals("borrow")) { 
                			Username = br.readLine();
                			input = br.readLine();
                			flagstr = borrow(input,Username);
                			if (flagstr == null)
                				pw.println("The book is not available.");
                			else
                				pw.println("You borrowed a book. - " + flagstr);
                			pw.flush();
                			Collections.sort(booklist);
                			DataWriter(booklist);
                		}
                		else if (line.equals("return")) { 
                			Username = br.readLine();
                			input = br.readLine();
                			flagstr = returnf(input,Username);
                			if (flagstr == null)
                				pw.println("You did not borrow the book.");
                			else
                				pw.println("You returned a book. - " + flagstr);
                			pw.flush();
                			Collections.sort(booklist);
                			DataWriter(booklist);
                		}
                		else if (line.equals("search")) {
                			input = br.readLine();
                			input = input.toLowerCase();
                			int count=0;
                			for(int k=0; k<booklist.size(); k++) {
                				if (booklist.get(k).getName().toLowerCase().contains(input))
                					count++;
                				else if (booklist.get(k).getAuthor().toLowerCase().contains(input))
                					count++;
                			}
                			pw.println("Your search matched " + count + " results.");
                			pw.flush();
                			pw.println(count);
                			pw.flush();
                			count=1;
        
                			for(int k=0; k<booklist.size(); k++) {
                				if (booklist.get(k).getName().toLowerCase().contains(input)) {
                					pw.println(count + ". " + booklist.get(k).getName() + ", " + booklist.get(k).getAuthor());
                					pw.flush();
                					count++;
                				}
                				else if (booklist.get(k).getAuthor().toLowerCase().contains(input)) {
                					pw.println(count + ". " + booklist.get(k).getName() + ", " + booklist.get(k).getAuthor());
                					pw.flush();
                					count++;
                				}
                			}
                				
                		}
                		else if (line.equals("info")) { /////////////////
                			Username = br.readLine();
                			int count=0;
                			for(int k=0; k<booklist.size(); k++) 
                				if (booklist.get(k).getBorrower().equals(Username)) count++;
                			
                			pw.println("You are currently borrowing " + count + " books:");
                			pw.flush();
                			pw.println(count);
                			pw.flush();
                			count=1;
                			
                			for(int k=0; k<booklist.size(); k++)
                				if (booklist.get(k).getBorrower().equals(Username)) {
                					pw.println(count + ". " + booklist.get(k).getName() + ", " + booklist.get(k).getAuthor());
                					pw.flush();
                					count++;
                				}
                		}             
                }
                pw.close();
                br.close();
                socket.close();
			}
			catch(IOException e) { //if client exit
				//System.out.println("A Client terminated.");
			}
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.print("Please give the port number as an argument.");
			System.exit(0);
		} 
		
		Server server = new Server(100,Integer.parseInt(args[0]));
		server.start();
	}
}
