package model.sukienlichsu;

import java.util.ArrayList;

import model.nhanvatlichsu.NhanVatLichSu;
import model.thoiky.ThoiKy;

public class ChienTranh extends SuKienLichSu {
	private String ketQua;
    private String benThuNhat;
    private String benThuHai;
       
	public ChienTranh(String ten, String thoiGian, String diaDiem, ThoiKy thoiKy, String noiDung,
			ArrayList<NhanVatLichSu> nhanVatLienQuan, String ketQua, String benThuNhat, String benThuHai) {
		super(ten, thoiGian, diaDiem, thoiKy, noiDung, nhanVatLienQuan);
		this.setKetQua(ketQua);
		this.setBenThuNhat(benThuNhat);
		this.setBenThuHai(benThuHai);
	}
	
	public ChienTranh() {
		super();
	}
	
	public String getKetQua() {
		return ketQua;
	}
	
	public void setKetQua(String ketQua) {
		this.ketQua = ketQua;
	}
	
	public String getBenThuNhat() {
		return benThuNhat;
	}
	
	public void setBenThuNhat(String benThuNhat) {
		this.benThuNhat = benThuNhat;
	}
	
	public String getBenThuHai() {
		return benThuHai;
	}
	
	public void setBenThuHai(String benThuHai) {
		this.benThuHai = benThuHai;
	} 
}
