package com.sample.client;
/*
 * Komplex alkatrészek kezelése
 */

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sample.ejb.ComplexService;
import com.sample.ejb.ComplexServiceImpl;
import com.sample.ejb.ProductService;
import com.sample.ejb.ProductServiceImpl;
import com.sample.ejb.OrdersService;
import com.sample.ejb.OrdersServiceImpl;
import com.sample.ejb.Manuf_scopeService;
import com.sample.ejb.Manuf_scopeServiceImpl;
import com.sample.ejb.OrdersService;
import com.sample.ejb.OrdersServiceImpl;
import com.sample.ejb.Manuf_scopeService;
import com.sample.ejb.Manuf_scopeServiceImpl;
import com.sample.jpa.entities.Complex;
import com.sample.jpa.entities.Product;
import com.sample.jpa.entities.Orders;
import com.sample.jpa.entities.Manuf_scope;

import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ManufactureScopeHandlePanel extends JPanel {
	JPanel panelCont = this;
	JPanel panelFirst = new JPanel();
	JPanel panelSecond = new JPanel();
	JPanel panelThird = new JPanel();
	JButton buttonOne = new JButton("Switch to second panel/workspace");
	JButton buttonSecond = new JButton("Switch to first panel/workspace");
	CardLayout cl = new CardLayout();
	String[] modeString = { "Gyartasi celok felvetele", "Gyartasi celok modositasa", "Gyartasi celok torlese" };
	private final JComboBox comboBoxFunctionSwitcher1 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher2 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher3 = new JComboBox(modeString);

	private final JTextField txtTermeknevFirst = new JTextField();
	private final JButton btnFelvetelFirst = new JButton("Felvetel");
	private final JButton btnUjAlkatreszFirst = new JButton("Uj kesztermek");

	private final JTextField txtTermeknevSecond = new JTextField();
	private final JButton btnFelvetelSecond = new JButton("Felvetel");
	private final JButton btnUjAlkatreszSecond = new JButton("Uj kesztermek");

	private final JButton btnTorles = new JButton("Torles");

	private JScrollPane scrollPaneFirst;
	private JScrollPane scrollPaneSecond;

	private int newUpdateElements = 0;
	private int originalUpdateElements = 0;

	public ManufactureScopeHandlePanel() throws NamingException {
		final ProductService ejbProduct = lookupRemoteEJB();

		final OrdersService ejbOrders = lookupOrdersRemoteEJB();
		final Manuf_scopeService ejbManuf_scope = lookupManuf_scopeRemoteEJB();

		txtTermeknevFirst.setText("Termeknev");
		txtTermeknevFirst.setColumns(10);
		panelCont.setLayout(cl);
		panelSecond.setLayout(new MigLayout("", "[183px][][][][][][]", "[23px][][][][][][][][]"));
		panelThird.setLayout(new MigLayout("", "[183px,grow][][][][][][]", "[23px][][][][][][][][]"));
/*
		// TEST, majd del
		panelFirst.setBackground(Color.BLUE);
		panelSecond.setBackground(Color.GREEN);
		panelThird.setBackground(Color.RED);
		// Eddig
*/
		cl.show(panelCont, "Panel1");
		// ----------------------------ELSO
		// PANEL----------------------------------
		panelFirst.setSize(1000, 1000);
		panelCont.add(panelFirst, "1");
		panelFirst.setLayout(new MigLayout("", "[163px][4px][138px][89px]", "[20px][23px][23px][87px]"));
		comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
		panelFirst.add(comboBoxFunctionSwitcher1, "cell 2 0,growx,aligny top");

		panelFirst.add(txtTermeknevFirst, "cell 0 1,growx,aligny center");
		panelFirst.add(btnFelvetelFirst, "cell 2 1,alignx left,aligny top");

		JPanel newElementsPanelFirst = new JPanel();
		newElementsPanelFirst.setSize(4000, 400);
		newElementsPanelFirst.setLayout(new GridLayout(13, 6, 10, 0));

		// Mentesnel itt erhetoek el az adatok
		btnFelvetelFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product existCheck = ejbProduct.getProduct(txtTermeknevFirst.getText());
				if (existCheck.getId() == null) {

					java.awt.Component[] comps = newElementsPanelFirst.getComponents();
					if (comps.length > 0) {
						String[] compNames = new String[comps.length / 2];
						String[] compDb = new String[comps.length / 2];
						int compNameCounter = 0;
						int compDbCounter = 0;
						for (int i = 0; i < comps.length; i++) {
							if (i % 2 == 0) {// combo
								JComboBox combo = (JComboBox) comps[i];
								compNames[compNameCounter] = combo.getSelectedItem().toString();
								compNameCounter++;
							} else {// text dara
								JTextField text = (JTextField) comps[i];
								compDb[compDbCounter] = text.getText();
								compDbCounter++;
							}

						}

						// Manuf scope felvetele
						Manuf_scope manuf_scope = new Manuf_scope();
						manuf_scope.setMs_id(txtTermeknevFirst.getText());
						manuf_scope.setIs_ordered(0);
						manuf_scope.setDeadline("2015.12.30");
						ejbManuf_scope.addManuf_scope(manuf_scope);

						Manuf_scope uploadedManuf_scope = ejbManuf_scope.getManuf_scope(manuf_scope.getMs_id());

						// orders tábla töltése
						for (int i = 0; i < compNames.length; i++) {

							Product compInstances = ejbProduct.getProduct(compNames[i]);
							Orders orders = new Orders();

							orders.setManuf_scope_id(uploadedManuf_scope.getId());
							orders.setProduct_id(compInstances.getId());
							orders.setNumber(Integer.parseInt(compDb[i]));
							ejbOrders.addOrders(orders);

						}

					} else {
		    			JOptionPane.showMessageDialog(ManufactureScopeHandlePanel.this,
		    				    "Nincs termek megadva");
					}

				}else{
	    			JOptionPane.showMessageDialog(ManufactureScopeHandlePanel.this,
	    				    "Mar letezik");
				}
			}
		});

		scrollPaneFirst = new JScrollPane(newElementsPanelFirst);
		scrollPaneFirst.setSize(600, 600);
		panelFirst.add(scrollPaneFirst, "cell 0 3 3 1,grow");
		panelFirst.add(btnUjAlkatreszFirst, "cell 3 3,alignx left,aligny top");
		btnUjAlkatreszFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String[] products = ejbProduct.getAllProductNames();


				JComboBox comboBox = new JComboBox(products);
				JTextField txtDarab = new JTextField();
				newElementsPanelFirst.add(comboBox);

				newElementsPanelFirst.add(txtDarab);

				panelFirst.revalidate();
				validate();

			}
		});

		scrollPaneFirst.setVisible(true);

		comboBoxFunctionSwitcher1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				if (jcb.getSelectedIndex() == 0)
					comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
				if (jcb.getSelectedIndex() == 1)
					comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
				if (jcb.getSelectedIndex() == 2)
					comboBoxFunctionSwitcher3.setSelectedItem(modeString[2]);
				cl.show(panelCont, Integer.toString(jcb.getSelectedIndex() + 1));
			}
		});

		// ----------------------------MASODIK
		// PANEL----------------------------------
		JComboBox comboBoxElementSelector = new JComboBox(ejbManuf_scope.getAllManuf_scopeNames());

		panelSecond.setSize(1000, 1000);
		panelCont.add(panelSecond, "2");
		panelSecond.setLayout(new MigLayout("", "[163px][4px][138px][89px]", "[20px][23px][23px][87px]"));
		comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
		panelSecond.add(comboBoxFunctionSwitcher2, "cell 2 0,growx,aligny top");
		panelSecond.add(comboBoxElementSelector, "cell 0 1,growx,aligny center");
		panelSecond.add(txtTermeknevSecond, "cell 0 2,growx,aligny center");
		panelSecond.add(btnFelvetelSecond, "cell 2 2,alignx left,aligny top");

		JPanel newElementsPanelSecond = new JPanel();
		newElementsPanelSecond.setSize(4000, 400);
		newElementsPanelSecond.setLayout(new GridLayout(13, 6, 10, 0));

		comboBoxElementSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxElementSelector.getSelectedItem() != null) {
					Manuf_scope manufToUpdate = ejbManuf_scope.getManuf_scope(comboBoxElementSelector.getSelectedItem().toString());

					txtTermeknevSecond.setText(manufToUpdate.getMs_id());
					newElementsPanelSecond.removeAll();
					// combobox feltoltese nevekkel
					String[] components = ejbProduct.getAllProductNames();
					/*String[] complexProducts = ejbProduct.getAllComplexNames();
					String[] allProducts = new String[components.length + complexProducts.length];
					for (int i = 0; i < allProducts.length; i++) {
						if (i < components.length) {
							allProducts[i] = components[i];
						} else {
							allProducts[i] = complexProducts[i - components.length];
						}
*
					}*/

					List<Orders> ord_list = new ArrayList<Orders>();
					ord_list = ejbOrders.getOrdersToDelete(manufToUpdate.getId());

					for (int i = 0; i < ord_list.size(); i++) {

						Product showComp = ejbProduct.getProduct(ord_list.get(i).getProduct_id());
						originalUpdateElements = ord_list.size();

						// combok
						JComboBox comboBox = new JComboBox(components);
						comboBox.setSelectedItem(showComp.getName());
						newElementsPanelSecond.add(comboBox);
						// darab kiiras
						JTextField txtDarab = new JTextField();
						txtDarab.setText(ord_list.get(i).getNumber().toString());
						newElementsPanelSecond.add(txtDarab);
						// ejbComplex.remComplex(comp_list.get(i));
					}

					panelSecond.revalidate();
					validate();

				}
			}
		});

		btnFelvetelSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				java.awt.Component[] comps = newElementsPanelSecond.getComponents();
				if (comps.length > 0) {
					String[] compNames = new String[comps.length / 2];
					String[] compDb = new String[comps.length / 2];
					int compNameCounter = 0;
					int compDbCounter = 0;
					for (int i = 0; i < comps.length; i++) {
						if (i % 2 == 0) {// combo
							JComboBox combo = (JComboBox) comps[i];
							compNames[compNameCounter] = combo.getSelectedItem().toString();
							compNameCounter++;
						} else {// text dara
							JTextField text = (JTextField) comps[i];
							compDb[compDbCounter] = text.getText();
							compDbCounter++;
						}

					}

					Manuf_scope oldProd = ejbManuf_scope.getManuf_scope(comboBoxElementSelector.getSelectedItem().toString());
					// product felvetele
					Manuf_scope manufscopeC = new Manuf_scope();
					manufscopeC.setMs_id(txtTermeknevSecond.getText());
					manufscopeC.setId(oldProd.getId());
					manufscopeC.setIs_ordered(0);
					manufscopeC.setDeadline("2015.12.30");
					ejbManuf_scope.updManuf_scope(manufscopeC);

					Manuf_scope uploadedManuf_scope = ejbManuf_scope.getManuf_scope(manufscopeC.getMs_id());

					// orders tábla töltése
					for (int i = 0; i < originalUpdateElements; i++) {
						Product compInstances = ejbProduct.getProduct(compNames[i]);
						Orders ordersInst = new Orders();

						ordersInst.setManuf_scope_id(uploadedManuf_scope.getId());
						ordersInst.setProduct_id(compInstances.getId());
						ordersInst.setNumber(Integer.parseInt(compDb[i]));
						ejbOrders.updOrders(ordersInst);
					}

					for (int i = 0; i < newUpdateElements; i++) {
						Product compInstances = ejbProduct.getProduct(compNames[originalUpdateElements + i]);
						Orders ordersInst = new Orders();

						ordersInst.setManuf_scope_id(uploadedManuf_scope.getId());
						ordersInst.setProduct_id(compInstances.getId());
						ordersInst.setNumber(Integer.parseInt(compDb[originalUpdateElements + i]));
						ejbOrders.addOrders(ordersInst);

					}
					newUpdateElements = 0;

				} else {
	    			JOptionPane.showMessageDialog(ManufactureScopeHandlePanel.this,
	    				    "Nincs termek megadva");
				}

			}
		});

		scrollPaneSecond = new JScrollPane(newElementsPanelSecond);
		scrollPaneSecond.setSize(600, 600);
		panelSecond.add(scrollPaneSecond, "cell 0 3 3 1,grow");
		panelSecond.add(btnUjAlkatreszSecond, "cell 3 3,alignx left,aligny top");
		btnUjAlkatreszSecond.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newUpdateElements++;
				Manuf_scope manufToUpdate = ejbManuf_scope.getManuf_scope(comboBoxElementSelector.getSelectedItem().toString());

				txtTermeknevSecond.setText(manufToUpdate.getMs_id());
				// newElementsPanelSecond.removeAll();
				// combobox feltoltese nevekkel
				String[] components = ejbProduct.getAllProductNames();
				/*String[] complexProducts = ejbProduct.getAllComplexNames();
				String[] allProducts = new String[components.length + complexProducts.length];
				for (int i = 0; i < allProducts.length; i++) {
					if (i < components.length) {
						allProducts[i] = components[i];
					} else {
						allProducts[i] = complexProducts[i - components.length];
					}

				}*/

				JComboBox comboBox = new JComboBox(components);
				JTextField txtDarab = new JTextField();
				newElementsPanelSecond.add(comboBox);

				newElementsPanelSecond.add(txtDarab);

				panelSecond.revalidate();
				validate();

			}
		});

		scrollPaneSecond.setVisible(true);

		comboBoxFunctionSwitcher2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				if (jcb.getSelectedIndex() == 0)
					comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
				if (jcb.getSelectedIndex() == 1)
					comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
				if (jcb.getSelectedIndex() == 2)
					comboBoxFunctionSwitcher3.setSelectedItem(modeString[2]);
				cl.show(panelCont, Integer.toString(jcb.getSelectedIndex() + 1));
			}
		});

		panelCont.add(panelThird, "3");
		comboBoxFunctionSwitcher3.setSelectedItem(modeString[2]);
		panelThird.add(comboBoxFunctionSwitcher3, "cell 1 0,growx");

		JComboBox comboBox_1 = new JComboBox(ejbManuf_scope.getAllManuf_scopeNames());
		panelThird.add(comboBox_1, "cell 0 1,growx");

		panelThird.add(btnTorles, "cell 1 1");

		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Manuf_scope deleteProd=ejbManuf_scope.getManuf_scope(comboBox_1.getSelectedItem().toString());
				int deleteID=deleteProd.getId();
				
				List<Orders> cont_list=new ArrayList<Orders>();
				cont_list= ejbOrders.getOrdersToDelete(deleteID);
				
				for(int i=0;i<cont_list.size();i++){
					ejbOrders.remOrders(cont_list.get(i));
				}
				
				
				
				ejbManuf_scope.remManuf_scope(deleteProd);
				

			}
		});

		comboBoxFunctionSwitcher3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox jcb = (JComboBox) e.getSource();
				if (jcb.getSelectedIndex() == 0)
					comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
				if (jcb.getSelectedIndex() == 1)
					comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
				if (jcb.getSelectedIndex() == 2)
					comboBoxFunctionSwitcher3.setSelectedItem(modeString[2]);
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
