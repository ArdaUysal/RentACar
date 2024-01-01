package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class User {
	
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	private int id;
	String name,surname,email,password;
	
	public ArrayList<Car> getCarList() throws SQLException{
		ArrayList<Car> carList = new ArrayList<>();
		Car car;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM cars");
			while(rs.next()) {
				car = new Car();
				car.setId(rs.getInt("id"));
				car.setBrand(rs.getString("brand"));
				car.setGear(rs.getString("gear"));
				car.setFuel(rs.getString("fuel"));
				car.setCapacity(rs.getInt("capacity"));
				car.setDeposit(rs.getInt("deposit"));
				car.setRental_fee(rs.getInt("rental_fee"));
				car.setCount(rs.getInt("count"));
				carList.add(car);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return carList;
		
		
	}
	
	public User(int id, String name, String surname, String email, String password) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}
	
	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean addUser(String name,String surname,String email,String password) {
		String sorgu = "INSERT INTO users (name,surname,email,password)" + "VALUES(?,?,?,?)";
		boolean key = false;
		try {
			Statement st1 = con.createStatement();
			rs = st1.executeQuery("SELECT * FROM users");
			while(rs.next()) {
				if(email.equals(rs.getString("email"))) {
					return false;
				}
			}
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(key) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void rentACar(int count, int id) throws SQLException {
		count--;
		String sorgu = "UPDATE `RentACar`.`cars` SET `count` = '"+count+"' WHERE (`id` = '"+id+"')";
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			/*preparedStatement.setInt(1,count);
			preparedStatement.setInt(2,id);*/
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
