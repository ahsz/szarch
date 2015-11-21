package szarch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface Utility<T>{
	
	public Connect connect = new Connect();
	public Connection conn = connect.get_connection();
	
	int add(T object);

}
