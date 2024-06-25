package model;

public class OrderDetail {
    private String maChiTietDonHang;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double gia;

    public OrderDetail(String detailId, String productId, String productName, int quantity, double price) {
        this.maChiTietDonHang = detailId;
        this.maSanPham = productId;
        this.tenSanPham = productName;
        this.soLuong = quantity;
        this.gia = price;
    }

    public String getmaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setmaChiTietDonHang(String detailId) {
        this.maChiTietDonHang = detailId;
    }

    public String getmaSanPham() {
        return maSanPham;
    }

    public void setmaSanPham(String productId) {
        this.maSanPham = productId;
    }

    public String gettenSanPham() {
        return tenSanPham;
    }

    public void settenSanPham(String productName) {
        this.tenSanPham = productName;
    }

    public int getsoLuong() {
        return soLuong;
    }

    public void setsoLuong(int quantity) {
        this.soLuong = quantity;
    }

    public double getgiaSanPham() {
        return gia;
    }

    public void setgiaSanPham(double price) {
        this.gia = price;
    }
}