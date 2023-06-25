package crawler.lehoi;

import java.io.IOException;

import crawler.util.datain.AGetData;
// import javax.swing.text.html.parser.Element;

import org.jsoup.select.Elements;

// import crawler.util.datain.GetData;
import model.lehoi.LeHoi;
import java.util.ArrayList;
// import model.nhanvatlichsu.NhanVatLichSu;
// import crawler.util.datain.GetData;

public class LeHoi3 extends AGetData {
	private ArrayList<LeHoi> leHoi;

	public LeHoi3() {
		String url = "https://dulichkhampha24.com/le-hoi-o-da-nang.html";
		this.setUrl(url);
		this.connect();
		leHoi = new ArrayList<LeHoi>();
		}

		public ArrayList<LeHoi> getLeHoi() {
			return leHoi;
		}
	
	

	public void scraping() throws IOException {
		Elements row = this.getDoc().select("body > div > div > div > div > div > div > article > div.td-post-content");
		Elements var1 = row.select("h3");
		Elements var2 = row.select("ul");
		for(int i = 0, j = 2; i < var1.size() && j<var2.size(); i++, j++)
		{
			LeHoi data = new LeHoi();
			String ten = var1.get(i).select("span > span >strong").text();
			ten = ten.substring(3).trim();
			data.setTen(ten);
			
			Elements var3 = var2.get(j).select("li");
			
			String ngay = var3.get(0).text();
			ngay = ngay.replace("Thời gian: ", "");
			data.setThoiGian(ngay);
			
			String diaDiem = var3.get(1).text();
			diaDiem = diaDiem.replace("Địa điểm: ", "");
			data.setDiaDiem(diaDiem);
			leHoi.add(data);
		}
		
	}

	public static void main(String[] args) throws IOException {
		LeHoi3 leHoi3 = new LeHoi3();
		leHoi3.scraping();
		ArrayList<LeHoi> content = leHoi3.getLeHoi();
		// ArrayList<NhanVatLichSu> in;
		for (LeHoi s : content) {
			System.out.println();
			System.out.println("ten:" + s.getTen());
			System.out.println("dia diem:" + s.getDiaDiem());
			System.out.println("thoi gian:" + s.getThoiGian());
			System.out.println("noi dung:" + s.getNoiDung());
		}

	}
}
