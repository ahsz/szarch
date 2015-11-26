package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product implements Utility<Product> {

	private java.lang.Integer id;
	private String name;
	
	public java.lang.Integer get_id() 	{ return id; }
	public String get_name() 			{ return name; }
	
	public void set_id(java.lang.Integer id) 	{ this.id = id; }
	public void set_name(String name) 			{ this.name = name; }
	
	public Product(){
		this.id = null;
		this.name = null;
	}

	public String toString() {
		return id + "\t" + name + "\t";
	}
	
	/**
	 * Add a new product to the database. 
	 *
	 * @param  Product the role what is will be added to the database
	 * @return int     0 - error, else - the database id of the newly added user
	 */
	@Override
	public int add(Product product) {
		int product_id = 0;
		
		if (product.get_id() != null){
			System.out.print("id is not null!");
			return product_id;
		}
		
		if (product.get_name() == null){
			System.out.print("name is null!");
			return product_id;
		}
		
		try {
			String query = "INSERT INTO product VALUES (null,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setString(i, product.get_name()); i++;

			stmt.execute();
			
			query = "SELECT * FROM product WHERE name = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setString(1, product.get_name());
			ResultSet r = stmt.executeQuery();
			r.next();
			product_id = r.getInt("id");
	
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return product_id;
	}

	/**
	 * List product(s) from the database.
	 *
	 * @param  Product       the product(s) what is searched for
	 * @return List<Product> ArrayList of the product(s)
	 */
	@Override
	public List<Product> get(Product product) {
		List<Product> prd_list = new ArrayList<Product>();

		try {	
			String query = "SELECT * FROM product";
			if (product.get_id() != null || product.get_name() != null)	
				query += " WHERE";
			if (product.get_id() != null)									
				query += " id = ?";
			if (product.get_id() != null && product.get_name() != null)
				query += " AND";
			if (product.get_name() != null)									
				query += " name = ?";

			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (product.get_id() != null)			{ stmt.setInt(i, product.get_id()); 		i++; } 
			if (product.get_name() != null) 		{ stmt.setString(i, product.get_name()); 	i++; }

			ResultSet r = stmt.executeQuery();
			Product prd = null;
			while (r.next()){
				prd = new Product();
				prd.set_id(r.getInt("id"));
				prd.set_name(r.getString("name"));;
				prd_list.add(prd);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return prd_list;
	}

	/**
	 * Delete proudct(s) from the database.
	 * With null Product all user will be deleted!
	 *
	 * @param  Product the proudct(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Product product) {
		try {	
			String query = "DELETE FROM product ";
			if (product.get_id() != null || product.get_name() != null)
				query += " WHERE";
			if (product.get_id() != null)									
				query += " id = ?";
			if (product.get_id() != null && product.get_name() != null)
				query += " AND";
			if (product.get_name() != null)									
				query += " name = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (product.get_id() != null) 			{ stmt.setInt(i, product.get_id()); 		i++; }
			if (product.get_name() != null) 		{ stmt.setString(i, product.get_name()); 	i++; }
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}		
		
	}

	/**
	 * Update one product in the database, search by its id..
	 *
	 * @param  Product the product what will be updated
	 * @return void
	 */
	@Override
	public void update(Product product) {
		if (product.get_id() == null){
			System.out.print("id is null!");
			return;
		}
		if (product.get_name() == null){
			System.out.print("name is null!");
			return;
		}
		
		try {	
			String query = "UPDATE product SET name = ? WHERE id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			stmt.setString(i, product.get_name()); 	i++; 
			stmt.setInt(i, product.get_id()); 			i++; 
		
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}		
	}
}
