package com.sample.client;
/*
 * Késztermékek megtekintése
 */

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.sample.ejb.ComponentService;
import com.sample.ejb.ComponentServiceImpl;
import com.sample.ejb.ContainService;
import com.sample.ejb.ContainServiceImpl;
import com.sample.ejb.ProductService;
import com.sample.ejb.ProductServiceImpl;
import com.sample.jpa.entities.Component;
import com.sample.jpa.entities.Contain;
import com.sample.jpa.entities.Product;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;

public class ProductBrowsePanel extends JPanel {
	private JLabel txtTitleName;
	private JLabel txtTitlePassword;

	private JTextField inputName;
	private JTextField inputPassword;
	private JButton loginButton;
	private JButton registerButton;
	
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public ProductBrowsePanel() throws NamingException {
		final ComponentService ejb = lookupRemoteEJB();
		JPanel currentPanel=this;
		final ContainService ejbContain = lookupContainsRemoteEJB();
		final ProductService ejbProduct = lookupProductRemoteEJB();
		setLayout(new MigLayout("", "[133px,grow][grow][][][][][][]", "[14px][][][][][]"));
		String[] productList = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		
		JLabel lblKesztermekekMegtekintese = new JLabel("Kesztermekek megtekintese");
		this.add(lblKesztermekekMegtekintese, "cell 0 0,alignx left,aligny top");
		JComboBox comboBox = new JComboBox(ejbProduct.getAllProductNames());
		add(comboBox, "cell 0 1,growx");
		


		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					Product prodToUpdate = ejbProduct.getProduct(comboBox.getSelectedItem().toString());

				//	txtTermeknevSecond.setText(prodToUpdate.getName());
					currentPanel.removeAll();
					currentPanel.add(lblKesztermekekMegtekintese, "cell 0 0,alignx left,aligny top");
					currentPanel.add(comboBox, "cell 0 1,growx");
					// combobox feltoltese nevekkel
					String[] components = ejb.getAllComponentNames();
					String[] complexComponents = ejb.getAllComplexNames();
					String[] allComponents = new String[components.length + complexComponents.length];
					for (int i = 0; i < allComponents.length; i++) {
						if (i < components.length) {
							allComponents[i] = components[i];
						} else {
							allComponents[i] = complexComponents[i - components.length];
						}

					}

					List<Contain> cont_list = new ArrayList<Contain>();
					cont_list = ejbContain.getContainToDelete(prodToUpdate.getId());

					for (int i = 0; i < cont_list.size(); i++) {
						
						
						JLabel lblTermek = new JLabel(ejb.getComponent(cont_list.get(i).getComponent_id()).getName());
						add(lblTermek, "cell 0 "+ 2+i);
						
						JLabel lblTermekDb = new JLabel(cont_list.get(i).getNumber().toString());
						add(lblTermekDb, "cell 1 "+ 2+i);
					}

					currentPanel.revalidate();
					validate();

				}
			}
		});

		
		
		

	}
	
	private static ComponentService lookupRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "Anyagbeszer";
		final String distinctName = "";
		final String beanName = ComponentServiceImpl.class.getSimpleName();

		final String viewClassName = ComponentService.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (ComponentService) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}

	private static ContainService lookupContainsRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "Anyagbeszer";
		final String distinctName = "";
		final String beanName = ContainServiceImpl.class.getSimpleName();

		final String viewClassName = ContainService.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (ContainService) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}

	private static ProductService lookupProductRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "Anyagbeszer";
		final String distinctName = "";
		final String beanName = ProductServiceImpl.class.getSimpleName();

		final String viewClassName = ProductService.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (ProductService) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}
}
