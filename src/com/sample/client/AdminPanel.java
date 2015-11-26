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

public class AdminPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminPanel() {
		
		JLabel lblAdminFelulet = new JLabel("Admin felulet");
		this.add(lblAdminFelulet);
	}
	

}
