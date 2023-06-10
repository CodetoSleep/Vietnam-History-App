package crawler.ditichlichsu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import crawler.util.datain.GetData;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ditich1 extends GetData {
	public static List<JSONObject> ditich2List;


	public Ditich1(){

	}

	public Ditich1(String url) {
		this.setUrl(url);
		connect();
		ditich2List = new ArrayList<>();
	}

	@Override
	public void scraping() throws IOException {
		Elements regions = this.getDoc().getElementsByTag("h2");
		regions.remove(0);

		for (Element region : regions) {
			Elements provinces = region.nextElementSiblings();

			for (Element province : provinces) {
				if (province.tagName().equals("h2")) break;
				if (province.tagName().equals("table")) continue;

				Element table = province.nextElementSibling();

				for (Element tr : table.select("tr")) {

					Elements td = tr.select("td");

					if (td.size() == 5) {
						JSONObject ditich = new JSONObject();

						String vung = region.text();
						String tinh = province.text();

						if (vung.contains("[sửa | sửa mã nguồn]")) {
							vung = vung.replace("[sửa | sửa mã nguồn]", "");
						}
						if (tinh.contains("[sửa | sửa mã nguồn]")) {
							tinh = tinh.replace("[sửa | sửa mã nguồn]", "");
						}

						String vitri = td.get(1).text();
						String loaihinhditich = td.get(2).text();
						String namcn = td.get(3).text();

						if(loaihinhditich.length() == 0) loaihinhditich = "Không rõ";
						if(vitri.length() == 0) vitri = "Không rõ";
						if(namcn.length() == 0) namcn = "Không rõ";
						List<JSONObject> figureList = new ArrayList<>();
						String figure = "Không rõ";
						JSONObject figureObject = new JSONObject();
						figureObject.put("ten", figure);
						figureList.add(figureObject);


						ditich.put("ten", td.get(0).text());
						ditich.put("diaDiem", vitri+", "+tinh);
						ditich.put("loaiXepHang","Di tích "+ loaihinhditich);
						ditich.put("namCongNhan", namcn);
						ditich.put("xepHang", "Không rõ");
						ditich.put("loaiXepHang", "Không rõ");
						ditich.put("nhanVatLienQuan", figureList);

						ditich2List.add(ditich);
					}
				}
			}
		}

	}

	public List<JSONObject> getDitich2List() {
		return ditich2List;
	}



	public static void main(String[] args) throws IOException {
		String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";
		Ditich1 ditich1 = new Ditich1(url);
		ditich1.connect(); // Connect to the URL
		ditich1.scraping(); // Perform scraping


		try (FileWriter file = new FileWriter("Project/VietnamHistory/src/data/ditichlichsu/relic2.json")) {
			file.write("[");

			for (int i = 0; i < ditich2List.size(); i++) {
				JSONObject jsonObject = ditich2List.get(i);
				file.write(jsonObject.toString(1));
				if (i < ditich2List.size() - 1) {
					file.write(",");
				}
				file.write(System.lineSeparator());
			}


			file.write("]");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
