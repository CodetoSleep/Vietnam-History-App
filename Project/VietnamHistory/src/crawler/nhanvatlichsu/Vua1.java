package crawler.nhanvatlichsu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.nhanvatlichsu.Vua;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import crawler.util.datain.AGetData;
public class Vua1 extends AGetData {
	private ArrayList<Vua> vua;

	public Vua1() {

		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		this.connect();
		this.vua = new ArrayList<>();
	}


	public ArrayList<Vua> getVua(){
		return vua;
	}

	@Override
	public void scraping() throws IOException {

		//Elements firstTable = this.doc.select("table.toccolours");
		/*
		for (Element t : firstTable) {
			if (tables.contains(t)) {
				tables.remove(t);
			}
		}
		 */
		//int lastTable = tables.size() - 1;
		//tables.remove(lastTable);
		Elements tables = doc.select("table");
		String strRegEx = "<sup[^?]*";
		String attrRegEx = "<a[^?]*";
		for (Element table : tables) {
			Elements rows = table.select("tr");
			for (Element row : rows) {
				Elements datas = row.getElementsByTag("td");
				if (datas.size() >= 10 && datas.size() < 24) {
					String name = datas.get(1).text();
					name = cleanData(name);
					Vua data = new Vua(name);

					String mieuHieu = datas.get(2).text();
					mieuHieu = cleanData(mieuHieu);
					data.setMieuHieu(mieuHieu);

					String thuyHieu = datas.get(3).text();
					thuyHieu = cleanData(thuyHieu);
					data.setThuyHieu(thuyHieu);

					String nienHieu = datas.get(4).text();
					nienHieu = cleanData(nienHieu);
					data.setNienHieu(nienHieu);

					String tenHuy = datas.get(5).text();
					tenHuy = cleanData(tenHuy);
					data.setTenHuy(tenHuy);

					String theThu = datas.get(6).text();
					theThu = cleanData(theThu);
					data.setTheThu(theThu);

					String namTriVi = datas.get(7).text();
					namTriVi = namTriVi.replaceAll(strRegEx, "");
					String ngang = datas.get(8).text();
					String end = datas.get(9).text();
					end = end.replaceAll(strRegEx, "");
					namTriVi = namTriVi.concat(ngang);
					namTriVi = namTriVi.concat(end);
					namTriVi = namTriVi.replaceAll(attrRegEx, "");
					namTriVi = cleanData(namTriVi);
					data.setNamTriVi(namTriVi);

					if (!mieuHieu.equals("")) {
						vua.add(data);
					}
				}
			}
		}
	}

	public String cleanData(String sample) {
		String data = sample;
		int index = data.indexOf("[");
		while (index != -1) {
			int close = data.indexOf("]");
			String tmp = data.substring(index, close + 1);
			data = data.replace(tmp, "");
			index = data.indexOf("[");
		}
		return data;
	}

	public void WriteJson() throws IOException {
		String filePath = "src/data/nhanvatlichsu/vua.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(filePath);
			gson.toJson(vua, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Vua1 vua = new Vua1();
		vua.scraping();
		ArrayList<Vua> content = vua.getVua();
		// for(Vua e: content){
		// 	System.out.println(e.getTen());
		// 	System.out.println(e.getNamTriVi());
		// }
		System.out.println("So luong: " + content.size());

		vua.WriteJson();
	}

}