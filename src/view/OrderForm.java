package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Order;
import model.OrderDetail;

public class OrderForm extends JFrame {

    private JTextField Field_MaHoaDon;
    private JTextField Field_TenKhachHang;
    private JTextField Field_NgayXuat;
    private JTextField Field_TongTien;
    private JButton Button_TaoHoaDon;
    private JButton Button_ThemSanPham;
    private JButton Button_ChiTietHD;
    private JButton Button_HuyHD;
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private Map<String, ProductForm> chiTietHoaDonMap;
    private Map<String, Order> hoaDonMap;
    private int hoaDonCounter;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                OrderForm frame = new OrderForm();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public OrderForm() {
        this.chiTietHoaDonMap = new HashMap<>();
        this.hoaDonMap = new HashMap<>();
        this.hoaDonCounter = 1;

        setTitle("Quản lý đơn xuất hàng");
        getContentPane().setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));
        Field_MaHoaDon = new JTextField();
        Field_TenKhachHang = new JTextField();
        Field_NgayXuat = new JTextField();
        Field_TongTien = new JTextField();
        Field_TongTien.setEditable(false);

        formPanel.add(new JLabel("Mã hóa đơn:"));
        formPanel.add(Field_MaHoaDon);
        formPanel.add(new JLabel("Tên khách hàng:"));
        formPanel.add(Field_TenKhachHang);
        formPanel.add(new JLabel("Ngày xuất hàng:"));
        formPanel.add(Field_NgayXuat);
        formPanel.add(new JLabel("Tổng tiền:"));
        formPanel.add(Field_TongTien);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        Button_TaoHoaDon = new JButton("Tạo đơn hàng");
        Button_ThemSanPham = new JButton("Thêm sản phẩm vào đơn hàng");
        Button_ChiTietHD = new JButton("Xem chi tiết hóa đơn");
        Button_HuyHD = new JButton("Hủy hóa đơn");

        buttonPanel.add(Button_TaoHoaDon);
        buttonPanel.add(Button_ThemSanPham);
        buttonPanel.add(Button_ChiTietHD);
        buttonPanel.add(Button_HuyHD);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Mã đơn hàng");
        tableModel.addColumn("Tên khách hàng");
        tableModel.addColumn("Ngày xuất hàng");
        tableModel.addColumn("Tổng tiền");

        orderTable = new JTable(tableModel);

        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(new JScrollPane(orderTable), BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add action listeners
        Button_TaoHoaDon.addActionListener(e -> createOrder());
        Button_ThemSanPham.addActionListener(e -> showProductForm());
        Button_ChiTietHD.addActionListener(e -> viewOrderDetails());
        Button_HuyHD.addActionListener(e -> deleteOrder());
    }

    private void createOrder() {
        String orderId = Field_MaHoaDon.getText();
        String customerName = Field_TenKhachHang.getText();
        String exportDateString = Field_NgayXuat.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date exportDate;

        try {
            exportDate = dateFormat.parse(exportDateString);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày xuất. Hãy nhập đúng định dạng dd/MM/yyyy.");
            return;
        }

        String detailId = String.valueOf(hoaDonCounter);
        hoaDonCounter++;

        Order order = new Order(orderId, customerName, exportDate, detailId);
        hoaDonMap.put(orderId, order);
        tableModel.addRow(new Object[]{orderId, customerName, dateFormat.format(exportDate), 0.0});
        ProductForm productForm = new ProductForm();
        chiTietHoaDonMap.put(orderId, productForm);

        productForm.get_Button_themSanPham().addActionListener(e -> addProduct(productForm));
        productForm.getButton_xacNhanDon().addActionListener(e -> confirmOrder(productForm));

        JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
        resetOrderFormFields();
    }

    private void showProductForm() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 hóa đơn để thêm sản phẩm");
            return;
        }

        String orderId = (String) tableModel.getValueAt(selectedRow, 0);
        ProductForm productForm = chiTietHoaDonMap.get(orderId);
        Order order = hoaDonMap.get(orderId);

        productForm.setChiTietHoaDon(orderId, order.getMaChiTietDonHang());
        productForm.setVisible(true);
    }

    private void addProduct(ProductForm productForm) {
        String orderId = productForm.get_Field_maHoaDon().getText();
        Order order = hoaDonMap.get(orderId);

        if (order == null) {
            JOptionPane.showMessageDialog(productForm, "Tạo một đơn hàng để thêm sản phẩm");
            return;
        }

        String detailId = productForm.get_Field_maChiTietDonHang().getText();
        String productId = productForm.get_Field_maSanPham().getText();
        String productName = productForm.get_Field_tenSanPham().getText();
        int quantity = Integer.parseInt(productForm.get_Field_soLuong().getText());
        double price = Double.parseDouble(productForm.get_Field_gia().getText());

        OrderDetail orderDetail = new OrderDetail(detailId, productId, productName, quantity, price);
        order.themChiTietHD(orderDetail);
        productForm.getTableModel().addRow(new Object[]{detailId, productId, productName, quantity, price});

        resetProductFormFields(productForm);
    }

    private void confirmOrder(ProductForm productForm) {
        String orderId = productForm.get_Field_maHoaDon().getText();
        Order order = hoaDonMap.get(orderId);
        if (order == null) {
            JOptionPane.showMessageDialog(productForm, "Không tìm thấy hóa đơn");
            return;
        }

        order.TinhToanTongTien();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(orderId)) {
                tableModel.setValueAt(order.getTongTien(), i, 3);
                break;
            }
        }

        JOptionPane.showMessageDialog(productForm, "Hóa đơn được xác nhận thành công");
        productForm.setVisible(false);
    }

    private void viewOrderDetails() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một hóa đơn để xem chi tiết");
            return;
        }

        String orderId = (String) tableModel.getValueAt(selectedRow, 0);
        Order order = hoaDonMap.get(orderId);
        ProductForm productForm = chiTietHoaDonMap.get(orderId);

        if (order == null || productForm == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy chi tiết đơn hàng");
            return;
        }

        DefaultTableModel productTableModel = productForm.getTableModel();
        productTableModel.setRowCount(0);  // Clear existing rows
        for (OrderDetail detail : order.getChiTietHoaDon()) {
            productTableModel.addRow(new Object[]{
                detail.getmaChiTietDonHang(),
                detail.getmaSanPham(),
                detail.gettenSanPham(),
                detail.getsoLuong(),
                detail.getgiaSanPham()
            });
        }

        productForm.setVisible(true);
    }

    private void deleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Chọn một hóa đơn để xóa");
            return;
        }

        String orderId = (String) tableModel.getValueAt(selectedRow, 0);
        hoaDonMap.remove(orderId);
        chiTietHoaDonMap.remove(orderId);
        tableModel.removeRow(selectedRow);
        JOptionPane.showMessageDialog(this, "Hóa đơn được xóa thành công");
    }

    private void resetOrderFormFields() {
        Field_MaHoaDon.setText("");
        Field_TenKhachHang.setText("");
        Field_NgayXuat.setText("");
    }

    private void resetProductFormFields(ProductForm productForm) {
        productForm.get_Field_maSanPham().setText("");
        productForm.get_Field_tenSanPham().setText("");
        productForm.get_Field_soLuong().setText("");
        productForm.get_Field_gia().setText("");
    }
}
