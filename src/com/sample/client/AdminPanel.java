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
import java.util.List;

import javax.imageio.ImageIO;
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
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import net.miginfocom.swing.MigLayout;

public class AdminPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminPanel() {
		
		
		//TEST
		String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
		String[] roleStrings = { "Boss", "Slave"};
		//Eddig
		
		setLayout(new MigLayout("", "[127.00px][157.00px][143px]", "[14px][23px]"));
		
		JLabel lblAdminFelulet = new JLabel("Admin felulet");
		add(lblAdminFelulet, "cell 1 0,alignx center,aligny top");
		
		JComboBox comboBoxUserList = new JComboBox(petStrings);
		add(comboBoxUserList, "cell 0 1,alignx left,aligny center");
		
		JComboBox comboBoxUserRoles = new JComboBox(roleStrings);
		add(comboBoxUserRoles, "cell 1 1,alignx left,aligny center");
		
		JButton btnJogosultsagBeallitasa = new JButton("Jogosultsag Beallitasa");
		add(btnJogosultsagBeallitasa, "cell 2 1,growx,aligny top");
	}
}
