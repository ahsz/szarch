package com.sample.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import java.util.stream.Collectors;
import javax.swing.SpringLayout;


import com.sample.ejb.UserService;
import com.sample.ejb.UserServiceImpl;
import com.sample.jpa.entities.User;

public class LoginPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	public LoginPanel() {
		addLoginFields();
	}
	
	public void addLoginFields(){
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblKeremJelentkezzenBe = new JLabel("Kerem jelentkezzen be!");
		add(lblKeremJelentkezzenBe);
		
		JLabel lblFelhasznalonev = new JLabel("Felhasznalonev:");
		springLayout.putConstraint(SpringLayout.NORTH, lblFelhasznalonev, 66, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblFelhasznalonev, 41, SpringLayout.WEST, this);
		add(lblFelhasznalonev);
		
		JLabel lblJelszo = new JLabel("Jelszo:");
		springLayout.putConstraint(SpringLayout.WEST, lblKeremJelentkezzenBe, 0, SpringLayout.WEST, lblJelszo);
		springLayout.putConstraint(SpringLayout.NORTH, lblJelszo, 25, SpringLayout.SOUTH, lblFelhasznalonev);
		springLayout.putConstraint(SpringLayout.EAST, lblJelszo, 0, SpringLayout.EAST, lblFelhasznalonev);
		add(lblJelszo);
		
		textField = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, lblKeremJelentkezzenBe, -24, SpringLayout.NORTH, textField);
		springLayout.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblFelhasznalonev);
		springLayout.putConstraint(SpringLayout.WEST, textField, 6, SpringLayout.EAST, lblFelhasznalonev);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblJelszo);
		springLayout.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblJelszo);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnBejelentkezes = new JButton("Bejelentkezes");
		springLayout.putConstraint(SpringLayout.NORTH, btnBejelentkezes, 17, SpringLayout.SOUTH, textField_1);
		springLayout.putConstraint(SpringLayout.WEST, btnBejelentkezes, 0, SpringLayout.WEST, textField);
		add(btnBejelentkezes);
		
		JButton btnRegisztracio = new JButton("Regisztracio");
		springLayout.putConstraint(SpringLayout.WEST, btnRegisztracio, 6, SpringLayout.EAST, btnBejelentkezes);
		springLayout.putConstraint(SpringLayout.SOUTH, btnRegisztracio, 0, SpringLayout.SOUTH, btnBejelentkezes);
		add(btnRegisztracio);

		
		
		
		btnRegisztracio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					testRemoteEJB();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblKeremJelentkezzenBe.setText("belogolva");

			}
		});
		
		btnBejelentkezes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					testRemoteEJB2();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblKeremJelentkezzenBe.setText("regelve");

			}
		});
	
	}
	
	
	
	
	private static void testRemoteEJB2() throws NamingException {


		final UserService ejb = lookupRemoteEJB();
        String s = ejb.echo("Frank"); 
        System.out.println(s);
        
        
        // Add new user
        
		User user = new User();
	
		user.setName("andraskaaaaaaa");
		user.setPassword("password");
		user.setRole_id(1);

		int i = ejb.addUser(user);
		System.out.println(i);
		
        
        // List users
        /*
        User user = new User();
    
		List<User> usr_list = ejb.getUser(user);

		for (int i = 0; i < usr_list.size(); i++){
			System.out.println(usr_list.get(i));
		}
		*/
        
        // Update user
        /*
		User user = new User();
		
		user.setId(2);
		user.setName("andras_updated");
		user.setPassword("password_updated");
		user.setRole_id(1);
		
		int i  = ejb.updUser(user);
		System.out.println(i);
		*/
        
        // Delete user
        
        /*
        User user = new User();
       
		user.setId(5);
		user.setName("andraska");
		user.setPassword("password");
		user.setRole_id(1);
		int i  = ejb.remUser(user);
		System.out.println(i);
		*/
	
        // Add new role
        /*
		Role role = new Role();
	
		role.setName("boss");

		int i = role_ejb.addRole(role);
		System.out.println(i);
		*/
	}

	
	



	private static void testRemoteEJB() throws NamingException {

		final UserService ejb = lookupRemoteEJB();

		ArrayList<UserLocal> bela=  (ArrayList<UserLocal>)ejb.getUser();
		String jozsi=bela.get(0).geXtName();
		//UserLocal valaki= bela.get(0);

		System.out.println(bela);
	}

	private static UserService lookupRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "Anyagbeszer";
		final String distinctName = "";
		final String beanName = UserServiceImpl.class.getSimpleName();

		final String viewClassName = UserService.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (UserService) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}
}
