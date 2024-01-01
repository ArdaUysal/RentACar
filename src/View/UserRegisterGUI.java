package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserRegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fld_user_reg_name;
	private JTextField fld_user_reg_surname;
	private JTextField fld_user_reg_email;
	private JTextField fld_user_reg_pass;
	static User user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegisterGUI frame = new UserRegisterGUI(user);
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
	public UserRegisterGUI(User user) {
		setTitle("Kayıt Ol");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adı:");
		lblNewLabel.setBounds(6, 197, 80, 16);
		contentPane.add(lblNewLabel);
		
		fld_user_reg_name = new JTextField();
		fld_user_reg_name.setBounds(6, 215, 288, 36);
		contentPane.add(fld_user_reg_name);
		fld_user_reg_name.setColumns(10);
		
		JLabel lblSoyad = new JLabel("Soyadı:");
		lblSoyad.setBounds(6, 252, 80, 16);
		contentPane.add(lblSoyad);
		
		fld_user_reg_surname = new JTextField();
		fld_user_reg_surname.setColumns(10);
		fld_user_reg_surname.setBounds(6, 270, 288, 36);
		contentPane.add(fld_user_reg_surname);
		
		JLabel lblEposta = new JLabel("E-Posta:");
		lblEposta.setBounds(6, 308, 80, 16);
		contentPane.add(lblEposta);
		
		fld_user_reg_email = new JTextField();
		fld_user_reg_email.setColumns(10);
		fld_user_reg_email.setBounds(6, 326, 288, 36);
		contentPane.add(fld_user_reg_email);
		
		JLabel lblifre = new JLabel("Şifre:");
		lblifre.setBounds(6, 364, 80, 16);
		contentPane.add(lblifre);
		
		fld_user_reg_pass = new JTextField();
		fld_user_reg_pass.setColumns(10);
		fld_user_reg_pass.setBounds(6, 382, 288, 36);
		contentPane.add(fld_user_reg_pass);
		
		JButton btnNewButton = new JButton("Kayıt Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_user_reg_name.getText().length() == 0 || fld_user_reg_surname.getText().length() == 0 || fld_user_reg_email.getText().length() == 0 || fld_user_reg_pass.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz.");
				} else {
					boolean addUser = user.addUser(fld_user_reg_name.getText(), fld_user_reg_surname.getText(), fld_user_reg_email.getText(), fld_user_reg_pass.getText());
					if(addUser) {
						JOptionPane.showMessageDialog(null, "Kayıt olma işlemi başarılı.");
						fld_user_reg_name.setText(null);
						fld_user_reg_surname.setText(null);
						fld_user_reg_email.setText(null);
						fld_user_reg_pass.setText(null);
					} else {
						JOptionPane.showMessageDialog(null, "Bu e-posta zaten kayıtlı.");
						fld_user_reg_email.setText(null);
						fld_user_reg_pass.setText(null);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		btnNewButton.setBounds(6, 430, 288, 36);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Rent Araç Kiralamaya Kayıt Ol!");
		lblNewLabel_1.setFont(new Font("Lava Telugu", Font.BOLD, 19));
		lblNewLabel_1.setBounds(6, 163, 288, 36);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbl_reg_logo = new JLabel(new ImageIcon(getClass().getResource("projelogo.jpeg")));
		lbl_reg_logo.setBounds(40, 6, 214, 145);
		contentPane.add(lbl_reg_logo);
		
		JButton btnGeri = new JButton("Geri");
		btnGeri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btnGeri.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		btnGeri.setBounds(6, 470, 288, 36);
		contentPane.add(btnGeri);
	}
}
