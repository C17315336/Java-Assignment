package com.main.assignment;

/**
 * Admin Class for Java Assignment
 * 	Class used to display interaction to user
 *
 * Compiled on the 12th of April 2019
 * By: 	Eoghan Byrne
 * 		eoghan.byrne4@mydit.ie
 *
 * Using JavaSE 1.8
 * with references libs of;
 * 		- MySQL Connector
 * 		- DbUtils
 */

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Admin extends JFrame {
	static Admin theAdmin;

	class AdminInput extends JPanel implements ActionListener {
		/**
		 * Attributes for the AdminInpurt object
		 *
		 * lb = Label
		 * tf = Text Field
		 * 
		 */
		private JLabel lbInfo;
		private JLabel lbDomain;
		private JLabel lbPort;
		private JLabel lbUsername;
		private JLabel lbPassword;
		private JLabel lbDatabase;
		private JLabel lbTable;
		private JTextField tfDomain;
		private JTextField tfPort;
		private JTextField tfUsername;
		private JTextField tfPassword;
		private JTextField tfDatabase;
		private JTextField tfTable;
		private JButton btSubmit;

		/**
		 * Constructor for the AdminInput object
		 */
		public AdminInput() {
			super();

			// Layout set to grid mode
			GridBagLayout gbAdminInput = new GridBagLayout();
			GridBagConstraints gbcAdminInput = new GridBagConstraints();
			setLayout(gbAdminInput);

			
			
			// Labels
			lbInfo = new JLabel("Enter your connection details below");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 1;
			gbcAdminInput.gridwidth = 2;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(10, 10, 10, 10);
			gbAdminInput.setConstraints(lbInfo, gbcAdminInput);
			add(lbInfo);

			
			lbDomain = new JLabel("Domain");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 2;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 10, 0, 0);
			gbAdminInput.setConstraints(lbDomain, gbcAdminInput);
			add(lbDomain);

			
			lbPort = new JLabel("Port");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 3;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 10, 0, 0);
			gbAdminInput.setConstraints(lbPort, gbcAdminInput);
			add(lbPort);

			
			lbUsername = new JLabel("Username");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 4;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 10, 0, 0);
			gbAdminInput.setConstraints(lbUsername, gbcAdminInput);
			add(lbUsername);

			
			lbPassword = new JLabel("Password");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 5;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 10, 0, 0);
			gbAdminInput.setConstraints(lbPassword, gbcAdminInput);
			add(lbPassword);

			
			lbDatabase = new JLabel("Database");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 6;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 10, 0, 0);
			gbAdminInput.setConstraints(lbDatabase, gbcAdminInput);
			add(lbDatabase);

			
			lbTable = new JLabel("Table");
			gbcAdminInput.gridx = 1;
			gbcAdminInput.gridy = 7;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 1;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 10, 0, 0);
			gbAdminInput.setConstraints(lbTable, gbcAdminInput);
			add(lbTable);

			
			
			// Text fields
			tfDomain = new JTextField(ConnectionInfo.getDbdomain());
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 2;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 0, 0, 10);
			gbAdminInput.setConstraints(tfDomain, gbcAdminInput);
			add(tfDomain);

			
			tfPort = new JTextField(ConnectionInfo.getDbport());
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 3;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 0, 0, 10);
			gbAdminInput.setConstraints(tfPort, gbcAdminInput);
			add(tfPort);

			
			tfUsername = new JTextField(ConnectionInfo.getDbuser());
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 4;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 0, 0, 10);
			gbAdminInput.setConstraints(tfUsername, gbcAdminInput);
			add(tfUsername);

			
			tfPassword = new JTextField(ConnectionInfo.getDbpass());
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 5;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 0, 0, 10);
			gbAdminInput.setConstraints(tfPassword, gbcAdminInput);
			add(tfPassword);

			
			tfDatabase = new JTextField(ConnectionInfo.getDbdata());
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 6;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 0, 0, 10);
			gbAdminInput.setConstraints(tfDatabase, gbcAdminInput);
			add(tfDatabase);

			
			tfTable = new JTextField(ConnectionInfo.getDbtable());
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 7;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.BOTH;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbcAdminInput.insets = new Insets(0, 0, 0, 10);
			gbAdminInput.setConstraints(tfTable, gbcAdminInput);
			add(tfTable);

			
			btSubmit = new JButton("Save");
			btSubmit.addActionListener(this);
			gbcAdminInput.gridx = 2;
			gbcAdminInput.gridy = 8;
			gbcAdminInput.gridwidth = 1;
			gbcAdminInput.gridheight = 1;
			gbcAdminInput.fill = GridBagConstraints.VERTICAL;
			gbcAdminInput.weightx = 1;
			gbcAdminInput.weighty = 0;
			gbcAdminInput.anchor = GridBagConstraints.NORTH;
			gbAdminInput.setConstraints(btSubmit, gbcAdminInput);
			add(btSubmit);
		}

		/**
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btSubmit) {
				// Action for btSubmit
				ConnectionInfo.setDbdomain(tfDomain.getText());
				ConnectionInfo.setDbport(tfPort.getText());
				ConnectionInfo.setDbuser(tfUsername.getText());
				ConnectionInfo.setDbpass(tfPassword.getText());
				ConnectionInfo.setDbdata(tfDatabase.getText());
				ConnectionInfo.setDbtable(tfTable.getText());
				//System.out.println("jdbc:mysql://" + ConnectionInfo.getDbdomain() + ":" + ConnectionInfo.getDbport() + "/" + ConnectionInfo.getDbdata() + ", " + ConnectionInfo.getDbuser() + ", " + ConnectionInfo.getDbpass()); <-- Used for debug
				ConnectionInfo.setDburl("jdbc:mysql://" + ConnectionInfo.getDbdomain() + ":" + ConnectionInfo.getDbport() + "/" + ConnectionInfo.getDbdata());
			}
		}
	}

	AdminInput pnAdminInput;

	/**
	 */
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		theAdmin = new Admin();
	}

	/**
	 */
	public Admin() {
		super("Admin Config");

		pnAdminInput = new AdminInput();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setContentPane(pnAdminInput);
		pack();
		setVisible(true);
	}
}
