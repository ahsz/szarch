package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Role implements Utility<Role> {
	private java.lang.Integer id;
	private String name;
	
	
	public java.lang.Integer get_id() 		{ return id; }
	public String get_name() 				{ return name; }
	
	public void set_id(java.lang.Integer id) 	{ this.id = id; }
	public void set_name(String name) 			{ this.name = name; }

	public Role(){
		this.id = null;
		this.name = null;
	}
	
	public String toString() {
		return id + "\t" + name;
	}

	/**
	 * Add a new role to the database. 
	 *
	 * @param  Role the role what is will be added to the database
	 * @return int  0 - error, else - the database id of the newly added user
	 */
	@Override
	public int add(Role role) {
		
		int role_id = 0;
		
		if (role.get_id() != null){
			System.out.print("id is not null!");
			return role_id;
		}
		
		if (role.get_name() == null){
			System.out.print("name is null!");
			return role_id;
		}
		
		try {
			String query = "INSERT INTO role VALUES (null,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setString(i, role.get_name()); i++;

			stmt.execute();
			
			query = "SELECT * FROM role WHERE name = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setString(1, role.get_name());
			ResultSet r = stmt.executeQuery();
			r.next();
			role_id = r.getInt("id");
	
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return role_id;
	}
	
	/**
	 * List role(s) from the database.
	 *
	 * @param  Role       the role(s) what is searched for
	 * @return List<Role> ArrayList of the role(s)
	 */
	@Override
	public List<Role> get(Role role) {
		
		List<Role> rol_list = new ArrayList<Role>();

		try {	
			String query = "SELECT * FROM role";
			if (role.get_id() != null || role.get_name() != null)	
				query += " WHERE";
			if (role.get_id() != null)									
				query += " id = ?";
			if (role.get_id() != null && role.get_name() != null)
				query += " AND";
			if (role.get_name() != null)									
				query += " name = ?";

			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (role.get_id() != null)			{ stmt.setInt(i, role.get_id()); 		i++; } 
			if (role.get_name() != null) 		{ stmt.setString(i, role.get_name()); 	i++; }

			ResultSet r = stmt.executeQuery();
			Role rol = null;
			while (r.next()){
				rol = new Role();
				rol.set_id(r.getInt("id"));
				rol.set_name(r.getString("name"));;
				rol_list.add(rol);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rol_list;
	}
	
	/**
	 * Delete role(s) from the database.
	 * With null Role all user will be deleted!
	 *
	 * @param  Role the role(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Role role) {

		try {	
			String query = "DELETE FROM role ";
			if (role.get_id() != null || role.get_name() != null)
				query += " WHERE";
			if (role.get_id() != null)									
				query += " id = ?";
			if (role.get_id() != null && role.get_name() != null)
				query += " AND";
			if (role.get_name() != null)									
				query += " name = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (role.get_id() != null) 			{ stmt.setInt(i, role.get_id()); 		i++; }
			if (role.get_name() != null) 		{ stmt.setString(i, role.get_name()); 	i++; }
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
	
	/**
	 * Update one role in the database, search by its id.
	 *
	 * @param  Role the role what will be updated
	 * @return void
	 */
	@Override
	public void update(Role role) {
		if (role.get_id() == null){
			System.out.print("id is null!");
			return;
		}
		if (role.get_name() == null){
			System.out.print("name is null!");
			return;
		}
		
		try {	
			String query = "UPDATE role SET name = ? WHERE id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			stmt.setString(i, role.get_name()); 	i++; 
			stmt.setInt(i, role.get_id()); 			i++; 
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
}
