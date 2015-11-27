package com.sample.client;
/*
 * Admin felület
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;

import com.sample.ejb.UserService;
import com.sample.ejb.UserServiceImpl;
import com.sample.jpa.entities.User;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;

public class AdminPanel extends JPanel {

	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public AdminPanel() throws NamingException {
		
		
		//TEST
		String[] roleStrings = { "Manager", "Gyartasvezeto","Beszerzo","default"};
		//Eddig
		


		final UserService ejb = lookupRemoteEJB();

		
		/*User user=new User();
		
		
		List<User> usr_list=new ArrayList<User>();
		usr_list= ejb.getUser(user);*/
		
		String[] userNames=ejb.getAllUserNames();
		
		setLayout(new MigLayout("", "[127.00px][157.00px][143px]", "[14px][23px]"));
		
		JLabel lblAdminFelulet = new JLabel("Admin felulet");
		add(lblAdminFelulet, "cell 1 0,alignx center,aligny top");
		
		JComboBox comboBoxUserList = new JComboBox(userNames);
		add(comboBoxUserList, "cell 0 1,alignx left,aligny center");
		
		JComboBox comboBoxUserRoles = new JComboBox(roleStrings);
		add(comboBoxUserRoles, "cell 1 1,alignx left,aligny center");
		
		JButton btnJogosultsagBeallitasa = new JButton("Jogosultsag Beallitasa");
		btnJogosultsagBeallitasa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User updateUser=ejb.getUser(comboBoxUserList.getSelectedIndex()+1);
				updateUser.setRole_id(comboBoxUserRoles.getSelectedIndex()+1);
				ejb.updUser(updateUser);
			}
		});
		add(btnJogosultsagBeallitasa, "cell 2 1,growx,aligny top");
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
