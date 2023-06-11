package crawler.ditichlichsu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import crawler.util.dataout.ICombine;
import crawler.util.dataout.IWriteJson;
import org.json.JSONArray;
import org.json.JSONObject;
import model.ditichlichsu.DiTichLichSu;
import model.nhanvatlichsu.NhanVatLichSu;

public class DiTichFull implements IWriteJson, ICombine {
	List<JSONObject> ditichFullList = new ArrayList<>();
	private ArrayList<DiTichLichSu> ditichlichsu;
	// private ArrayList<NhanVatLichSu> nvls;
	public DiTichFull(){
		this.ditichlichsu = new ArrayList<>();
	}

	public ArrayList<DiTichLichSu> getDitichlichsu() {
		return ditichlichsu;
	}

	@Override
	public void Combine() throws IOException {
//		DiTichLichSu data = new DiTichLichSu();

		String relic2Content = new String(Files.readAllBytes(Paths.get("Project/VietnamHistory/src/data/ditichlichsu/relic2.json")));
		String relic3Content = new String(Files.readAllBytes(Paths.get("Project/VietnamHistory/src/data/ditichlichsu/relic3.json")));

		// Parse the content as JSON arrays
		JSONArray relic2Array = new JSONArray(relic2Content);
		JSONArray relic3Array = new JSONArray(relic3Content);


		// Iterate over the objects in relic2Array
		Set<String> ditichSet = new HashSet<>();

		for (int i = 0; i < relic3Array.length(); i++) {
			JSONObject obj3 = relic3Array.getJSONObject(i);
			String diTich3 = obj3.getString("ten");
			ditichSet.add(diTich3);
			ditichFullList.add(obj3);

//			DiTichLichSu diTichLichSu = createDiTichLichSu(obj3);
//			ditichlichsu.add(diTichLichSu);


			// NhanVatLichSu person = new NhanVatLichSu();
			DiTichLichSu data = new DiTichLichSu();


			data.setTen(obj3.getString("ten"));
			data.setDiaDiem(obj3.getString("diaDiem"));
			data.setLoaiDiTich(obj3.getString("loaiDiTich"));
			data.setXepHang(obj3.getString("xepHang"));
			data.setLoaiXepHang(obj3.getString("loaiXepHang"));
			data.setNamCongNhan(obj3.getString("namCongNhan"));
//			data.setNhanVatLienQuan(person.setTen(obj3.getJSONArray("nhanVatLienQuan").toString()));
			data.setNhanVatLienQuan(createNhanVatLichSu(obj3.getJSONArray("nhanVatLienQuan")));

			ditichlichsu.add(data);

		}

		for (int i = 0; i < relic2Array.length(); i++) {
			JSONObject obj2 = relic2Array.getJSONObject(i);
			String diTich2 = obj2.getString("ten");
			if (!ditichSet.contains(diTich2)) {
				ditichFullList.add(obj2);
				DiTichLichSu data = new DiTichLichSu();

//				DiTichLichSu diTichLichSu = createDiTichLichSu(obj2);
//				ditichlichsu.add(diTichLichSu);


				// ArrayList<NhanVatLichSu> nvls = new ArrayList<>();

				data.setTen(obj2.getString("ten"));
				data.setDiaDiem(obj2.getString("diaDiem"));
				data.setLoaiXepHang(obj2.getString("loaiXepHang"));
				data.setNamCongNhan(obj2.getString("namCongNhan"));
				data.setXepHang("Không rõ");
				data.setLoaiDiTich("Không rõ");
//				data.setNhanVatLienQuan("Không rõ");
				data.setNhanVatLienQuan(createNhanVatLichSu(obj2.getJSONArray("nhanVatLienQuan")));


				ditichlichsu.add(data);
			}
		}
	}

//	private DiTichLichSu createDiTichLichSu(JSONObject jsonObject) {
//		String ten = jsonObject.getString("ten");
//		String diaDiem = jsonObject.getString("diaDiem");
//		String loaiDiTich = jsonObject.getString("loaiDiTich");
//		String namCongNhan = jsonObject.getString("namCongNhan");
//		JSONArray nhanVatLienQuanArray = jsonObject.getJSONArray("nhanVatLienQuan");
//		ArrayList<NhanVatLichSu> nhanVatLienQuan = createNhanVatLichSu(nhanVatLienQuanArray);
//		String ghiChu = jsonObject.getString("ghiChu");
//
//		return new DiTichLichSu(ten, diaDiem, loaiDiTich, namCongNhan, nhanVatLienQuan, ghiChu);
//	}

//	private ArrayList<NhanVatLichSu> createNhanVatLichSu(JSONArray jsonArray) {
//		ArrayList<NhanVatLichSu> nhanVatLichSuList = new ArrayList<>();
//		JSONObject nhanVatObj = jsonArray.getJSONObject(0);
//			// Create NhanVatLichSu instance and add it to the list
//		NhanVatLichSu nhanVatLichSu = createNhanVatLichSu(nhanVatObj);
//		nhanVatLichSuList.add(nhanVatLichSu);
//		return nhanVatLichSuList;
//	}
//
	private NhanVatLichSu icreateNhanVatLichSu(JSONObject jsonObject) {
		String ten = jsonObject.getString("ten");
		return new NhanVatLichSu(ten);
	}

	private ArrayList<NhanVatLichSu> createNhanVatLichSu(JSONArray jsonArray) {
		ArrayList<NhanVatLichSu> nhanVatLichSuList = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject nhanVatObj = jsonArray.getJSONObject(i);
			NhanVatLichSu nhanVatLichSu = icreateNhanVatLichSu(nhanVatObj);
			nhanVatLichSuList.add(nhanVatLichSu);
		}

		return nhanVatLichSuList;
	}


	@Override
	public void WriteJson() throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try (FileWriter file = new FileWriter("Project/VietnamHistory/src/data/ditichlichsu/DiTich.json")) {

//			file.write("[");
//			for (int i = 0; i < ditichFullList.size(); i++) {
//				JSONObject jsonObject = ditichFullList.get(i);
//				file.write(jsonObject.toString(1));
//				if (i < ditichFullList.size() - 1) {
//					file.write(",");
//				}
//				file.write(System.lineSeparator());
//			}
//			file.write("]");

			gson.toJson(ditichlichsu, file);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		DiTichFull ditichfull = new DiTichFull();
		ditichfull.Combine();
		ditichfull.WriteJson();
		ArrayList<DiTichLichSu> content = ditichfull.getDitichlichsu();
		for(DiTichLichSu e: content){
			System.out.println(e.getTen());
			System.out.println(e.getDiaDiem());
			System.out.println(e.getLoaiDiTich());
			System.out.println(e.getLoaiXepHang());
			System.out.println(e.getXepHang());
			System.out.println(e.getNhanVatLienQuan());
			System.out.println(e.getNamCongNhan());
			System.out.println();
		}
	}
}
