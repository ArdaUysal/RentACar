package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.DBConnection;
import Model.Car;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_rent_id;
	private JTable table_cars;
	private DefaultTableModel carModel = null;
	private Object[] carData = null;
	private static User user = new User();
	
	private DBConnection dbcon = new DBConnection();	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI(user);
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
	public UserGUI(User user) throws SQLException {
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
		for(int i = 0; i < user.getCarList().size(); i++) {
			carData[0] = user.getCarList().get(i).getId();
			carData[1] = user.getCarList().get(i).getBrand();
			carData[2] = user.getCarList().get(i).getGear();
			carData[3] = user.getCarList().get(i).getFuel();
			carData[4] = user.getCarList().get(i).getCapacity();
			carData[5] = user.getCarList().get(i).getDeposit();
			carData[6] = user.getCarList().get(i).getRental_fee();
			carData[7] = user.getCarList().get(i).getCount();
			carModel.addRow(carData);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 642);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("projelogo.jpeg")));
		lbl_logo.setBounds(790, 6, 194, 156);
		contentPane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Merhaba, Sayın " + user.getName());
		lblNewLabel.setBounds(56, 48, 294, 16);
		contentPane.add(lblNewLabel);
		
		JButton btn_user_exit = new JButton("Çıkış Yap");
		btn_user_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI() ;
				login.setVisible(true);
				dispose() ;
			}
		});
		btn_user_exit.setBounds(66, 76, 117, 29);
		contentPane.add(btn_user_exit);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 156, 979, 440);
		contentPane.add(tabbedPane);
		
		JPanel user_panel = new JPanel();
		tabbedPane.addTab("Araç Kirala", null, user_panel, null);
		user_panel.setLayout(null);
		
		JScrollPane w_scroll_user = new JScrollPane();
		w_scroll_user.setBounds(6, 6, 752, 382);
		user_panel.add(w_scroll_user);
		
		table_cars = new JTable(carModel);
		w_scroll_user.setViewportView(table_cars);
		
		fld_rent_id = new JTextField();
		fld_rent_id.setBounds(762, 159, 190, 36);
		user_panel.add(fld_rent_id);
		fld_rent_id.setColumns(10);
		
		JButton btn_rent = new JButton("Kirala");
		btn_rent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_rent_id.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Lütfen boş alanları doldurunuz.");
				} else {
					Connection con = dbcon.connDb();
					Statement st;
					boolean correct = false;
					try {
						st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM cars");
						while(rs.next()) {
						if(Integer.parseInt(fld_rent_id.getText()) == rs.getInt("id")) {
							//JOptionPane.showMessageDialog(null, "Araç başarıyla kiralandı.");
							Car car = new Car();
							car.setId(rs.getInt("id"));
							car.setBrand(rs.getString("brand"));
							car.setGear(rs.getString("gear"));
							car.setFuel(rs.getString("fuel"));
							car.setCapacity(rs.getInt("capacity"));
							car.setDeposit(rs.getInt("deposit"));
							car.setRental_fee(rs.getInt("rental_fee"));
							car.setCount(rs.getInt("count"));
							if(car.getCount() == 0) {
								JOptionPane.showMessageDialog(null, "Bu araç stokta yok.");
							} else {
								PayGUI payGUI = new PayGUI(user,car);
								payGUI.setVisible(true);
								dispose();
							}
							//user.rentACar(car.getCount(),car.getId());
							updateCarList();
							fld_rent_id.setText(null);
							correct = true;
							break;
						} 						
						}
						if(!correct) {
							JOptionPane.showMessageDialog(null, "Girmiş olduğunuz ID tanımlı değil.");
							fld_rent_id.setText(null);					}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_rent.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		btn_rent.setBounds(762, 202, 190, 43);
		user_panel.add(btn_rent);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(770, 122, 61, 36);
		user_panel.add(lblNewLabel_1);
		
	}
	public void updateCarList() throws SQLException {
		DefaultTableModel clearList = (DefaultTableModel) table_cars.getModel();
		clearList.setRowCount(0);
		for(int i = 0; i < user.getCarList().size(); i++) {
			carData[0] = user.getCarList().get(i).getId();
			carData[1] = user.getCarList().get(i).getBrand();
			carData[2] = user.getCarList().get(i).getGear();
			carData[3] = user.getCarList().get(i).getFuel();
			carData[4] = user.getCarList().get(i).getCapacity();
			carData[5] = user.getCarList().get(i).getDeposit();
			carData[6] = user.getCarList().get(i).getRental_fee();
			carData[7] = user.getCarList().get(i).getCount();
			carModel.addRow(carData);
		}		
	}
	
	


}
