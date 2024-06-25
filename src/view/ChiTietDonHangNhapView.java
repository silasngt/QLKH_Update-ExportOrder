package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ChiTietDonHangNhapView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private String orderId;
    private JTable orderTable;
    private int selectedRow;
	private DonHangNhapView dhnview;
	private JTextField idChiTietOrderField;
	private JTextField idKhoField;
	private JTextField idProductField;
	private JTextField productNameField;
	private JTextField quantityField;
	private JTextField costField;
	private DefaultTableModel modelChiTietDN;
	private JTable tableChiTietNhap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					ChiTietDonHangNhapView frame = new ChiTietDonHangNhapView("N3");
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChiTietDonHangNhapView(String orderId, JTable orderTable, int selectedRow) {
        this.orderId = orderId;
        this.orderTable = orderTable;
        this.selectedRow = selectedRow;
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(750, 450);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel panelTop = new JPanel(new BorderLayout());
		
        JPanel panelNhap = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần


        // Mã đơn hàng
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelNhap.add(new JLabel("Mã chi tiết đơn hàng:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Để các JTextField mở rộng theo chiều ngang
        idChiTietOrderField = new JTextField(15);
        idChiTietOrderField.setEditable(false); // Đặt thành chỉ đọc
        idChiTietOrderField.setBackground(Color.LIGHT_GRAY); // Tùy chọn: thay đổi nền để chỉ ra rằng nó là trường chỉ đọc
        panelNhap.add(idChiTietOrderField, gbc);
        
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        panelNhap.add(new JLabel("Mã kho:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        idKhoField = new JTextField(15);
        panelNhap.add(idKhoField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelNhap.add(new JLabel("Mã sản phẩm:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        idProductField = new JTextField(15);
        panelNhap.add(idProductField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        panelNhap.add(new JLabel("Tên sản phẩm: "), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        productNameField = new JTextField(15);
        panelNhap.add(productNameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelNhap.add(new JLabel("Số lượng:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        quantityField = new JTextField(15);
        panelNhap.add(quantityField, gbc);
		
        gbc.gridx = 2;
        gbc.gridy = 2;
        panelNhap.add(new JLabel("Đơn giá:"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        costField = new JTextField(15);
        
        panelNhap.add(costField, gbc);
        
        // Tạo panel cho các button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        
        JButton addProductButton = new JButton("Thêm" );
        JButton confirmButton = new JButton("Xác nhận");
        
        buttonPanel.add(addProductButton);
        buttonPanel.add(confirmButton);
        
        panelTop.add(buttonPanel, BorderLayout.SOUTH);
        panelTop.add(panelNhap, BorderLayout.CENTER);
        
        // Tạo model và table
        modelChiTietDN = new DefaultTableModel();
        modelChiTietDN.addColumn("Mã sản phẩm");
        modelChiTietDN.addColumn("Tên sản phẩm");
        modelChiTietDN.addColumn("Mã kho");
        modelChiTietDN.addColumn("Số lượng");
        modelChiTietDN.addColumn("Đơn giá");

        tableChiTietNhap = new JTable(modelChiTietDN);

        // Tạo JScrollPane và thêm bảng vào đó
        JScrollPane tableScrollPane = new JScrollPane(tableChiTietNhap);

        // Thêm buttonPanel vào phía trên và tableScrollPane vào giữa của mainPanel
  
        contentPane.add(panelTop, BorderLayout.NORTH);
        contentPane.add(tableScrollPane, BorderLayout.CENTER);
        
		setContentPane(contentPane);
		
		
    	// Đặt mã chi tiết đơn hàng
    	setOrderDetailCode(orderId);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Add action listener for the "Thêm" button
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get data from fields
                String idProduct = idProductField.getText();
                String productName = productNameField.getText();
                String idKho = idKhoField.getText();
                String quantity = quantityField.getText();
                String cost = costField.getText();

                // Validate data (optional, you can add more checks)
                if (idProduct.isEmpty() || productName.isEmpty() || idKho.isEmpty() || quantity.isEmpty() || cost.isEmpty()) {
                    JOptionPane.showMessageDialog(ChiTietDonHangNhapView.this, "Please fill in all fields.");
                    return;
                }

                // Add data to the table model
                modelChiTietDN.addRow(new Object[]{idProduct, productName, idKho, quantity, cost});

                // Clear fields for new input
                idProductField.setText("");
                productNameField.setText("");
                idKhoField.setText("");
                quantityField.setText("");
                costField.setText("");
            }
        });
        
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmTotal();
            }
        });
	}

	private void setOrderDetailCode(String idOrder) {
	    // Đặt mã chi tiết đơn hàng dựa vào mã đơn hàng
	    // Ví dụ: mã chi tiết đơn hàng = "1" nếu orderCode là "N1", "2" nếu orderCode là "N2", ...
	    String orderDetailCode = idOrder.replace("N", "");
	    idChiTietOrderField.setText(orderDetailCode);
	}
	
    private void confirmTotal() {
        try {
            double totalAmount = 0.0;
            for (int i = 0; i < modelChiTietDN.getRowCount(); i++) {
                int quantity = Integer.parseInt(modelChiTietDN.getValueAt(i, 3).toString());
                double price = Double.parseDouble(modelChiTietDN.getValueAt(i, 4).toString());
                totalAmount += quantity * price;
            }

            // Cập nhật tổng số tiền vào hàng đã chọn
            if (selectedRow != -1) {
                orderTable.setValueAt(totalAmount, selectedRow, 3); // Cập nhật cột tổng tiền
            }
            // Đóng cửa sổ chi tiết đơn hàng
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi tính tổng số tiền.");
        }
    }
    
    public Object[][] getProductData() {
        DefaultTableModel model = (DefaultTableModel) tableChiTietNhap.getModel();
        Object[][] data = new Object[model.getRowCount()][5]; // Số cột là 5 tương ứng với số cột trong bảng ChiTietDonHangView
        for (int row = 0; row < model.getRowCount(); row++) {
            for (int col = 0; col < 5; col++) { // Lặp qua từng cột
                data[row][col] = model.getValueAt(row, col);

            }
        }

        return data;
    }

}
