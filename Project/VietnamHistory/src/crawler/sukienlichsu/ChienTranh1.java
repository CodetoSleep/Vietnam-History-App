package crawler.sukienlichsu;

import java.io.IOException;
import model.sukienlichsu.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import crawler.util.datain.GetData;



public class ChienTranh1 extends GetData {
	private ArrayList<ChienTranh> CTVN;
	public ChienTranh1() {
		String url = "https://vi.m.wikipedia.org/wiki/C%C3%A1c_cu%E1%BB%99c_chi%E1%BA%BFn_tranh_Vi%E1%BB%87t_Nam_tham_gia";
		this.setUrl(url);
		this.connect();
		this.CTVN= new ArrayList<ChienTranh>();
	};
	
	public ArrayList<ChienTranh> getCTVN(){
		return CTVN;
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

	

	@Override
	public void scraping() throws IOException {
		// TODO Auto-generated method stub
		Elements tables = doc.select("table");

        for (int i = 2; i < tables.size(); i++) {
            Element table = tables.get(i);

            Elements rows = table.select("tr");
            for (Element row : rows) {
            	Elements cells = row.select("td");
                if (cells.size() >= 4) {
                    ChienTranh c = new ChienTranh();
                    
                	Element firstCell = cells.get(0);
                	String data1 = firstCell.text();
                	c.setTen(loaiboThoiGian(data1));
                	c.setThoiGian(CaoThoiGian(data1));
                	
                    Element secondCell = cells.get(1);
                    String data2 = secondCell.text();
                    c.setBenThuNhat(data2);
                  
                    Element thirdCell = cells.get(2);
                    String data3 = thirdCell.text();
                    c.setBenThuHai(data3);

                    Element fourthCell = cells.get(3);
                    String data4 = fourthCell.text();
                    c.setKetQua(data4);

        			this.CTVN.add(c);

                }
            }
	
	    }
	}
	
	public static void main(String args[]) throws IOException{
		ChienTranh1 CT1 = new ChienTranh1();
		CT1.scraping();
		ArrayList<ChienTranh> content = CT1.getCTVN();
		for (ChienTranh s : content) {
			System.out.println(s.getTen());
			System.out.println(s.getThoiGian());
			System.out.println(s.getBenThuNhat());
			System.out.println(s.getBenThuHai());
			System.out.println(s.getKetQua());
		}
	}
}
