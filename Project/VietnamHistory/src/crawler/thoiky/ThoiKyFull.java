package crawler.thoiky;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawler.util.dataout.ICombine;
import crawler.util.dataout.IWriteJson;
import model.thoiky.ThoiKy;

public class ThoiKyFull implements IWriteJson, ICombine {
	private ArrayList<ThoiKy> thoiKyFull;
	private CacDoiVua cacDoiVua;
	private KinhDo kinhDo;
	private KinhDoFull kinhDoFull;
	private ThoiKy1 thoiKy1;

	public ThoiKyFull(){
		thoiKyFull = new ArrayList<ThoiKy>();
	}


	@Override
	public void combine() throws IOException {
		thoiKy1 = new ThoiKy1();
		thoiKy1.scraping();
		thoiKyFull = thoiKy1.getThoiKy();

		cacDoiVua = new CacDoiVua();
		cacDoiVua.scraping();
		ArrayList<ThoiKy> thoiKy2 = new ArrayList<ThoiKy>();
		thoiKy2 = cacDoiVua.getThoiKy();
		for(ThoiKy tk : thoiKyFull){
			for(ThoiKy tk2 : thoiKy2){
				if(tk.getTen().equals(tk2.getTen())){
					tk.setCacDoiVua(tk2.getCacDoiVua());
					tk.setThoiGianTonTai(tk2.getThoiGianTonTai());
					tk.setNguoiSangLap(tk2.getNguoiSangLap());
				}
			}
		}

		kinhDo = new KinhDo();
		kinhDo.scraping();
		thoiKy2 = kinhDo.getThoiKy();
		for(ThoiKy tk : thoiKy2){
			kinhDoFull = new KinhDoFull(tk.getTen());
			kinhDoFull.scraping();
			tk.setKinhDo(kinhDoFull.getCapital());
			tk.setTen(tk.getTen().toUpperCase());
			if(tk.getTen().equals("HỒNG BÀNG THỊ – HÙNG VƯƠNG")){
				tk.setTen("HỌ HỒNG BÀNG");
				tk.setKinhDo("Phong Châu");
			}
			else if(tk.getTen().equals("BẮC THUỘC LẦN 1")){
				tk.setTen("THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC THỐNG TRỊ LẦN THỨ NHẤT");
			}
			else if(tk.getTen().equals("HAI BÀ TRƯNG")){
				tk.setTen("TRƯNG NỮ VƯƠNG");
			}
			else if(tk.getTen().equals("BẮC THUỘC LẦN 2")){
				tk.setTen("THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC LẦN THỨ HAI");
			}
			else if(tk.getTen().equals("NHÀ TIỀN LÝ")){
				tk.setTen("NHÀ TIỀN LÝ VÀ NHÀ TRIỆU");
			}
			else if(tk.getTen().equals("BẮC THUỘC LẦN 3")){
				tk.setTen("THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC LẦN THỨ BA");
			}
			else if(tk.getTen().equals("NHÀ HẬU LÝ")){
				tk.setTen("NHÀ LÝ");
				tk.setKinhDo("THĂNG LONG");
			}
			else if(tk.getTen().equals("NHÀ HẬU LÊ – LÊ SƠ")){
				tk.setTen("TRIỀU LÊ SƠ");
				tk.setKinhDo("ĐÔNG KINH");
			}
			else if(tk.getTen().equals("	NHÀ MẠC – TIỀN KỲ")){
				tk.setTen("NHÀ MẠC");
			}
			else if(tk.getTen().equals("NHÀ HẬU LÊ – LÊ TRUNG HƯNG")){
				tk.setTen("NHÀ HẬU LÊ");
				tk.setKinhDo("ĐÔNG KINH");
			}
		}
		for (ThoiKy tk: thoiKyFull){
			for (ThoiKy tk2 : thoiKy2){
				if(tk.getTen().equals(tk2.getTen())){
					tk.setKinhDo(tk2.getKinhDo());
				}
			}
		}

		for (ThoiKy tk: thoiKyFull){
			if(tk.getTen().equals("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM")){
				tk.setKinhDo("Hà Nội");
			}
			else if(tk.getTen().equals("NHÀ MẠC")){
				tk.setKinhDo("ĐÔNG KINH");
			}
			else if(tk.getTen().equals("THỜI KỲ ĐẤU TRANH CHỐNG NHÀ MINH ĐÔ HỘ")){
				tk.setKinhDo("Không rõ");
				tk.setThoiGianTonTai("1414 - 1427");
				tk.setKinhDo("Mô Độ");
			}
			else if(tk.getTen().equals("THỜI KỲ XÂY DỰNG NỀN TỰ CHỦ")){
				tk.setKinhDo("Đại La");
			}
			else if(tk.getTen().equals("THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC LẦN THỨ BA")){
				tk.setThoiGianTonTai("603 – 939");
				tk.setNguoiSangLap(null);
			}
			else if(tk.getTen().equals("THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC LẦN THỨ HAI")){
				tk.setThoiGianTonTai("43 - 543");
				tk.setNguoiSangLap(null);
			}
		}
	}

	@Override
	public void writeJson() throws IOException {
		String filePath = "src/data/thoiky/thoiKy.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(thoiKyFull, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ThoiKyFull t = new ThoiKyFull();
		t.combine();
		t.writeJson();
	}

}
