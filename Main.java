package szarch;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		User user = new User();
		Role role = new Role();
		Manuf_scope manuf_scope = new Manuf_scope();
		Order order = new Order();
		Product product = new Product();
		
		/**
		 *  Clear DB
		 */
		user.delete(user);
		role.delete(role);
		order.delete(order);
		manuf_scope.delete(manuf_scope);
		product.delete(product);
		
		
		/**
		 *  User class test
		 */	
		/*
		user.set_name("Andras");
		user.set_pw("akarmi");
		user.set_role_id(1);
		user.add(user);
		
		user.set_name("Keremi");
		user.set_pw("akarmi");
		user.set_role_id(1);
		user.add(user);
		
		user.set_id(null);
		user.set_name("Andras");
		user.set_pw(null);
		user.set_role_id(1);
	
		List<User> usr_list = new ArrayList<User>();
		
		usr_list = user.get(user);
		
		System.out.println(usr_list.get(0));
		
		user.set_id(null);
		user.set_name("Keremi");
		user.set_pw(null);
		user.set_role_id(null);

		user.delete(user);
		
		user.set_id(usr_list.get(0).get_id());
		user.set_name("Andras2");
		user.set_pw(null);
		user.set_role_id(null);
		
		user.update(user);
		*/
		
		
		/**
		 *  Role class test
		 */
		/*
		role.set_name("master");
		role.add(role);
		
		role.set_name("slave");
		role.add(role);
		
		role.set_id(null);
		role.set_name("master");

	
		List<Role> rol_list = new ArrayList<Role>();
		
		rol_list = role.get(role);
		
		System.out.println(rol_list.get(0));
		
		role.set_id(null);
		role.set_name("slave");

		role.delete(role);
		
		role.set_id(rol_list.get(0).get_id());
		role.set_name("master2");
	
		role.update(role);
		*/
		
		/**
		 *  Manuf_scope class test
		 */
		/*
		manuf_scope.set_ms_id("manuf_scope1");
		manuf_scope.set_is_ordered(0);
		manuf_scope.set_deadline("20151201");
		manuf_scope.add(manuf_scope);
		
		manuf_scope.set_ms_id("manuf_scope2");
		manuf_scope.set_is_ordered(1);
		manuf_scope.set_deadline("20141201");
		manuf_scope.add(manuf_scope);
	
		List<Manuf_scope> msc_list = new ArrayList<Manuf_scope>();
		
		msc_list = manuf_scope.get(manuf_scope);
		
		System.out.println(msc_list.get(0));
		
		manuf_scope.set_id(null);
		manuf_scope.set_deadline("20141201");

		manuf_scope.delete(manuf_scope);
		
		manuf_scope.set_id(null);
		manuf_scope.set_ms_id("manuf_scope1");
		manuf_scope.set_is_ordered(null);
		manuf_scope.set_deadline("20160101");
	
		manuf_scope.update(manuf_scope);
		*/
		
		
		/**
		 *  Order class test
		 */	
		/*
		manuf_scope.set_ms_id("manuf_scope10");
		manuf_scope.set_is_ordered(0);
		manuf_scope.set_deadline("20151201");
		manuf_scope.add(manuf_scope);

		product.set_name("product10");
		product.add(product);
		
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(0).get_id());
		order.set_product_id(product.get(product).get(0).get_id());
		order.set_number(10);
		order.add(order);
		
		manuf_scope.set_ms_id("manuf_scope11");
		manuf_scope.set_is_ordered(0);
		manuf_scope.set_deadline("20151201");
		manuf_scope.add(manuf_scope);
		
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(0).get_id());
		order.set_product_id(product.get(product).get(0).get_id());
		order.set_number(10);
		order.add(order);

		List<Order> ord_list = new ArrayList<Order>();
		
		ord_list = order.get(order);
		
		System.out.println(ord_list.get(0));
		
		order.delete(order);
		
		manuf_scope.set_ms_id("manuf_scope10");
		manuf_scope.set_is_ordered(0);
		manuf_scope.set_deadline("20151201");
		
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(0).get_id());
		order.set_product_id(product.get(product).get(0).get_id());
		order.set_number(15);
		
		order.update(order);
		*/
		
		/**
		 *  Product class test
		 */
		/*
		product.set_name("axe");
		product.add(product);
		
		product.set_name("hammer");
		product.add(product);
		
		product.set_id(null);
		product.set_name("axe");

	
		List<Product> prd_list = new ArrayList<Product>();
		
		prd_list = product.get(product);
		
		System.out.println(prd_list.get(0));
		
		product.set_id(null);
		product.set_name("hammer");

		product.delete(product);
		
		product.set_id(prd_list.get(0).get_id());
		product.set_name("big_axe");
	
		product.update(product);
		*/
		
		/**
		 *  Clear DB
		 */
		user.delete(user);
		role.delete(role);
		order.delete(order);
		manuf_scope.delete(manuf_scope);
		product.delete(product);
	}
}
