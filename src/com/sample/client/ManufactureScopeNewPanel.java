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
import javax.swing.JButton;
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

public class ManufactureScopeNewPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ManufactureScopeNewPanel() {
		
		JLabel lblGyartasiCelokKezelese = new JLabel("Gyartasi Celok kezelese");
		this.add(lblGyartasiCelokKezelese);

		
	}
	
	
}
