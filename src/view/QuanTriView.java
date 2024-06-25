package view;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.util.Map;
import java.util.HashMap;
//Import thư viện JDBC của MySQL
//import com.mysql.cj.jdbc.Driver;
import java.awt.SystemColor;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.border.EtchedBorder;
public class QuanTriView extends JFrame {

    private static final long serialVersionUID = 1L;
    public JPanel contentPane;
    private JPanel panel_1;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTable table;
    private DefaultTableModel model;
    private Map<String, boolean[]> rolePermissions = new HashMap<>();
    private Map<String, String[]> rolePermissionNames = new HashMap<>();
    private List<JCheckBox> checkBoxes;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QuanTriView frame = new QuanTriView();
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
    public QuanTriView() {
    	setBackground(SystemColor.inactiveCaptionBorder);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 701, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        RoundedPanel jPanelMenu = new RoundedPanel(50, Color.WHITE);
//        contentPane.add(jPanelMenu, BorderLayout.WEST);
        jPanelMenu.setLayout(new BorderLayout());

        JLabel labelHouse = new JLabel("");
        labelHouse.setHorizontalAlignment(SwingConstants.CENTER);
//        Image img = new ImageIcon(getClass().getResource("/img/House.png")).getImage();
//        labelHouse.setIcon(new ImageIcon(img));
        jPanelMenu.add(labelHouse, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        JLabel labelDH = new JLabel("Đơn Hàng");
        labelDH.setHorizontalAlignment(SwingConstants.CENTER);
        labelDH.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        Image imgDH = new ImageIcon(getClass().getResource("/img/icon_DonHang.png")).getImage();
//        Image scaledImgDH = imgDH.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//        labelDH.setIcon(new ImageIcon(scaledImgDH));
        labelDH.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelDH);

        JLabel labelTK = new JLabel("Tồn Kho");
        labelTK.setHorizontalAlignment(SwingConstants.CENTER);
        labelTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        Image imgTK = new ImageIcon(getClass().getResource("/img/icon_TonKho.png")).getImage();
//        Image scaledImgTK = imgTK.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//        labelTK.setIcon(new ImageIcon(scaledImgTK));
        labelTK.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelTK);

        JLabel labelNX = new JLabel("Nhập/Xuất");
        labelNX.setHorizontalAlignment(SwingConstants.CENTER);
        labelNX.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        Image imgNX = new ImageIcon(getClass().getResource("/img/icon_NhapXuat.png")).getImage();
//        Image scaledImgNX = imgNX.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//        labelNX.setIcon(new ImageIcon(scaledImgNX));
        labelNX.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelNX);

        JLabel labelVT = new JLabel("Vị Trí Kho");
        labelVT.setHorizontalAlignment(SwingConstants.CENTER);
        labelVT.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        Image imgVT = new ImageIcon(getClass().getResource("/img/icon_ViTri.png")).getImage();
//        Image scaledImgVT = imgVT.getScaledInstance(40, 36, Image.SCALE_SMOOTH);
//        labelVT.setIcon(new ImageIcon(scaledImgVT));
        labelVT.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelVT);

        JLabel labelQT = new JLabel("Quản Trị");
        labelQT.setHorizontalAlignment(SwingConstants.CENTER);
        labelQT.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        Image imgQT = new ImageIcon(getClass().getResource("/img/icon_QuanTri.png")).getImage();
//        Image scaledImgQT = imgQT.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//        labelQT.setIcon(new ImageIcon(scaledImgQT));
        labelQT.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelQT);

        JLabel labelTKe = new JLabel("Thống kê");
        labelTKe.setHorizontalAlignment(SwingConstants.CENTER);
        labelTKe.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        Image imgTKe = new ImageIcon(getClass().getResource("/img/icon_ThongKe.png")).getImage();
//        Image scaledImgTKe = imgTKe.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//        labelTKe.setIcon(new ImageIcon(scaledImgTKe));
        labelTKe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(labelTKe);

        panel.add(Box.createVerticalStrut(10));
        panel.add(Box.createVerticalGlue());

        jPanelMenu.add(panel, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane, BorderLayout.CENTER);

        panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.activeCaption);
        tabbedPane.addTab("Quản lí nhân viên", null, panel_1, null);
        tabbedPane.setBackgroundAt(0, SystemColor.activeCaption);
        panel_1.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(SystemColor.activeCaption);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setBackground(SystemColor.activeCaption);

        titlePanel.add(Box.createHorizontalGlue());
        JLabel lblNewLabel_6 = new JLabel("THÔNG TIN NHÂN VIÊN");
        lblNewLabel_6.setForeground(Color.BLACK);
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 18));
        titlePanel.add(lblNewLabel_6);
        titlePanel.add(Box.createHorizontalGlue());

        topPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        centerPanel.setBackground(SystemColor.activeCaption);

        JLabel label = new JLabel("Mã nhân viên", SwingConstants.RIGHT);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel.add(label);
        textField_4 = new JTextField();
        centerPanel.add(textField_4);

        JLabel label_3 = new JLabel("Tên nhân viên", SwingConstants.RIGHT);
        label_3.setForeground(Color.BLACK);
        label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel.add(label_3);
        textField_6 = new JTextField();
        centerPanel.add(textField_6);

        JLabel label_1 = new JLabel("Chức vụ", SwingConstants.RIGHT);
        label_1.setForeground(Color.BLACK);
        label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel.add(label_1);
        textField_5 = new JTextField();
        centerPanel.add(textField_5);

        JLabel label_4 = new JLabel("Số điện thoại", SwingConstants.RIGHT);
        label_4.setForeground(Color.BLACK);
        label_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel.add(label_4);
        textField_7 = new JTextField();
        centerPanel.add(textField_7);

        JLabel label_2 = new JLabel("Giới tính", SwingConstants.RIGHT);
        label_2.setForeground(Color.BLACK);
        label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel.add(label_2);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(SystemColor.activeCaption);
        JCheckBox chckbxNewCheckBox = new JCheckBox("Nam");
        chckbxNewCheckBox.setForeground(Color.BLACK);
        chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
        JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Nữ");
        chckbxNewCheckBox_1.setForeground(Color.BLACK);
        chckbxNewCheckBox_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
        genderPanel.add(chckbxNewCheckBox);
        genderPanel.add(chckbxNewCheckBox_1);
        centerPanel.add(genderPanel);

        JLabel label_5 = new JLabel("Email", SwingConstants.RIGHT);
        label_5.setForeground(Color.BLACK);
        label_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel.add(label_5);
        textField_8 = new JTextField();
        centerPanel.add(textField_8);

        centerPanel.add(new JLabel(""));
        centerPanel.add(new JLabel(""));
        
                JLabel label_6 = new JLabel("Tìm kiếm", SwingConstants.RIGHT);
                label_6.setForeground(Color.BLACK);
                label_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
                centerPanel.add(label_6);
        textField_9 = new JTextField();
        centerPanel.add(textField_9);

        topPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setBackground(SystemColor.activeCaption);

        JButton btnNewButton = new JButton("Thêm");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 16));
        bottomPanel.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Xóa");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
        bottomPanel.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Tìm");
        btnNewButton_2.setForeground(Color.BLACK);
        btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        bottomPanel.add(btnNewButton_2);

        topPanel.add(bottomPanel, BorderLayout.SOUTH);

        panel_1.add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        table.setBackground(SystemColor.activeCaption);
        table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Mã nhân viên", "Tên nhân viên", "Chức Vụ", "SDT", "Giới Tính", "Email" }));
        scrollPane.setViewportView(table);

        model = (DefaultTableModel) table.getModel();



        
        JPanel panel_2 = new JPanel();
        panel_2.setBackground(SystemColor.inactiveCaption);
        tabbedPane.addTab("Phân quyền", null, panel_2, null);
        tabbedPane.setBackgroundAt(1, SystemColor.activeCaption);
        panel_2.setLayout(new BorderLayout());

        // Phần Bắc: Chứa label "Quản lí phân quyền"
        JLabel lblNewLabel = new JLabel("QUẢN LÍ PHÂN QUYỀN");
        lblNewLabel.setForeground(SystemColor.desktop);
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel_2.add(lblNewLabel, BorderLayout.NORTH);

        // Phần Trung tâm: Chứa label "Nhóm quyền", JComboBox và các JCheckBox
        JPanel centerPanel1 = new JPanel(new GridBagLayout());
        centerPanel1.setBackground(SystemColor.activeCaption);

        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.anchor = GridBagConstraints.WEST;
        gbcLabel.insets = new Insets(5, 10, 5, 10);
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;

        JLabel lblNewLabel_1 = new JLabel("Nhóm quyền");
        lblNewLabel_1.setForeground(SystemColor.desktop);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        centerPanel1.add(lblNewLabel_1, gbcLabel);

        GridBagConstraints gbcComboBox = new GridBagConstraints();
        gbcComboBox.anchor = GridBagConstraints.WEST;
        gbcComboBox.insets = new Insets(5, 10, 5, 10);
        gbcComboBox.gridx = 1;
        gbcComboBox.gridy = 0;

        JComboBox<String> comboBox = new JComboBox<>(new String[] {"Quản lý", "Nhân viên"});
        comboBox.setForeground(SystemColor.desktop);
        comboBox.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel1.add(comboBox, gbcComboBox);

        GridBagConstraints gbcCheckBox1 = new GridBagConstraints();
        gbcCheckBox1.anchor = GridBagConstraints.WEST;
        gbcCheckBox1.insets = new Insets(5, 10, 5, 10);
        gbcCheckBox1.gridx = 0;
        gbcCheckBox1.gridy = 1;

        JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Quản lí nhập hàng");
        chckbxNewCheckBox_2.setForeground(SystemColor.desktop);
        chckbxNewCheckBox_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel1.add(chckbxNewCheckBox_2, gbcCheckBox1);

        GridBagConstraints gbcCheckBox2 = new GridBagConstraints();
        gbcCheckBox2.anchor = GridBagConstraints.WEST;
        gbcCheckBox2.insets = new Insets(5, 10, 5, 10);
        gbcCheckBox2.gridx = 1;
        gbcCheckBox2.gridy = 1;

        JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Quản lí xuất hàng");
        chckbxNewCheckBox_3.setForeground(SystemColor.desktop);
        chckbxNewCheckBox_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel1.add(chckbxNewCheckBox_3, gbcCheckBox2);

        GridBagConstraints gbcCheckBox3 = new GridBagConstraints();
        gbcCheckBox3.anchor = GridBagConstraints.WEST;
        gbcCheckBox3.insets = new Insets(5, 10, 5, 10);
        gbcCheckBox3.gridx = 0;
        gbcCheckBox3.gridy = 2;

        JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Quản lí nhân viên");
        chckbxNewCheckBox_4.setForeground(SystemColor.desktop);
        chckbxNewCheckBox_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel1.add(chckbxNewCheckBox_4, gbcCheckBox3);

        GridBagConstraints gbcCheckBox4 = new GridBagConstraints();
        gbcCheckBox4.anchor = GridBagConstraints.WEST;
        gbcCheckBox4.insets = new Insets(5, 10, 5, 10);
        gbcCheckBox4.gridx = 1;
        gbcCheckBox4.gridy = 2;

        JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Quản lí tồn kho");
        chckbxNewCheckBox_5.setForeground(SystemColor.desktop);
        chckbxNewCheckBox_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel1.add(chckbxNewCheckBox_5, gbcCheckBox4);

        GridBagConstraints gbcCheckBox5 = new GridBagConstraints();
        gbcCheckBox5.anchor = GridBagConstraints.WEST;
        gbcCheckBox5.insets = new Insets(5, 10, 5, 10);
        gbcCheckBox5.gridx = 0;
        gbcCheckBox5.gridy = 3;
        gbcCheckBox5.gridwidth = 2;

        JCheckBox chckbxNewCheckBox_6 = new JCheckBox("Quản lí thống kê");
        chckbxNewCheckBox_6.setForeground(SystemColor.desktop);
        chckbxNewCheckBox_6.setFont(new Font("Times New Roman", Font.BOLD, 16));
        centerPanel1.add(chckbxNewCheckBox_6, gbcCheckBox5);

        panel_2.add(centerPanel1, BorderLayout.CENTER);
        
     // Sau khi bạn đã tạo comboBox và tất cả các JCheckBox

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) comboBox.getSelectedItem();
                boolean isManager = "Quản lý".equals(selectedRole);

                chckbxNewCheckBox_2.setSelected(true);  // Quản lí nhập hàng
                chckbxNewCheckBox_3.setSelected(true);  // Quản lí xuất hàng
                chckbxNewCheckBox_4.setSelected(isManager);  // Quản lí nhân viên
                chckbxNewCheckBox_5.setSelected(isManager);  // Quản lí tồn kho
                chckbxNewCheckBox_6.setSelected(isManager);  // Quản lí thống kê
            }
        });

        // Thiết lập trạng thái ban đầu
        comboBox.setSelectedIndex(0);

        // Phần Nam: Chứa 2 nút "Sửa quyền" và "Lưu quyền"
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(SystemColor.activeCaption);
        JButton btnNewButton_4 = new JButton("Sửa quyền");
        btnNewButton_4.setForeground(SystemColor.desktop);
        btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        southPanel.add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("Lưu quyền");
        btnNewButton_5.setForeground(SystemColor.desktop);
        btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
        southPanel.add(btnNewButton_5);

        panel_2.add(southPanel, BorderLayout.SOUTH);
        
       

        // Kết nối và lấy dữ liệu từ cơ sở dữ liệu
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // Thiết lập kết nối với cơ sở dữ liệu MySQL
//            String url = "jdbc:mysql://localhost:3306/Nhanvien";
//            String user = "root";
//            String password = "thuan12345";
//            
//            DriverManager.registerDriver(new Driver());
//            conn = DriverManager.getConnection(url, user, password);
//
//            // Query để lấy dữ liệu từ bảng nhân viên (ví dụ)
//            String sql = "SELECT * FROM Hg";
//            stmt = conn.prepareStatement(sql);
//            rs = stmt.executeQuery();
//
//            // Duyệt ResultSet và thêm từng dòng vào model của JTable
//            while (rs.next()) {
//                Object[] row = new Object[] {
//                    rs.getString("manhanvien"),
//                    rs.getString("tennhanvien"),
//                    rs.getString("chucvu"),
//                    rs.getString("sodienthoai"),
//                    rs.getString("gioitinh"),
//                    rs.getString("email")
//                };
//                model.addRow(row);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            // Đóng các đối tượng ResultSet, PreparedStatement và Connection
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        
//     // Thông tin kết nối đến MySQL
//        String url = "jdbc:mysql://localhost:3306/Nhanvien";
//        String user = "root";
//        String password = "thuan12345";
//
//     // Sự kiện cho nút "Thêm"
//        btnNewButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Lấy dữ liệu từ các trường
//                String maNV = textField_4.getText();
//                String tenNV = textField_6.getText();
//                String chucVu = textField_5.getText();
//                String soDienThoai = textField_7.getText();
//                String gioiTinh = (chckbxNewCheckBox.isSelected()) ? "Nam" : "Nữ";
//                String email = textField_8.getText();
//                
//                // Thêm vào bảng (JTable)
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                model.addRow(new Object[]{maNV, tenNV, chucVu, soDienThoai, gioiTinh, email});
//                
//                // Lưu vào cơ sở dữ liệu
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//                try {
//                    // Kết nối đến cơ sở dữ liệu
//                    conn = DriverManager.getConnection(url, user, password);
//                    
//                    // Câu lệnh SQL để thêm dữ liệu vào bảng nhân viên
//                    String sql = "INSERT INTO Hg (manhanvien, tennhanvien, chucvu, sodienthoai, gioitinh, email) VALUES (?, ?, ?, ?, ?, ?)";
//                    
//                    // Tạo đối tượng PreparedStatement để thực thi câu lệnh SQL
//                    pstmt = conn.prepareStatement(sql);
//                    pstmt.setString(1, maNV);
//                    pstmt.setString(2, tenNV);
//                    pstmt.setString(3, chucVu);
//                    pstmt.setString(4, soDienThoai);
//                    pstmt.setString(5, gioiTinh);
//                    pstmt.setString(6, email);
//                    
//                    // Thực thi câu lệnh
//                    pstmt.executeUpdate();
//                    
//                    // Thông báo thành công (nếu cần)
//                    JOptionPane.showMessageDialog(QuanTriView.this, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    // Xử lý các lỗi kết nối hoặc thao tác với cơ sở dữ liệu
//                } finally {
//                    // Đóng các đối tượng PreparedStatement và Connection sau khi sử dụng
//                    try {
//                        if (pstmt != null) pstmt.close();
//                        if (conn != null) conn.close();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//                
//                // Xóa nội dung các trường sau khi thêm thành công
//                textField_4.setText("");
//                textField_5.setText("");
//                textField_6.setText("");
//                textField_7.setText("");
//                textField_8.setText("");
//                chckbxNewCheckBox.setSelected(false);
//                chckbxNewCheckBox_1.setSelected(false);
//            }
//        });
//        
//        btnNewButton_1.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = table.getSelectedRow();
//                
//                if (selectedRow == -1) {
//                    return;
//                }
//                
//                String maNV = (String) table.getValueAt(selectedRow, 0);
//                
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                model.removeRow(selectedRow);
//                
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//                try {
//                    conn = DriverManager.getConnection(url, user, password);
//                    
//                    String sql = "DELETE FROM Hg WHERE manhanvien = ?";
//                    
//                    pstmt = conn.prepareStatement(sql);
//                    pstmt.setString(1, maNV);
//                    
//                    int rowsAffected = pstmt.executeUpdate();
//                    
//                    if (rowsAffected > 0) {
//                        // Hiển thị hộp thoại thông báo khi xóa thành công
//                        JOptionPane.showMessageDialog(QuanTriView.this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        JOptionPane.showMessageDialog(QuanTriView.this, "Không thể xóa nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    }
//                    
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(QuanTriView.this, "Lỗi khi xóa nhân viên: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//                } finally {
//                    try {
//                        if (pstmt != null) pstmt.close();
//                        if (conn != null) conn.close();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });
//        
//     // Sự kiện cho nút "Tìm"
//        btnNewButton_2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Lấy mã nhân viên từ textField_9
//                String maNV = textField_9.getText().trim(); // trim() để loại bỏ khoảng trắng đầu cuối
//                
//                // Xóa hết dữ liệu trong bảng (table)
//                DefaultTableModel model = (DefaultTableModel) table.getModel();
//                model.setRowCount(0);
//                
//                // Kết nối đến cơ sở dữ liệu
//                Connection conn = null;
//                PreparedStatement pstmt = null;
//                try {
//                    // Thiết lập kết nối đến cơ sở dữ liệu
//                    conn = DriverManager.getConnection(url, user, password);
//                    
//                    // Câu lệnh SQL để tìm kiếm nhân viên theo mã nhân viên
//                    String sql = "SELECT * FROM Hg WHERE manhanvien = ?";
//                    
//                    // Tạo đối tượng PreparedStatement để thực thi câu lệnh SQL
//                    pstmt = conn.prepareStatement(sql);
//                    pstmt.setString(1, maNV);
//                    
//                    // Thực thi câu lệnh
//                    ResultSet rs = pstmt.executeQuery();
//                    
//                    // Kiểm tra xem có kết quả trả về hay không
//                    if (!rs.isBeforeFirst()) { // Nếu không có kết quả (rs không trỏ đến dòng trước đầu tiên)
//                        // Hiển thị hộp thoại thông báo khi không tìm thấy nhân viên
//                        JOptionPane.showMessageDialog(contentPane, "Không tìm thấy nhân viên có mã " + maNV, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//                    } else {
//                        // Hiển thị thông tin nhân viên lên bảng
//                        while (rs.next()) {
//                            String maNhanVien = rs.getString("manhanvien");
//                            String tenNhanVien = rs.getString("tennhanvien");
//                            String chucVu = rs.getString("chucvu");
//                            String soDienThoai = rs.getString("sodienthoai");
//                            String gioiTinh = rs.getString("gioitinh");
//                            String email = rs.getString("email");
//                            
//                            // Thêm thông tin vào bảng (table)
//                            model.addRow(new Object[]{maNhanVien, tenNhanVien, chucVu, soDienThoai, gioiTinh, email});
//                        }
//                    }
//                    
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                    // Xử lý các lỗi kết nối hoặc thực thi câu lệnh SQL
//                } finally {
//                    // Đóng các đối tượng PreparedStatement và Connection sau khi sử dụng
//                    try {
//                        if (pstmt != null) pstmt.close();
//                        if (conn != null) conn.close();
//                    } catch (SQLException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        });

       
       
        
        

     // Tạo một danh sách để lưu trữ các JCheckBox
        checkBoxes = Arrays.asList(
        	    chckbxNewCheckBox_2, chckbxNewCheckBox_3, chckbxNewCheckBox_4, 
        	    chckbxNewCheckBox_5, chckbxNewCheckBox_6
        	);
        rolePermissions.put("Quản lý", new boolean[checkBoxes.size()]);
        rolePermissions.put("Nhân viên", new boolean[checkBoxes.size()]);
     // Khởi tạo quyền mặc định cho mỗi vai trò
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedRole = (String) e.getItem();
                    boolean[] permissions = rolePermissions.get(selectedRole);
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        checkBoxes.get(i).setSelected(permissions[i]);
                    }
                }
            }
        });
        
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) comboBox.getSelectedItem();
                boolean[] permissions = rolePermissions.get(selectedRole);
                
                boolean anySelected = false;
                for (int i = 0; i < checkBoxes.size(); i++) {
                    permissions[i] = checkBoxes.get(i).isSelected();
                    if (permissions[i]) {
                        anySelected = true;
                    }
                }

                if (anySelected) {
                    JOptionPane.showMessageDialog(QuanTriView.this, 
                        "Lưu quyền thành công cho " + selectedRole, 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(QuanTriView.this, 
                        "Vui lòng chọn quyền để lưu", 
                        "Cảnh báo", 
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        });  
        
        checkBoxes = Arrays.asList(
                chckbxNewCheckBox_2, chckbxNewCheckBox_3, chckbxNewCheckBox_4, 
                chckbxNewCheckBox_5, chckbxNewCheckBox_6
            );
       
        rolePermissions.put("Quản lý", new boolean[checkBoxes.size()]);
        rolePermissions.put("Nhân viên", new boolean[checkBoxes.size()]);
        // Khởi tạo tên quyền ban đầu
        String[] initialNames = new String[checkBoxes.size()];
        for (int i = 0; i < checkBoxes.size(); i++) {
            initialNames[i] = checkBoxes.get(i).getText();
        }
        rolePermissionNames.put("Quản lý", initialNames.clone());
        rolePermissionNames.put("Nhân viên", initialNames.clone());

      

        // ... code cho comboBox và btnNewButton_5 (Lưu quyền) như trước

        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) comboBox.getSelectedItem();
                boolean[] permissions = rolePermissions.get(selectedRole);
                String[] names = rolePermissionNames.get(selectedRole);

                for (int i = 0; i < checkBoxes.size(); i++) {
                    if (checkBoxes.get(i).isSelected()) {
                        String currentName = names[i];
                        String input = JOptionPane.showInputDialog(QuanTriView.this,
                            "Nhập tên mới cho quyền " + currentName + ":",
                            currentName);
                        
                        if (input != null && !input.trim().isEmpty()) {
                            names[i] = input.trim();
                            permissions[i] = true;
                            checkBoxes.get(i).setText(input.trim());
                        }
                    }
                }

                // Cập nhật trạng thái checkbox sau khi sửa
                for (int i = 0; i < checkBoxes.size(); i++) {
                    checkBoxes.get(i).setSelected(permissions[i]);
                }

                JOptionPane.showMessageDialog(QuanTriView.this,
                    "Đã cập nhật quyền cho " + selectedRole,
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Thêm ItemListener cho comboBox để cập nhật tên quyền khi chuyển đổi vai trò
        comboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedRole = (String) e.getItem();
                    boolean[] permissions = rolePermissions.get(selectedRole);
                    String[] names = rolePermissionNames.get(selectedRole);
                    for (int i = 0; i < checkBoxes.size(); i++) {
                        checkBoxes.get(i).setSelected(permissions[i]);
                        checkBoxes.get(i).setText(names[i]);
                    }
                }
            }
        });
//        setVisible(true);
//        
    }
}