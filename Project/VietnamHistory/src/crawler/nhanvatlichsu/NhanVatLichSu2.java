package crawler.nhanvatlichsu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.util.datain.AGetData;
import model.nhanvatlichsu.NhanVatLichSu;
import model.thoiky.ThoiKy;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NhanVatLichSu2 extends AGetData {
	private static ArrayList<NhanVatLichSu> list = new ArrayList<>();
	private NhanVatLichSu nvls;
	public NhanVatLichSu2(String url) {
		this.setUrl(url);
		this.connect();
		this.nvls = new NhanVatLichSu();
	}

	public NhanVatLichSu2() {
	}


	public NhanVatLichSu getNVLS2(){
		return nvls;
	}

	@Override
	public void scraping() throws IOException {
		Element nameDiv = this.doc.getElementsByClass("active section").first();
		String name = nameDiv.text();
		//System.out.println(name);
		Element table = this.doc.select("table").first();
		Element tableBody = table.select("tbody").first();
		Elements rows = tableBody.getElementsByTag("tr");
		nvls = new NhanVatLichSu(name);

		for (Element row : rows) {
			Elements rowData = row.select("td");
			if (rowData.size() != 1) {
				String headline = rowData.get(0).text();
				if (headline.contains("Tên khác")) {
					nvls.setTenKhac(rowData.get(1).text());
				}
				else if (headline.contains("Năm sinh")) {
					String year = rowData.get(1).text();
					int index = year.indexOf("-");
					String namSinh = year.substring(0, index);
					String namMat = year.substring(index + 1);
					nvls.setNamSinh(namSinh.trim());
					nvls.setNamMat(namMat.trim());
				}
				else if (headline.contains("Tỉnh thành")) {
					String queQuan = rowData.get(1).text();
					nvls.setQueQuan(queQuan);
				}
				else if (headline.contains("Thời kì")) {
					Elements br = rowData.get(1).getElementsByTag("br");
					if (br.size() <= 1) {
						String data = rowData.get(1).text();
						int index = data.indexOf("-");
						int openIndex = data.indexOf("(");
						if (openIndex != -1) {
							String dynastyData = data.substring(index + 2, openIndex);
							dynastyData = dynastyData.trim();

							ThoiKy dynasty = new ThoiKy(dynastyData);
							nvls.getThoiKy().add(dynasty);
						} else {
							String dynastyData = data.substring(index + 2);
							dynastyData = dynastyData.trim();
							ThoiKy dynasty = new ThoiKy(dynastyData);
							nvls.getThoiKy().add(dynasty);
						}
					}
					else { // nhieu thoi ki
						String data = rowData.get(1).html();

						String[] dynasties = data.split("<br>");
						for (String d : dynasties) {
							int openIndex = d.indexOf("(");
							int index = d.indexOf("- ");
							if (openIndex != -1) {
								String getDynasty = d.substring(index + 1, openIndex);
								getDynasty = getDynasty.trim();
								ThoiKy dynasty = new ThoiKy(getDynasty);
								nvls.getThoiKy().add(dynasty);
							}
							else {
								String getDynasty = d.substring(index + 1);
								getDynasty = getDynasty.trim();
								ThoiKy dynasty = new ThoiKy(getDynasty);
								nvls.getThoiKy().add(dynasty);
							}
						}
					}
				}
			}
			else {
				Elements paragraphs = rowData.get(0).getElementsByTag("p");
				StringBuffer ghiChu = new StringBuffer();
				for (Element p : paragraphs) {
					ghiChu = ghiChu.append(p.text());
				}
				nvls.setGhiChu(ghiChu.toString());
			}
		}



	}

	public void WriteJson() throws IOException {
		String filePath = "src/crawler/test/nvls2.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(filePath);
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		NhanVatLichSu2 vn = new NhanVatLichSu2();
		int pageIndex = 1;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";

		while (pageIndex <= 2300) {
			String url = urlFirstHalf + pageIndex;
			NhanVatLichSu2 vanSu = new NhanVatLichSu2(url);
			vanSu.scraping();
			list.add(vanSu.getNVLS2());
			pageIndex += 1;
		}
		System.out.println("So luong: " + list.size());
		vn.WriteJson();
	}
}
