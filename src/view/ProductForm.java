package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ProductForm extends JFrame {

	 private JTextField Field_MaHoaDon;
	 private JTextField Field_MaChiTietDonHang;
	 private JTextField Field_MaSanPham;
	 private JTextField Field_TenSanPham;
	 private JTextField Field_SoLuong;
	 private JTextField Field_Gia;
	 private JTable productTable;
	 private DefaultTableModel tableModel;
	 private JButton Button_ThemSanPham;
	 private JButton Button_XacNhanDon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductForm frame = new ProductForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public  ProductForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Chi tiết hóa đơn");
        getContentPane().setLayout(new BorderLayout());

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Mã hóa đơn:"));
        Field_MaHoaDon = new JTextField();
        Field_MaHoaDon.setEnabled(false);  // Make the Order ID field non-editable
        panel.add(Field_MaHoaDon);

        panel.add(new JLabel("Mã chi tiết đơn hàng:"));
        Field_MaChiTietDonHang = new JTextField();
        Field_MaChiTietDonHang.setEnabled(false);  // Make the Detail ID field non-editable
        panel.add(Field_MaChiTietDonHang);

        panel.add(new JLabel("Mã sản phẩm:"));
        Field_MaSanPham = new JTextField();
        panel.add(Field_MaSanPham);

        panel.add(new JLabel("Tên sản phẩm"));
        Field_TenSanPham = new JTextField();
        panel.add(Field_TenSanPham);

        panel.add(new JLabel("Số lượng:"));
        Field_SoLuong = new JTextField();
        panel.add(Field_SoLuong);

        panel.add(new JLabel("Price:"));
        Field_Gia = new JTextField();
        panel.add(Field_Gia);

        Button_ThemSanPham = new JButton("Thêm sản phẩm");
        panel.add(Button_ThemSanPham);

        Button_XacNhanDon = new JButton("Xác nhận đơn hàng");
        panel.add(Button_XacNhanDon);

        getContentPane().add(panel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Mã chi tiết đơn hàng", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá"}, 0);
        productTable = new JTable(tableModel);
        getContentPane().add(new JScrollPane(productTable), BorderLayout.CENTER);
    }

    public JTextField get_Field_maHoaDon() {
        return Field_MaHoaDon;
    }

    public JTextField get_Field_maChiTietDonHang() {
        return Field_MaChiTietDonHang;
    }

    public JTextField get_Field_maSanPham() {
        return Field_MaSanPham;
    }

    public JTextField get_Field_tenSanPham() {
        return Field_TenSanPham;
    }

    public JTextField get_Field_soLuong() {
        return Field_SoLuong;
    }

    public JTextField get_Field_gia() {
        return Field_Gia;
    }

    public JTable getBangSanPham() {
        return productTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton get_Button_themSanPham() {
        return Button_ThemSanPham;
    }

    public JButton getButton_xacNhanDon() {
        return Button_XacNhanDon;
    }

    // Method to set the order details including detail ID
    public void setChiTietHoaDon(String orderId, String detailId) {
        this.Field_MaHoaDon.setText(orderId);
        this.Field_MaChiTietDonHang.setText(detailId);
    }
}
