package com.sample.client;
/*
 * Késztermékek megtekintése
 */

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	 */
	public ProductBrowsePanel() {
		setLayout(new MigLayout("", "[133px,grow][grow][][][][][][]", "[14px][][][][][]"));
		String[] productList = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		
		JLabel lblKesztermekekMegtekintese = new JLabel("Kesztermekek megtekintese");
		this.add(lblKesztermekekMegtekintese, "cell 0 0,alignx left,aligny top");
		JComboBox comboBox = new JComboBox(productList);
		add(comboBox, "cell 0 1,growx");
		
		JLabel lblTermek = new JLabel("Termek 1");
		add(lblTermek, "cell 0 2");
		
		JLabel lblTermekDb = new JLabel("Termek 1 db");
		add(lblTermekDb, "cell 1 2");


	}
}
