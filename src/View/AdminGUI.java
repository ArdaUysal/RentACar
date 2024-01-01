package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
	
	static Admin admin = new Admin();
	private JTextField fld_brand;
	private JTextField fld_gear;
	private JTextField fld_fuel;
	private JTextField fld_capacity;
	private JTextField fld_deposit;
	private JTextField fld_rental_fee;
	private JTable table_cars;
	private DefaultTableModel carModel = null;
	private Object[] carData = null;
	private JTextField fld_car_id;
	private JTextField fld_car_count;

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
		setTitle("Admin Sistemi");
		
		carModel = new DefaultTableModel();
		Object[] colCarName = new Object[8];
		colCarName[0] = "ID";
		colCarName[1] = "ARABA";
		colCarName[2] = "VİTES";
		colCarName[3] = "YAKIT";
		colCarName[4] = "KAPASİTE";
		colCarName[5] = "DEPOZİTO";
		colCarName[6] = "KİRALAMA ÜCRETİ";
		colCarName[7] = "ARAÇ SAYISI";
		carModel.setColumnIdentifiers(colCarName);
		carData = new Object[8];
		for(int i = 0; i < admin.getCarList().size(); i++) {
			carData[0] = admin.getCarList().get(i).getId();
			carData[1] = admin.getCarList().get(i).getBrand();
			carData[2] = admin.getCarList().get(i).getGear();
			carData[3] = admin.getCarList().get(i).getFuel();
			carData[4] = admin.getCarList().get(i).getCapacity();
			carData[5] = admin.getCarList().get(i).getDeposit();
			carData[6] = admin.getCarList().get(i).getRental_fee();
			carData[7] = admin.getCarList().get(i).getCount();
			carModel.addRow(carData);
		}
		
		setBackground(new Color(255, 204, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 642);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Merhaba, Sayın " + admin.getName());
		lblNewLabel.setBounds(30, 30, 195, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(30, 58, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lbl_admin_logo = new JLabel(new ImageIcon(getClass().getResource("projelogo.jpeg")));
		lbl_admin_logo.setBounds(751, 6, 210, 147);
		contentPane.add(lbl_admin_logo);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(6, 124, 993, 460);
		contentPane.add(w_tab);
		
		JPanel w_admin = new JPanel();
		w_tab.addTab("Araç Yönetimi", null, w_admin, null);
		w_admin.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Marka Model:");
		lblNewLabel_1.setBounds(745, 6, 95, 16);
		w_admin.add(lblNewLabel_1);
		
		fld_brand = new JTextField();
		fld_brand.setBounds(745, 23, 221, 24);
		w_admin.add(fld_brand);
		fld_brand.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Vites:");
		lblNewLabel_1_1.setBounds(745, 45, 95, 16);
		w_admin.add(lblNewLabel_1_1);
		
		fld_gear = new JTextField();
		fld_gear.setColumns(10);
		fld_gear.setBounds(745, 59, 221, 24);
		w_admin.add(fld_gear);
		
		JLabel lblNewLabel_1_2 = new JLabel("Yakıt:");
		lblNewLabel_1_2.setBounds(745, 84, 95, 16);
		w_admin.add(lblNewLabel_1_2);
		
		fld_fuel = new JTextField();
		fld_fuel.setColumns(10);
		fld_fuel.setBounds(745, 99, 221, 24);
		w_admin.add(fld_fuel);
		
		JLabel lblNewLabel_1_3 = new JLabel("Kapasite:");
		lblNewLabel_1_3.setBounds(745, 124, 95, 16);
		w_admin.add(lblNewLabel_1_3);
		
		fld_capacity = new JTextField();
		fld_capacity.setColumns(10);
		fld_capacity.setBounds(745, 141, 221, 24);
		w_admin.add(fld_capacity);
		
		fld_deposit = new JTextField();
		fld_deposit.setColumns(10);
		fld_deposit.setBounds(745, 183, 221, 24);
		w_admin.add(fld_deposit);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Depozito Ücreti:");
		lblNewLabel_1_3_1.setBounds(745, 166, 102, 16);
		w_admin.add(lblNewLabel_1_3_1);
		
		fld_rental_fee = new JTextField();
		fld_rental_fee.setColumns(10);
		fld_rental_fee.setBounds(745, 227, 221, 24);
		w_admin.add(fld_rental_fee);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Kiralama Ücreti (1 Günlük):");
		lblNewLabel_1_3_2.setBounds(745, 210, 194, 16);
		w_admin.add(lblNewLabel_1_3_2);
		
		JScrollPane w_scrollAdmin = new JScrollPane();
		w_scrollAdmin.setBounds(6, 5, 727, 403);
		w_admin.add(w_scrollAdmin);
		
		table_cars = new JTable(carModel);
		w_scrollAdmin.setViewportView(table_cars);
		
		JButton btn_addCar = new JButton("Araç Ekle");
		btn_addCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_brand.getText().length() == 0 || fld_gear.getText().length() == 0 ||fld_fuel.getText().length() == 0 || fld_capacity.getText().length() == 0 || fld_deposit.getText().length() == 0 || fld_rental_fee.getText().length() == 0  || fld_car_count.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Lütfen Boş Alanları Doldurunuz.");
				} else {
					try {
						boolean add = admin.addCar(fld_brand.getText(), fld_gear.getText(), fld_fuel.getText() ,Integer.parseInt(fld_capacity.getText()), Integer.parseInt(fld_deposit.getText()), Integer.parseInt(fld_rental_fee.getText()), Integer.parseInt(fld_car_count.getText()));
						if(add) {
							JOptionPane.showMessageDialog(null, "Ekleme başarılı.");
							fld_brand.setText(null);
							fld_gear.setText(null);
							fld_fuel.setText(null);
							fld_capacity.setText(null);
							fld_deposit.setText(null);
							fld_rental_fee.setText(null);
							fld_car_count.setText(null);
							updateCarList();
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
		btn_addCar.setBounds(745, 310, 221, 26);
		w_admin.add(btn_addCar);	
		
		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setBounds(745, 336, 61, 16);
		w_admin.add(lblNewLabel_2);
		
		fld_car_id = new JTextField();
		fld_car_id.setBounds(745, 352, 219, 26);
		w_admin.add(fld_car_id);
		fld_car_id.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Araç Sil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_car_id.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Lütfen silmek istediğiniz aracın ID'sini girin.");
				} else {
					try {
						boolean delete = admin.deleteCar(Integer.parseInt(fld_car_id.getText()));
						if(delete) {
							JOptionPane.showMessageDialog(null, "Araç Silindi");
							fld_car_id.setText(null);
							updateCarList();
						}
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		btnNewButton_1.setBounds(745, 381, 221, 26);
		w_admin.add(btnNewButton_1);
		
		fld_car_count = new JTextField();
		fld_car_count.setColumns(10);
		fld_car_count.setBounds(745, 274, 221, 24);
		w_admin.add(fld_car_count);
		
		JLabel lblNewLabel_1_3_2_1 = new JLabel("Araç Sayısı");
		lblNewLabel_1_3_2_1.setBounds(745, 257, 194, 16);
		w_admin.add(lblNewLabel_1_3_2_1);
		
		table_cars.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_cars.getValueAt(table_cars.getSelectedRow(),0).toString());
					String selectBrand = table_cars.getValueAt(table_cars.getSelectedRow(),1).toString();
					String selectGear = table_cars.getValueAt(table_cars.getSelectedRow(),2).toString();
					String selectFuel = table_cars.getValueAt(table_cars.getSelectedRow(),3).toString();
					int setCapacity = Integer.parseInt(table_cars.getValueAt(table_cars.getSelectedRow(),4).toString());
					int setDeposit = Integer.parseInt(table_cars.getValueAt(table_cars.getSelectedRow(),5).toString());
					int setRental_fee = Integer.parseInt(table_cars.getValueAt(table_cars.getSelectedRow(),6).toString());
					int setCount = Integer.parseInt(table_cars.getValueAt(table_cars.getSelectedRow(),7).toString());
					try {
						boolean update = admin.updateCar(selectID, selectBrand, selectGear, selectFuel, setCapacity, setDeposit, setRental_fee, setCount);
						if(update) {
							JOptionPane.showMessageDialog(null, "Araç Başarıyla Güncellendi");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
		
		
	}
	
	public void updateCarList() throws SQLException {
		DefaultTableModel clearList = (DefaultTableModel) table_cars.getModel();
		clearList.setRowCount(0);
		for(int i = 0; i < admin.getCarList().size(); i++) {
			carData[0] = admin.getCarList().get(i).getId();
			carData[1] = admin.getCarList().get(i).getBrand();
			carData[2] = admin.getCarList().get(i).getGear();
			carData[3] = admin.getCarList().get(i).getFuel();
			carData[4] = admin.getCarList().get(i).getCapacity();
			carData[5] = admin.getCarList().get(i).getDeposit();
			carData[6] = admin.getCarList().get(i).getRental_fee();
			carData[7] = admin.getCarList().get(i).getCount();
			carModel.addRow(carData);
		}		
	}
}
