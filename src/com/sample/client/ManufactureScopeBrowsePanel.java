package com.sample.client;

/*
 * Gyártási célok kezelése
 */

import java.awt.Dimension;
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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.sample.ejb.Manuf_scopeService;
import com.sample.ejb.Manuf_scopeServiceImpl;
import com.sample.ejb.OrdersService;
import com.sample.ejb.OrdersServiceImpl;
import com.sample.ejb.ProductService;
import com.sample.ejb.ProductServiceImpl;
import com.sample.jpa.entities.Contain;
import com.sample.jpa.entities.Manuf_scope;
import com.sample.jpa.entities.Orders;
import com.sample.jpa.entities.Product;
import com.sun.xml.internal.ws.api.Component;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class ManufactureScopeBrowsePanel extends JPanel {

	JPanel panelCont = this;
	JPanel panelFirst = new JPanel();
	JPanel panelSecond = new JPanel();
	JPanel panelThird = new JPanel();
	JButton buttonOne = new JButton("Switch to second panel/workspace");
	JButton buttonSecond = new JButton("Switch to first panel/workspace");
	CardLayout cl = new CardLayout();
	String[] modeString = { "Beszerzendo alkatreszek listazasa", "Gyartasi celok megrendelese" };
	private final JComboBox comboBoxFunctionSwitcher1 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher2 = new JComboBox(modeString);

	private final JLabel txtTermeknevFirst = new JLabel();
	private final JLabel txtTermeknevSecond = new JLabel();
	private final JLabel txtTermekDarabFirst = new JLabel();

	private final JLabel lblAlkatreszNeve = new JLabel("Alkatresz neve");
	private final JLabel lblAlkatreszDarab = new JLabel("Alkatresz darab");
	private final JButton btnMegrendel = new JButton("Megrendel");
	/**
	 * Create the panel.
	 * @throws NamingException 
	 */
	public ManufactureScopeBrowsePanel() throws NamingException {
		final ProductService ejbProduct = lookupRemoteEJB();

		final OrdersService ejbOrders = lookupOrdersRemoteEJB();
		final Manuf_scopeService ejbManuf_scope = lookupManuf_scopeRemoteEJB();
		txtTermeknevFirst.setText("Termeknev");
		
		txtTermekDarabFirst.setText("Darab");
	

		panelCont.setLayout(cl);
		panelSecond.setLayout(new MigLayout("", "[183px,grow][][][]", "[23px][][][]"));
		panelThird.setLayout(new MigLayout("", "[183px,grow][][][][][][]", "[23px][][][][][][][][]"));
		/*
		//TEST, majd del
		panelFirst.setBackground(Color.BLUE);
		panelSecond.setBackground(Color.GREEN);
		panelThird.setBackground(Color.RED);
		//Eddig
		*/
		
		cl.show(panelCont, "Panel1");
		//----------------------------ELSO PANEL----------------------------------
		panelFirst.setSize(1000,1000);
		panelCont.add(panelFirst, "1");
		panelFirst.setLayout(new MigLayout("", "[163px][4px][138px][89px]", "[20px][][23px][23px][87px]"));
		comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
		panelFirst.add(comboBoxFunctionSwitcher1, "cell 2 0,growx,aligny top");

		panelFirst.add(txtTermeknevFirst, "cell 0 1,growx,aligny center");
		panelFirst.add(txtTermekDarabFirst, "cell 2 1,alignx left,aligny top");
		
		
		comboBoxFunctionSwitcher1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				if (jcb.getSelectedIndex() == 0)
					comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
				if (jcb.getSelectedIndex() == 1)
					comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
				cl.show(panelCont, Integer.toString(jcb.getSelectedIndex() + 1));
			}
		});

		
		//----------------------------MASODIK PANEL----------------------------------

		String[] allManufScopes=ejbManuf_scope.getAllManuf_scopeNames();
		JComboBox comboBox = new JComboBox(allManufScopes);
		panelSecond.setSize(1000,1000);
		panelCont.add(panelSecond, "2");
		panelSecond.setLayout(new MigLayout("", "[163px][4px][138px][89px]", "[20px][23px][23px][87px]"));
		comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
		panelSecond.add(comboBoxFunctionSwitcher2, "cell 2 0");
		
		panelSecond.add(comboBox, "cell 0 1");
		btnMegrendel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manuf_scope manufScopeToUpdate = ejbManuf_scope.getManuf_scope(comboBox.getSelectedItem().toString());
				manufScopeToUpdate.setIs_ordered(1);
				ejbManuf_scope.updManuf_scope(manufScopeToUpdate);
				
			}
		});
		
		panelSecond.add(btnMegrendel, "cell 1 1");
		
		panelSecond.add(lblAlkatreszNeve, "cell 0 2");
		panelSecond.add(lblAlkatreszDarab, "cell 1 2");
		

		
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					Manuf_scope manufScopeToUpdate = ejbManuf_scope.getManuf_scope(comboBox.getSelectedItem().toString());

				//	txtTermeknevSecond.setText(prodToUpdate.getName());
					panelSecond.removeAll();
					
					panelSecond.add(comboBoxFunctionSwitcher2, "cell 2 0");
					
					panelSecond.add(comboBox, "cell 0 1");
					
					panelSecond.add(btnMegrendel, "cell 1 1");
					
					
					
					/*panelSecond.add(lblAlkatreszNeve, "cell 0 2");
					panelSecond.add(lblAlkatreszDarab, "cell 1 2");*/
					

					List<Orders> orders_list = new ArrayList<Orders>();
					orders_list = ejbOrders.getOrdersToDelete(manufScopeToUpdate.getId());

					for (int i = 0; i < orders_list.size(); i++) {
						
						
						JLabel lblTermek = new JLabel(ejbProduct.getProduct(orders_list.get(i).getProduct_id()).getName());
						panelSecond.add(lblTermek, "cell 0 "+ 2+i);
						
						JLabel lblTermekDb = new JLabel(orders_list.get(i).getNumber().toString());
						panelSecond.add(lblTermekDb, "cell 1 "+ 2+i);
					}

					panelSecond.revalidate();
					validate();

				}
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		comboBoxFunctionSwitcher2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				if (jcb.getSelectedIndex() == 0)
					comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
				if (jcb.getSelectedIndex() == 1)
					comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);

				cl.show(panelCont, Integer.toString(jcb.getSelectedIndex() + 1));
			}
		});


	}
	private static ProductService lookupRemoteEJB() throws NamingException {
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

	private static OrdersService lookupOrdersRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "Anyagbeszer";
		final String distinctName = "";
		final String beanName = OrdersServiceImpl.class.getSimpleName();

		final String viewClassName = OrdersService.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (OrdersService) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}

	private static Manuf_scopeService lookupManuf_scopeRemoteEJB() throws NamingException {
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);

		final String appName = "";
		final String moduleName = "Anyagbeszer";
		final String distinctName = "";
		final String beanName = Manuf_scopeServiceImpl.class.getSimpleName();

		final String viewClassName = Manuf_scopeService.class.getName();
		System.out.println("Looking EJB via JNDI ");
		System.out.println(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

		return (Manuf_scopeService) context.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);

	}
}
