package crawler.nhanvatlichsu;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.util.datain.AGetData;
import model.nhanvatlichsu.NhanVatLichSu;
import model.thoiky.ThoiKy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class NhanVatLichSu1 extends AGetData {
	private static ArrayList<NhanVatLichSu> list = new ArrayList<>();
	private NhanVatLichSu nvls;

	public ArrayList<NhanVatLichSu> getListNVLS1(){
		return list;
	}

	public NhanVatLichSu1(String url) {
		this.setUrl(url);
		this.connect();
		this.nvls = new NhanVatLichSu();
	}

	public NhanVatLichSu1() {
	}

	public NhanVatLichSu getNVLS1() {
		return nvls;
	}

	@Override
	public void scraping() throws IOException {
		Element nameDiv = this.doc.selectFirst("div.page-header h2");
		String name = nameDiv.text();
		//System.out.println(name);
		nvls = new NhanVatLichSu(name);
		Element info = doc.selectFirst(".infobox");
		String strRegEx = "<sup[^?]*";
		String space = " ";
		if (info != null) {
			for (Element row : info.select("tr")) {
				if (row.selectFirst("th:contains(Triều Đại)") != null || row.selectFirst("th:contains(Thời kỳ)") != null || row.selectFirst("th:contains(Hoàng tộc)") != null || row.selectFirst("th:contains(Kỷ nguyên)") != null) {
					if (row.selectFirst("td") == null) {
						String thoiKy = row.nextElementSibling().text();
						thoiKy = removeBrackets(thoiKy.replaceAll(strRegEx, "")).trim();
						ThoiKy dynasty = new ThoiKy(thoiKy);
						nvls.getThoiKy().add(dynasty);
					} else {
						String thoiKy = row.selectFirst("td").text();
						thoiKy = removeBrackets(thoiKy.replaceAll(strRegEx, "")).trim();
						ThoiKy dynasty = new ThoiKy(thoiKy);
						nvls.getThoiKy().add(dynasty);
					}
				}

				if (row.selectFirst("th:contains(Tên đầy đủ)") != null || row.selectFirst("th:contains(Tên khác)") != null || row.selectFirst("th:contains(Tự)") != null || row.selectFirst("th:contains(Tên khai sinh)") != null || row.selectFirst("th:contains(Biệt danh)") != null || row.selectFirst("th:contains(Tên thật)") != null) {
					if (row.selectFirst("td") == null) {
						String tenKhac = row.nextElementSibling().text();
						tenKhac = removeBrackets(tenKhac.replaceAll(strRegEx, ""));
						nvls.setTenKhac(tenKhac);
					} else {
						String tenKhac = row.selectFirst("td").text();
						tenKhac = removeBrackets(tenKhac.replaceAll(strRegEx, ""));
						nvls.setTenKhac(tenKhac);
					}
				}
				if (row.selectFirst("th:contains(Sinh)") != null && row.selectFirst("th:contains(Học sinh trường)") == null) {
					String data = row.selectFirst("td").wholeText();
					String[] namS = data.split("\\n");
					String namSinhSpace = namS[0].replaceAll(strRegEx, "");
					String namSinh = checkString(namSinhSpace.replaceAll(space, " "));
					//String namSinh = checkString(namSinhNum);
					nvls.setNamSinh(namSinh);
				}

				if (row.selectFirst("th:contains(Mất)") != null && row.selectFirst("th:contains(Nguyên nhân mất)") == null) {
					String data = row.selectFirst("td").wholeText();
					String[] namM = data.split("\\n");
					String namMatSpace = namM[0].replaceAll(strRegEx, "");
					String namMat = checkString(namMatSpace.replaceAll(space, " "));
					//String namMat = checkString(namMatNum);
					nvls.setNamMat(namMat);
				}

				if (row.selectFirst("th:contains(Quê quán)") != null) {
					String queQuan = row.selectFirst("td").text();
					queQuan = queQuan.replaceAll(strRegEx, "");
					nvls.setQueQuan(queQuan);
				}
			}
		}
	}

	public static String checkString(String input) {
		if (!input.matches(".*\\d.*")) {
			return null;
		} else {
			return input;
		}
	}

	public static String removeBrackets(String input) {
		String pattern = "\\([^()]*\\)|\\[[^\\[\\]]*\\]|\\{[^{}]*\\}";
		String output = input.replaceAll(pattern, "");
		return output;
	}
		public void ketNoi() throws IOException {
			int i = 0;
			String urlF = "https://nguoikesu.com/nhan-vat?start=";
			while (i <= 100) {
				url = urlF + i;
				this.doc = Jsoup
						.connect(this.url)
						.timeout(10000)
						.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
						.get();
				for (Element x : doc.select("h2 a")) {
					String urls = ("https://nguoikesu.com" + x.attr("href"));
					NhanVatLichSu1 nguoiKeSu = new NhanVatLichSu1(urls);
					nguoiKeSu.scraping();
					list.add(nguoiKeSu.getNVLS1());
				}
				i += 5;
			}
		}



	public void WriteJson() throws IOException {
		String filePath = "src/crawler/test/nvls1.json";
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
		NhanVatLichSu1 vn = new NhanVatLichSu1();
		vn.ketNoi();
		System.out.println("So luong: " + list.size());
		vn.WriteJson();
		}
	}

