package crawler.sukienlichsu;

import java.io.IOException;
import model.sukienlichsu.*;



import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import crawler.util.datain.AGetData;
public class SuKien2 extends AGetData {
    private ArrayList<SuKienLichSu> sukien2 = new ArrayList<SuKienLichSu>(180);
    private ArrayList<String> SK2Links = new ArrayList<>(180);
	public ArrayList<SuKienLichSu> getSuKien2(){
		return sukien2;
	}
	public SuKien2() {
		String url = "https://thuvienlichsu.com/category/su-kien/";
		this.setUrl(url);
		this.connect();
		;
	}

	@Override
	public void scraping() throws IOException {
		processPage(url);
		for(int i=0; i<SK2Links.size(); i++) {
			SuKienLichSu s = new SuKienLichSu();
			this.setUrl(SK2Links.get(i));
			this.connect();
			Element paragraph = doc.selectFirst("#content > div > div > div > div.bs-blog-post.single > article > p");
			Element title = doc.selectFirst("#content > div > div > div > div.bs-blog-post.single > div > h1 > a");
			s.setTen(loaiboThoiGian(title.text()));
			s.setThoiGian(CaoThoiGian(title.text()));
            s.setNoiDung(paragraph.text());	  
            s.setThoiKy(null);
            this.sukien2.add(s);
		}
		
		
        
	};
	public void processPage(String url) throws IOException {
     

        // Find all anchor tags in the document
        Elements links = doc.select("div > article > h4 > a");
        printLinks(links);

        // Find the "next" button and process the next page recursively
        Element nextButton = doc.selectFirst("a.next.page-numbers");
        if (nextButton != null) {
            String nextPageUrl = nextButton.absUrl("href");
            this.setUrl(nextPageUrl);
            this.connect();
            processPage(nextPageUrl);
        }
    }

    public void printLinks(Elements links) {
        for (Element link : links) {
            // String linkText = link.text();
            String linkUrl = link.attr("href");
            SK2Links.add(linkUrl);
            
        }
    }
	
    private String CaoThoiGian(String input) {
	    StringBuilder result = new StringBuilder();
	    int openParenthesisCount = 0;

	    for (char c : input.toCharArray()) {
	        if (c == '(') {
	            openParenthesisCount++;
	        } else if (c == ')' && openParenthesisCount > 0) {
	            openParenthesisCount--;
	        } else if (openParenthesisCount > 0) {
	            result.append(c);
	        }
	    }

	    return result.toString();
	}
	
	private String loaiboThoiGian(String input) {
	    StringBuilder result = new StringBuilder();
	    int openParenthesisCount = 0;

	    for (char c : input.toCharArray()) {
	        if (c == '(') {
	            openParenthesisCount++;
	        } else if (c == ')' && openParenthesisCount > 0) {
	            openParenthesisCount--;
	        } else if (openParenthesisCount == 0) {
	            result.append(c);
	        }
	    }

	    return result.toString();
	}
    /*public void printLinkContent(String linkUrl) throws IOException {
        Elements paragraphs = doc.select("#content > div > div > div > div.bs-blog-post.single > article > p");

        if (!paragraphs.isEmpty()) {
            for (Element paragraph : paragraphs) {
                String paragraphText = paragraph.text();
                System.out.println(paragraphText);
            }
            System.out.println();
        }
    }*/
	public static void main(String[] args) throws IOException{
		SuKien2 test = new SuKien2();
		test.scraping();
		ArrayList<SuKienLichSu> content = test.getSuKien2();
		for (SuKienLichSu s : content) {
			System.out.println(s.getTen());
			System.out.println(s.getThoiGian());
			System.out.println(s.getNoiDung());
		}
    }
	
	
}
