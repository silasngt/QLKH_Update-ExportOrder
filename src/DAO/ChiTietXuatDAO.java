package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ChiTietXuat;

public class ChiTietXuatDAO extends myConnect {

	public boolean insertOrderDetail(ChiTietXuat chiTietXuat) {
        if (openConnectDB()) {
            String checkSql = "SELECT COUNT(*) FROM ChiTietXuat WHERE MaChiTietXuat = ?";
            String insertSql = "INSERT INTO ChiTietXuat (MaChiTietXuat, MaHoaDonXuat, MaSanPham, SoLuong) VALUES (?, ?, ?, ?)";
            String updatePriceSql = "UPDATE SanPham SET GiaBan = ? WHERE MaSanPham = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                 PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                 PreparedStatement updatePriceStmt = conn.prepareStatement(updatePriceSql)) {
                 
                // Kiểm tra tồn tại
                checkStmt.setString(1, chiTietXuat.getMaChiTietDonHang());
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("Duplicate entry: " + chiTietXuat.getMaChiTietDonHang());
                    return false;
                }

                // Chèn bản ghi mới
                insertStmt.setString(1, chiTietXuat.getMaChiTietDonHang());
                insertStmt.setString(2, chiTietXuat.getMaHoaDon());
                insertStmt.setString(3, chiTietXuat.getMaSanPham());
                insertStmt.setInt(4, chiTietXuat.getSoLuong());
                insertStmt.executeUpdate();

                // Cập nhật giá sản phẩm
                updatePriceStmt.setDouble(1, chiTietXuat.getGiaSanPham());
                updatePriceStmt.setString(2, chiTietXuat.getMaSanPham());
                updatePriceStmt.executeUpdate();

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnectDB();
            }
        }
        return false;
    }

    public List<ChiTietXuat> getOrderDetailsByOrderId(String detailId) {
        List<ChiTietXuat> chiTietXuats = new ArrayList<>();
        if (openConnectDB()) {
            String sql = "SELECT * FROM ChiTietXuat WHERE MaChiTietXuat = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, detailId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ChiTietXuat chiTietXuat = new ChiTietXuat(
                            rs.getString("MaChiTietXuat"),
                            rs.getString("MaHoaDonXuat"),
                            rs.getString("MaSanPham"),
                            rs.getString("TenSanPham"),
                            rs.getInt("SoLuong"),
                            0.0  // Giá không được lưu trữ trong database, mặc định là 0.0
                        );
                        chiTietXuats.add(chiTietXuat);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnectDB();
            }
        }
        return chiTietXuats;
    }

    public boolean deleteOrderDetailsByOrderId(String orderId) {
        if (openConnectDB()) {
            String sql = "DELETE FROM ChiTietXuat WHERE MaHoaDonXuat = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, orderId);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnectDB();
            }
        }
        return false;
    }

	
}
