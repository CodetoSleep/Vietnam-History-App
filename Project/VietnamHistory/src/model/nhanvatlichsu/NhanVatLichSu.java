package model.nhanvatlichsu;

import java.util.ArrayList;

import model.thoiky.ThoiKy;

public abstract class NhanVatLichSu {
	protected String ten;
	protected String tenKhac;
	protected String namSinh;
	protected String namMat;
	protected String queQuan;
	protected ArrayList<ThoiKy> thoiKy;
	protected String ghiChu;

	public NhanVatLichSu(String ten, String tenKhac, String namSinh, String namMat, String queQuan,
			ArrayList<ThoiKy> thoiKy, String ghiChu) {
		super();
		this.ten = ten;
		this.tenKhac = tenKhac;
		this.namSinh = namSinh;
		this.namMat = namMat;
		this.queQuan = queQuan;
		this.thoiKy = thoiKy;
		this.ghiChu = ghiChu;
	}
	
	public NhanVatLichSu(String ten) {
		super();
		this.ten = ten;
	}

	public NhanVatLichSu(){
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getTenKhac() {
		return tenKhac;
	}

	public void setTenKhac(String tenKhac) {
		this.tenKhac = tenKhac;
	}

	public String getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}

	public String getNamMat() {
		return namMat;
	}

	public void setNamMat(String namMat) {
		this.namMat = namMat;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public ArrayList<ThoiKy> getThoiKy() {
		return thoiKy;
	}

	public void setThoiKy(ArrayList<ThoiKy> thoiKy) {
		this.thoiKy = thoiKy;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
}
