package crawler.lehoi;

import java.io.IOException;

import crawler.util.datain.AGetData;
// import javax.swing.text.html.parser.Element;

import org.jsoup.select.Elements;

// import crawler.util.datain.GetData;
import model.lehoi.LeHoi;
import java.util.ArrayList;
import model.nhanvatlichsu.NhanVatLichSu;;

public class LeHoi2 extends AGetData {
	private ArrayList<LeHoi> leHoi;

	public LeHoi2() {
		String url = "https://angiangtourist.vn/thoi-gian-va-dia-diem-to-chuc-cac-le-hoi-lon-o-an-giang/#Tr";
		this.setUrl(url);
		this.connect();
		leHoi = new ArrayList<LeHoi>();
		}

		public ArrayList<LeHoi> getLeHoi() {
			return leHoi;
		}
	
	

	public void scraping() throws IOException {
		Elements row = this.getDoc().select("body> div> main > div > div > div > article > div > div > p");
		
		for(int i = 0; i < row.size(); i++)
		{
			LeHoi data = new LeHoi();
			if((!(row.get(i).select("strong").isEmpty())) && (!(row.get(i).select("br").isEmpty()))){
			String ten;
			ten = row.get(i).select("strong").text();
			data.setTen(ten);
			String[] all = row.get(i).ownText().split("\\:");
			if(i == 10)
			{
				all[1] = all[1].substring(0, (all[1].length() - 10)).trim();
				all[2] = all[2].substring(0, (all[2].length() - 9)).trim();
				all[3] = all[3].substring(0, (all[3].length() - 9)).trim();
				all[4] = all[4].trim();
			}
			else if(i == 13)
			{
				all[1] = all[1].substring(0, (all[1].length() - 9)).trim();
				all[2] = all[2].substring(0, (all[2].length() - 18)).trim();
				all[3] = all[3].substring(0, (all[3].length() - 9)).trim();
				all[4] = all[4].trim();
			}
			else {
			all[1] = all[1].substring(0, (all[1].length() - 10)).trim();
			all[2] = all[2].substring(0, (all[2].length() - 18)).trim();
			all[3] = all[3].substring(0, (all[3].length() - 9)).trim();
			all[4] = all[4].trim();
			}

					
			String thoiGian;
			thoiGian = all[1];
			data.setThoiGian(thoiGian);

			String diaDiem;
			diaDiem = all[2];
			data.setDiaDiem(diaDiem);
			data.setDiaDiem(diaDiem);

			String nhanVat;
			nhanVat = all[3];

			NhanVatLichSu doiTuong = new NhanVatLichSu();
			doiTuong.setTen(nhanVat);
			ArrayList<NhanVatLichSu> doiTuongList = new ArrayList<>();
			doiTuongList.add(doiTuong);
			data.setNhanVatLienQuan(doiTuongList);
			

			String ghiChu;
			ghiChu = all[4];
			data.setNoiDung(ghiChu);
			
			if(i == 10)
			{
				data.setThoiGian(all[2]);
				data.setDiaDiem(all[3]);
				doiTuong.setTen(all[1]);
				data.setNoiDung(all[4]);
				ArrayList<NhanVatLichSu> doiTuongList1 = new ArrayList<>();
				doiTuongList1.add(doiTuong);
				data.setNhanVatLienQuan(doiTuongList1);
			}

			leHoi.add(data);
			}
			else{
				continue;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		LeHoi2 leHoi2 = new LeHoi2();
		leHoi2.scraping();
		ArrayList<LeHoi> content = leHoi2.getLeHoi();
		ArrayList<NhanVatLichSu> in;
		for (LeHoi s : content) {
			System.out.println();
			System.out.println("ten:" + s.getTen());
			System.out.println("dia diem:" + s.getDiaDiem());
			System.out.println("thoi gian:" + s.getThoiGian());
			System.out.println("noi dung:" + s.getNoiDung());
			in = s.getNhanVatLienQuan() ;
			int m = 1;
			for(NhanVatLichSu k : in)
			{
				System.out.println("nhan vat " + m + ":" + k.getTen());
				m++;
			}
		}

	}
	
	
}
