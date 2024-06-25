package view;
import javax.swing.*;



import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox rememberMeCheckBox;

    public LoginView() {
    	getContentPane().setBackground(SystemColor.controlShadow);
        setTitle("Đăng nhập");
        setBounds(100, 100, 450, 373);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        // Thêm ảnh
        JLabel imageLabel = new JLabel("");
        imageLabel.setBounds(157, 0, 100, 100);
        ImageIcon icon = new ImageIcon("/img/House.png"); // Thay đổi đường dẫn đến ảnh của bạn
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        getContentPane().add(imageLabel);

        // Label "Đăng nhập"
        JLabel loginLabel = new JLabel("ĐĂNG NHẬP");
        loginLabel.setForeground(new Color(0, 0, 0));
        loginLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setBounds(65, 104, 288, 25);
        getContentPane().add(loginLabel);

        // Tên đăng nhập
        JLabel lblUsername = new JLabel("Tên đăng nhập");
        lblUsername.setForeground(new Color(0, 0, 0));
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblUsername.setBounds(120, 139, 100, 14);
        getContentPane().add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(120, 163, 151, 20);
        getContentPane().add(usernameField);
        usernameField.setColumns(10);

        // Mật khẩu
        JLabel lblPassword = new JLabel("Mật khẩu");
        lblPassword.setForeground(new Color(0, 0, 0));
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
        lblPassword.setBounds(120, 193, 100, 14);
        getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 212, 151, 20);
        getContentPane().add(passwordField);

//         Checkbox "Ghi nhớ đăng nhập"
        rememberMeCheckBox = new JCheckBox("Ghi nhớ đăng nhập");
        rememberMeCheckBox.setForeground(new Color(0, 0, 0));
        rememberMeCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
        rememberMeCheckBox.setBounds(120, 248, 155, 23);
        getContentPane().add(rememberMeCheckBox);

        // Nút đăng nhập
        loginButton = new JButton("ĐĂNG NHẬP");
        loginButton.setForeground(new Color(0, 0, 0));
        loginButton.setBackground(Color.ORANGE);
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
        loginButton.setBounds(142, 282, 133, 23);
        getContentPane().add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean rememberMe = rememberMeCheckBox.isSelected();

                if (authenticateUser(username, password)) {
                    JOptionPane.showMessageDialog(LoginView.this, "Đăng nhập thành công!");
                    if (rememberMe) {
                        // Lưu thông tin đăng nhập (trong ứng dụng thực tế, bạn nên mã hóa thông tin này)
                        // Ví dụ: saveLoginInfo(username, password);
                    }
                    openMainGUI();
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Đăng nhập thất bại. Vui lòng thử lại.");
                }
            }
        });
    }

    private boolean authenticateUser(String username, String password) {
        // Thực hiện xác thực với cơ sở dữ liệu ở đây
        // Đây chỉ là một ví dụ đơn giản, bạn nên thay thế bằng logic xác thực thực tế
        return username.equals("admin") && password.equals("password");
    }

    private void openMainGUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DonHangNhapView frame = new DonHangNhapView();
                    frame.donNhapButton.setBackground(Color.LIGHT_GRAY);
                    frame.donNhapButton.setOpaque(true);
                    frame.lastSelectedButton = frame.donNhapButton;
                    OrderForm frameOD = new OrderForm();
                  
                    frame.setVisible(true);
                    LoginView.this.dispose(); // Đóng cửa sổ đăng nhập
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}