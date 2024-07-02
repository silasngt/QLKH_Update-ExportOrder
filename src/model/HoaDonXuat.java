package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonXuat {
    private String maHoaDon;
    private String tenKhachHang;
    private Date ngayXuat;
    private double tongTien;
    private List<ChiTietXuat> chiTietHoaDon;
    private String maChiTietDonHang;

    public HoaDonXuat(String orderId, String customerName, Date exportDate, String detailId) {
        this.maHoaDon = orderId;
        this.tenKhachHang = customerName;
        this.ngayXuat = exportDate;
        this.tongTien = 0.0;
        this.chiTietHoaDon = new ArrayList<>();
        this.maChiTietDonHang = detailId;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public List<ChiTietXuat> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public void setChiTietHoaDon(List<ChiTietXuat> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }

    public String getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setMaChiTietDonHang(String maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public void themChiTietHD(ChiTietXuat detail) {
        chiTietHoaDon.add(detail);
        tongTien += detail.getGiaSanPham() * detail.getSoLuong();
    }

    public void removeChiTietHD(ChiTietXuat detail) {
        chiTietHoaDon.remove(detail);
        tongTien -= detail.getGiaSanPham() * detail.getSoLuong();
    }

    public void tinhToanTongTien() {
        tongTien = 0;
        for (ChiTietXuat detail : chiTietHoaDon) {
            tongTien += detail.getGiaSanPham() * detail.getSoLuong();
        }
    }

	public ChiTietXuat getChiTietXuat(String detailId) {
		// TODO Auto-generated method stub
		return null;
	}
}
