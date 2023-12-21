package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Model.Admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_user_email;
	private JPasswordField fld_user_pass;
	private JTextField fld_admin_email;
	private JPasswordField fld_admin_pass;
	private DBConnection dbcon = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setTitle("Araba Kiralama");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 470);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 204, 0));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("projelogo.jpeg")));
		lbl_logo.setBounds(186, 0, 192, 160);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Rent Araç Kiralamaya Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 23));
		lblNewLabel.setBounds(88, 172, 406, 49);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		w_tabpane.setForeground(new Color(255, 255, 255));
		w_tabpane.setBackground(new Color(0, 0, 0));
		w_tabpane.setBounds(17, 211, 539, 225);
		w_pane.add(w_tabpane);
		
		JPanel panel = new JPanel();
		w_tabpane.addTab("Kullanıcı Girişi", null, panel, null);
		panel.setBackground(new Color(226, 226, 226));
		panel.setLayout(null);
		
		JLabel lblEposta = new JLabel("E-Posta:");
		lblEposta.setBounds(23, 16, 83, 28);
		lblEposta.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		panel.add(lblEposta);
		
		JLabel lblsifre = new JLabel("Şifre:");
		lblsifre.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblsifre.setBounds(23, 65, 83, 28);
		panel.add(lblsifre);
		
		fld_user_email = new JTextField();
		fld_user_email.setBounds(118, 19, 251, 28);
		panel.add(fld_user_email);
		fld_user_email.setColumns(10);
		
		fld_user_pass = new JPasswordField();
		fld_user_pass.setBounds(118, 69, 251, 26);
		panel.add(fld_user_pass);
		
		JButton btn_user_login = new JButton("Giriş Yap");
		btn_user_login.setBounds(43, 116, 209, 43);
		panel.add(btn_user_login);
		
		JButton btn_user_register = new JButton("Kayıt Ol");
		btn_user_register.setBounds(275, 116, 209, 43);
		panel.add(btn_user_register);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(226, 226, 226));
		w_tabpane.addTab("Admin Girişi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblEposta_1 = new JLabel("E-Posta:");
		lblEposta_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEposta_1.setBounds(22, 17, 83, 28);
		panel_1.add(lblEposta_1);
		
		fld_admin_email = new JTextField();
		fld_admin_email.setColumns(10);
		fld_admin_email.setBounds(117, 20, 251, 28);
		panel_1.add(fld_admin_email);
		
		JLabel lblsifre_1 = new JLabel("Şifre:");
		lblsifre_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblsifre_1.setBounds(22, 68, 83, 28);
		panel_1.add(lblsifre_1);
		
		fld_admin_pass = new JPasswordField();
		fld_admin_pass.setBounds(117, 72, 251, 26);
		panel_1.add(fld_admin_pass);
		
		JButton btn_admin_login = new JButton("Giriş Yap");
		btn_admin_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_admin_email.getText().length() == 0 || fld_admin_pass.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Lütfen Boş Alanları Doldurunuz.");
				} else {
					try {
						Connection con = dbcon.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM admins");
						while(rs.next()) {
							if(fld_admin_email.getText().equals(rs.getString("email")) && fld_admin_pass.getText().equals(rs.getString("password"))) {
								Admin admin = new Admin();
								admin.setId(rs.getInt("id"));
								admin.setName(rs.getString("name"));
								admin.setSurname(rs.getString("surname"));
								admin.setEmail(rs.getString("email"));
								admin.setPassword(rs.getString("password"));
								AdminGUI adminGUI = new AdminGUI(admin);
								adminGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_admin_login.setBounds(22, 119, 476, 43);
		panel_1.add(btn_admin_login);
	}
}
