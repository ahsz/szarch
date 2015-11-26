package szarch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private Connection conn = null;
	
	public Connect(){
		try {
           String userName = "dbuser";
           String password = "giantmouse159";

           String url = "jdbc:mysql://25.63.189.116:3306/szarch";
           try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {				
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
           conn = DriverManager.getConnection(url, userName, password);
			
			} catch (SQLException ex) {				
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public Connection get_connection(){
		if (conn!=null)
			return conn;
		else {	
			System.out.println("Connection failed");
			return null;
		}
	}
}
