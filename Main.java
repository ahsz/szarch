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
		Contain contain = new Contain();
		
		/**
		 *  Clear DB
		 */
		user.delete(user);
		role.delete(role);
		contain.delete(contain);
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
		
		user = new User();	
		user.delete(user);
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

		role = new Role();
		role.delete(role);

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
		
		manuf_scope = new Manuf_scope();
		manuf_scope.delete(manuf_scope);

		*/
		
		
		/**
		 *  Order class test
		 */	
		/*
		manuf_scope.set_ms_id("manuf_scope1");
		manuf_scope.set_is_ordered(0);
		manuf_scope.set_deadline("20151201");
		manuf_scope.add(manuf_scope);

		product.set_name("product1");
		product.add(product);
		
		manuf_scope.set_ms_id("manuf_scope2");
		manuf_scope.set_is_ordered(0);
		manuf_scope.set_deadline("20151201");
		manuf_scope.add(manuf_scope);
		
		product.set_name("product2");
		product.add(product);
		
		manuf_scope = new Manuf_scope();
		product = new Product();
		
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(0).get_id());
		order.set_product_id(product.get(product).get(0).get_id());
		order.set_number(10);
		order.add(order);
		
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(1).get_id());
		order.set_product_id(product.get(product).get(1).get_id());
		order.set_number(15);
		order.add(order);
		
		System.out.println(order.get(order).get(0));
		
		order.delete(order);
				
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(0).get_id());
		order.set_product_id(product.get(product).get(0).get_id());
		order.set_number(20);
		
		order.update(order);
		System.out.println(order.get(order).get(0));

		order = new Order();
		product = new Product();
		contain = new Contain();
		order.delete(order);
		manuf_scope.delete(manuf_scope);
		product.delete(product);
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
		
		product = new Product();
		product.delete(product);
		*/
		
		/**
		 *  Contain class test
		 */	
		/*
		product.set_name("product1");
		product.add(product);
		
		//add component

		product.set_name("product2");
		product.add(product);
		
		//add component
		
		manuf_scope = new Manuf_scope();
		product = new Product();
		
		contain.set_product_id(product.get(product).get(0).get_id());
		contain.set_component_id(1);//(component.get(component).get(0).get_id());
		contain.set_number(10);
		contain.add(contain);
		
		contain.set_product_id(product.get(product).get(1).get_id());
		contain.set_component_id(1);//(component.get(component).get(1).get_id());
		contain.set_number(15);
		contain.add(contain);
		
		System.out.println(contain.get(contain).get(0));
		
		contain.delete(contain);
				
		contain.set_product_id(product.get(product).get(0).get_id());
		contain.set_component_id(1);//(component.get(component).get(0).get_id());
		contain.set_number(20);
		
		contain.update(contain);
		System.out.println(contain.get(contain).get(0));

		contain = new Contain();
		product = new Product();
		//component = new Contain();
		contain.delete(contain);
		product.delete(product);
		//compoent.delete(product);
		*/
	
		
		
		/**
		 *  Clear DB
		 */
		/*
		user = new User();
		role = new Role();
		manuf_scope = new Manuf_scope();
		order = new Order();
		product = new Product();
		contain = new Contain();
		
		user.delete(user);
		role.delete(role);
		contain.delete(contain);
		order.delete(order);
		manuf_scope.delete(manuf_scope);
		product.delete(product);
		*/
	}
}
