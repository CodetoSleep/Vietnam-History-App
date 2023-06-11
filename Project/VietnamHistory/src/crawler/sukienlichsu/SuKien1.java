package crawler.sukienlichsu;
import model.thoiky.*;
import model.sukienlichsu.*;
import java.io.IOException;
// import java.io.File;
// import java.io.FileWriter;

import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;

import crawler.util.datain.GetData;

public class SuKien1 extends GetData {
	private ArrayList<SuKienLichSu> NienBieuLichSu = new ArrayList<SuKienLichSu>();
	
	public ArrayList<SuKienLichSu> getList(){
		return NienBieuLichSu;
	}
	public SuKien1() {
		String url = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		this.connect();
		;
	}
	private String ChuaChuSo(String DuLieuTho) {
		char[] chars = DuLieuTho.toCharArray();
		for (char c : chars) {
			if (Character.isDigit(c))
				return DuLieuTho;
		}
		return "";
	}

	private String CaoThoiGian(String DuLieuTho) {
		DuLieuTho = DuLieuTho.replaceAll("[^0-9]", "#");
		String[] arr = DuLieuTho.split("#");

		boolean LaMotNam = false;
		int SoNamTrongDuLieu = 0;
		StringBuilder ThoiGianTraVe = new StringBuilder();

		for (String s : arr) {
			if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$"))
				LaMotNam = true;
			SoNamTrongDuLieu++;
			if (SoNamTrongDuLieu > 1)
				ThoiGianTraVe.append(" - ");
			ThoiGianTraVe.append(s);
		}

		if (!LaMotNam)
			return "";
		return ThoiGianTraVe.toString();
	}
	
	private String CaoDiaDiem(String DuLieuTho, String KyTuCanXoa) {
		// StringBuilder s = new StringBuilder();
		final String[] tukhoa = new String[] { "Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh ", "Văn hóa ", "Khởi nghĩa ", "Vụ án ", "Giải phóng " };
		boolean CanChinhSua = false;
		DuLieuTho = DuLieuTho.replace(KyTuCanXoa, "");

		for (int i = 0; i < tukhoa.length; i++) {
			if (DuLieuTho.contains(tukhoa[i])) {
				CanChinhSua = true;
				DuLieuTho = DuLieuTho.replace(tukhoa[i], "");
				DuLieuTho = DuLieuTho.replaceAll("[-()]", "");
			}
		}
		if (CanChinhSua)
			return DuLieuTho;
		return "";
	}


	@Override
	public void scraping() throws IOException {
		String TamNhoGiaTriThoiGian = "";
		Element noi_dung_chinh = this.doc.getElementById("bodyContent");
		// Elements thoi_ki = noi_dung_chinh.select("h3");
		Elements su_kien = noi_dung_chinh.select("p, dd");
		for (int i=0; i<su_kien.size();i++) {
			String ten = su_kien.get(i).select("a").text();
			if (ten.length() == 0) {
				TamNhoGiaTriThoiGian = su_kien.get(i).select("b").text();
				TamNhoGiaTriThoiGian = ChuaChuSo(TamNhoGiaTriThoiGian);
				continue;
			}
			String thoi_gian = su_kien.get(i).select("b").text();
			if (ChuaChuSo(thoi_gian) == "")
				continue;
			if (CaoThoiGian(thoi_gian) == "")
				thoi_gian = thoi_gian.concat(" " + TamNhoGiaTriThoiGian);
			SuKienLichSu s = new SuKienLichSu();
			s.setTen(ten);
			s.setThoiGian(thoi_gian);
			s.setDiaDiem(CaoDiaDiem(ten, thoi_gian));
			ThoiKy t = new ThoiKy();
			if(i>=6 && i<=9) {
				t.setTen("THỜI ĐẠI ĐỒ ĐÁ CŨ");
				s.setThoiKy(t);
			}else if(i>=10 && i<=14) {
				t.setTen("THỜI ĐẠI ĐỒ ĐÁ MỚI");
				s.setThoiKy(t);
			}else if(i>=15 && i<=17) {
				t.setTen("THỜI ĐẠI ĐỒ ĐỒNG ĐÁ");
				s.setThoiKy(t);
			}else if(i>=18 && i<=19) {
				t.setTen("Thời đại đồ đồng");
				s.setThoiKy(t);
			}else if(i>=20 && i<=23) {
				t.setTen("THỜI ĐẠI ĐỒ ĐỒNG");
				s.setThoiKy(t);
			}else if(i>=24 && i<=26) {
				t.setTen("THỜI SƠ SỬ");
				s.setThoiKy(t);
			}else if(i>=27 && i<=53) {
				t.setTen("THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC");
				s.setThoiKy(t);
			}else if(i>=54 && i<=162) {
				t.setTen("THỜI PHONG KIẾN ĐỘC LẬP");
				s.setThoiKy(t);
			}else if(i>=163 && i<=205) {
				t.setTen("VIỆT NAM DÂN CHỦ CỘNG HÒA");
				s.setThoiKy(t);
			}else if(i>=206 && i<=222) {
				t.setTen("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM");
				s.setThoiKy(t);
			}
			s.setNoiDung(null);
			// s.setNien_dai(CaoTrieuDai(thoi_gian));
			NienBieuLichSu.add(s);
		}
	};
	
	public static void main(String args[]) throws IOException{
		SuKien1 sukien = new SuKien1();
		sukien.scraping();
		ArrayList<SuKienLichSu> content = sukien.getList();
		for (SuKienLichSu s : content) {
			System.out.println(s.getThoiKy().getTen());
			System.out.println(s.getTen());
			System.out.println(s.getThoiGian());
			System.out.println(s.getDiaDiem());
		}
	}
	
	
}
