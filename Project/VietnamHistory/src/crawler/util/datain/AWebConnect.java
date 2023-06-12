package crawler.util.datain;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class AWebConnect {
	protected Document doc;
	protected String url;
	
	public AWebConnect() {
		
	}
	
	protected void setUrl(String url) {
		this.url = url;
	}
	
	public Document getDoc() {
		return this.doc;
	}
	
	protected void connect() {
		try {
			doc = Jsoup.connect(this.url).timeout(10000).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36").get();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
