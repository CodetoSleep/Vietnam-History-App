package crawler.ditichlichsu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import crawler.util.datain.AGetData;

public class DiTich2 extends AGetData {
	private String name, Type, address;
//	private String Figure;
	private String TypeOfRank, RecordYear, Rank;
	public static List<JSONObject> ditich2List = new ArrayList<>();
//	private List<JSONObject> Figure = new ArrayList<>();

	private List<JSONObject> figureList = new ArrayList<>();

	public DiTich2(){

	}

	public List<JSONObject> getListOfFigure() {
		return figureList;
	}


	public DiTich2(String url) {
		this.setUrl(url);
	}

	@Override
	public void scraping() throws IOException {

		this.name = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > h2").text();
		this.address = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__sidebar > div:nth-child(1) > section > div > div > div.hl__contact-info__address > span").text();

		Elements info = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section  div > span:nth-child(2)");
		for (Element eachInfo : info) {
			if (eachInfo.text().contains("Loại hình di tích")) {
				this.Type = eachInfo.text().replace("Loại hình di tích: ", "");
			}
			if (eachInfo.text().contains("Xếp hạng")) {
				this.Rank = eachInfo.text().replace("Xếp hạng: ", "");
			}
			if (eachInfo.text().contains("Loại hình xếp hạng")) {
				this.TypeOfRank = eachInfo.text().replace("Loại hình xếp hạng: ", "");
			}
			if (eachInfo.text().contains("Đối tượng thờ")) {
				String figure = eachInfo.text().replace("Đối tượng thờ: ", "");
				if (figure == null || figure.length() == 0) {
					figure = "Không rõ";
				}
				JSONObject figureJson = new JSONObject();
				figureJson.put("ten", figure);
				figureList.add(figureJson);
			}
			if (eachInfo.text().contains("Quyết định xếp hạng")) {
				this.RecordYear = eachInfo.text().replace("Quyết định xếp hạng: ", "");
			}
		}
		if (this.Type == null || this.Type.length() == 0 ) {
			this.Type = "Không rõ";
		}
		if (this.Rank == null || this.Rank.length() == 0) {
			this.Rank = "Không rõ";
		}
		if (this.TypeOfRank == null || this.TypeOfRank.length() == 0) {
			this.TypeOfRank = "Không rõ";
		}
//		if (this.Figure == null || this.Figure.length() == 0) {
//			this.Figure = "Không rõ";
//		}
		if (this.RecordYear == null || this.RecordYear.length() == 0) {
			this.RecordYear = "Không rõ";
		}
	}


//	public List<String> getFigureList() {
//		return figureList;
//	}
	public String getType() {
		return Type;
	}

	public String getRank() {
		return Rank;
	}

	public String getTypeOfRank() {
		return TypeOfRank;
	}

//	public String getFigure() {
//		return Figure;
//	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getRecordYear() {
		return RecordYear;
	}


	public List<JSONObject> getDitich3List() {
		return ditich2List;
	}
//	public List<JSONObject> getFigure() {
//		return Figure;
//	}

	// Create a list to store the JSONObjects
	public static void main(String[] args) throws IOException {
		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId=";
		for (int i = 1865; i <= 6193; i++)
		{
			System.out.println(i);
			JSONObject ditich2 = new JSONObject();
			String url = baseUrl + Integer.toString(i);
			DiTich2 r = new DiTich2(url);
			r.connect();
			r.scraping();

//			ditich2.put("Di tích", r.getName());
//			ditich2.put("Vị trí", r.getAddress());
//			ditich2.put("Loại hình di tích", r.getType());
//			ditich2.put("Xếp hạng", r.getRank());
//			ditich2.put("Loại hình xếp hạng", r.getTypeOfRank());
//			ditich2.put("Nhân vật liên quan", r.getFigureList());
//			ditich2.put("Năm CN", r.getRecordYear());

			ditich2.put("ten", r.getName());
			ditich2.put("diaDiem", r.getAddress());
			ditich2.put("loaiDiTich", r.getType());
			ditich2.put("xepHang", r.getRank());
			ditich2.put("loaiXepHang", r.getTypeOfRank());
			ditich2.put("nhanVatLienQuan", r.getListOfFigure());
			ditich2.put("namCongNhan", r.getRecordYear());
			ditich2List.add(ditich2);
		}

		try (FileWriter file = new FileWriter("Project/VietnamHistory/src/data/ditichlichsu/relic3.json")) {
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
