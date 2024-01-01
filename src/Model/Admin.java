package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Admin extends User {
	
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Admin() {}

	public Admin(int id, String name, String surname, String email, String password) {
		super(id, name, surname, email, password);
	}
	
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
	
	public boolean addCar(String brand, String gear, String fuel, int capacity, int deposit, int rental_fee, int count) throws SQLException {
		String sorgu = "INSERT INTO cars (brand,gear,fuel,capacity,deposit,rental_fee,count)" + "VALUES(?,?,?,?,?,?,?)";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1,brand);
			preparedStatement.setString(2,gear);
			preparedStatement.setString(3,fuel);
			preparedStatement.setInt(4,capacity);
			preparedStatement.setInt(5, deposit);
			preparedStatement.setInt(6, rental_fee);
			preparedStatement.setInt(7, count);
			preparedStatement.executeUpdate();
			key = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(key) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteCar(int id) throws SQLException {
		String sorgu = "DELETE FROM cars WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			key = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(key) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean updateCar(int id,String brand,String gear,String fuel,int capacity,int deposit,int rental_fee, int count) throws SQLException {
		String sorgu = "UPDATE cars SET brand = ?, gear = ?, fuel = ?, capacity = ?, deposit = ?, rental_fee = ?, count = ? WHERE id = ?";
		boolean key = false;
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(sorgu);
			preparedStatement.setString(1,brand);
			preparedStatement.setString(2,gear);
			preparedStatement.setString(3,fuel);
			preparedStatement.setInt(4,capacity);
			preparedStatement.setInt(5,deposit);
			preparedStatement.setInt(6,rental_fee);
			preparedStatement.setInt(7,count);
			preparedStatement.setInt(8,id);
			preparedStatement.executeUpdate();
			key = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if(key) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
