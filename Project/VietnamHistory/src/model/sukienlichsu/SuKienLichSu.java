package model.sukienlichsu;

import java.util.ArrayList;
import model.thoiky.*;
import model.nhanvatlichsu.*;

public class SuKienLichSu {
	
	private String ten;
	private String thoiGian;
	private String diaDiem;
	private ThoiKy thoiKy;
	private String noiDung;
	private ArrayList<NhanVatLichSu> nhanVatLienQuan;

	public SuKienLichSu(String ten, String thoiGian, String diaDiem, ThoiKy thoiKy, String noiDung,
			ArrayList<NhanVatLichSu> nhanVatLienQuan) {
		super();
		this.setTen(ten);
		this.setThoiGian(thoiGian);
		this.setDiaDiem(diaDiem);
		this.setThoiKy(thoiKy);
		this.setNoiDung(noiDung);
		this.setNhanVatLienQuan(nhanVatLienQuan);
	}
	public SuKienLichSu(){
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

	public ThoiKy getThoiKy() {
		return thoiKy;
	}

	public void setThoiKy(ThoiKy thoiKy) {
		this.thoiKy = thoiKy;
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
