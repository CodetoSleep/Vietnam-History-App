package model.nhanvatlichsu;

import java.util.ArrayList;

import model.thoiky.ThoiKy;

public class NhanVatLichSu {
	private String ten;
	private String tenKhac;
	private String namSinh;
	private String namMat;
	private String queQuan;
	private ArrayList<ThoiKy> thoiKy = new ArrayList<>();
	private String ghiChu;

	public NhanVatLichSu(String ten, String tenKhac, String namSinh, String namMat, String queQuan,
			ArrayList<ThoiKy> thoiKy, String ghiChu) {
		super();
		this.setTen(ten);
		this.setTenKhac(tenKhac);
		this.setNamSinh(namSinh);
		this.setNamMat(namMat);
		this.setQueQuan(queQuan);
		this.setThoiKy(thoiKy);
		this.setGhiChu(ghiChu);
	}
	
	public NhanVatLichSu(String ten) {
		super();
		this.setTen(ten);
	}
	
	public NhanVatLichSu(String ten, String namSinh, String namMat, String queQuan, String ghiChu) {
		super();
		this.setTen(ten);
		this.setNamSinh(namSinh);
		this.setNamMat(namMat);
		this.setQueQuan(queQuan);
		this.setGhiChu(ghiChu);
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
