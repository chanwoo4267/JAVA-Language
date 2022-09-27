import java.util.ArrayList;
import java.util.Collections;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class BookInfo implements Comparable<BookInfo>
{
	private String price;
	private String name;
	private String author;
	private int rank;
	
	BookInfo(String price, String name, String author, int rank)
	{
		this.price = price;
		this.name = name;
		this.author = author;
		this.rank = rank;
	}
	
	public String getPrice()
	{
		return this.price;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getAuthor()
	{
		return this.author;
	}
	
	public int getRank()
	{
		return this.rank;
	}
	
	@Override
	public int compareTo(BookInfo s)
	{
		if (this.rank < s.getRank())
			return 1;
		else if (this.rank > s.getRank())
			return -1;
		else
			return 0;
	}
	
	public String toString()
	{
		return "#" + rank + " " + name + ", " + author + ", " + price;
	}
}

class BookReader
{
	public static ArrayList<BookInfo> readBooks(String input)
	{
		ArrayList<BookInfo> b = new ArrayList<BookInfo>();
		ArrayList<String> lines = new ArrayList<String>();
		URL url = null;
		BufferedReader reader = null;
		String line = "";
		
		try {
			url = new URL(input);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = reader.readLine()) != null)
			{
				if(line.trim().length() > 0) {
					lines.add(line);
				}
			}
			reader.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		String name = null;
		String author = null;
		String price = null;
		
		int rank = 1;
		for(int i=0; i<lines.size(); i++ ) {
			String l = lines.get(i);
			
			if (l.contains("a title=\"\" class=\" \" onclick")) {
				int begin = l.indexOf("\">") + 2;
				int end = l.indexOf("a>") - 2;
				name = l.substring(begin, end);
			}
			
			else if (l.contains("div class=\"product-shelf-author")) {
				int begin = l.indexOf("\">",50) + 2;
				int end = l.indexOf("a>") - 2;
				author = l.substring(begin, end);
			}
			
			else if (l.contains("span class=\"current\""))
			{
				l = lines.get(i+1);
				int begin = l.indexOf("\">",50) + 2;
				int end = l.indexOf("a>") - 2;
				price = l.substring(begin, end);
				b.add(new BookInfo(price,name,author,rank));
				rank++;
			}
			
		}
		
		return b;
	}
}

public class Problem19 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooks("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i=0; i<books.size(); i++) {
		BookInfo book = books.get(i);
		System.out.println(book);
		}
	}
}
