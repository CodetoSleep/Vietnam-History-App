package crawler.sukienlichsu;

import java.io.IOException;
import model.sukienlichsu.*;

import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.util.datain.AGetData;

public class ChienTranh2 extends AGetData {
    private ArrayList<ChienTranh> chientranh2 = new ArrayList<ChienTranh>(100);
    private ArrayList<String> CT2Links = new ArrayList<>(100);

    public ArrayList<ChienTranh> getChienTranh2() {
        return chientranh2;
    }

    public ChienTranh2() {
        String url = "https://nguoikesu.com/tu-lieu/quan-su?filter_tag[0]=";
        this.setUrl(url);
        this.connect();
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
    @Override
    public void scraping() throws IOException {
        processPage(url);
        for(int i=0; i<CT2Links.size(); i++) {
        	ChienTranh s = new ChienTranh();
        	this.setUrl(CT2Links.get(i));
        	this.connect();
        	Elements table = doc.select("table > tbody > tr > td > table > tbody > tr:nth-child(4) > td > table");
        	if( table.isEmpty()) {
        		table = doc.select("table > tbody > tr > td > table > tbody > tr:nth-child(3) > td > table");
        	}
        	if( table.isEmpty()) {
        		table = doc.select("#content > div.com-content-article.item-page > div.com-content-article__body > div.infobox > table:nth-child(3)");
        	}
        	Elements rows = table.select("tr");
        	Element title = doc.selectFirst("#content > div.com-content-article.item-page > div.page-header > h1");
        	s.setTen(loaiboThoiGian(title.text()));
            for (Element row : rows) {
            	Elements cells = row.select("td");
            	for (int k=0;k<cells.size();k++) {
            	    if(cells.get(k).text().equalsIgnoreCase("Thời gian")) {
            	    	s.setThoiGian(cells.get(k+1).text());
            	    }
            	    if(cells.get(k).text().equalsIgnoreCase("Địa điểm")) {
            	    	s.setDiaDiem(cells.get(k+1).text());
            	    }
            	    if(cells.get(k).text().equalsIgnoreCase("Kết quả")) {
            	    	s.setKetQua(cells.get(k+1).text());
            	    }
            	}
            }
            
            Elements table1 = doc.select("table > tbody > tr > td > table");
            if (table1 == null) {
            	table1 = doc.select("#content > div.com-content-article.item-page > div.com-content-article__body > div.infobox > table:nth-child(3)");
            }
            Elements row1s = table1.select(" tr");
            for (int h=0; h<row1s.size();h++) {
                // Check if the row contains the search string
                if (row1s.get(h).text().contains("Tham chiến")) {
                	Elements side = row1s.get(h+1).select("td");
                	s.setBenThuNhat(side.get(0).text());
                	if(side.size() >= 2 && side.get(1) != null) {
                	s.setBenThuHai(side.get(1).text());
                	}
                    break; // Optional: Exit the loop if the first match is found
                }
            }
            if(s.getTen().equalsIgnoreCase("Trận Ngọc Hồi - Đống Đa năm 1789")) {
            	s.setBenThuHai("Đại Thanh nhà Lê");
            }
            this.chientranh2.add(s);
		}
    }

    public void processPage(String url) throws IOException {
        Elements links = doc.select("div > div > h2 > a");
        printLinks(links);

        Element nextButton = doc.selectFirst("div > nav > ul > li:nth-child(13) > a");
        if (nextButton != null) {
        	String absHref = nextButton.attr("abs:href"); // "http://jsoup.org/"
            this.setUrl(absHref);
            this.connect();
            processPage(absHref);
        }
    }

    public void printLinks(Elements links) {
        for (Element link : links) {
            String linkUrl = link.attr("abs:href");
            CT2Links.add(linkUrl);
        }
    }

    

    public static void main(String[] args) throws IOException {
        ChienTranh2 test = new ChienTranh2();
        test.scraping();
        ArrayList<ChienTranh> content = test.getChienTranh2();
		for (ChienTranh s : content) {
			System.out.println(s.getTen());
			System.out.println(s.getThoiGian());
			System.out.println(s.getDiaDiem());
			System.out.println(s.getBenThuNhat());
			System.out.println(s.getBenThuHai());
			System.out.println(s.getKetQua());
		}
    }
}
