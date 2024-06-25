package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.Map;

import javax.swing.BorderFactory;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.border.EmptyBorder;

//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.DefaultCategoryDataset;
//
//import controller.baoCaoChiTietController;
//import controller.thongKeTongQuatController;


import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.NoRouteToHostException;


public class baoCaoView {

    public JFrame frame;
	private JTable tableTKTQ;
	private JTable tableBCCT;
	private JComboBox<String> comboBoxTKTQ;
	private JComboBox<String> comboBoxBCCT;
	private DefaultTableModel tableModelTKTQ;
	private DefaultTableModel tableModelBCCT;
	private Map<String, Object[][]> dataMap;
//	private Map< String,Object[][]> dataMapBCCT;
	private Button buttonDT; 
	private Button buttonTK;
//	private ChartPanel chartPanelBCCT;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    baoCaoView window = new baoCaoView();
//                    thongKeTongQuatController controller = new thongKeTongQuatController(window);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
	protected void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}


	/**
     * Create the application.
     */
    public baoCaoView() {
        initialize();

    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
    	
    	

        frame = new JFrame();
        frame.setBounds(100, 100, 701, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
//        frame.setVisible(true);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tabbedPane.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        RoundedPanel jPanelMenu = new RoundedPanel(50, Color.WHITE);
        jPanelMenu.setLayout(new BorderLayout(0, 0));
//        frame.getContentPane().add(jPanelMenu, BorderLayout.WEST);


        JLabel labelHouse = new JLabel("");
        labelHouse.setHorizontalAlignment(SwingConstants.CENTER);
//        Image img = new ImageIcon(this.getClass().getResource("/House.png")).getImage();
//        labelHouse.setIcon(new ImageIcon(img));
        jPanelMenu.add(labelHouse);
        
        JPanel panel = new JPanel();
		panel.setOpaque(false);

		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		
		JLabel labelDH = new JLabel("Đơn Hàng");
		labelDH.setHorizontalAlignment(SwingConstants.CENTER);
		labelDH.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		Image imgDH = new ImageIcon(this.getClass().getResource("/icon_DonHang.png")).getImage();
//		Image sacledImgDH = imgDH.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//		labelDH.setIcon(new ImageIcon(sacledImgDH));
		labelDH.setBounds(29, 88, 140, 50);
		panel.add(labelDH);
		
		labelDH.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		JLabel labelTK = new JLabel("Tồn Kho");
		labelTK.setHorizontalAlignment(SwingConstants.CENTER);
		labelTK.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		Image imgTK = new ImageIcon(this.getClass().getResource("/icon_TonKho.png")).getImage();
//		Image sacledImgTK = imgTK.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//		labelTK.setIcon(new ImageIcon(sacledImgTK));
		labelTK.setBounds(29, 149, 140, 50);
		panel.add(labelTK);
		
		labelTK.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel labelNX = new JLabel("Nhập/Xuất");
		labelNX.setHorizontalAlignment(SwingConstants.CENTER);
		labelNX.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		Image imgNX = new ImageIcon(this.getClass().getResource("/icon_NhapXuat.png")).getImage();
//		Image sacledImgNX = imgNX.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//		labelNX.setIcon(new ImageIcon(sacledImgNX));
		labelNX.setBounds(29, 197, 140, 50);
		panel.add(labelNX);
		
		labelNX.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel labelVT = new JLabel("Vị Trí Kho");
		labelVT.setHorizontalAlignment(SwingConstants.CENTER);
		labelVT.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		Image imgVT = new ImageIcon(this.getClass().getResource("/icon_ViTri.png")).getImage();
//		Image sacledImgVT = imgVT.getScaledInstance(40, 36, Image.SCALE_SMOOTH);
//		labelVT.setIcon(new ImageIcon(sacledImgVT));
		labelVT.setBounds(29, 258, 140, 50);
		panel.add(labelVT);
		
		labelVT.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel labelQT = new JLabel("Quản Trị");
		labelQT.setHorizontalAlignment(SwingConstants.CENTER);
		labelQT.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		Image imgQT = new ImageIcon(this.getClass().getResource("/icon_QuanTri.png")).getImage();
//		Image sacledImgQT = imgQT.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//		labelQT.setIcon(new ImageIcon(sacledImgQT));
		labelQT.setBounds(29, 319, 140, 50);
		panel.add(labelQT);
		
		labelQT.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		JLabel labelTKe = new JLabel("Thống kê");
		labelTKe.setHorizontalAlignment(SwingConstants.CENTER);
		labelTKe.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		Image imgTKe = new ImageIcon(this.getClass().getResource("/icon_ThongKe.png")).getImage();
//		Image sacledImgTKe = imgTKe.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
//		labelTKe.setIcon(new ImageIcon(sacledImgTKe));
		labelTKe.setBounds(29, 376, 140, 50);
		panel.add(labelTKe);
		
		labelTKe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		jPanelMenu.add(labelHouse, BorderLayout.NORTH);
		jPanelMenu.add(panel, BorderLayout.CENTER);

        JPanel thongKeTongQuatPanel = new JPanel(new BorderLayout());
        thongKeTongQuatPanel.setBackground(new Color(245, 255, 255));


        JPanel labelPanel = new JPanel();
   
        labelPanel.setBackground(new Color(255, 255, 255));
        labelPanel.setLayout(new GridLayout(2, 2, 20, 20)); 

       
        Button buttonKH = new Button("Khách hàng: " + 132);
        buttonKH.setPreferredSize(new Dimension(200, 100)); 
        buttonKH.setForeground(new Color(255, 255, 255));
        buttonKH.setBackground(new Color(0, 128, 192));
        buttonKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelPanel.add(buttonKH);

        Button buttonNV = new Button("Nhân viên: " + 44);
        buttonNV.setPreferredSize(new Dimension(200, 100));
        buttonNV.setForeground(new Color(255, 255, 255));
        buttonNV.setBackground(new Color(0, 128, 192));
        buttonNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelPanel.add(buttonNV);

        buttonDT = new Button("Doanh thu:");
        buttonDT.setPreferredSize(new Dimension(200, 100)); 
        buttonDT.setForeground(new Color(255, 255, 255));
        buttonDT.setBackground(new Color(0, 128, 192));
        buttonDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
        buttonDT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                thongKeTongQuatController controller = new thongKeTongQuatController(baoCaoView.this);
//                controller.updateTotalRevenue();
            }
        });
        labelPanel.add(buttonDT);

        buttonTK = new Button("Tồn Kho:");
        buttonTK.setPreferredSize(new Dimension(200, 100)); 
        buttonTK.setForeground(new Color(255, 255, 255));
        buttonTK.setBackground(new Color(0, 128, 192));
        buttonTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
        buttonTK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                thongKeTongQuatController controller = new thongKeTongQuatController(baoCaoView.this);
//                controller.updateTonKho();
            }
        });
        labelPanel.add(buttonTK);

       
        thongKeTongQuatPanel.add(labelPanel, BorderLayout.NORTH);

        
        //bb la jpanel gom combobox + table
        JPanel bb =new JPanel(new BorderLayout());
       
        //combobox NAM cho Thong Ke Tong Quat
        comboBoxTKTQ = new JComboBox<>();
        comboBoxTKTQ.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBoxTKTQ.setModel(new DefaultComboBoxModel<>(new String[] {"2022", "2023", "2024"}));
        comboBoxTKTQ.setSelectedIndex(0); 
        comboBoxTKTQ.setToolTipText("");
        comboBoxTKTQ.setBounds(205, 208, 160, 30);
        bb.add(comboBoxTKTQ,BorderLayout.NORTH);
        //thongKeTongQuatPanel.add(comboBoxTKTQ);
        
        String[] columnNamesTKTQ = {"Quý", "Quý 1", "Quý 2", "Quý 3", "Quý 4",};
      
        tableModelTKTQ = new DefaultTableModel(new Object[][]{}, columnNamesTKTQ);
        tableTKTQ = new JTable(tableModelTKTQ);
        tableTKTQ.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tableTKTQ.setBackground(new Color(255, 255, 255));
        tableTKTQ.setForeground(new Color(0, 0, 0));
        JScrollPane scrollPaneTKTQ = new JScrollPane(tableTKTQ);
        scrollPaneTKTQ.setBounds(0, 239, 560, 427);
        bb.add(scrollPaneTKTQ,BorderLayout.CENTER);
        
       // thongKeTongQuatPanel.add(scrollPaneTKTQ);
        
        thongKeTongQuatPanel.add(bb,BorderLayout.CENTER);
        
        

     

        comboBoxTKTQ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = (String) comboBoxTKTQ.getSelectedItem();
//                thongKeTongQuatController controllerTKTQ = new thongKeTongQuatController(baoCaoView.this);
//                controllerTKTQ.updateTableDataTKTQ(selectedYear);
               
            }
        });

        JPanel baoCaoChiTietPanel = new JPanel(new BorderLayout());
        
        //bc chua combobox vs scrollpane
        JPanel bc = new JPanel(new BorderLayout());
        
        String[] columnNamesBCCT = {"Tháng", "Chi Phí", "Doanh Thu", "Lợi Nhuận"};
        tableModelBCCT = new DefaultTableModel(new Object[][]{}, columnNamesBCCT);
        tableBCCT = new JTable(tableModelBCCT);
        tableBCCT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tableBCCT.setBackground(new Color(255, 255, 255));
        tableBCCT.setForeground(new Color(0, 0, 0));
        JScrollPane scrollPaneBCCT = new JScrollPane(tableBCCT);
       
        bc.add(scrollPaneBCCT,BorderLayout.CENTER);
        
        //combobox BCCT 
        
        comboBoxBCCT = new JComboBox<>();
        comboBoxBCCT.setBounds(226, 322, 87, 23);
       
        comboBoxBCCT.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBoxBCCT.setModel(new DefaultComboBoxModel<>(new String[] {"2022", "2023", "2024"}));
        comboBoxBCCT.setSelectedIndex(0); 
        comboBoxBCCT.setToolTipText("");  
        bc.add(comboBoxBCCT,BorderLayout.NORTH);
        baoCaoChiTietPanel.add(bc,BorderLayout.CENTER);
        comboBoxBCCT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = (String) comboBoxBCCT.getSelectedItem();
//                baoCaoChiTietController controllerBCCT = new baoCaoChiTietController(baoCaoView.this);
//                controllerBCCT.updateTableDataBCCT(selectedYear);
//                controllerBCCT.updateChart(selectedYear);
            }
        });

        // tao chart
//        chartPanelBCCT = new ChartPanel(null);
//        chartPanelBCCT.setPreferredSize(new Dimension(560, 367));
//        baoCaoChiTietPanel.add(chartPanelBCCT,BorderLayout.NORTH);
        

        tabbedPane.addTab("Thống kê tổng quát", thongKeTongQuatPanel);
        tabbedPane.addTab("Báo cáo chi tiết", baoCaoChiTietPanel);
        
      
    }

    public Map<String, Object[][]> getDataMap() {
        return dataMap;
    }
    

    
    public void addComboBoxListenerTKTQ(ActionListener listener) {
        comboBoxTKTQ.addActionListener(listener);
    }
    public void updateTableTKTQ(Object[][] newData) {
        String[] columnNames = {"Quý", "Quý 1", "Quý 2", "Quý 3", "Quý 4"};
        tableModelTKTQ.setDataVector(newData, columnNames);
    }
    
    public void addComboBoxListenerBCCT(ActionListener listener) {
        comboBoxBCCT.addActionListener(listener);
    }
   
    public void updateTableBCCT(Object[][] newData) {
        String[] columnNames = {"Tháng", "Chi Phí", "Doanh Thu", "Lợi Nhuận"};
        tableModelBCCT.setDataVector(newData, columnNames);
    }
    
    public void updateTotalRevenue(String formattedTotalRevenue) {
        buttonDT.setLabel("Doanh thu: " + formattedTotalRevenue);
    }
    public void updateTonKho(int tongTonKho) {
        buttonTK.setLabel("Tồn Kho: " + tongTonKho);
    }
    
//    public void updateChartBCCT(DefaultCategoryDataset dataset) {
//        JFreeChart barChart = ChartFactory.createBarChart(
//            "Báo cáo chi tiết",
//            "Tháng",
//            "Giá trị",
//            dataset,
//            PlotOrientation.VERTICAL,
//            true, true, false
//        );
//        chartPanelBCCT.setChart(barChart);
//    }
//   
    // Phương thức để lấy content pane
    public Container getContentPanel() {
        return frame.getContentPane();
    }
}
   