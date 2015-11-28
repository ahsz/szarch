package com.sample.client;
/*
 * Komplex alkatrészek kezelése
 */

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sample.ejb.ComplexService;
import com.sample.ejb.ComplexServiceImpl;
import com.sample.ejb.ComponentService;
import com.sample.ejb.ComponentServiceImpl;
import com.sample.ejb.ContainService;
import com.sample.ejb.ContainServiceImpl;
import com.sample.ejb.ProductService;
import com.sample.ejb.ProductServiceImpl;
import com.sample.jpa.entities.Complex;
import com.sample.jpa.entities.Component;
import com.sample.jpa.entities.Contain;
import com.sample.jpa.entities.Product;

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

public class ProductModifyPanel extends JPanel {
	JPanel panelCont = this;
	JPanel panelFirst = new JPanel();
	JPanel panelSecond = new JPanel();
	JPanel panelThird = new JPanel();
	JButton buttonOne = new JButton("Switch to second panel/workspace");
	JButton buttonSecond = new JButton("Switch to first panel/workspace");
	CardLayout cl = new CardLayout();
	String[] modeString = { "Kesztermek felvetele", "Kesztermek modositasa", "Kesztermek torlese" };
	private final JComboBox comboBoxFunctionSwitcher1 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher2 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher3 = new JComboBox(modeString);

	private final JTextField txtTermeknevFirst = new JTextField();
	private final JButton btnFelvetelFirst = new JButton("Felvetel");
	private final JButton btnUjAlkatreszFirst = new JButton("Uj alkatresz");

	private final JTextField txtTermeknevSecond = new JTextField();
	private final JButton btnFelvetelSecond = new JButton("Felvetel");
	private final JButton btnUjAlkatreszSecond = new JButton("Uj alkatresz");

	private final JButton btnTorles = new JButton("Torles");

	private JScrollPane scrollPaneFirst;
	private JScrollPane scrollPaneSecond;

	private int newUpdateElements = 0;
	private int originalUpdateElements = 0;

	public ProductModifyPanel() throws NamingException {
		final ComponentService ejb = lookupRemoteEJB();

		final ContainService ejbContain = lookupContainsRemoteEJB();
		final ProductService ejbProduct = lookupProductRemoteEJB();

		txtTermeknevFirst.setText("Termeknev");
		txtTermeknevFirst.setColumns(10);
		panelCont.setLayout(cl);
		panelSecond.setLayout(new MigLayout("", "[183px][][][][][][]", "[23px][][][][][][][][]"));
		panelThird.setLayout(new MigLayout("", "[183px,grow][][][][][][]", "[23px][][][][][][][][]"));

		// TEST, majd del
		panelFirst.setBackground(Color.BLUE);
		panelSecond.setBackground(Color.GREEN);
		panelThird.setBackground(Color.RED);
		// Eddig

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
				Component existCheck = ejb.getComponent(txtTermeknevFirst.getText());
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

						// product felvetele
						Product product = new Product();
						product.setName(txtTermeknevFirst.getText());

						ejbProduct.addProduct(product);

						Product uploadedProduct = ejbProduct.getProduct(product.getName());

						// contain tábla töltése
						for (int i = 0; i < compNames.length; i++) {

							Component compInstances = ejb.getComponent(compNames[i]);
							Contain contain = new Contain();

							contain.setProduct_id(uploadedProduct.getId());
							contain.setComponent_id(compInstances.getId());
							contain.setNumber(Integer.parseInt(compDb[i]));
							ejbContain.addContain(contain);

						}

					} else {
						// error no components
					}

				} else {
					// error name exists
				}
			}
		});

		scrollPaneFirst = new JScrollPane(newElementsPanelFirst);
		scrollPaneFirst.setSize(600, 600);
		panelFirst.add(scrollPaneFirst, "cell 0 3 3 1,grow");
		panelFirst.add(btnUjAlkatreszFirst, "cell 3 3,alignx left,aligny top");
		btnUjAlkatreszFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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

				JComboBox comboBox = new JComboBox(allComponents);
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
		JComboBox comboBoxElementSelector = new JComboBox(ejbProduct.getAllProductNames());

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
					Product prodToUpdate = ejbProduct.getProduct(comboBoxElementSelector.getSelectedItem().toString());

					txtTermeknevSecond.setText(prodToUpdate.getName());
					newElementsPanelSecond.removeAll();
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

						Component showComp = ejb.getComponent(cont_list.get(i).getComponent_id());
						originalUpdateElements = cont_list.size();

						// combok
						JComboBox comboBox = new JComboBox(allComponents);
						comboBox.setSelectedItem(showComp.getName());
						newElementsPanelSecond.add(comboBox);
						// darab kiiras
						JTextField txtDarab = new JTextField();
						txtDarab.setText(cont_list.get(i).getNumber().toString());
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

					Product oldProd = ejbProduct.getProduct(comboBoxElementSelector.getSelectedItem().toString());
					// product felvetele
					Product product = new Product();
					product.setName(txtTermeknevSecond.getText());
					product.setId(oldProd.getId());
					ejbProduct.updProduct(product);

					Product uploadedProduct = ejbProduct.getProduct(product.getName());

					// contain tábla töltése
					for (int i = 0; i < originalUpdateElements; i++) {
						Component compInstances = ejb.getComponent(compNames[i]);
						Contain containInst = new Contain();

						containInst.setProduct_id(uploadedProduct.getId());
						containInst.setComponent_id(compInstances.getId());
						containInst.setNumber(Integer.parseInt(compDb[i]));
						ejbContain.updContain(containInst);
					}

					for (int i = 0; i < newUpdateElements; i++) {
						Component compInstances = ejb.getComponent(compNames[originalUpdateElements + i]);
						Contain containInst = new Contain();

						containInst.setProduct_id(uploadedProduct.getId());
						containInst.setComponent_id(compInstances.getId());
						containInst.setNumber(Integer.parseInt(compDb[originalUpdateElements + i]));
						ejbContain.addContain(containInst);

					}
					newUpdateElements = 0;

				} else {
					// error no components
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
				Product prodToUpdate = ejbProduct.getProduct(comboBoxElementSelector.getSelectedItem().toString());

				txtTermeknevSecond.setText(prodToUpdate.getName());
				// newElementsPanelSecond.removeAll();
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

				JComboBox comboBox = new JComboBox(allComponents);
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

		JComboBox comboBox_1 = new JComboBox(ejbProduct.getAllProductNames());
		panelThird.add(comboBox_1, "cell 0 1,growx");

		panelThird.add(btnTorles, "cell 1 1");

		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Product deleteProd=ejbProduct.getProduct(comboBox_1.getSelectedItem().toString());
				int deleteID=deleteProd.getId();
				
				List<Contain> cont_list=new ArrayList<Contain>();
				cont_list= ejbContain.getContainToDelete(deleteID);
				
				for(int i=0;i<cont_list.size();i++){
					ejbContain.remContain(cont_list.get(i));
				}
				
				
				
				ejbProduct.remProduct(deleteProd);
				

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
