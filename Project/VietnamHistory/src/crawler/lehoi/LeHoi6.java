package crawler.lehoi;

import java.io.IOException;

import crawler.util.datain.GetData;

import org.jsoup.select.Elements;

import model.lehoi.LeHoi;
import java.util.ArrayList;


public class LeHoi6 extends GetData {
	private ArrayList<LeHoi> leHoi;

		public ArrayList<LeHoi> getLeHoi() {
			return leHoi;
		}
	

	public void scraping() throws IOException {	
		leHoi = new ArrayList<LeHoi>();
		for(int i = 0; i <= 89; i++)
		{
			String url1 = "https://blog.mytour.vn/danh-muc/le-hoi-su-kien?page=";
			String url = url1 + i;
			this.setUrl(url);
			this.connect();

			Elements row = this.getDoc().select("body > div > div > div > div > div > div");
			for(int j = 0; j < row.size(); j++)
			{
				LeHoi data = new LeHoi();
				Elements the = row.get(j).select("a");
				String ten = the.attr("title");
				if(ten.startsWith("Lễ")||ten.startsWith("Hội")) {
				data.setTen(ten);
				leHoi.add(data);
				}
			}
			
			
		}
		
	}

	public static void main(String[] args) throws IOException {
		LeHoi6 leHoi6 = new LeHoi6();
		leHoi6.scraping();
		ArrayList<LeHoi> content = leHoi6.getLeHoi();
		for (LeHoi s : content) {
			System.out.println(s.getTen());
			System.out.println();
			System.out.println(s.getDiaDiem());
			System.out.println(s.getThoiGian());
			System.out.println(s.getNoiDung());
		}


	}
	
	
}
