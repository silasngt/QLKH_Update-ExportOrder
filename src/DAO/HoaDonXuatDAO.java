package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HoaDonXuat;

public class HoaDonXuatDAO extends myConnect {

    public boolean insertOrder(HoaDonXuat hoaDonXuat) {
        if (openConnectDB()) {
            String sql = "INSERT INTO HoaDonXuat (MaHoaDonXuat, TenKhachHang, NgayXuat, TongTien) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, hoaDonXuat.getMaHoaDon());
                stmt.setString(2, hoaDonXuat.getTenKhachHang());
                stmt.setDate(3, new java.sql.Date(hoaDonXuat.getNgayXuat().getTime()));
                stmt.setDouble(4, hoaDonXuat.getTongTien());
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

    public HoaDonXuat getOrderById(String orderId) {
        if (openConnectDB()) {
            String sql = "SELECT * FROM HoaDonXuat WHERE MaHoaDonXuat = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, orderId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        HoaDonXuat hoaDonXuat = new HoaDonXuat(
                            rs.getString("MaHoaDonXuat"),
                            rs.getString("TenKhachHang"),
                            rs.getDate("NgayXuat"),
                            rs.getString("TongTien")
                        );
                        return hoaDonXuat;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnectDB();
            }
        }
        return null;
    }

    public List<HoaDonXuat> getAllOrders() {
        List<HoaDonXuat> hoaDonXuats = new ArrayList<>();
        if (openConnectDB()) {
            String sql = "SELECT * FROM HoaDonXuat";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HoaDonXuat hoaDonXuat = new HoaDonXuat(
                        rs.getString("MaHoaDonXuat"),
                        rs.getString("TenKhachHang"),
                        rs.getDate("NgayXuat"),
                        rs.getString("TongTien")
                    );
                    hoaDonXuats.add(hoaDonXuat);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnectDB();
            }
        }
        return hoaDonXuats;
    }

    public boolean deleteOrder(String orderId) {
        if (openConnectDB()) {
            String sql = "DELETE FROM HoaDonXuat WHERE MaHoaDonXuat = ?";
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
