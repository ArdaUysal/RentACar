package Helper;
import java.sql.*;

public class DBConnection {
	Connection c = null;
	
	static final String DB = "jdbc:mysql://localhost:3306/RentACar";
	static final String USER = "root";
	static final String PASS = "312Arda259";
	
	public DBConnection() { }
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection(DB,USER,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

}
