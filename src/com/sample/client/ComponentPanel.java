package com.sample.client;
/*
 *  alkatrészek kezelése
 */

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sample.jpa.entities.Component;
import com.sample.ejb.ComponentService;
import com.sample.ejb.ComponentServiceImpl;
import com.sample.ejb.UserService;
import com.sample.ejb.UserServiceImpl;
import com.sample.jpa.entities.Component;

import net.miginfocom.swing.MigLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class ComponentPanel extends JPanel {
	JPanel panelCont = this;
	JPanel panelFirst = new JPanel();
	JPanel panelSecond = new JPanel();
	JPanel panelThird = new JPanel();
	JButton buttonOne = new JButton("Switch to second panel/workspace");
	JButton buttonSecond = new JButton("Switch to first panel/workspace");
	CardLayout cl = new CardLayout();
	String[] modeString = { "Alkatresz felvetele", "Alkatresz  modositasa", "Alkatresz torlese" };
	private final JComboBox comboBoxFunctionSwitcher1 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher2 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher3 = new JComboBox(modeString);

	
	private final JTextField txtTermeknevFirst = new JTextField();
	private final JButton btnFelvetelFirst = new JButton("Felvetel");
	
	private final JTextField txtTermeknevSecond = new JTextField();
	private final JButton btnFelvetelSecond = new JButton("Modosit");



	private final JButton btnTorles = new JButton("Torles");
	private final JTextField txtArFirst = new JTextField();
	private final JTextField txtIdotartamFirst = new JTextField();
	private final JTextField txtArSecond = new JTextField();
	private final JTextField txtIdotartamSecond = new JTextField();


	public ComponentPanel() throws NamingException {
		final ComponentService ejb = lookupRemoteEJB();

		txtIdotartamFirst.setText("Idotartam");
		txtIdotartamFirst.setColumns(10);
		txtIdotartamSecond.setText("Idotartam");
		txtIdotartamSecond.setColumns(10);
		
		
		txtArFirst.setText("Ar");
		txtArFirst.setColumns(10);
		txtArSecond.setText("Ar");
		txtArSecond.setColumns(10);

		txtTermeknevFirst.setText("Termeknev");
		txtTermeknevFirst.setColumns(10);
		txtTermeknevSecond.setText("Termeknev");
		txtTermeknevSecond.setColumns(10);
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
		panelFirst.setLayout(new MigLayout("", "[163px,grow][4px][138px][89px]", "[20px][23px][23px][87px]"));
		comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
		panelFirst.add(comboBoxFunctionSwitcher1, "cell 2 0,growx,aligny top");

		panelFirst.add(txtTermeknevFirst, "cell 0 1,growx,aligny center");
		panelFirst.add(btnFelvetelFirst, "cell 2 1,alignx left,aligny top");
		
		panelFirst.add(txtArFirst, "cell 0 2,growx");
		
		panelFirst.add(txtIdotartamFirst, "cell 0 3,growx");
		    
		    
		    //Mentesnel itt erhetoek el az adatok
			btnFelvetelFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Component component=new Component();
					component.setName(txtTermeknevFirst.getText());
					component.setIs_compex(0);
					component.setPrice(Integer.parseInt(txtArFirst.getText()));
					component.setPurch_time(Integer.parseInt(txtIdotartamFirst.getText()));
					ejb.addComponent(component);
					
					//java.awt.Component[] comps= newElementsPanelFirst.getComponents();

				}
			});
		
			JComboBox comboBoxElementSelector = new JComboBox(ejb.getAllComponentNames());
			JComboBox comboBoxDelSelector = new JComboBox(ejb.getAllComponentNames());
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
				comboBoxElementSelector.removeAllItems();
				comboBoxDelSelector.removeAllItems();
				String[] compNames=ejb.getAllComponentNames();
			    for(int i=0;i<compNames.length;i++){
			    	comboBoxElementSelector.addItem(compNames[i]);
			    	comboBoxDelSelector.addItem(compNames[i]);
			    }
				
			}
		});

		
		//----------------------------MASODIK PANEL----------------------------------



		
		panelSecond.setSize(1000,1000);
		panelCont.add(panelSecond, "2");
		panelSecond.setLayout(new MigLayout("", "[163px][4px][138px][89px]", "[20px][23px][23px][87px]"));
		comboBoxFunctionSwitcher2.setSelectedItem(modeString[1]);
		panelSecond.add(comboBoxFunctionSwitcher2, "cell 2 0");
		panelSecond.add(comboBoxElementSelector, "cell 0 1,growx,aligny center");
		
		
		
		panelSecond.add(txtTermeknevSecond, "cell 0 2,growx,aligny center");
		panelSecond.add(btnFelvetelSecond, "cell 2 2,alignx left,aligny top");
		
		panelSecond.add(txtArSecond, "cell 0 3,growx");
		
		panelSecond.add(txtIdotartamSecond, "cell 0 4,growx");
		
		
		comboBoxElementSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxElementSelector.getSelectedItem()!=null){
				Component comp=ejb.getComponent(comboBoxElementSelector.getSelectedItem().toString());
				
				txtTermeknevSecond.setText(comp.getName());
				txtArSecond.setText(comp.getPrice().toString());
				txtIdotartamSecond.setText(comp.getPurch_time().toString());}
			}
		});
		

		    
		    //Mentesnel itt erhetoek el az adatok
			btnFelvetelSecond.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Component comp=ejb.getComponent(comboBoxElementSelector.getSelectedItem().toString());
					comp.setName(txtTermeknevSecond.getText());
					comp.setName(txtTermeknevSecond.getText());
					comp.setPrice(Integer.parseInt(txtArSecond.getText()));
					comp.setPurch_time(Integer.parseInt(txtIdotartamSecond.getText()));
					ejb.updComponent(comp);
					//java.awt.Component[] compsSecond= newElementsPanelSecond.getComponents();
					String s="";
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
				if (jcb.getSelectedIndex() == 2)
					comboBoxFunctionSwitcher3.setSelectedItem(modeString[2]);
				cl.show(panelCont, Integer.toString(jcb.getSelectedIndex() + 1));
				comboBoxElementSelector.removeAllItems();
				comboBoxDelSelector.removeAllItems();
				String[] compNames=ejb.getAllComponentNames();
			    for(int i=0;i<compNames.length;i++){
			    	comboBoxElementSelector.addItem(compNames[i]);
			    	comboBoxDelSelector.addItem(compNames[i]);
			    }
			}
		});

		panelCont.add(panelThird, "3");
		comboBoxFunctionSwitcher3.setSelectedItem(modeString[2]);
		panelThird.add(comboBoxFunctionSwitcher3, "cell 1 0,growx");
		
		panelThird.add(comboBoxDelSelector, "cell 0 1,growx");
		
		panelThird.add(btnTorles, "cell 1 1");

		
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Component comp=ejb.getComponent(comboBoxDelSelector.getSelectedItem().toString());
				ejb.remComponent(comp);
				comboBoxElementSelector.removeAllItems();
				comboBoxDelSelector.removeAllItems();
				String[] compNames=ejb.getAllComponentNames();
			    for(int i=0;i<compNames.length;i++){
			    	comboBoxElementSelector.addItem(compNames[i]);
			    	comboBoxDelSelector.addItem(compNames[i]);
			    }
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
				comboBoxElementSelector.removeAllItems();
				comboBoxDelSelector.removeAllItems();
				String[] compNames=ejb.getAllComponentNames();
			    for(int i=0;i<compNames.length;i++){
			    	comboBoxElementSelector.addItem(compNames[i]);
			    	comboBoxDelSelector.addItem(compNames[i]);
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
	


}
