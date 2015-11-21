package szarch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Component implements Utility<Component> {
	private java.lang.Integer id;
	private String name;
	private java.lang.Integer is_complex;
	private java.lang.Integer purch_time;
	private java.lang.Integer price;
	
	public java.lang.Integer get_id() { return id; }
	public String get_name() { return name; }
	public java.lang.Integer get_is_complex() { return is_complex; }
	public java.lang.Integer get_purch_time() { return purch_time; }
	public java.lang.Integer get_price() { return price; }
	
	public void set_id(java.lang.Integer id) { this.id = id; }
	public void set_name(String name) { this.name = name; }
	public void set_is_complex(java.lang.Integer is_complex) { this.is_complex = is_complex; }
	public void set_purch_time(java.lang.Integer purch_time) { this.purch_time = purch_time; }
	public void set_price(java.lang.Integer price) { this.price = price; }
	
	public Component(){
		this.id = null;
		this.name = null;
		this.is_complex = null;
		this.purch_time = null;
		this.price = null;
	}

	public String toString() {
		return id + "\t" + name + "\t" + is_complex + "\t" + purch_time + "\t" + price;
	}
	
	/**
	 * Add a new component to the database. 
	 *
	 * @param  Component the component what is will be added to the database
	 * @return int   1 - added, else - the database id of the newly added user
	 */
	@Override
	public int add(Component component) {
		int component_id = 0;
		
		if (component.get_id() != null){
			System.out.print("id is not null!");
			return component_id;
		}
		
		if (component.get_name() == null || component.get_is_complex() == null || component.get_purch_time() == null || component.get_price() == null){
			System.out.print("name or is_complex or purch_time or price is null!");
			return component_id;
		}
		
		try {
			String query = "INSERT INTO component VALUES (null,?,?,?,?)";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i = 1;
			stmt.setString(i, component.get_name()); i++;
			stmt.setInt(i, component.get_is_complex()); i++;
			stmt.setInt(i, component.get_purch_time()); i++;
			stmt.setInt(i, component.get_price()); i++;
			
			stmt.execute();
			
			query = "SELECT * FROM component WHERE name = ?";	
			stmt = conn.prepareStatement(query);
			stmt.setString(1, component.get_name());
			ResultSet r = stmt.executeQuery();
			r.next();
			component_id = r.getInt("id");
	
		} catch (SQLException ex) {
			   System.out.println("SQLException: " + ex.getMessage());
			   System.out.println("SQLState: " + ex.getSQLState());
			   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return component_id;
	}
	
	/**
	 * List component(s) from the database.
	 *
	 * @param  Component the component(s) what is searched for
	 * @return List<Compoent> ArrayList of the component(s)
	 */
	@Override
	public List<Component> get(Component component) {
		List<Component> cmp_list = new ArrayList<Component>();

		try {	
			String query = "SELECT * FROM component";
			if (component.get_id() != null || component.get_name() != null || component.get_is_complex() != null || component.get_purch_time() != null || component.get_price() != null)	
				query += " WHERE";
			if (component.get_id() != null)									
				query += " id = ?";
			if (component.get_id() != null && component.get_name() != null)
				query += " AND";
			if (component.get_name() != null)									
				query += " name = ?";
			if ((component.get_id() != null || component.get_name() != null) && component.get_is_complex() != null)
				query += " AND";
			if (component.get_is_complex() != null)									
				query += " is_complex = ?";
			if ((component.get_id() != null || component.get_name() != null || component.get_is_complex() != null) && component.get_purch_time() != null)
				query += " AND";
			if (component.get_purch_time() != null)									
				query += " purch_time = ?";
			if ((component.get_id() != null || component.get_name() != null || component.get_is_complex() != null || component.get_purch_time() != null) && component.get_price() != null)
				query += " AND";
			if (component.get_price() != null)									
				query += " price = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (component.get_id() != null)			{ stmt.setInt(i, component.get_id()); 			i++; } 
			if (component.get_name() != null)		{ stmt.setString(i, component.get_name()); 		i++; }
			if (component.get_is_complex() != null)	{ stmt.setInt(i, component.get_is_complex()); 	i++; } 
			if (component.get_purch_time() != null) { stmt.setInt(i, component.get_purch_time());	i++; }
			if (component.get_price() != null) 		{ stmt.setInt(i, component.get_price());		i++; }

			ResultSet r = stmt.executeQuery();
			Component cmp = null;
			while (r.next()){
				cmp = new Component();
				cmp.set_id(r.getInt("id"));
				cmp.set_name(r.getString("name"));
				cmp.set_is_complex(r.getInt("is_complex"));
				cmp.set_purch_time(r.getInt("purch_time"));
				cmp.set_price(r.getInt("price"));
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
	 * Delete component(s) from the database.
	 * With null Component all component will be deleted!
	 *
	 * @param  Component the component(s) what will be deleted
	 * @return void
	 */
	@Override
	public void delete(Component component) {
		try {	
			String query = "DELETE FROM component";
			if (component.get_id() != null || component.get_name() != null || component.get_is_complex() != null || component.get_purch_time() != null || component.get_price() != null)	
				query += " WHERE";
			if (component.get_id() != null)									
				query += " id = ?";
			if (component.get_id() != null && component.get_name() != null)
				query += " AND";
			if (component.get_name() != null)									
				query += " name = ?";
			if ((component.get_id() != null || component.get_name() != null) && component.get_is_complex() != null)
				query += " AND";
			if (component.get_is_complex() != null)									
				query += " is_complex = ?";
			if ((component.get_id() != null || component.get_name() != null || component.get_is_complex() != null) && component.get_purch_time() != null)
				query += " AND";
			if (component.get_purch_time() != null)									
				query += " purch_time = ?";
			if ((component.get_id() != null || component.get_name() != null || component.get_is_complex() != null || component.get_purch_time() != null) && component.get_price() != null)
				query += " AND";
			if (component.get_price() != null)									
				query += " price = ?";
			
			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (component.get_id() != null)			{ stmt.setInt(i, component.get_id()); 			i++; } 
			if (component.get_name() != null)		{ stmt.setString(i, component.get_name()); 		i++; }
			if (component.get_is_complex() != null)	{ stmt.setInt(i, component.get_is_complex()); 	i++; } 
			if (component.get_purch_time() != null) { stmt.setInt(i, component.get_purch_time());	i++; }
			if (component.get_price() != null) 		{ stmt.setInt(i, component.get_price());		i++; }

			stmt.execute();

		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;
		
	}
	
	/**
	 * Update one component in the database, search by its id.
	 *
	 * @param  Component the component what will be updated
	 * @return void
	 */
	@Override
	public void update(Component component) {
		if (component.get_id() == null){
			System.out.print("id is null!");
			return;
		}
		if (component.get_name() == null && component.get_is_complex() == null && component.get_purch_time() == null && component.get_price() == null){
			System.out.print("both name and is_complex and purch_time and price is null!");
			return;
		}
		
		try {	
			String query = "UPDATE component SET";
			if (component.get_name() != null)
				query += " name = ?";
			if (component.get_name() != null && component.get_is_complex() != null)
				query += " AND";
			if (component.get_is_complex() != null)
				query += " is_complex = ?";
			if ((component.get_name() != null || component.get_is_complex() != null) && component.get_purch_time() != null)
				query += " AND";
			if (component.get_purch_time() != null)
				query += " purch_time = ?";
			if ((component.get_name() != null || component.get_is_complex() != null || component.get_purch_time() != null) && component.get_price() != null)
				query += " AND";
			if (component.get_price() != null)
				query += " price = ?";
			query += " WHERE";
			if (component.get_id() != null)
				query += " id = ?";

			java.sql.PreparedStatement stmt = conn.prepareStatement(query);
			
			int i=1;
			if (component.get_name() != null)			{stmt.setString(i, component.get_name()); 		i++;}
			if (component.get_is_complex() != null)		{stmt.setInt(i, component.get_is_complex()); 	i++;}	
			if (component.get_purch_time() != null)		{stmt.setInt(i, component.get_purch_time()); 	i++;}
			if (component.get_price() != null)			{stmt.setInt(i, component.get_price()); 		i++;}
			if (component.get_id() != null)				{stmt.setInt(i, component.get_id()); 			i++;}
			
			stmt.execute();
			
		} catch (SQLException ex) {
		   System.out.println("SQLException: " + ex.getMessage());
		   System.out.println("SQLState: " + ex.getSQLState());
		   System.out.println("VendorError: " + ex.getErrorCode());
		}
		return;
	}
}
