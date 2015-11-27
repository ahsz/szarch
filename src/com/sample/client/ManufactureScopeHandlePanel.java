package com.sample.client;

/*
 * Gyártási célok kezelése
 */

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
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
import com.sun.xml.internal.ws.api.Component;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class ManufactureScopeHandlePanel extends JPanel {

	JPanel panelCont = this;
	JPanel panelFirst = new JPanel();
	JPanel panelSecond = new JPanel();
	JPanel panelThird = new JPanel();
	JButton buttonOne = new JButton("Switch to second panel/workspace");
	JButton buttonSecond = new JButton("Switch to first panel/workspace");
	CardLayout cl = new CardLayout();
	String[] modeString = { "Gyartasi cel felvetele", "Gyartasi cel modositasa", "Gyartasi cel torlese" };
	private final JComboBox comboBoxFunctionSwitcher1 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher2 = new JComboBox(modeString);
	private final JComboBox comboBoxFunctionSwitcher3 = new JComboBox(modeString);
	private final JComboBox comboBoxElementSelector = new JComboBox();
	
	private final JTextField txtTermeknevFirst = new JTextField();
	private final JButton btnFelvetelFirst = new JButton("Felvetel");
	private final JButton btnUjAlkatreszFirst = new JButton("Uj alkatresz");
	
	private final JTextField txtTermeknevSecond = new JTextField();
	private final JButton btnFelvetelSecond = new JButton("Felvetel");
	private final JButton btnUjAlkatreszSecond = new JButton("Uj alkatresz");


	private final JComboBox comboBox_1 = new JComboBox();
	private final JButton btnTorles = new JButton("Torles");

	private  JScrollPane scrollPaneFirst;
	private  JScrollPane scrollPaneSecond;


	/**
	 * Create the panel.
	 */
	public ManufactureScopeHandlePanel() {
		

		txtTermeknevFirst.setText("Termeknev");
		txtTermeknevFirst.setColumns(10);
		panelCont.setLayout(cl);
		panelSecond.setLayout(new MigLayout("", "[183px][][][][][][]", "[23px][][][][][][][][]"));
		panelThird.setLayout(new MigLayout("", "[183px,grow][][][][][][]", "[23px][][][][][][][][]"));
		
		//TEST, majd del
		panelFirst.setBackground(Color.BLUE);
		panelSecond.setBackground(Color.GREEN);
		panelThird.setBackground(Color.RED);
		//Eddig
		
		
		cl.show(panelCont, "Panel1");
		//----------------------------ELSO PANEL----------------------------------
		panelFirst.setSize(1000,1000);
		panelCont.add(panelFirst, "1");
		panelFirst.setLayout(new MigLayout("", "[163px][4px][138px][89px]", "[20px][23px][23px][87px]"));
		comboBoxFunctionSwitcher1.setSelectedItem(modeString[0]);
		panelFirst.add(comboBoxFunctionSwitcher1, "cell 2 0,growx,aligny top");

		panelFirst.add(txtTermeknevFirst, "cell 0 1,growx,aligny center");
		panelFirst.add(btnFelvetelFirst, "cell 2 1,alignx left,aligny top");
		
		    JPanel newElementsPanelFirst = new JPanel();
		    newElementsPanelFirst.setSize(4000, 400);
		    newElementsPanelFirst.setLayout(new GridLayout(13, 6, 10, 0));
		    
		    
		    //Mentesnel itt erhetoek el az adatok
			btnFelvetelFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Component[] comps= newElementsPanelFirst.getComponents();
					String s="";
				}
			});


		
		scrollPaneFirst = new JScrollPane(newElementsPanelFirst);
		scrollPaneFirst.setSize(600, 600);
		panelFirst.add(scrollPaneFirst, "cell 0 3 3 1,grow");
		panelFirst.add(btnUjAlkatreszFirst, "cell 3 3,alignx left,aligny top");
		btnUjAlkatreszFirst.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox comboBox = new JComboBox();
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

		
		//----------------------------MASODIK PANEL----------------------------------

		
		
		panelSecond.setSize(1000,1000);
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
		    
		    
		    //Mentesnel itt erhetoek el az adatok
			btnFelvetelSecond.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					java.awt.Component[] compsSecond= newElementsPanelSecond.getComponents();
					String s="";
				}
			});


		
		scrollPaneSecond = new JScrollPane(newElementsPanelSecond);
		scrollPaneSecond.setSize(600, 600);
		panelSecond.add(scrollPaneSecond, "cell 0 3 3 1,grow");
		panelSecond.add(btnUjAlkatreszSecond, "cell 3 3,alignx left,aligny top");
		btnUjAlkatreszSecond.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox comboBox = new JComboBox();
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
		
		panelThird.add(comboBox_1, "cell 0 1,growx");
		
		panelThird.add(btnTorles, "cell 1 1");

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

}
