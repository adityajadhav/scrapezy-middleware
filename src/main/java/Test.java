import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Test {

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.connect("https://www.bloomberg.com/quote/SPX:IND").get();
		String title = doc.title();
		//System.out.println(title);
		
		Element price = doc.select("div.price").first();
		System.out.println(price.html());

	}

}
