package crawler.thoiky;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.util.datain.GetData;
import model.nhanvatlichsu.Vua;
import model.thoiky.ThoiKy;

public class CacDoiVua extends GetData {
	private ArrayList<ThoiKy> thoiKy;
	
	public CacDoiVua() {
		String url = "https://www.wikiwand.com/vi/B%E1%BA%A3n_m%E1%BA%ABu:Danh_s%C3%A1ch_vua_v%C3%A0_ho%C3%A0ng_%C4%91%E1%BA%BF_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		this.connect();
		this.thoiKy = new ArrayList<ThoiKy>();
	}

	public ArrayList<ThoiKy> getThoiKy(){
		return thoiKy;
	}

	@Override
	public void scraping() throws IOException {
		Elements tableBody = this.getDoc().select("#content-root > article > span > div > table > tbody ");
		Elements rows = tableBody.select("tr");
		for (int i = 1; i<rows.size(); i++){
			Element row = rows.get(i);
			ThoiKy data = new ThoiKy();
			Elements trieuDai = row.select("th > a");
			Element getTenTrieuDai = trieuDai.get(0);
			String tenTrieuDai = getTenTrieuDai.text().trim().toUpperCase();
			if(tenTrieuDai.equals("HỒNG BÀNG THỊ")){
				tenTrieuDai = "HỌ HỒNG BÀNG";
			}
			else if(tenTrieuDai.equals("HAI BÀ TRƯNG")){
				tenTrieuDai = "TRƯNG NỮ VƯƠNG";
			}
			else if(tenTrieuDai.equals("NHÀ TIỀN LÝ")){
				tenTrieuDai = "NHÀ TIỀN LÝ VÀ NHÀ TRIỆU";
			}
			else if(tenTrieuDai.equals("BẮC THUỘC LẦN BA")){
				tenTrieuDai = "THỜI KỲ ĐẤU TRANH CHỐNG PHONG KIẾN PHƯƠNG BẮC THỐNG TRỊ LẦN THỨ NHẤT";
			}
			else if(tenTrieuDai.equals("TỰ CHỦ")){
				tenTrieuDai = "THỜI KỲ XÂY DỰNG NỀN TỰ CHỦ";
			}
			else if(tenTrieuDai.equals("NHÀ LÊ SƠ")){
				tenTrieuDai = "TRIỀU LÊ SƠ";
			}
			else if(tenTrieuDai.equals("NHÀ LÊ TRUNG HƯNG")){
				tenTrieuDai = "NHÀ HẬU LÊ";
			}
			data.setTen(tenTrieuDai);

			Element giaiDoan = row.select("th").first();
			giaiDoan.select("a").remove();
			String thoiGianTonTai = giaiDoan.text().trim();
			if(thoiGianTonTai.equals("Chống (713–723) (779–791)")){
				thoiGianTonTai = "(713-723; 779-791)";
			}
			thoiGianTonTai = thoiGianTonTai.replace("(", "").replace(")", "");
			data.setThoiGianTonTai(thoiGianTonTai);

			ArrayList<Vua> listVua = new ArrayList<Vua>();

			Elements vuaData = row.select("td > div > span > a");
			for (Element e : vuaData){
				Vua vua = new Vua(e.text().trim());
				listVua.add(vua);
			}
			data.setCacDoiVua(listVua);
			data.setNguoiSangLap(listVua.get(0).getTen());
			thoiKy.add(data);
		}
	}

	public static void main(String[] args) throws IOException {
		CacDoiVua cacDoiVua = new CacDoiVua();
		cacDoiVua.scraping();
		ArrayList<ThoiKy> content = cacDoiVua.getThoiKy();
		for(ThoiKy e: content){
			System.out.println(e.getTen());
			System.out.println(e.getThoiGianTonTai());
			for(Vua d: e.getCacDoiVua()){
				System.out.println(d.getTen());
			}
			System.out.println();
		}
	}
}
