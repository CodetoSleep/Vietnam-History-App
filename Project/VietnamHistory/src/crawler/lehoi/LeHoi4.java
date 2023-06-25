package crawler.lehoi;

import java.io.IOException;

import crawler.util.datain.AGetData;
// import javax.swing.text.html.parser.Element;

import org.jsoup.select.Elements;

import model.lehoi.LeHoi;
import java.util.ArrayList;
import model.nhanvatlichsu.NhanVatLichSu;


public class LeHoi4 extends AGetData {
	private ArrayList<LeHoi> leHoi;

	public LeHoi4() {
		String url = "https://alltours.vn/tuyen-quang/cac-le-hoi-o-tuyen-quang.html";
		this.setUrl(url);
		this.connect();
		leHoi = new ArrayList<LeHoi>();
		}

		public ArrayList<LeHoi> getLeHoi() {
			return leHoi;
		}
	
	public void scraping() throws IOException {
		Elements row = this.getDoc().select("body > div.clr > div.clr > main> div.container.clr> div > div>  article > div.single-blog-content.entry.clr");
		Elements var1 = row.select("h2");
		Elements var2 = row.select("p");
		for(int i = 0, j = 1; i < var1.size() && j<var2.size(); i++, j++)
		{
			LeHoi data = new LeHoi();
			String ten = var1.get(i).select("span").text();
			ten = ten.substring(3).trim();
			if(i == 0)
			{
				ten = ten.substring(0, (ten.length() - 30)).trim();
			}
			data.setTen(ten);
			String [] all = var2.get(j).text().split("\\:");
			if(	i >= 4)
			{
				String p = all[2];
				all[2] = all[3];
				all[3] = p;
				all[1] = all[1].substring(0, (all[1].length() - 10)).trim();
				all[2] = all[2].substring(0, (all[2].length() - 9)).trim();
				all[3] = all[3].substring(0, (all[3].length() - 18)).trim();
				for(int m = 5; m < all.length; m++)
				{
				all[4] = all[4].trim() +":" + all[m];
				}
				all[4] = all[4].trim();
			}
			else {
			all[1] = all[1].substring(0, (all[1].length() - 18)).trim();
			all[2] = all[2].substring(0, (all[2].length() - 9)).trim();
			all[3] = all[3].substring(0, (all[3].length() - 8)).trim();
			for(int m = 5; m < all.length; m++)
			{
			all[4] = all[4].trim() +":" + all[m];
			}
			all[4] = all[4].trim();
			}
			
			String ngay = all[3];
			data.setThoiGian(ngay);
			
			String diaDiem = all[1];
			data.setDiaDiem(diaDiem);
			
			String nhanVat = all[2];
			NhanVatLichSu doiTuong = new NhanVatLichSu();
			doiTuong.setTen(nhanVat);
			ArrayList<NhanVatLichSu> doiTuongList = new ArrayList<>();
			doiTuongList.add(doiTuong);
			data.setNhanVatLienQuan(doiTuongList);
			
			String ghiChu = all[4];
			data.setNoiDung(ghiChu);
			
			leHoi.add(data);
		}
		
	}

	public static void main(String[] args) throws IOException {
		LeHoi4 leHoi4 = new LeHoi4();
		leHoi4.scraping();
		ArrayList<LeHoi> content = leHoi4.getLeHoi();

		for (LeHoi s : content) {
			System.out.println();
			System.out.println("ten:" + s.getTen());
			System.out.println("dia diem:" + s.getDiaDiem());
			System.out.println("thoi gian:" + s.getThoiGian());
			System.out.println("noi dung:" + s.getNoiDung());
		}


	}
	
	
}
