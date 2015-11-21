package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User implements Utility<User>{

	private java.lang.Integer id;
	private String name;
	private String password;
	private java.lang.Integer role_id;
	private static String aes_key;
	
	public java.lang.Integer get_id()			{ return id; }
	public String get_name() 					{ return name; }
	public String get_pw()						{ return password; }
	public java.lang.Integer get_role_id()		{ return role_id; }
	public String get_aes_key()					{ return aes_key; }
	
	public void set_id(java.lang.Integer new_id)				{ id = new_id; }
	public void set_name(String new_name) 						{ name = new_name; }
	public void set_pw(String new_pw)							{ password = new_pw; }
	public void set_role_id(java.lang.Integer new_role_id) 		{ role_id = new_role_id; }
	public void set_aes_key(String new_aes_key)					{ aes_key = new_aes_key; }
	  
	static { aes_key = "akarmi"; }
	
	User(){
		this.id = null;
		this.name = null;
		this.password = null;
		this.role_id = null;	
	}
	
	public String toString() {
		return id + "\t" + name + "\t" + password + "\t" + role_id + "\t" + aes_key;
	}
	
	/**
	 * Add a new user to the database. 
	 *
	 * @param  User the user what is will be added to the database
	 * @return      the database id of the newly added user
	 */
	@Override
	public int add(User user) {
		
		int user_id = 0;
		
		if (user.get_id() != null){
			System.out.print("id is not null!");
			return user_id;
		}
		
		if (user.get_name() == null || user.get_pw() == null || user.get_role_id() == null){
			System.out.print("Name or password or role_id is null!");
			return user_id;
		}
		
		try {
			String query = "INSERT INTO user VALUES (null,?,AES_ENCRYPT(?,?),?)";
		
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setString(i, user.get_name()); i++;
			stmt.setString(i, user.get_pw()); i++;
			stmt.setString(i, aes_key); i++;
			stmt.setInt(i, user.get_role_id()); i++;
			
			stmt.execute();
			
			query = "SELECT * FROM user WHERE name = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setString(1, user.get_name());
			ResultSet r = stmt.executeQuery();
			r.next();
			user_id = r.getInt("id");
	
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		return user_id;
	}


	/**
	 * List user(s) from the database.
	 *
	 * @param  User       the user(s) what is searched for
	 * @return List<User> ArrayList of the users
	 */
	@Override
	public List<User> get(User user){

		List<User> usr_list = new ArrayList<User>();
		
		if (user.get_pw() != null){
			System.out.print("password is not null!");
			return null;
		}
		
		try {	
			String query = "SELECT id, name, AES_DECRYPT(password,?) AS password, role_id FROM user";
			//String query = "SELECT * FROM user";
			if (user.get_id() != null || user.get_name() != null || user.get_role_id() != null)	
				query += " WHERE";
			if (user.get_id() != null)									
				query += " id = ?";
			if (user.get_id() != null && user.get_name() != null)
				query += " AND";
			if (user.get_name() != null)									
				query += " name = ?";
			if ((user.get_id() != null || user.get_name() != null) && user.get_role_id() != null)					
				query += " AND";
			if (user.get_role_id() != null)										
				query += " role_id = ?";

			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			stmt.setString(i,  user.get_aes_key()); i++;
			if (user.get_id() != null)			{ stmt.setInt(i, user.get_id()); 		i++; } 
			if (user.get_name() != null) 		{ stmt.setString(i, user.get_name()); 	i++; }
			if (user.get_role_id() != null)		{ stmt.setInt(i, user.get_role_id()); 	i++; } 

			ResultSet r = stmt.executeQuery();
			User usr = null;
			while (r.next()){
				usr = new User();
				usr.set_id(r.getInt("id"));
				usr.set_name(r.getString("name"));;
				usr.set_pw(r.getString("password"));
				usr_list.add(usr);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return usr_list;
	}

	/**
	 * Delete user(s) from the database.
	 * With null User all user will be deleted!
	 *
	 * @param  User       the user(s) what will be deleted
	 * @return void
	 */
	public void delete(User user){
		if (user.get_pw() != null){
			System.out.print("password is not null!");
			return;
		}
		
		try {	
			String query = "DELETE FROM user ";
			if (user.get_id() != null || user.get_name() != null || user.get_role_id() != null)	
				query += " WHERE";
			if (user.get_id() != null)									
				query += " id = ?";
			if (user.get_id() != null && user.get_name() != null)
				query += " AND";
			if (user.get_name() != null)									
				query += " name = ?";
			if ((user.get_id() != null || user.get_name() != null) && user.get_role_id() != null)					
				query += " AND";
			if (user.get_role_id() != null)										
				query += " role_id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (user.get_id() != null) 			{ stmt.setInt(i, user.get_id()); 		i++; }
			if (user.get_name() != null) 		{ stmt.setString(i, user.get_name()); 	i++; }
			if (user.get_role_id() != null)		{ stmt.setInt(i, user.get_role_id());	i++; }
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	/**
	 * Update one user in the database.
	 *
	 * @param  User the user what will be updated
	 * @return void
	 */
	public void update(User user){
		
		if (user.get_id() == null){
			System.out.print("id is null!");
			return;
		}
		if (user.get_name() == null && user.get_pw() == null && user.get_role_id() == null){
			System.out.print("both of name, password and role_id is null!");
			return;
		}
		
		try {	
			String query = "UPDATE user SET ";
			if (user.get_name() != null)									
				query += " name=?";
			if (user.get_name() != null && user.get_pw() != null)
				query += ", ";
			if (user.get_pw() != null)									
				query += " password=?";
			if ((user.get_name() != null || user.get_pw() != null) && user.get_role_id() != null)					
				query += ", ";
			if (user.get_role_id() != null)										
				query += " role_id = ?";
			
			query += " WHERE id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (user.get_name() != null) 		{ stmt.setString(i, user.get_name()); 	i++; }
			if (user.get_pw() != null)			{ stmt.setString(i, user.get_pw());		i++; }
			if (user.get_role_id() != null)		{ stmt.setInt(i, user.get_role_id());	i++; }
			stmt.setInt(i, user.get_id()); 		i++; 
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
	}		
}