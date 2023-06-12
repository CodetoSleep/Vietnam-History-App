package crawler.thoiky;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.util.datain.AGetData;
import model.thoiky.ThoiKy;

public class ThoiKy1 extends AGetData {
	private ArrayList<ThoiKy> thoiKy;
	
	public ThoiKy1() {
		String url = "https://nguoikesu.com/tu-lieu/bang-doi-chieu-cac-trieu-dai-viet-nam-va-cac-trieu-dai-trung-quoc";
		this.setUrl(url);
		this.connect();
		this.thoiKy = new ArrayList<ThoiKy>();
	}

	public ArrayList<ThoiKy> getThoiKy() {
		return thoiKy;
	}

	@Override
	public void scraping() throws IOException {
		Elements superContents = this.getDoc().select("#content > div.com-content-article.item-page > div.com-content-article__body > table.table.table-bordered > tbody > tr > td");
		Elements contents = superContents.select("strong , p > strong");
		for (Element e: contents) {
			if (e.text().equals("Chủ tịch nước") || e.text().equals("Thời gian") || e.text().equals("Trung Quốc") ) {
				continue;
			}
			String quocHieu= "";
			int startIndex = e.text().indexOf("QUỐC HIỆU");
			if(startIndex != -1){
				int commaIndex = e.text().indexOf(",", startIndex);
				if (commaIndex != -1) {
					quocHieu = e.text().substring(startIndex + "QUỐC HIỆU".length(), commaIndex).trim();
				}
				else{
					int separatedComma = e.text().indexOf('K');
					quocHieu = e.text().substring(startIndex + "QUỐC HIỆU".length(), separatedComma).trim();
				}
			}
			else{
				quocHieu = "Không rõ";
			}
			String result;
			int index = e.text().indexOf('(');
			if (index != -1) {
				result = e.text().substring(0, index-1);
			}
			else {
				result = e.text();
			}
			int idx = result.indexOf('.');
			if (idx != -1) {
				result = result.substring(idx+2);
			}
			if (result.equals("THỜI KỲ X Y DỰNG NỀN TỰ CHỦ")){
				result = "THỜI KỲ XÂY DỰNG NỀN TỰ CHỦ";
			} else if (result.equals("PHONG KIẾN PHƯƠNG BẮC ĐÔ HỘ LẦN THỨ NHẤT")){
				result = "NHÀ TRIỆU";
			}
			result = result.toUpperCase();
			ThoiKy t = new ThoiKy(result);
			t.setQuocHieu(quocHieu);
			if(t.getTen().equals("NHÀ LÝ")){
				t.setQuocHieu(t.getQuocHieu() + " (TỪ 1054 ĐỔI QUỐC HIỆU LÀ ĐẠI VIỆT)" );
			}
			String giaiDoan;
			if(result.equals("VIỆT NAM DÂN CHỦ CỘNG HÒA") || result.equals("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM")){
				giaiDoan = e.text().substring(e.text().indexOf("(") + 1,e.text().indexOf(")") );
				t.setThoiGianTonTai(giaiDoan);
				t.setNguoiSangLap(null);
			}

			this.thoiKy.add(t);
		}
	}
	
	public static void main(String[] args) throws IOException {
		ThoiKy1 thoiKy1 = new ThoiKy1();
		thoiKy1.scraping();
		ArrayList<ThoiKy> content = thoiKy1.getThoiKy();
		for (ThoiKy s : content) {
			System.out.println(s.getTen());
			System.out.println();
			System.out.println(s.getQuocHieu());
		}
	}
}
