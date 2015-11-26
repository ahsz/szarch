package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contain implements Utility<Contain> {
	private java.lang.Integer product_id;
	private java.lang.Integer component_id;
	private java.lang.Integer number;

	
	public java.lang.Integer get_product_id() 	{ return product_id; }
	public java.lang.Integer get_component_id() { return component_id; } 
	public java.lang.Integer get_number() 		{ return number; }
	
	public void set_product_id(java.lang.Integer product_id) 		{ this.product_id = product_id; }
	public void set_component_id(java.lang.Integer component_id) 	{ this.component_id = component_id; }
	public void set_number(java.lang.Integer number) 				{ this.number = number; }
	
	public Contain(){
		this.product_id = null;
		this.component_id = null;
		this.number = null;
	}
	
	public String toString() {
		return product_id + "\t" + component_id + "\t" + number;
	}
	
	/**
	 * Add a new contain to the database. 
	 *
	 * @param  Contain the contain what is will be added to the database
	 * @return int   1 - added, 0 - error
	 */
	@Override
	public int add(Contain contain) {

		int contain_id = 0;
		
		if (contain.get_product_id() == null || contain.get_component_id() == null || contain.get_number() == null){
			System.out.print("product_id or compoennt_id or number is null!");
			return contain_id;
		}
		
		try {
			String query = "INSERT INTO contain VALUES (?,?,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setInt(i, contain.get_product_id()); i++;
			stmt.setInt(i, contain.get_component_id()); i++;
			stmt.setInt(i, contain.get_number()); i++;
			
			stmt.execute();
			
			query = "SELECT * FROM contain WHERE product_id = ? AND component_id = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, contain.get_product_id());
			stmt.setInt(2, contain.get_component_id());
			ResultSet r = stmt.executeQuery();
			r.next();
			if (r.getInt("product_id") == contain.get_product_id() && r.getInt("component_id") == contain.get_component_id() && r.getInt("number") == contain.get_number())
				contain_id = 1;
			
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return contain_id;
	}
	
	/**
	 * List contain(s) from the database.
	 *
	 * @param  Contain       the contain(s) what is searched for
	 * @return List<Contain> ArrayList of the contain(s)
	 */
	@Override
	public List<Contain> get(Contain contain) {
		
		List<Contain> cnt_list = new ArrayList<Contain>();

		try {	
			String query = "SELECT * FROM contain";
			if (contain.get_product_id() != null || contain.get_component_id() != null || contain.get_number() != null)	
				query += " WHERE";
			if (contain.get_product_id() != null)									
				query += " product_id = ?";
			if (contain.get_product_id() != null && contain.get_component_id() != null)
				query += " AND";
			if (contain.get_component_id() != null)									
				query += " component_id = ?";
			if ((contain.get_product_id() != null || contain.get_component_id() != null) && contain.get_number() != null)
				query += " AND";
			if (contain.get_number() != null)									
				query += " number = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (contain.get_product_id() != null)		{ stmt.setInt(i, contain.get_product_id()); 	i++; } 
			if (contain.get_component_id() != null)			{ stmt.setInt(i, contain.get_component_id()); 		i++; }
			if (contain.get_number() != null)				{ stmt.setInt(i, contain.get_number()); 			i++; } 


			ResultSet r = stmt.executeQuery();
			Contain cnt = null;
			while (r.next()){
				cnt = new Contain();
				cnt.set_product_id(r.getInt("product_id"));
				cnt.set_component_id(r.getInt("component_id"));
				cnt.set_number(r.getInt("number"));
				cnt_list.add(cnt);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return cnt_list;
	}
	
	/**
	 * Delete contain(s) from the database.
	 * With null Contain all contain will be deleted!
	 *
	 * @param  Contain the contain(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Contain contain) {

		try {	
			String query = "DELETE FROM contain";
			if (contain.get_product_id() != null || contain.get_component_id() != null || contain.get_number() != null)	
				query += " WHERE";
			if (contain.get_product_id() != null)									
				query += " product_id = ?";
			if (contain.get_product_id() != null && contain.get_component_id() != null)
				query += " AND";
			if (contain.get_component_id() != null)									
				query += " component_id = ?";
			if ((contain.get_product_id() != null || contain.get_component_id() != null) && contain.get_number() != null)
				query += " AND";
			if (contain.get_number() != null)									
				query += " number = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (contain.get_product_id() != null)		{ stmt.setInt(i, contain.get_product_id()); 	i++; } 
			if (contain.get_component_id() != null)			{ stmt.setInt(i, contain.get_component_id()); 		i++; }
			if (contain.get_number() != null)				{ stmt.setInt(i, contain.get_number()); 			i++; } 


			stmt.execute();

		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;
	}
	
	/**
	 * Update one contain in the database, search by its product_id and component_id.
	 *
	 * @param  Contain the contain what will be updated
	 * @return void
	 */
	@Override
	public void update(Contain contain) {
		if (contain.get_product_id() == null || contain.get_component_id() == null){
			System.out.print("product_id or component_id is null!");
			return;
		}
		if (contain.get_number() == null){
			System.out.print("number is null!");
			return;
		}
		
		try {	
			String query = "UPDATE contain SET number = ? WHERE product_id = ? AND component_id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
		
			stmt.setInt(1, contain.get_number());
			stmt.setInt(2, contain.get_product_id());
			stmt.setInt(3, contain.get_component_id());

			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;		
	}
}
