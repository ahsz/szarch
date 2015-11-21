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
		Complex complex = new Complex();
		Component component = new Component();

		
		/**
		 *  Clear DB
		 */
		user.delete(user);
		role.delete(role);
		contain.delete(contain);
		order.delete(order);
		manuf_scope.delete(manuf_scope);
		product.delete(product);
		complex.delete(complex);
		component.delete(component);

		
		
		/**
		 *  User class test
		 */	
		/*
		role.set_name("boss");
		role.add(role);
	
		user.set_name("Andras");
		user.set_pw("akarmi");
		user.set_role_id(role.get(role).get(0).get_id());
		user.add(user);
		
		user.set_name("Keremi");
		user.set_pw("akarmi");
		user.set_role_id(role.get(role).get(0).get_id());
		user.add(user);
		
		user.set_id(null);
		user.set_name("Andras");
		user.set_pw(null);
		user.set_role_id(1);
	
		user = new User();
		System.out.println(user.get(user).get(0));
		
		user.set_id(null);
		user.set_name("Keremi");
		user.set_pw(null);
		user.set_role_id(null);

		user.delete(user);
		
		user = new User();
		user.set_id(user.get(user).get(0).get_id());
		user.set_name("Andras2");
		user.set_pw(null);
		user.set_role_id(null);
		
		user.update(user);
		System.out.println(user.get(user).get(0));
		
		user = new User();	
		user.delete(user);
		role.delete(role);
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

		role = new Role();
		System.out.println(role.get(role).get(0));
		
		role.set_id(null);
		role.set_name("slave");

		role.delete(role);
		
		role = new Role();
		role.set_id(role.get(role).get(0).get_id());
		role.set_name("master2");
	
		role.update(role);
		role = new Role();
		System.out.println(role.get(role).get(0));
		
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
	
		
		manuf_scope = new Manuf_scope();
		System.out.println(manuf_scope.get(manuf_scope).get(0));
		
		manuf_scope.set_id(null);
		manuf_scope.set_deadline("20141201");

		manuf_scope.delete(manuf_scope);
		
		manuf_scope.set_id(null);
		manuf_scope.set_ms_id("manuf_scope1");
		manuf_scope.set_is_ordered(null);
		manuf_scope.set_deadline("20160101");
	
		manuf_scope.update(manuf_scope);
		manuf_scope = new Manuf_scope();
		System.out.println(manuf_scope.get(manuf_scope).get(0));
		
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
		
		order.delete(order);
		
		order = new Order();
		System.out.println(order.get(order).get(0));
				
		order.set_manuf_scope_id(manuf_scope.get(manuf_scope).get(0).get_id());
		order.set_product_id(product.get(product).get(0).get_id());
		order.set_number(20);
		
		order.update(order);
		order = new Order();
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


		product = new Product();
		System.out.println(product.get(product).get(0));
		
		product.set_id(null);
		product.set_name("hammer");

		product.delete(product);
		
		product = new Product();
		product.set_id(product.get(product).get(0).get_id());
		product.set_name("big_axe");
	
		product.update(product);
		product = new Product();
		System.out.println(product.get(product).get(0));
		
		product = new Product();
		product.delete(product);
		*/
		
		/**
		 *  Contain class test
		 */	
		/*
		product.set_name("product1");
		product.add(product);
		
		component.set_name("screw");
		component.set_is_complex(0);
		component.set_purch_time(5);
		component.set_price(50);
		component.add(component);
		
		product.set_name("product2");
		product.add(product);
		
		component.set_name("nail");
		component.set_is_complex(0);
		component.set_purch_time(5);
		component.set_price(50);
		component.add(component);
		
		product = new Product();
		component = new Component();
		
		contain.set_product_id(product.get(product).get(0).get_id());
		contain.set_component_id(component.get(component).get(0).get_id());
		contain.set_number(10);
		contain.add(contain);
		
		contain.set_product_id(product.get(product).get(1).get_id());
		contain.set_component_id(component.get(component).get(1).get_id());
		contain.set_number(15);
		contain.add(contain);
		
		System.out.println(contain.get(contain).get(0));
		
		contain.delete(contain);
				
		contain.set_product_id(product.get(product).get(0).get_id());
		contain.set_component_id(component.get(component).get(0).get_id());
		contain.set_number(20);
		
		
		contain.update(contain);
		contain = new Contain();
		System.out.println(contain.get(contain).get(0));

		contain = new Contain();
		product = new Product();
		component = new Component();
		contain.delete(contain);
		product.delete(product);
		component.delete(component);
		*/
	
		/**
		 *  Component class test
		 */
		/*
		component.set_name("screw");
		component.set_is_complex(0);
		component.set_purch_time(10);
		component.set_price(100);
		component.add(component);
		
		component.set_name("nail");
		component.set_is_complex(0);
		component.set_purch_time(20);
		component.set_price(200);
		component.add(component);
	
		
		component = new Component();
		System.out.println(component.get(component).get(0));
		
		component.set_id(null);
		component.set_name("nail");

		component.delete(component);
		
		component = new Component();
		component.set_id(component.get(component).get(0).get_id());
		component.set_is_complex(1);
	
		component.update(component);
		
		component = new Component();
		System.out.println(component.get(component).get(0));
		
		component = new Component();
		component.delete(component);
		*/
		
		
		/**
		 *  Complex class test
		 */	
		/*
		component.set_name("chair");
		component.set_is_complex(1);
		component.set_purch_time(5);
		component.set_price(50);
		component.add(component);
		
		component.set_name("chair leg");
		component.set_is_complex(0);
		component.set_purch_time(1);
		component.set_price(10);
		component.add(component);
		
		component.set_name("bed");
		component.set_is_complex(1);
		component.set_purch_time(10);
		component.set_price(100);
		component.add(component);
		
		component.set_name("bed leg");
		component.set_is_complex(0);
		component.set_purch_time(2);
		component.set_price(20);
		component.add(component);
		
		component = new Component();
		complex.set_cmpnt_container_id(component.get(component).get(0).get_id());
		complex.set_cmpnt_contained_id(component.get(component).get(1).get_id());
		complex.set_number(4);
		complex.add(complex);
		
		component = new Component();
		complex.set_cmpnt_container_id(component.get(component).get(2).get_id());
		complex.set_cmpnt_contained_id(component.get(component).get(3).get_id());
		complex.set_number(4);
		complex.add(complex);
		
		complex = new Complex();
		System.out.println(complex.get(complex).get(0));

		
		complex.set_cmpnt_container_id(component.get(component).get(2).get_id());
		complex.set_cmpnt_contained_id(component.get(component).get(3).get_id());
		complex.delete(complex);
				
		complex.set_cmpnt_container_id(component.get(component).get(0).get_id());
		complex.set_cmpnt_contained_id(component.get(component).get(1).get_id());
		complex.set_number(10);

		
		complex.update(complex);
		
		complex = new Complex();
		System.out.println(complex.get(complex).get(0));

		complex = new Complex();
		component = new Component();
		complex.delete(complex);
		component.delete(component);
		*/
		
		/**
		 *  Clear DB
		 */
		
		user = new User();
		role = new Role();
		manuf_scope = new Manuf_scope();
		order = new Order();
		product = new Product();
		contain = new Contain();
		component = new Component();
		complex = new Complex();
	
		user.delete(user);
		role.delete(role);
		contain.delete(contain);
		order.delete(order);
		manuf_scope.delete(manuf_scope);
		product.delete(product);
		component.delete(component);
		complex.delete(complex);
		
		
	}
}
