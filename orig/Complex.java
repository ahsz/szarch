package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Complex implements Utility<Complex> {
	private java.lang.Integer cmpnt_container_id;
	private java.lang.Integer cmpnt_contained_id;
	private java.lang.Integer number;
	
	public java.lang.Integer get_cmpnt_container_id() 	{ return cmpnt_container_id; }
	public java.lang.Integer get_cmpnt_contained_id() 	{ return cmpnt_contained_id; }
	public java.lang.Integer get_number() 				{ return number; }
	
	public void set_cmpnt_container_id(java.lang.Integer cmpnt_container_id)	{ this.cmpnt_container_id = cmpnt_container_id; }
	public void set_cmpnt_contained_id(java.lang.Integer cmpnt_contained_id) 	{ this.cmpnt_contained_id = cmpnt_contained_id; }
	public void set_number(java.lang.Integer number) 							{ this.number = number; }
	
	public Complex(){
		this.cmpnt_container_id = null;
		this.cmpnt_contained_id = null;
		this.number = null;
	}

	public String toString() {
		return cmpnt_container_id + "\t" + cmpnt_contained_id + "\t" + number;
	}
	
	/**
	 * Add a new complex to the database. 
	 *
	 * @param  Complex the complex what is will be added to the database
	 * @return int   1 - added, 0 - error
	 */
	@Override
	public int add(Complex complex) {

		int complex_id = 0;
		
		if (complex.get_cmpnt_container_id() == null || complex.get_cmpnt_contained_id() == null || complex.get_number() == null){
			System.out.print("cmpnt_container_id or cmpnt_contained_id or number is null!");
			return complex_id;
		}
		
		try {
			String query = "INSERT INTO complex VALUES (?,?,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setInt(i, complex.get_cmpnt_container_id()); i++;
			stmt.setInt(i, complex.get_cmpnt_contained_id()); i++;
			stmt.setInt(i, complex.get_number()); i++;
			
			stmt.execute();
			
			query = "SELECT * FROM complex WHERE cmpnt_container_id = ? AND cmpnt_contained_id = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, complex.get_cmpnt_container_id());
			stmt.setInt(2, complex.get_cmpnt_contained_id());
			ResultSet r = stmt.executeQuery();
			r.next();
			if (r.getInt("cmpnt_container_id") == complex.get_cmpnt_container_id() && r.getInt("cmpnt_contained_id") == complex.get_cmpnt_contained_id() && r.getInt("number") == complex.get_number())
				complex_id = 1;
			
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return complex_id;
	}
	
	/**
	 * List complex(s) from the database.
	 *
	 * @param  Complex       the complex(s) what is searched for
	 * @return List<Complex> ArrayList of the complex(s)
	 */
	@Override
	public List<Complex> get(Complex complex) {

		List<Complex> cmp_list = new ArrayList<Complex>();

		try {	
			String query = "SELECT * FROM complex";
			if (complex.get_cmpnt_container_id() != null || complex.get_cmpnt_contained_id() != null || complex.get_number() != null)	
				query += " WHERE";
			if (complex.get_cmpnt_container_id() != null)									
				query += " cmpnt_container_id = ?";
			if (complex.get_cmpnt_container_id() != null && complex.get_cmpnt_contained_id() != null)
				query += " AND";
			if (complex.get_cmpnt_contained_id() != null)									
				query += " cmpnt_contained_id = ?";
			if ((complex.get_cmpnt_container_id() != null || complex.get_cmpnt_contained_id() != null) && complex.get_number() != null)
				query += " AND";
			if (complex.get_number() != null)									
				query += " number = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (complex.get_cmpnt_container_id() != null)		{ stmt.setInt(i, complex.get_cmpnt_container_id()); 	i++; } 
			if (complex.get_cmpnt_contained_id() != null)		{ stmt.setInt(i, complex.get_cmpnt_contained_id()); 	i++; }
			if (complex.get_number() != null)					{ stmt.setInt(i, complex.get_number()); 				i++; } 


			ResultSet r = stmt.executeQuery();
			Complex cmp = null;
			while (r.next()){
				cmp = new Complex();
				cmp.set_cmpnt_container_id(r.getInt("cmpnt_container_id"));
				cmp.set_cmpnt_contained_id(r.getInt("cmpnt_contained_id"));
				cmp.set_number(r.getInt("number"));
				cmp_list.add(cmp);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return cmp_list;
	}
	
	/**
	 * Delete complex(s) from the database.
	 * With null Complex all complex will be deleted!
	 *
	 * @param  Complex the complex(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Complex complex) {
		try {	
			String query = "DELETE FROM complex";
			if (complex.get_cmpnt_container_id() != null || complex.get_cmpnt_contained_id() != null || complex.get_number() != null)	
				query += " WHERE";
			if (complex.get_cmpnt_container_id() != null)									
				query += " cmpnt_container_id = ?";
			if (complex.get_cmpnt_container_id() != null && complex.get_cmpnt_contained_id() != null)
				query += " AND";
			if (complex.get_cmpnt_contained_id() != null)									
				query += " cmpnt_contained_id = ?";
			if ((complex.get_cmpnt_container_id() != null || complex.get_cmpnt_contained_id() != null) && complex.get_number() != null)
				query += " AND";
			if (complex.get_number() != null)									
				query += " number = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (complex.get_cmpnt_container_id() != null)		{ stmt.setInt(i, complex.get_cmpnt_container_id()); 	i++; } 
			if (complex.get_cmpnt_contained_id() != null)		{ stmt.setInt(i, complex.get_cmpnt_contained_id()); 	i++; }
			if (complex.get_number() != null)					{ stmt.setInt(i, complex.get_number()); 				i++; } 


			stmt.execute();

		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;
	}
	
	/**
	 * Update one complex in the database, search by its cmpnt_container_id and cmpnt_contained_id.
	 *
	 * @param  Complex the complex what will be updated
	 * @return void
	 */
	@Override
	public void update(Complex complex) {
		if (complex.get_cmpnt_container_id() == null || complex.get_cmpnt_contained_id() == null){
			System.out.print("cmpnt_container_id or cmpnt_contained_id is null!");
			return;
		}
		if (complex.get_number() == null){
			System.out.print("number is null!");
			return;
		}
		
		try {	
			String query = "UPDATE complex SET number = ? WHERE cmpnt_container_id = ? AND cmpnt_contained_id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
		
			stmt.setInt(1, complex.get_number());
			stmt.setInt(2, complex.get_cmpnt_container_id());
			stmt.setInt(3, complex.get_cmpnt_contained_id());

			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;			
	}
}
