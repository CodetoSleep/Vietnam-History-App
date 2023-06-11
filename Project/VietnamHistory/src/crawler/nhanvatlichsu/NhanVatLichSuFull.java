package crawler.nhanvatlichsu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.util.dataout.ICombine;
import crawler.util.dataout.IWriteJson;
import model.nhanvatlichsu.NhanVatLichSu;
import model.thoiky.ThoiKy;

public class NhanVatLichSuFull implements IWriteJson, ICombine {
	private NhanVatLichSu nvlsFull;
	private ArrayList<NhanVatLichSu> listFull = new ArrayList<>();
	private ArrayList<NhanVatLichSu> list1 = new ArrayList<>();

	public NhanVatLichSuFull(){
		nvlsFull = new NhanVatLichSu();
	}

	public static String replaceThoiKy(String original) {
		String thoiKy = "";
		switch (original) {
			case "Bắc thuộc lần 1": {
				thoiKy = thoiKy.concat("Thời kỳ đấu tranh chống phong kiến phương Bắc thống trị lần thứ nhất");
				break;
			}
			case "Hai Bà Trưng": {
				thoiKy = thoiKy.concat("Trưng Nữ Vương");
				break;
			}
			case "Nhà Tiền Lý, Triệu": {
				thoiKy = thoiKy.concat("Nhà Tiền Lý và Nhà Triệu");
				break;
			}
			case "Hậu Trần": {
				thoiKy = thoiKy.concat("Nhà Hậu Trần");
				break;
			}
			case "Trịnh - Nguyễn": {
				thoiKy = thoiKy.concat("Nhà Hậu Lê");
				break;
			}
			case "Triều Lê Sơ": {
				thoiKy = thoiKy.concat("Nhà Lê sơ");
				break;
			}
			case "Nam - Bắc Triều": {
				thoiKy = thoiKy.concat("Nhà Mạc");
				break;
			}
			case "Nhà Nguyễn độc lập": {
				thoiKy = thoiKy.concat("Nhà Nguyễn");
				break;
			}
			case "Pháp đô hộ": {
				thoiKy = thoiKy.concat("Đế quốc Việt Nam");
				break;
			}
			case "Nước Việt Nam mới": {
				thoiKy = thoiKy.concat("Việt Nam Dân chủ Cộng hòa");
				break;
			}
			case "Dựng nước": {
				thoiKy = thoiKy.concat("Họ Hồng Bàng");
				break;
			}
			default: {
				thoiKy = thoiKy.concat(original);
			}
		}
		return thoiKy;
	}

	@Override
	public void Combine() throws IOException {
		int pageIndex = 1;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";
		while (pageIndex <= 2300) {
			String url = urlFirstHalf + pageIndex;
			NhanVatLichSu2 vanSu = new NhanVatLichSu2(url);
			vanSu.scraping();
			listFull.add(vanSu.getNVLS2());
			pageIndex += 1;
		}

		NhanVatLichSu1 nguoiKeSu = new NhanVatLichSu1();
		nguoiKeSu.ketNoi();
		list1 = nguoiKeSu.getListNVLS1();

		for (int i = 0; i < listFull.size(); i++) {
			NhanVatLichSu nvlsF = listFull.get(i);
			for (NhanVatLichSu nvls1 : list1) {
				if (nvlsF.getTen().equals(nvls1.getTen()) || nvlsF.getTen().equals(nvls1.getTenKhac())) {
					if (nvlsF.getNamSinh() == null) nvlsFull.setNamSinh(nvls1.getNamSinh());
					else if (nvlsF.getNamMat() == null) nvlsFull.setNamMat(nvls1.getNamMat());
					else if (nvlsF.getQueQuan() == null) nvlsFull.setQueQuan(nvls1.getQueQuan());
					else if (nvlsF.getThoiKy() == null) nvlsFull.setThoiKy(nvls1.getThoiKy());
				}
			}
		}

		ArrayList<String> tenAll = new ArrayList<>();
		for (int i = 0; i < listFull.size(); i++) {
			tenAll.add(listFull.get(i).getTen());
		}
		for (int j = 0; j < list1.size(); j++){
			String ten = list1.get(j).getTen();
			String tenKhac = list1.get(j).getTenKhac();
			if(!tenAll.contains(ten) && !tenAll.contains(tenKhac)) listFull.add(list1.get(j));
		}

		for (NhanVatLichSu figure : listFull) {
			ArrayList<ThoiKy> dynastyList = figure.getThoiKy();
			for (ThoiKy dynasty : dynastyList) {
				String name = dynasty.getTen();
				dynasty.setTen(replaceThoiKy(name));
			}
		}
		System.out.println("So luong: " + listFull.size());

		Vua1 vua = new Vua1();
		vua.scraping();
		vua.WriteJson();
	}

	@Override
	public void WriteJson() throws IOException {
		String filePath = "src/crawler/test/nvls.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(filePath);
			gson.toJson(listFull, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		NhanVatLichSuFull nvF = new NhanVatLichSuFull();
		nvF.Combine();
		nvF.WriteJson();
	}
}
