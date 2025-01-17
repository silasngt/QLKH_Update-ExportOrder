package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.HoaDonXuatDAO;
import DAO.ChiTietXuatDAO;
import model.HoaDonXuat;
import model.ChiTietXuat;

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
    private Map<String, HoaDonXuat> hoaDonMap;
    private Map<String, Integer> chiTietCounterMap;
    private int hoaDonCounter;
	private HoaDonXuatDAO hoaDonXuatDAO;
	private ChiTietXuatDAO chiTietXuatDAO;

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
        this.chiTietCounterMap = new HashMap<>();
        this.hoaDonCounter = 1;

        // Initialize DAO for database connection
        hoaDonXuatDAO = new HoaDonXuatDAO();
        chiTietXuatDAO = new ChiTietXuatDAO();
        
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

        // Add action listeners for buttons
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

        HoaDonXuat hoaDonXuat = new HoaDonXuat(orderId, customerName, exportDate, detailId);
        hoaDonMap.put(orderId, hoaDonXuat);
        tableModel.addRow(new Object[]{orderId, customerName, dateFormat.format(exportDate), 0.0});
        ProductForm productForm = new ProductForm();
        chiTietHoaDonMap.put(orderId, productForm);
        chiTietCounterMap.put(orderId, 1);

        // Save order to database
        if (hoaDonXuatDAO.insertOrder(hoaDonXuat)) {
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công và lưu vào database");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu hóa đơn vào database");
        }
        
        productForm.get_Button_themSanPham().addActionListener(e -> addProduct(productForm));
        productForm.getButton_xacNhanDon().addActionListener(e -> confirmOrder(productForm));
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
        HoaDonXuat hoaDonXuat = hoaDonMap.get(orderId);

        productForm.setChiTietHoaDon(orderId, hoaDonXuat.getMaChiTietDonHang());
        productForm.setVisible(true);
    }

    private void addProduct(ProductForm productForm) {
        String orderId = productForm.get_Field_maHoaDon().getText();
        HoaDonXuat hoaDonXuat = hoaDonMap.get(orderId);

        if (hoaDonXuat == null) {
            JOptionPane.showMessageDialog(productForm, "Tạo một đơn hàng để thêm sản phẩm");
            return;
        }

        int detailCounter = chiTietCounterMap.get(orderId);
        String detailId = orderId + "-" + detailCounter;
        chiTietCounterMap.put(orderId, detailCounter + 1);

        String productId = productForm.get_Field_maSanPham().getText();
        String productName = productForm.get_Field_tenSanPham().getText();
        int quantity = Integer.parseInt(productForm.get_Field_soLuong().getText());
        double price = Double.parseDouble(productForm.get_Field_gia().getText());

        if (quantity < 0 || price < 0) {
            JOptionPane.showMessageDialog(productForm, "Số lượng và giá không được là số âm. Vui lòng nhập lại.");
            return;
        }

        ChiTietXuat chiTietXuat = new ChiTietXuat(detailId, orderId, productId, productName, quantity, price);
        hoaDonXuat.themChiTietHD(chiTietXuat);
        productForm.getTableModel().addRow(new Object[]{orderId, detailId, productId, productName, quantity, price});

        // Save product detail to database including price
        if (chiTietXuatDAO.insertOrderDetail(chiTietXuat)) {
            JOptionPane.showMessageDialog(productForm, "Thêm sản phẩm thành công và lưu vào database");
        } else {
            JOptionPane.showMessageDialog(productForm, "Lỗi khi lưu sản phẩm vào database");
        }
        resetProductFormFields(productForm);
    }

    private void confirmOrder(ProductForm productForm) {
        String orderId = productForm.get_Field_maHoaDon().getText();
        HoaDonXuat hoaDonXuat = hoaDonMap.get(orderId);
        if (hoaDonXuat == null) {
            JOptionPane.showMessageDialog(productForm, "Không tìm thấy hóa đơn");
            return;
        }

        DefaultTableModel productTableModel = productForm.getTableModel();
        int rowCount = productTableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String detailId = (String) productTableModel.getValueAt(i, 1);
            String productId = (String) productTableModel.getValueAt(i, 2);
            String productName = (String) productTableModel.getValueAt(i, 3);
            int quantity;
            double price;
            try {
                quantity = Integer.parseInt(productTableModel.getValueAt(i, 4).toString());
                price = Double.parseDouble(productTableModel.getValueAt(i, 5).toString());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(productForm, "Lỗi chuyển đổi dữ liệu: " + e.getMessage());
                return;
            }

            ChiTietXuat chiTietXuat = new ChiTietXuat(detailId, orderId, productId, productName, quantity, price);

            // Lưu chi tiết đơn hàng vào database
            if (!chiTietXuatDAO.insertOrderDetail(chiTietXuat)) {
//                JOptionPane.showMessageDialog(productForm, "Chi tiết đơn hàng đã tồn tại: " + detailId);
                continue; // Bỏ qua bản ghi trùng lặp và tiếp tục xử lý các bản ghi khác
            }
        }

        hoaDonXuat.tinhToanTongTien();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(orderId)) {
                tableModel.setValueAt(hoaDonXuat.getTongTien(), i, 3);
                break;
            }
        }

        JOptionPane.showMessageDialog(productForm, "Hóa đơn được xác nhận thành công");
        productForm.setVisible(false);
    }

    private void viewOrderDetails() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 hóa đơn để xem chi tiết");
            return;
        }

        String orderId = (String) tableModel.getValueAt(selectedRow, 0);
        ProductForm productForm = chiTietHoaDonMap.get(orderId);
        productForm.setVisible(true);
    }

    private void deleteOrder() {
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Hãy chọn 1 hóa đơn để xóa");
            return;
        }

        String orderId = (String) tableModel.getValueAt(selectedRow, 0);
        HoaDonXuat hoaDonXuat = hoaDonMap.get(orderId);

        if (hoaDonXuat == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn để xóa");
            return;
        }

        // Xóa tất cả các chi tiết hóa đơn liên quan từ bảng ChiTietXuat
        if (!chiTietXuatDAO.deleteOrderDetailsByOrderId(orderId)) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa chi tiết hóa đơn từ database");
            return;
        }

        // Xóa hóa đơn từ bảng HoaDonXuat
        if (hoaDonXuatDAO.deleteOrder(orderId)) {
            JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa hóa đơn từ database");
            return;
        }

        chiTietHoaDonMap.remove(orderId);
        hoaDonMap.remove(orderId);
        chiTietCounterMap.remove(orderId);
        tableModel.removeRow(selectedRow);
    }

    private void resetOrderFormFields() {
        Field_MaHoaDon.setText("");
        Field_TenKhachHang.setText("");
        Field_NgayXuat.setText("");
        Field_TongTien.setText("");
    }

    private void resetProductFormFields(ProductForm productForm) {
        productForm.get_Field_maSanPham().setText("");
        productForm.get_Field_tenSanPham().setText("");
        productForm.get_Field_soLuong().setText("");
        productForm.get_Field_gia().setText("");
    }
}
