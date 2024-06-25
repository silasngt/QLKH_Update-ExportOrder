package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class XemChiTietDonHangNhapView extends JFrame {
    private JLabel titleLabel;
    private JLabel orderIdLabel;
    private JLabel customerLabel;
    private JLabel orderDateLabel;
    private JLabel totalPriceLabel;
    private JTable productTable;
    private DefaultTableModel productTableModel;

    public XemChiTietDonHangNhapView(String orderId, String customerName, String orderDate,String toltalPrice ,Object[][] productData) {
        setTitle("Phiếu Đơn Hàng");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        titleLabel = new JLabel("PHIẾU ĐƠN HÀNG");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(new JLabel("Mã đơn hàng:"));
        orderIdLabel = new JLabel(orderId);
        infoPanel.add(orderIdLabel);
        infoPanel.add(new JLabel("Tên khách hàng:"));
        customerLabel = new JLabel(customerName);
        infoPanel.add(customerLabel);
        infoPanel.add(new JLabel("Ngày đặt:"));
        orderDateLabel = new JLabel(orderDate);
        infoPanel.add(orderDateLabel);

        headerPanel.add(infoPanel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tạo bảng chi tiết sản phẩm
        String[] columnNames = {"Mã sản phẩm", "Tên sản phẩm", "Mã kho", "Số lượng", "Đơn giá"};
        // Tạo dữ liệu mẫu (thay thế với dữ liệu thực tế)

        productTableModel = new DefaultTableModel(productData, columnNames);
        productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        add(productScrollPane, BorderLayout.CENTER);
        
        // Panel chứa thông tin tổng tiền ở phía dưới cùng
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPriceLabel = new JLabel("Tổng tiền: " + toltalPrice);
        footerPanel.add(totalPriceLabel);
        add(footerPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
