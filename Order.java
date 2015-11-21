package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Order implements Utility<Order> {
	private java.lang.Integer manuf_scope_id;
	private java.lang.Integer product_id;
	private java.lang.Integer number;

	public java.lang.Integer get_manuf_scope_id() { return manuf_scope_id; }
	public java.lang.Integer get_product_id() { return product_id; }
	public java.lang.Integer get_number() { return number; }
	
	public void set_manuf_scope_id(java.lang.Integer manuf_scope_id) { this.manuf_scope_id = manuf_scope_id; }
	public void set_product_id(java.lang.Integer product_id) { this.product_id = product_id; }
	public void set_number(java.lang.Integer number) { this.number = number; }

	public Order(){
		this.manuf_scope_id = null;
		this.product_id = null;
		this.number = null;
	}
	
	public String toString() {
		return manuf_scope_id + "\t" + product_id + "\t" + number;
	}
	
	/**
	 * Add a new order to the database. 
	 *
	 * @param  Order the order what is will be added to the database
	 * @return        1 - added, 0 - error
	 */
	@Override
	public int add(Order order) {

		int order_id = 0;
		
		if (order.get_manuf_scope_id() == null || order.get_product_id() == null || order.get_number() == null){
			System.out.print("manuf_scope_id or is_ordered or deadline is null!");
			return order_id;
		}
		
		try {
			String query = "INSERT INTO orders VALUES (?,?,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setInt(i, order.get_manuf_scope_id()); i++;
			stmt.setInt(i, order.get_product_id()); i++;
			stmt.setInt(i, order.get_number()); i++;
			
			stmt.execute();
			
			query = "SELECT * FROM orders WHERE manuf_scope_id = ? AND product_id = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, order.get_manuf_scope_id());
			stmt.setInt(2, order.get_product_id());
			ResultSet r = stmt.executeQuery();
			r.next();
			if (r.getInt("manuf_scope_id") == order.get_manuf_scope_id() && r.getInt("product_id") == order.get_product_id())
				order_id = 1;
			
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return order_id;
	}

	/**
	 * List order(s) from the database.
	 *
	 * @param  Order       the order(s) what is searched for
	 * @return List<Order> ArrayList of the order(s)
	 */
	@Override
	public List<Order> get(Order order) {

		List<Order> ord_list = new ArrayList<Order>();

		try {	
			String query = "SELECT * FROM orders";
			if (order.get_manuf_scope_id() != null || order.get_product_id() != null || order.get_number() != null)	
				query += " WHERE";
			if (order.get_manuf_scope_id() != null)									
				query += " manuf_scope_id = ?";
			if (order.get_manuf_scope_id() != null && order.get_product_id() != null)
				query += " AND";
			if (order.get_product_id() != null)									
				query += " product_id = ?";
			if ((order.get_manuf_scope_id() != null || order.get_product_id() != null) && order.get_number() != null)
				query += " AND";
			if (order.get_number() != null)									
				query += " number = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (order.get_manuf_scope_id() != null)		{ stmt.setInt(i, order.get_manuf_scope_id()); 	i++; } 
			if (order.get_product_id() != null)			{ stmt.setInt(i, order.get_product_id()); 		i++; }
			if (order.get_number() != null)				{ stmt.setInt(i, order.get_number()); 			i++; } 


			ResultSet r = stmt.executeQuery();
			Order ord = null;
			while (r.next()){
				ord = new Order();
				ord.set_manuf_scope_id(r.getInt("manuf_scope_id"));
				ord.set_product_id(r.getInt("product_id"));
				ord.set_number(r.getInt("number"));
				ord_list.add(ord);
			}	
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return ord_list;
	}

	
	/**
	 * Delete order(s) from the database.
	 * With null Order all order will be deleted!
	 *
	 * @param  Order the order(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Order order) {

		List<Order> ord_list = new ArrayList<Order>();

		try {	
			String query = "DELETE FROM orders";
			if (order.get_manuf_scope_id() != null || order.get_product_id() != null || order.get_number() != null)	
				query += " WHERE";
			if (order.get_manuf_scope_id() != null)									
				query += " manuf_scope_id = ?";
			if (order.get_manuf_scope_id() != null && order.get_product_id() != null)
				query += " AND";
			if (order.get_product_id() != null)									
				query += " product_id = ?";
			if ((order.get_manuf_scope_id() != null || order.get_product_id() != null) && order.get_number() != null)
				query += " AND";
			if (order.get_number() != null)									
				query += " number = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (order.get_manuf_scope_id() != null)		{ stmt.setInt(i, order.get_manuf_scope_id()); 	i++; } 
			if (order.get_product_id() != null)			{ stmt.setInt(i, order.get_product_id()); 		i++; }
			if (order.get_number() != null)				{ stmt.setInt(i, order.get_number()); 			i++; } 


			stmt.execute();

		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;
		
	}

	/**
	 * Update one order in the database.
	 *
	 * @param  Order the order what will be updated
	 * @return void
	 */
	@Override
	public void update(Order order) {
		if (order.get_manuf_scope_id() == null && order.get_product_id() == null){
			System.out.print("both manuf_scope_id and product_id is null!");
			return;
		}
		if (order.get_number() == null){
			System.out.print("number is null!");
			return;
		}
		
		try {	
			String query = "UPDATE orders SET number = ? WHERE manuf_scope_id = ? AND product_id = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, order.get_number());
			stmt.setInt(2, order.get_manuf_scope_id());
			stmt.setInt(3, order.get_product_id());

			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;		
	}
}
