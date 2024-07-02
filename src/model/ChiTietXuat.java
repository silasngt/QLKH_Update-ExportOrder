package model;

public class ChiTietXuat {
    private String maChiTietDonHang;
    private String maHoaDon;
    private String maSanPham;
    private String tenSanPham;
    private int soLuong;
    private double giaSanPham;

    // Constructor
    public ChiTietXuat(String maChiTietDonHang, String maHoaDon, String maSanPham,String tenSanPham, int soLuong, double giaSanPham) {
        this.maChiTietDonHang = maChiTietDonHang;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.giaSanPham = giaSanPham;
    }

    // Getters and setters
    
    public String getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public void setMaChiTietDonHang(String maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }
}
