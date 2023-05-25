package model.ditichlichsu;

import java.util.ArrayList;

import model.nhanvatlichsu.NhanVatLichSu;

public class DiTichLichSu {
	private String ten;
	private String diaDiem;
	private String loaiDiTich;
	private String namCongNhan;
	private ArrayList<NhanVatLichSu> nhanVatLienQuan;
	private String ghiChu;
	
	public DiTichLichSu(String ten, String diaDiem, String loaiDiTich, String namCongNhan,
			ArrayList<NhanVatLichSu> nhanVatLienQuan, String ghiChu) {
		super();
		this.ten = ten;
		this.diaDiem = diaDiem;
		this.loaiDiTich = loaiDiTich;
		this.namCongNhan = namCongNhan;
		this.nhanVatLienQuan = nhanVatLienQuan;
		this.ghiChu = ghiChu;
	}

	public DiTichLichSu(String ten) {
		super();
		this.ten = ten;
	}
	
	public DiTichLichSu() {
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public String getLoaiDiTich() {
		return loaiDiTich;
	}

	public void setLoaiDiTich(String loaiDiTich) {
		this.loaiDiTich = loaiDiTich;
	}

	public String getNamCongNhan() {
		return namCongNhan;
	}

	public void setNamCongNhan(String namCongNhan) {
		this.namCongNhan = namCongNhan;
	}

	public ArrayList<NhanVatLichSu> getNhanVatLienQuan() {
		return nhanVatLienQuan;
	}

	public void setNhanVatLienQuan(ArrayList<NhanVatLichSu> nhanVatLienQuan) {
		this.nhanVatLienQuan = nhanVatLienQuan;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
