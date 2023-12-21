package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Admin;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static Admin admin;
	private JTextField fld_brand;
	private JTextField fld_gear;
	private JTextField fld_fuel;
	private JTextField fld_capacity;
	private JTextField fld_deposit;
	private JTextField fld_rental_fee;
	private JTable table_cars;
	private DefaultTableModel carModel = null;
	private Object[] carData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI(admin);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminGUI(Admin admin) throws SQLException {
		
		carModel = new DefaultTableModel();
		Object[] colCarName = new Object[6];
		colCarName[0] = "ARABA";
		colCarName[1] = "VİTES";
		colCarName[2] = "YAKIT";
		colCarName[3] = "KAPASİTE";
		colCarName[4] = "DEPOZİTO";
		colCarName[5] = "KİRALAMA ÜCRETİ";
		carModel.setColumnIdentifiers(colCarName);
		carData = new Object[6];
		for(int i = 0; i < admin.getCarList().size(); i++) {
			carData[0] = admin.getCarList().get(i).getBrand();
			carData[1] = admin.getCarList().get(i).getGear();
			carData[2] = admin.getCarList().get(i).getFuel();
			carData[3] = admin.getCarList().get(i).getCapacity();
			carData[4] = admin.getCarList().get(i).getDeposit();
			carData[5] = admin.getCarList().get(i).getRental_fee();
			carModel.addRow(carData);
		}
		
		setBackground(new Color(255, 204, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 574);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Merhaba, Sayın " + admin.getName());
		lblNewLabel.setBounds(30, 30, 195, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(30, 58, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lbl_admin_logo = new JLabel(new ImageIcon(getClass().getResource("projelogo.jpeg")));
		lbl_admin_logo.setBounds(521, 6, 210, 147);
		contentPane.add(lbl_admin_logo);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(6, 124, 778, 416);
		contentPane.add(w_tab);
		
		JPanel w_admin = new JPanel();
		w_tab.addTab("Araç Yönetimi", null, w_admin, null);
		w_admin.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Marka Model:");
		lblNewLabel_1.setBounds(530, 6, 95, 16);
		w_admin.add(lblNewLabel_1);
		
		fld_brand = new JTextField();
		fld_brand.setBounds(530, 23, 221, 24);
		w_admin.add(fld_brand);
		fld_brand.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vites:");
		lblNewLabel_1_1.setBounds(530, 45, 95, 16);
		w_admin.add(lblNewLabel_1_1);
		
		fld_gear = new JTextField();
		fld_gear.setColumns(10);
		fld_gear.setBounds(530, 59, 221, 24);
		w_admin.add(fld_gear);
		
		JLabel lblNewLabel_1_2 = new JLabel("Yakıt:");
		lblNewLabel_1_2.setBounds(530, 84, 95, 16);
		w_admin.add(lblNewLabel_1_2);
		
		fld_fuel = new JTextField();
		fld_fuel.setColumns(10);
		fld_fuel.setBounds(530, 99, 221, 24);
		w_admin.add(fld_fuel);
		
		JLabel lblNewLabel_1_3 = new JLabel("Kapasite:");
		lblNewLabel_1_3.setBounds(530, 124, 95, 16);
		w_admin.add(lblNewLabel_1_3);
		
		fld_capacity = new JTextField();
		fld_capacity.setColumns(10);
		fld_capacity.setBounds(530, 141, 221, 24);
		w_admin.add(fld_capacity);
		
		fld_deposit = new JTextField();
		fld_deposit.setColumns(10);
		fld_deposit.setBounds(530, 183, 221, 24);
		w_admin.add(fld_deposit);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Depozito Ücreti:");
		lblNewLabel_1_3_1.setBounds(530, 166, 102, 16);
		w_admin.add(lblNewLabel_1_3_1);
		
		fld_rental_fee = new JTextField();
		fld_rental_fee.setColumns(10);
		fld_rental_fee.setBounds(530, 227, 221, 24);
		w_admin.add(fld_rental_fee);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Kiralama Ücreti (1 Günlük):");
		lblNewLabel_1_3_2.setBounds(530, 210, 194, 16);
		w_admin.add(lblNewLabel_1_3_2);
		
		JScrollPane w_scrollAdmin = new JScrollPane();
		w_scrollAdmin.setBounds(6, 5, 512, 337);
		w_admin.add(w_scrollAdmin);
		
		table_cars = new JTable(carModel);
		w_scrollAdmin.setViewportView(table_cars);
		
		JButton btn_addCar = new JButton("Araç Ekle");
		btn_addCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_brand.getText().length() == 0 || fld_gear.getText().length() == 0 ||fld_fuel.getText().length() == 0 || fld_capacity.getText().length() == 0 || fld_deposit.getText().length() == 0 || fld_rental_fee.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Lütfen Boş Alanları Doldurunuz.");
				} else {
					try {
						boolean add = admin.addCar(fld_brand.getText(), fld_gear.getText(), fld_fuel.getText() ,Integer.parseInt(fld_capacity.getText()), Integer.parseInt(fld_deposit.getText()), Integer.parseInt(fld_rental_fee.getText()));
						if(add) {
							JOptionPane.showMessageDialog(null, "Ekleme başarılı.");
							fld_brand.setText(null);
							fld_gear.setText(null);
							fld_fuel.setText(null);
							fld_capacity.setText(null);
							fld_deposit.setText(null);
							fld_rental_fee.setText(null);
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		}});
		btn_addCar.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		btn_addCar.setBackground(new Color(251, 251, 251));
		btn_addCar.setBounds(530, 263, 219, 59);
		w_admin.add(btn_addCar);
	}
}
