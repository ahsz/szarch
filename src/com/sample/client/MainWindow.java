package com.sample.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

public class MainWindow {

	private LoginPanel loginPanel;
	private JFrame initialWindow;
	private LoginPanel startPanel= null;
	private ManufactureScopeHandlePanel manufactureScopeHandlePanel;
	private AdminPanel adminPanel;
	
	private ProductBrowsePanel  productBrowsePanel;
	private ProductModifyPanel  productModifyPanel;
	private ComponentComplexPanel componentComplexPanel;
	
	private ComponentPanel  componentPanel;
	private ManufactureScopeBrowsePanel mnufactureScopeBrowsePanel;
	
	//public static Utility ut;
	private static int TYPE_MANUF_NEW = 1;
	private static int TYPE_ADMIN = 2;
	private static int TYPE_PRODUCT_BROWSE = 3;
	private static int TYPE_PRODUCT_MODIFY = 4;
	private static int TYPE_COMPONENT_COMPLEX = 5;
	private static int TYPE_COMPONENT = 6;
	private static int TYPE_MANUF_BROWSE= 7;
	private static int TYPE_LOGIN= 8;
	
	//static { ut = new Utility(); }//
	public static int USER_TYPE=4;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.initialWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initialWindow = new JFrame();
		initialWindow.setBounds(100, 100, 800, 670);
		initialWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initialWindow.getContentPane().setLayout(null);
		 
		try {
			startPanel= new LoginPanel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		startPanel.setBounds(0, 26, 784, 605);
		initialWindow.getContentPane().add(startPanel);
				
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 26);
		initialWindow.getContentPane().add(menuBar);
		
		JMenu menuFile = new JMenu("F\u00E1jl");
		menuBar.add(menuFile);
		
		
		
		//Manager---------------------------------------------------------------------------------------------
		JMenuItem menuItemManufactureScopeNew = new JMenuItem("Gyartasi celok kezelese");
		menuFile.add(menuItemManufactureScopeNew);
		menuItemManufactureScopeNew.addActionListener(new MenuAction(startPanel, manufactureScopeHandlePanel, TYPE_MANUF_NEW));

				
		JMenuItem menuItemAdmin = new JMenuItem("Admin felulet");
		menuFile.add(menuItemAdmin);
		menuItemAdmin.addActionListener(new MenuAction(startPanel, adminPanel, TYPE_ADMIN));
		
		//Gyartasvezeto---------------------------------------------------------------------------------------------
		JMenuItem menuItemProductBrowse = new JMenuItem("Kesztermek megtekintese");
		menuFile.add(menuItemProductBrowse);
		menuItemProductBrowse.addActionListener(new MenuAction(startPanel, productBrowsePanel, TYPE_PRODUCT_BROWSE));

				
		JMenuItem menuItemProductModify = new JMenuItem("Kesztermek kezelese");
		menuFile.add(menuItemProductModify);
		menuItemProductModify.addActionListener(new MenuAction(startPanel, productModifyPanel, TYPE_PRODUCT_MODIFY));
		
		JMenuItem menuItemComponentComplex = new JMenuItem("Komplex alkatresz kezelese");
		menuFile.add(menuItemComponentComplex);
		menuItemComponentComplex.addActionListener(new MenuAction(startPanel, componentComplexPanel, TYPE_COMPONENT_COMPLEX));
		
		
		//Beszerzo---------------------------------------------------------------------------------------------
		JMenuItem menuItemComponent = new JMenuItem("Alkatreszek kezelese");
		menuFile.add(menuItemComponent);
		menuItemComponent.addActionListener(new MenuAction(startPanel, componentPanel, TYPE_COMPONENT));

				
		JMenuItem menuItemManufactureScopeBrowse = new JMenuItem("Beszerzesi lista");
		menuFile.add(menuItemManufactureScopeBrowse);
		menuItemManufactureScopeBrowse.addActionListener(new MenuAction(startPanel, mnufactureScopeBrowsePanel, TYPE_MANUF_BROWSE));
		

		JMenuItem menuItemSearch = new JMenuItem("Kilepes");
		menuFile.add(menuItemSearch);
		menuItemSearch.addActionListener(new MenuAction(startPanel, loginPanel, TYPE_LOGIN));
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private class MenuAction implements ActionListener {

	    private JPanel newPanel;
	    private JPanel oldPanel;
		private int type;
	    private MenuAction(JPanel oldPanel, JPanel pnl, int type) {
	        this.newPanel = pnl;
	        this.oldPanel = oldPanel;
	        this.type = type;
	    }
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        changePanel(oldPanel, newPanel, type);
	    }
	    
	    private void changePanel(JPanel oldPanel, JPanel panel, int type) {
	    	
	    	

	    	if(type == TYPE_MANUF_NEW){
		    	try {
					manufactureScopeHandlePanel = new ManufactureScopeHandlePanel();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newPanel = manufactureScopeHandlePanel;
	    	}
		    if(type == TYPE_ADMIN){
		    	try {
					adminPanel = new AdminPanel();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newPanel = adminPanel;
		    }
	    	if(type == TYPE_PRODUCT_BROWSE){
	    		try {
					productBrowsePanel = new ProductBrowsePanel();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newPanel = productBrowsePanel;
	    	}
		    if(type == TYPE_PRODUCT_MODIFY){
		    	try {
					productModifyPanel = new ProductModifyPanel();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newPanel = productModifyPanel;
		    }
	    	if(type == TYPE_COMPONENT_COMPLEX){
	    		try {
					componentComplexPanel = new ComponentComplexPanel();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newPanel = componentComplexPanel;
	    	}
		    if(type == TYPE_COMPONENT){
		    	try {
					componentPanel = new ComponentPanel();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	newPanel = componentPanel;
		    }
		    if(type == TYPE_MANUF_BROWSE){
		    	mnufactureScopeBrowsePanel = new ManufactureScopeBrowsePanel();
		    	newPanel = mnufactureScopeBrowsePanel;
		    }
		    /*
	private ManufactureScopeNewPanel manufactureScopeNewPanel;
	private AdminPanel adminPanel;
	
	private ProductBrowsePanel  productBrowsePanel;
	private ProductModifyPanel  productModifyPanel;
	private ComponentComplexPanel componentComplexPanel;
	
	private ComponentPanel  componentPanel;
	private ManufactureScopeBrowsePanel mnufactureScopeBrowsePanel;
			*/
	    	oldPanel.removeAll();
	    	if(type != TYPE_LOGIN)
		    oldPanel.add(newPanel);
	    	if(type == TYPE_LOGIN){
	    		try {
					startPanel.addLoginFields();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		    oldPanel.revalidate();
		    oldPanel.repaint();   
		}
	}
	
	
}
