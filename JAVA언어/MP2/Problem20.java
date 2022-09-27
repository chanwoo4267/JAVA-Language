import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
	public static ArrayList<BookInfo> readBooksJsoup(String inf)
	{
		ArrayList<BookInfo> b = new ArrayList<BookInfo>();
		ArrayList<String> lines = new ArrayList<String>();
		String in = inf;
		Document doc = null;
		try {
			doc = Jsoup.connect(in).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		String name = null;
		String author = null;
		String price = null;
		Elements d = doc.select("div.product-shelf-title.pr-m > h3 > a");
		Elements e = doc.select("div.product-shelf-author.contributors > a");
		Elements f = doc.select("td.buy-new.three-column-padding > span.current > a");
		for(int i=0; i<d.size(); i++) {
			name = d.eq(i).text();
			author = e.eq(i).text();
			price = f.eq(i).text();
			b.add(new BookInfo(price,name,author,i+1));
		}
		return b;
	}
}

public class Problem20 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooksJsoup("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i=0; i<books.size(); i++) {
		BookInfo book = books.get(i);
		System.out.println(book);
		}
	}
}