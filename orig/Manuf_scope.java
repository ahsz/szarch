package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Manuf_scope implements Utility<Manuf_scope> {
	private java.lang.Integer id;
	private String ms_id;
	private java.lang.Integer is_ordered;
	private String deadline;
	
	
	public java.lang.Integer get_id() 			{ return id; }
	public String get_ms_id() 					{ return ms_id; }
	public java.lang.Integer get_is_ordered() 	{ return is_ordered; }	
	public String get_deadline() 				{ return deadline; }	
	
	public void set_id(java.lang.Integer id) 					{ this.id = id; }
	public void set_ms_id(String ms_id) 						{ this.ms_id = ms_id; };
	public void set_is_ordered(java.lang.Integer is_ordered) 	{ this.is_ordered = is_ordered; }
	public void set_deadline(String deadline) 					{ this.deadline = deadline; }
	
	public Manuf_scope(){
		this.id = null;
		this.ms_id = null;
		this.is_ordered = null;
		this.deadline = null;
	}

	public String toString() {
		return id + "\t" + ms_id + "\t" + is_ordered + "\t" + deadline;
	}
	
	/**
	 * Add a new manufacturing scope to the database. 
	 *
	 * @param  Manuf_scope 	the manufacturing scope what is will be added to the database
	 * @return int    		0 - error, else - the database id of the newly added user
	 */
	@Override
	public int add(Manuf_scope manuf_scope) {
		
		int manuf_scope_id = 0;
		
		if (manuf_scope.get_id() != null){
			System.out.print("id is not null!");
			return manuf_scope_id;
		}
		
		if (manuf_scope.get_ms_id() == null || manuf_scope.get_is_ordered() == null || manuf_scope.get_deadline() == null){
			System.out.print("ms_id or is_ordered or deadline is null!");
			return manuf_scope_id;
		}
		
		try {
			String query = "INSERT INTO manuf_scope VALUES (null,?,?,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setString(i, manuf_scope.get_ms_id()); i++;
			stmt.setInt(i, manuf_scope.get_is_ordered()); i++;
			stmt.setString(i, manuf_scope.get_deadline()); i++;
			
			stmt.execute();
			
			query = "SELECT * FROM manuf_scope WHERE ms_id = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setString(1, manuf_scope.get_ms_id());
			ResultSet r = stmt.executeQuery();
			r.next();
			manuf_scope_id = r.getInt("id");
	
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return manuf_scope_id;
	}

	/**
	 * List manufacturing scope(s) from the database.
	 *
	 * @param  Manuf_scope       the manufacturing scope(s) what is searched for
	 * @return List<Manuf_scope> ArrayList of the manufacturing scope(s)
	 */
	@Override
	public List<Manuf_scope> get(Manuf_scope manuf_scope) {
		
		List<Manuf_scope> msc_list = new ArrayList<Manuf_scope>();

		try {	
			String query = "SELECT * FROM manuf_scope";
			if (manuf_scope.get_id() != null || manuf_scope.get_ms_id() != null || manuf_scope.get_is_ordered() != null || manuf_scope.get_deadline() != null)	
				query += " WHERE";
			if (manuf_scope.get_id() != null)									
				query += " id = ?";
			if (manuf_scope.get_id() != null && manuf_scope.get_ms_id() != null)
				query += " AND";
			if (manuf_scope.get_ms_id() != null)									
				query += " ms_id = ?";
			if ((manuf_scope.get_id() != null || manuf_scope.get_ms_id() != null) && manuf_scope.get_is_ordered() != null)
				query += " AND";
			if (manuf_scope.get_is_ordered() != null)									
				query += " is_ordered = ?";
			if ((manuf_scope.get_id() != null || manuf_scope.get_ms_id() != null || manuf_scope.get_is_ordered() != null) && manuf_scope.get_deadline() != null)
				query += " AND";
			if (manuf_scope.get_deadline() != null)									
				query += " deadline = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (manuf_scope.get_id() != null)			{ stmt.setInt(i, manuf_scope.get_id()); 			i++; } 
			if (manuf_scope.get_ms_id() != null)		{ stmt.setString(i, manuf_scope.get_ms_id()); 		i++; }
			if (manuf_scope.get_is_ordered() != null)	{ stmt.setInt(i, manuf_scope.get_is_ordered()); 	i++; } 
			if (manuf_scope.get_deadline() != null) 	{ stmt.setString(i, manuf_scope.get_deadline());	i++; }

			ResultSet r = stmt.executeQuery();
			Manuf_scope msc = null;
			while (r.next()){
				msc = new Manuf_scope();
				msc.set_id(r.getInt("id"));
				msc.set_ms_id(r.getString("ms_id"));
				msc.set_is_ordered(r.getInt("is_ordered"));
				msc.set_deadline(r.getString("deadline"));
				msc_list.add(msc);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return msc_list;
	}
	
	/**
	 * Delete manufacturing scope(s) from the database.
	 * With null Manuf_scope all manufacturing scope will be deleted!
	 *
	 * @param  Manuf_scope the manufacturing scope(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Manuf_scope manuf_scope) {
		try {	
			String query = "DELETE FROM manuf_scope ";
			if (manuf_scope.get_id() != null || manuf_scope.get_ms_id() != null || manuf_scope.get_is_ordered() != null || manuf_scope.get_deadline() != null)	
				query += " WHERE";
			if (manuf_scope.get_id() != null)									
				query += " id = ?";
			if (manuf_scope.get_id() != null && manuf_scope.get_ms_id() != null)
				query += " AND";
			if (manuf_scope.get_ms_id() != null)									
				query += " ms_id = ?";
			if ((manuf_scope.get_id() != null || manuf_scope.get_ms_id() != null) && manuf_scope.get_is_ordered() != null)
				query += " AND";
			if (manuf_scope.get_is_ordered() != null)									
				query += " is_ordered = ?";
			if ((manuf_scope.get_id() != null || manuf_scope.get_ms_id() != null || manuf_scope.get_is_ordered() != null) && manuf_scope.get_deadline() != null)
				query += " AND";
			if (manuf_scope.get_deadline() != null)									
				query += " deadline = ?";

			java.sql.PreparedStatement stmt = conn.prepareStatement(query);

			int i=1;
			if (manuf_scope.get_id() != null)			{ stmt.setInt(i, manuf_scope.get_id()); 			i++; } 
			if (manuf_scope.get_ms_id() != null)		{ stmt.setString(i, manuf_scope.get_ms_id()); 		i++; }
			if (manuf_scope.get_is_ordered() != null)	{ stmt.setInt(i, manuf_scope.get_is_ordered()); 	i++; } 
			if (manuf_scope.get_deadline() != null) 	{ stmt.setString(i, manuf_scope.get_deadline());	i++; }

			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
	
	/**
	 * Update one manufacturing scope in the database, search by its id or ms_id.
	 *
	 * @param  Manuf_scope the manufacturing scope what will be updated
	 * @return void
	 */
	@Override
	public void update(Manuf_scope manuf_scope) {
		if (manuf_scope.get_id() == null && manuf_scope.get_ms_id() == null){
			System.out.print("both id and ms_id is null!");
			return;
		}
		if (manuf_scope.get_is_ordered() == null && manuf_scope.get_deadline() == null){
			System.out.print("both is_ordered and deadline is null!");
			return;
		}
		
		try {	
			String query = "UPDATE manuf_scope SET";
			if (manuf_scope.get_is_ordered() != null)
				query += " is_ordered = ?";
			if (manuf_scope.get_is_ordered() != null && manuf_scope.get_deadline() != null)
				query += " AND";
			if (manuf_scope.get_deadline() != null)
				query += " deadline = ?";
			query += " WHERE";
			if (manuf_scope.get_id() != null)
				query += " id = ?";
			if (manuf_scope.get_id() != null && manuf_scope.get_ms_id() != null)
				query += " AND";
			if (manuf_scope.get_ms_id() != null)
				query += " ms_id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (manuf_scope.get_is_ordered() != null)	{stmt.setInt(i, manuf_scope.get_is_ordered()); 	i++;}
			if (manuf_scope.get_deadline() != null)		{stmt.setString(i, manuf_scope.get_deadline()); i++;}	
			if (manuf_scope.get_id() != null)			{stmt.setInt(i, manuf_scope.get_id()); 			i++;}
			if (manuf_scope.get_ms_id() != null)		{stmt.setString(i, manuf_scope.get_ms_id()); 	i++;}
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;
	}
}
