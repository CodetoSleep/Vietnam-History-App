package crawler.lehoi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// import crawler.thoiky.ThoiKyFull;
// import crawler.util.datain.GetData;
// import javax.swing.text.html.parser.Element;

// import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.lehoi.LeHoi;
import java.util.ArrayList;
// import model.nhanvatlichsu.NhanVatLichSu;
// import model.thoiky.ThoiKy;
// import crawler.util.datain.GetData;

import crawler.util.dataout.ICombine;
import crawler.util.dataout.IWriteJson;

public class LeHoiFull implements IWriteJson, ICombine {
	private ArrayList<LeHoi> leHoiFull;
	// private String ten;
	// private String thoiGian;
	// private String diaDiem;
	// private String noiDung;
	
	public LeHoiFull(){
		leHoiFull = new ArrayList<LeHoi>();
	}


	public void combine() throws IOException {
		LeHoi1 leHoi1 = new LeHoi1();
		leHoi1.scraping();
		leHoiFull.addAll(leHoi1.getLeHoi());
		
		LeHoi2 leHoi2 = new LeHoi2();
		leHoi2.scraping();
		leHoiFull.addAll(leHoi2.getLeHoi());
			
		LeHoi3 leHoi3 = new LeHoi3();
		leHoi3.scraping();
		leHoiFull.addAll(leHoi3.getLeHoi());
		
		LeHoi4 leHoi4 = new LeHoi4();
		leHoi4.scraping();
		leHoiFull.addAll(leHoi4.getLeHoi());
		
		LeHoi5 leHoi5 = new LeHoi5();
		leHoi5.scraping();
		leHoiFull.addAll(leHoi5.getLeHoi());
		
		LeHoi6 leHoi6 = new LeHoi6();
		leHoi6.scraping();
		leHoiFull.addAll(leHoi6.getLeHoi());
	}

	@Override
	public void writeJson() throws IOException {
		String filePath = "src/data/lehoi/leHoi.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(leHoiFull, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		LeHoiFull t = new LeHoiFull();
		t.combine();
		t.writeJson();
	}


}
