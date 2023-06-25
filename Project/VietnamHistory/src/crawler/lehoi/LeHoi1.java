package crawler.lehoi;

import java.io.IOException;
import java.util.ArrayList;

// import javax.swing.text.html.parser.Element;

import org.jsoup.select.Elements;

import crawler.util.datain.AGetData;
import model.lehoi.LeHoi;
import model.nhanvatlichsu.NhanVatLichSu;
public class LeHoi1 extends AGetData {
	private ArrayList<LeHoi> leHoi;

	public LeHoi1() {
		String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		this.connect();
		leHoi = new ArrayList<LeHoi>();
	}

	public ArrayList<LeHoi> getLeHoi() {
		return leHoi;
	}



	@Override
	public void scraping() throws IOException {
		Elements row = this.getDoc().select("table.prettytable.wikitable > tbody > tr");
        for(int j = 1; j < row.size(); j++)
        {
           Elements a = row.get(j).select("td");
        	   LeHoi data = new LeHoi();
        	   
        	   data.setThoiGian(a.get(0).text());  
        	   
        	   data.setDiaDiem(a.get(1).text());
        	   
        	   data.setTen(a.get(2).text());
        	   
   				ArrayList<NhanVatLichSu> doiTuongList = new ArrayList<>();
   				String [] nhanVat = a.get(4).text().split("\\,");
   				// String [] p;
   				if(j == 10)
   				{
   					String[] newArray = new String[7];
   					newArray[0] = nhanVat[0];
   					newArray[1] = nhanVat[1].substring(22);
   					newArray[2] = nhanVat[2];
   					newArray[3] = nhanVat[3];
   					newArray[3] = newArray[3].substring(0, nhanVat[3].length() - 16);
   					newArray[4] = nhanVat[3];
   					newArray[4] = newArray[4].substring(14, nhanVat[3].length() - 1);
   					newArray[5] = nhanVat[4];
   					newArray[5] = newArray[5].substring(17, 28);
   					newArray[6] = nhanVat[4];
   					newArray[6] = newArray[6].substring(33);
   	   				for(String t : newArray) {
   	    	   			NhanVatLichSu doiTuong = new NhanVatLichSu();
   	    				doiTuong.setTen(t.trim());
   	    				doiTuongList.add(doiTuong);
   	    				}
   				}
   				else {				for(String t : nhanVat) {
   	   			NhanVatLichSu doiTuong = new NhanVatLichSu();
   				doiTuong.setTen(t.trim());
   				doiTuongList.add(doiTuong);
   				
   				}
   				}
   				
   				data.setNhanVatLienQuan(doiTuongList);
   				
   				data.setNoiDung(a.get(5).text());
   				
   				leHoi.add(data);
   				
          }
        
		}


	public static void main(String[] args) throws IOException {
		LeHoi1 leHoi1 = new LeHoi1();
		leHoi1.scraping();
		ArrayList<LeHoi> content = leHoi1.getLeHoi();
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
