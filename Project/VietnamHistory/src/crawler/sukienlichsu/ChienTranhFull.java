package crawler.sukienlichsu;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import crawler.util.dataout.IWriteJson;
import model.sukienlichsu.*;
import crawler.util.dataout.ICombine;
public class ChienTranhFull implements ICombine, IWriteJson{
	private ArrayList<ChienTranh> chienTranhFull;
	private ArrayList<ChienTranh> chienTranhFull2;

	private ChienTranh1 chientranh1;
	private ChienTranh2 chientranh2;
	
	public ChienTranhFull(){
		chienTranhFull = new ArrayList<ChienTranh>();
	}
	@Override
	public void writeJson() throws IOException {
		String filePath = "src/data/sukienlichsu/ChienTranh.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(chienTranhFull, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void combine() throws IOException {
		chientranh1 = new ChienTranh1();
		chientranh1.scraping();
		chienTranhFull = chientranh1.getCTVN();
		chientranh2 = new ChienTranh2();
		chientranh2.scraping();
		chienTranhFull2 = chientranh2.getChienTranh2();
        
		chienTranhFull.addAll(chienTranhFull2);

	}
	public static void main(String[] args) throws IOException {
		ChienTranhFull t = new ChienTranhFull();
		t.combine();
		t.writeJson();
	}
}
