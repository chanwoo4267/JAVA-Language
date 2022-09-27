package project;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Collections;

public class Client {
	static String name = null;
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.print("Please give the IP address and port number as arguments.");
			System.exit(0);
		} 
		try 
		{
			Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
			BufferedReader k = new BufferedReader(new InputStreamReader(System.in));
			OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            String title = null;
            String author = null;
            String borrower = null;
            String msg;
            if (name == null) {
            	while(true) {
	            	System.out.print("Enter userID>> ");
	            	String t = k.readLine();
	            	int i;
	            	if (t.length() == 0) {
	            		System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
						continue;
	            	}
	            	for(i=0; i<t.length(); i++) {
	            		if (t.charAt(i) < 48 || (t.charAt(i) > 57 && t.charAt(i) < 97) || t.charAt(i) > 122) {
							System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
							break;
						}	
	            	} if (i == t.length()) {
						System.out.println("Hello " + t + "!");
						name = t;
						pw.println(name);
						pw.flush();
						break;
					}
            	}
            } 
            
            System.out.print(name + ">> ");
            
            while((line = k.readLine()) != null){
            	if (line.equals("add")) {
            		System.out.print("add-title> ");
            		title = k.readLine();
            		if (title.length() == 0) {
            			System.out.print(name + ">> ");
            			continue;
            		}
            		else if (title.equals(" ")){
            			System.out.print(name + ">> ");
            			continue;
            		}
            		System.out.print("add-author> ");
            		author = k.readLine();
            		if (author.length() == 0) {
            			System.out.print(name + ">> ");
            			continue;
            		}
            		else if (author.equals(" ")){
            			System.out.print(name + ">> ");
            			continue;
            		}
            		borrower = "-";
            		pw.println("add");
            		pw.flush();
            		pw.println(title + "\t" + author + "\t" + borrower);
            		pw.flush();
            	}
            	
            	else if (line.equals("borrow")) {
            		System.out.print("borrow-title> ");
            		title = k.readLine();
            		if (title.length() == 0) {
            			System.out.print(name + ">> ");
            			continue;
            		}
            		else if (title.equals(" ")){
            			System.out.print(name + ">> ");
            			continue;
            		}
            		pw.println("borrow");
            		pw.flush();
            		pw.println(name); 
            		pw.flush();
            		pw.println(title);
            		pw.flush();
            	}
            	
            	else if (line.equals("return")) {
            		System.out.print("return-title> ");
            		title = k.readLine();
            		if (title.length() == 0) {
            			System.out.print(name + ">> ");
            			continue;
            		}
            		else if (title.equals(" ")){
            			System.out.print(name + ">> ");
            			continue;
            		}
            		pw.println("return");
            		pw.flush();
            		pw.println(name); 
            		pw.flush();
            		pw.println(title);
            		pw.flush();
            	}
            	
            	else if (line.equals("info")) {
            		pw.println("info");
            		pw.flush();
            		pw.println(name); 
            		pw.flush();
            		msg = br.readLine();           		
            		System.out.println(msg);
            		msg = br.readLine();

            		int d = Integer.parseInt(msg);
            		for(int s=0; s<d; s++)
            		{
            			msg = br.readLine();
            			System.out.println(msg);
            		}
            		System.out.print(name + ">> ");
            		continue;
            	}
            	
            	else if (line.equals("search")) {
            		System.out.print("search-string> ");
            		title = k.readLine();
            		if (title.length() == 0) {
            			System.out.print(name + ">> ");
            			continue;
            		} else if (title.length() < 3) {
            			System.out.println("Search string must be longer than 2 characters.");
            			System.out.print(name + ">> ");
            			continue;
            		}
            		pw.println("search");
            		pw.flush();
            		pw.println(title);
            		pw.flush();
            		
            		msg = br.readLine();           		
            		System.out.println(msg);
            		msg = br.readLine();
            		int d = Integer.parseInt(msg);
            		for(int s=0; s<d; s++)
            		{
            			msg = br.readLine();
            			System.out.println(msg);
            		}
            		System.out.print(name + ">> ");
            		continue;
            	}
            	
            	else {
            		System.out.println("[available commands]");
            		System.out.println("add: add a new book to the list of books.");
            		System.out.println("borrow: borrow a book from the library.");
            		System.out.println("return: return a book to the library.");
            		System.out.println("info: show list of books I am currently borrowing.");
            		System.out.println("search: search for books.");
            		System.out.print(name + ">> ");
            		continue;
            	}
  
				msg = br.readLine();
				System.out.println(msg);
				System.out.print(name + ">> ");
            }
			
		} catch(ConnectException ce) {
			System.out.print("Connection establishment failed.");
		} catch(IOException ie) {
			ie.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
