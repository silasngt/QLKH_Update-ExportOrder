package view;

import java.awt.BorderLayout;
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
    
    private JTextField Field_maHoaDon;
    private JTextField Field_maSanPham;
    private JTextField Field_tenSanPham;
    private JTextField Field_soLuong;
    private JTextField Field_gia;
    private JButton Button_themSanPham;
    private JButton Button_xacNhanDon;
    private DefaultTableModel tableModel;
    private JTable productTable;

    public ProductForm() {
        setTitle("Thêm sản phẩm vào hóa đơn");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        Field_maHoaDon = new JTextField();
        Field_maHoaDon.setEnabled(false);
        Field_maSanPham = new JTextField();
        Field_tenSanPham = new JTextField();
        Field_soLuong = new JTextField();
        Field_gia = new JTextField();

        formPanel.add(new JLabel("Mã hóa đơn:"));
        formPanel.add(Field_maHoaDon);
        formPanel.add(new JLabel("Mã sản phẩm:"));
        formPanel.add(Field_maSanPham);
        formPanel.add(new JLabel("Tên sản phẩm:"));
        formPanel.add(Field_tenSanPham);
        formPanel.add(new JLabel("Số lượng:"));
        formPanel.add(Field_soLuong);
        formPanel.add(new JLabel("Giá:"));
        formPanel.add(Field_gia);

        Button_themSanPham = new JButton("Thêm sản phẩm");
        Button_xacNhanDon = new JButton("Xác nhận đơn hàng");

        formPanel.add(Button_themSanPham);
        formPanel.add(Button_xacNhanDon);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã hóa đơn");
        tableModel.addColumn("Mã chi tiết đơn hàng");
        tableModel.addColumn("Mã sản phẩm");
        tableModel.addColumn("Tên sản phẩm");
        tableModel.addColumn("Số lượng");
        tableModel.addColumn("Giá");

        productTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(tableScrollPane, BorderLayout.CENTER);
    }

    public JTextField get_Field_maHoaDon() {
        return Field_maHoaDon;
    }

    public JTextField get_Field_maSanPham() {
        return Field_maSanPham;
    }

    public JTextField get_Field_tenSanPham() {
        return Field_tenSanPham;
    }

    public JTextField get_Field_soLuong() {
        return Field_soLuong;
    }

    public JTextField get_Field_gia() {
        return Field_gia;
    }

    public JButton get_Button_themSanPham() {
        return Button_themSanPham;
    }

    public JButton getButton_xacNhanDon() {
        return Button_xacNhanDon;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setChiTietHoaDon(String orderId, String chiTietId) {
        Field_maHoaDon.setText(orderId);
        // This method can be used to set other fields as needed
    }
}
