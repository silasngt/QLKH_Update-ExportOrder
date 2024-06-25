package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModernMenuExample extends JFrame {
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JButton lastSelectedButton; // Biến lưu trữ nút được chọn trước đó

    public ModernMenuExample() {
        setTitle("Modern Menu Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Tạo panel menu với góc bo tròn
        RoundedPanel jPanelMenu = new RoundedPanel(15, Color.WHITE);
        jPanelMenu.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các component

        // Thêm biểu tượng nhà vào đầu panel menu
        JLabel labelHouse = new JLabel();
        labelHouse.setHorizontalAlignment(SwingConstants.CENTER);
        Image img = new ImageIcon(getClass().getResource("/House.png")).getImage();
        labelHouse.setIcon(new ImageIcon(img.getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Chiếm toàn bộ chiều rộng
        jPanelMenu.add(labelHouse, gbc);

        // Thêm khoảng cách
        gbc.gridy++;
        jPanelMenu.add(Box.createVerticalStrut(20), gbc);

        // Thêm các mục menu vào panel
        gbc.gridy++;
        gbc.gridwidth = 1; // Trở lại chiếm một cột
        jPanelMenu.add(createMenuButton("Đơn Nhập", "DonNhap"), gbc);
        gbc.gridy++;
        jPanelMenu.add(createMenuButton("Đơn Xuất", "DonXuat"), gbc);
        gbc.gridy++;
        jPanelMenu.add(createMenuButton("Tồn Kho", "TonKho"), gbc);
        gbc.gridy++;
        jPanelMenu.add(createMenuButton("Quản Trị", "QuanTri"), gbc);
        gbc.gridy++;
        jPanelMenu.add(createMenuButton("Thống kê", "ThongKe"), gbc);

        contentPane.add(jPanelMenu, BorderLayout.WEST);

        // Nội dung chính với CardLayout
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        // Tạo các panel khác nhau cho mỗi thẻ
        mainPanel.add(createPanel("Đây là giao diện Đơn Nhập"), "DonNhap");
        mainPanel.add(createPanel("Đây là giao diện Đơn Xuất"), "DonXuat");
        mainPanel.add(createPanel("Đây là giao diện Tồn Kho"), "TonKho");
        mainPanel.add(createPanel("Đây là giao diện Quản Trị"), "QuanTri");
        mainPanel.add(createPanel("Đây là giao diện Thống kê"), "ThongKe");

        contentPane.add(mainPanel, BorderLayout.CENTER);
    }

    // Phương thức tạo các nút menu
    private JButton createMenuButton(String text, String cardName) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button.setFocusPainted(false); // Loại bỏ viền khi được chọn
        button.setContentAreaFilled(false); // Loại bỏ nền mặc định
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Đặt icon (tùy chọn, có thể bỏ qua nếu không cần)
        String iconPath = "/icon_" + cardName + ".png";
        Image img = new ImageIcon(getClass().getResource(iconPath)).getImage();
        Image scaledImg = img.getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImg));

        // Thêm hành động khi nhấp chuột
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Khôi phục màu nền của nút trước đó nếu có
                if (lastSelectedButton != null && lastSelectedButton != button) {
                    lastSelectedButton.setBackground(null);
                    lastSelectedButton.setOpaque(false);
                }

                // Đặt màu nền cho nút hiện tại
                button.setBackground(Color.LIGHT_GRAY);
                button.setOpaque(true);

                // Cập nhật nút được chọn trước đó
                lastSelectedButton = button;

                // Hiển thị thẻ tương ứng
                cardLayout.show(mainPanel, cardName);
            }
        });

        return button;
    }

    // Phương thức tạo các panel khác nhau
    private JPanel createPanel(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Tahoma", Font.PLAIN, 24));
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModernMenuExample frame = new ModernMenuExample();
            frame.setVisible(true);
        });
    }
}
