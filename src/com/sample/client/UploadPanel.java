package com.sample.client;

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

public class UploadPanel extends JPanel {
	/*private JTextField txtUpload;


	File file=new java.io.File("");*/
	
	private JLabel txtTitle;
	private JLabel txtTitleName;
	private JLabel txtTitlePassword;

	private JTextField inputName;
	private JTextField inputPassword;
	private JButton loginButton;
	private JButton registerButton;
	
	/**
	 * Create the panel.
	 */
	public UploadPanel() {
		//JPanel panel = new JPanel();


		this.setLayout(new GridLayout(0, 3, 0, 0));

		/*JLabel label = new JLabel("");
		panel.add(label);*/

		txtTitle = new JLabel();
		txtTitle.setText("Kerem jelentkezzen be:");
		add(txtTitle);

/*
		JLabel label_1 = new JLabel("");
		panel.add(label_1);

		txtTitleName = new JLabel();
		txtTitleName.setText("Felhasznalonev:");
		panel.add(txtTitleName);

		inputPassword = new JTextField();
		inputPassword.setColumns(40);
		panel.add(inputPassword);

		JLabel label_2 = new JLabel("");
		panel.add(label_2);

		txtTitlePassword = new JLabel();
		txtTitlePassword.setText("Jelszo:");
		panel.add(txtTitlePassword);

		inputName = new JTextField();
		inputName.setColumns(40);
		panel.add(inputName);

		JLabel label_3 = new JLabel("");
		panel.add(label_3);

		JLabel label_4 = new JLabel("");
		panel.add(label_4);

		loginButton = new JButton("Bejelentkezes");
		panel.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtTitle.setText("belogolva");

			}
		});

		registerButton = new JButton("Regisztracio");
		panel.add(registerButton);

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				txtTitle.setText("regelve");

			}
		});*/

	/*	JLabel label_5 = new JLabel("");
		panel.add(label_5);

		JLabel label_6 = new JLabel("");
		panel.add(label_6);

		JLabel label_7 = new JLabel("");
		panel.add(label_7);

		JLabel label_8 = new JLabel("");
		panel.add(label_8);

		JLabel label_9 = new JLabel("");
		panel.add(label_9);

		JLabel label_10 = new JLabel("");
		panel.add(label_10);*/
		
		
		
		/*
		txtUpload = new JTextField();
		txtUpload.setEditable(false);
		txtUpload.setText("Felt\u00F6lt\u00E9s:");
		txtUpload.setColumns(20);
		

		final JFileChooser fc = new JFileChooser();
		JButton btnSelectFiles = new JButton("F\u00E1jl kiv\u00E1laszt\u00E1sa");
		btnSelectFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int returnVal = fc.showOpenDialog(UploadPanel.this);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {

		            if(fc.getSelectedFile().getName().contains(".jpg")){
		            	File selectedFile = fc.getSelectedFile();
		            	 file=selectedFile;
		            	 
		 				String[] returnString;
						
						
			            System.out.println("Uploading: " + file.getName());
						txtUpload.setText("Kep feltoltve!");
		            	 
		            	 
		            	 
		            } else{
		    			JOptionPane.showMessageDialog(UploadPanel.this,
		    				    "Rossz formatum, valassz .jpeg-et!!!");
		            	 System.out.println("NEM .jpeg!!! " + file.getName());
		            	 
		            }
		            	
		           
		        } else {
		        	System.out.println("Open command cancelled by user.");
		        }
				

			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtUpload, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSelectFiles))
					.addContainerGap(571, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtUpload, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSelectFiles)
					.addContainerGap(420, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
*/
	}
}
