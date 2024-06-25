package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private String maHoaDon;
    private String tenKhachHang;
    private Date ngayXuat;
    private double tongTien;
    private List<OrderDetail> chiTietHoaDon;
    private String maChiTietDonHang;

    public Order(String orderId, String customerName, Date exportDate, String detailId) {
        this.maHoaDon = orderId;
        this.tenKhachHang = customerName;
        this.ngayXuat = exportDate;
        this.tongTien = 0.0;
        this.chiTietHoaDon = new ArrayList<>();
        this.maChiTietDonHang = detailId;
    }

    public String getMaDonHang() {
        return maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public List<OrderDetail> getChiTietHoaDon() {
        return chiTietHoaDon;
    }

    public String getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void themChiTietHD(OrderDetail detail) {
        chiTietHoaDon.add(detail);
        tongTien += detail.getgiaSanPham() * detail.getsoLuong();
    }

    public void TinhToanTongTien() {
        tongTien = 0;
        for (OrderDetail detail : chiTietHoaDon) {
            tongTien += detail.getgiaSanPham() * detail.getsoLuong();
        }
    }

	
}
