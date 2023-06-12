package crawler.lehoi;

import java.io.IOException;

import crawler.util.datain.AGetData;

import org.jsoup.select.Elements;

import model.lehoi.LeHoi;
import java.util.ArrayList;

public class LeHoi5 extends AGetData {
	private ArrayList<LeHoi> leHoi;

	public LeHoi5() {
		String url = "https://vinpearl.com/vi/le-hoi-o-ha-nam-top-10-le-hoi-dac-sac-lon-nhat-trong-nam";
		this.setUrl(url);
		this.connect();
		leHoi = new ArrayList<LeHoi>();
		}

		public ArrayList<LeHoi> getLeHoi() {
			return leHoi;
		}
	
	

	public void scraping() throws IOException {
		Elements row = this.getDoc().select("body > div.body > div.dialog-off-canvas-main-canvas > div.vp-container > div > div > div > div > div>div > div.content-wrapper.read-more > div.content");
		Elements var1 = row.select("h2");
		Elements var2 = row.select("ul");

		for(int i = 0, j = 0; i < var1.size() && j<var2.size(); i++, j++)
		{
			LeHoi data = new LeHoi();
			String ten = var1.get(i).text();
			ten = ten.substring(3).trim();
			data.setTen(ten);
			if(i == 0 || i == 2)
			{
				ten = ten.substring(0, ten.length() - 32).trim();
				data.setTen(ten);
			}
			else if((i == 9) || (i == 4) )
			{
				ten = ten.substring(0, ten.length() - 25).trim();
				data.setTen(ten);
			}
			
			Elements var3 = var2.get(j).select("li");
			
			String ngay = var3.get(0).text().substring(11).trim();
			data.setThoiGian(ngay);
			
			String diaDiem = var3.get(1).text().substring(9).trim();
			data.setDiaDiem(diaDiem);
			leHoi.add(data);
		}
		
	}

	public static void main(String[] args) throws IOException {
		LeHoi5 leHoi5 = new LeHoi5();
		leHoi5.scraping();
		ArrayList<LeHoi> content = leHoi5.getLeHoi();

		for (LeHoi s : content) {
			System.out.println();
			System.out.println("ten:" + s.getTen());
			System.out.println("dia diem:" + s.getDiaDiem());
			System.out.println("thoi gian:" + s.getThoiGian());
			System.out.println("noi dung:" + s.getNoiDung());
		}

	}
	
	
}
