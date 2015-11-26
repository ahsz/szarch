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
		
		JLabel lblKesztermekekMegtekintese = new JLabel("Kesztermekek megtekintese");
		this.add(lblKesztermekekMegtekintese);


	}
}
