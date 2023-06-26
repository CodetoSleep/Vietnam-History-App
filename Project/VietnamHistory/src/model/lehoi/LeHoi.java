package model.lehoi;

import java.util.ArrayList;
import model.nhanvatlichsu.NhanVatLichSu;

public class LeHoi {
	private String ten;
	private String thoiGian;
	private String diaDiem;
	private String noiDung;
	private ArrayList<NhanVatLichSu> nhanVatLienQuan;
	
	public LeHoi() {
		super();
	}
	
	public LeHoi(String ten, String thoiGian, String diaDiem, String noiDung,
			ArrayList<NhanVatLichSu> nhanVatLienQuan) {
		super();
		this.setTen(ten);
		this.setThoiGian(thoiGian);
		this.setDiaDiem(diaDiem);
		this.setNoiDung(noiDung);
		this.setNhanVatLienQuan(nhanVatLienQuan);
	}

	public LeHoi(String ten) {
		super();
		this.setTen(ten);
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public ArrayList<NhanVatLichSu> getNhanVatLienQuan() {
		return nhanVatLienQuan;
	}

	public void setNhanVatLienQuan(ArrayList<NhanVatLichSu> nhanVatLienQuan) {
		this.nhanVatLienQuan = nhanVatLienQuan;
	}
}
