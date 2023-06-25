package crawler.thoiky;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawler.util.datain.AGetData;
import model.thoiky.ThoiKy;

public class KinhDo extends AGetData {
	private ArrayList<ThoiKy> thoiKy;

	public KinhDo() {
		String url = "https://vi.wikipedia.org/wiki/Th%E1%BB%A7_%C4%91%C3%B4_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		this.connect();
		thoiKy = new ArrayList<ThoiKy>();
	}

	public ArrayList<ThoiKy> getThoiKy() {
		return thoiKy;
	}

	@Override
	public void scraping() throws IOException {

		int rowspan = 1;
		Elements rows = this.getDoc().select("#mw-content-text > div.mw-parser-output > table.wikitable > tbody > tr");
		for (int i = 1; i < rows.size(); i++) {
			Element row = rows.get(i);
			Elements columns = row.select("td");
			Element trieuDai;
			if (rowspan == 1) {
				String input = columns.get(0).text().trim();
				boolean isMatch;
				if (input.length() == 11 && Character.isDigit(input.charAt(0)) && Character.isDigit(input.charAt(1))
						&& Character.isDigit(input.charAt(2)) && Character.isDigit(input.charAt(3))
						&& Character.isDigit(input.charAt(9)) && Character.isDigit(input.charAt(10))
						&& Character.isDigit(input.charAt(7)) && Character.isDigit(input.charAt(8))) {
					isMatch = true;
				} else {
					isMatch = false;
				}
				if (!isMatch) {
					if (columns.size() == 2) {
						trieuDai = columns.get(1);
					} else {
						trieuDai = columns.get(2);
					}
				} else {
					trieuDai = columns.get(1);
				}
				rowspan = trieuDai.hasAttr("rowspan") ? Integer.parseInt(trieuDai.attr("rowspan")) : 1;
				String tenTrieuDai;
				tenTrieuDai = trieuDai.text();
				ThoiKy tk = new ThoiKy(tenTrieuDai);
				thoiKy.add(tk);
			} else {
				rowspan--;
			}

			// for (int j = 0; j < columns.size(); j++) {
			// Element column = columns.get(j);

			// // Kiểm tra thuộc tính rowspan của cột
			// int rowspan = Integer.parseInt(column.attr("rowspan"));
			// if (rowspan > 1) {
			// // Lấy dữ liệu của cột rowspan = 2
			// String data = column.text();
			// System.out.println("Row " + i + ", Column " + j + ": " + data);

			// // Điều chỉnh chỉ số hàng để bỏ qua các hàng đã gộp
			// for (int k = 1; k < rowspan; k++) {
			// rows.get(i + k).select("td").get(j).remove();
			// }
			// }
			// }

			// }
		}
	}

	public static void main(String[] args) throws IOException {
		KinhDo kinhDo = new KinhDo();
		kinhDo.scraping();
	}
}
