package com.sample.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.MessageDigest;
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

import org.apache.commons.codec.digest.DigestUtils;

import com.sample.ejb.RoleService;
import com.sample.ejb.RoleServiceImpl;
import com.sample.ejb.UserService;
import com.sample.ejb.UserServiceImpl;
import com.sample.jpa.entities.Role;
import com.sample.jpa.entities.User;

public class LoginPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	public LoginPanel() throws NamingException {
		addLoginFields();
	}
	
	public void addLoginFields() throws NamingException{
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		String[] roleStrings = { "Manager", "Gyartasvezeto","Beszerzo","default"};
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

		

		final UserService ejb = lookupRemoteEJB();

		
		btnRegisztracio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user=new User();

				User regTest=ejb.getUser(textField.getText());
				
				if(regTest.getId()==null){
					user.setName(textField.getText());
					user.setPassword(DigestUtils.md5Hex( textField_1.getText() ));
					user.setRole_id(4);
					ejb.addUser(user);
					lblKeremJelentkezzenBe.setText("Sikeres regisztracio, kerem jelentkezzen be!");
				
				}else{
					lblKeremJelentkezzenBe.setText("Mar letezik ilyen felhasznalo");
				}

				
			}
		});
		
		btnBejelentkezes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user=new User();
				user.setName(textField.getText());
				user.setPassword(DigestUtils.md5Hex( textField_1.getText() ));
				User loggedInUser=ejb.loginUser(user);
				if(loggedInUser.getId()!=null){
					MainWindow.USER_TYPE=loggedInUser.getRole_id();
					lblKeremJelentkezzenBe.setText("Sikeresen bejelentkezett: " +loggedInUser.getName() + " " + roleStrings[loggedInUser.getRole_id()-1]);
				}else{
					lblKeremJelentkezzenBe.setText("Nincs ilyen felhasznalo, vagy hibas a jelszo.");
				}


			}
		});
	
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
