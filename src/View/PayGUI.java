package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Car;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PayGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_user_cardNo;
	private JTextField fld_user_name;
	private JTextField fld_user_surname;
	private static User user;
	private static Car car;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayGUI frame = new PayGUI(user,car);
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
	public PayGUI(User user,Car car) {
		setTitle("Ödeme Ekranı");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(226, 226, 226));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel newlabel = new JLabel("Ad:");
		newlabel.setHorizontalAlignment(SwingConstants.LEFT);
		newlabel.setBounds(6, 47, 89, 16);
		contentPane.add(newlabel);
		
		JLabel lblNewLabel = new JLabel("Soyad:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(158, 47, 104, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kart Numarası:");
		lblNewLabel_1.setBounds(6, 144, 214, 16);
		contentPane.add(lblNewLabel_1);
		
		fld_user_cardNo = new JTextField();
		fld_user_cardNo.setBounds(6, 171, 288, 40);
		contentPane.add(fld_user_cardNo);
		fld_user_cardNo.setColumns(10);
		
		fld_user_name = new JTextField();
		fld_user_name.setBounds(6, 75, 130, 40);
		contentPane.add(fld_user_name);
		fld_user_name.setColumns(10);
		
		fld_user_surname = new JTextField();
		fld_user_surname.setBounds(158, 75, 130, 40);
		contentPane.add(fld_user_surname);
		fld_user_surname.setColumns(10);
		
		JButton btn_pay = new JButton("Onayla");
		btn_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_user_name.getText().length() == 0 || fld_user_surname.getText().length() == 0 || fld_user_cardNo.getText().length() == 0){
					JOptionPane.showMessageDialog(null, "Lütfen boş alanları doldurunuz.");
				} else {
					if(fld_user_name.getText().equals(user.getName()) && fld_user_surname.getText().equals(user.getSurname())) {
						JOptionPane.showMessageDialog(null, "Araç kiralama işlemi başarılı.");
						try {
							user.rentACar(car.getCount(),car.getId());
							UserGUI userGUI = new UserGUI(user);
							userGUI.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Girmiş olduğunuz bilgiler hatalı.");
					}
				}
			}
		});
		btn_pay.setBounds(79, 242, 141, 40);
		contentPane.add(btn_pay);
	}
}
