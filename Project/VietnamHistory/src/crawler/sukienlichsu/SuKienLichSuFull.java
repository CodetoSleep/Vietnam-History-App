package crawler.sukienlichsu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawler.util.dataout.ICombine;
import crawler.util.dataout.IWriteJson;
import model.sukienlichsu.*;
public class SuKienLichSuFull implements ICombine, IWriteJson {
	private ArrayList<SuKienLichSu> suKienFull;
	private SuKien1 sukien1;
	private SuKien2 sukien2;

	public SuKienLichSuFull(){
		suKienFull = new ArrayList<SuKienLichSu>();
	}


	@Override
	public void Combine() throws IOException {
		sukien1 = new SuKien1();
		sukien1.scraping();
		suKienFull = sukien1.getList();
		sukien2 = new SuKien2();
		sukien2.scraping();
		suKienFull.addAll(sukien2.getSuKien2());

	}

	@Override
	public void WriteJson() throws IOException {
		String filePath = "src/data/sukienlichsu/SuKien.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(suKienFull, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		SuKienLichSuFull t = new SuKienLichSuFull();
		t.Combine();
		t.WriteJson();
	}

}
