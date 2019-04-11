package com.main.assignment;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * @author Administrator
 * @created April 11, 2019
 */
public class GUI extends JFrame {
	static GUI theGUI;

	/**
	 * @author Administrator
	 * @created April 11, 2019
	 */
	class Window extends JPanel implements ActionListener {
		private JTable tbTable;
		private JTextField tfFilter;
		private JButton btFilter;
		private JLabel lbTitle;
		private JButton btClearDB;
		private JButton btSelectFile;
		private JCheckBox cbHeader;
		private JButton btImportFile;
		private JButton btQuit;
		private JButton btAdmin;
		private JButton btStop;
		private JButton btNameLocal;
		private JButton btLocality;
		private JButton btName;
		private JLabel lbOutput;

		/**
		 * Constructor for the Window object
		 */
		public Window() {
			super();

			GridBagLayout gbWindow = new GridBagLayout();
			GridBagConstraints gbcWindow = new GridBagConstraints();
			setLayout(gbWindow);

			tbTable = new JTable();
			tbTable.setAutoCreateRowSorter(true);
			tbTable.setDragEnabled(true);
			tbTable.setGridColor(new Color(238, 238, 238));
			tbTable.setSelectionBackground(new Color(212, 212, 212));
			tbTable.setSelectionForeground(new Color(0, 0, 0));
			final DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
			model.addColumn("Stop Number");
			model.addColumn("Name without Locality");
			model.addColumn("Locality");
			model.addColumn("Name");
			model.addColumn("Easting");
			model.addColumn("Northing");
			JScrollPane scpTable = new JScrollPane(tbTable);
			gbcWindow.gridx = 1;
			gbcWindow.gridy = 1;
			gbcWindow.gridwidth = 6;
			gbcWindow.gridheight = 7;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 1;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(scpTable, gbcWindow);
			add(scpTable);

			tfFilter = new JTextField();
			tfFilter.setColumns(10);
			gbcWindow.gridx = 5;
			gbcWindow.gridy = 0;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.HORIZONTAL;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(tfFilter, gbcWindow);
			add(tfFilter);

			btFilter = new JButton("Filter");
			btFilter.setRolloverEnabled(true);
			btFilter.addActionListener(this);
			gbcWindow.gridx = 6;
			gbcWindow.gridy = 0;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.HORIZONTAL;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btFilter, gbcWindow);
			add(btFilter);

			lbTitle = new JLabel("Data Explorer - Java Assignment - Eoghan Byrne");
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 0;
			gbcWindow.gridwidth = 5;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.HORIZONTAL;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 1;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(lbTitle, gbcWindow);
			add(lbTitle);

			btClearDB = new JButton("Clear Database");
			btClearDB.setForeground(new Color(255, 0, 0));
			btClearDB.setRolloverEnabled(true);
			btClearDB.setToolTipText("Delete contents of database at present");
			btClearDB.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 1;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btClearDB, gbcWindow);
			add(btClearDB);

			btSelectFile = new JButton("Select File to Import");
			btSelectFile.setRolloverEnabled(true);
			btSelectFile.setToolTipText("Select a CSV file to import");
			btSelectFile.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 2;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btSelectFile, gbcWindow);
			add(btSelectFile);

			cbHeader = new JCheckBox("Header Row");
			cbHeader.setRolloverEnabled(true);
			cbHeader.setToolTipText("Check this option if your CSV file contains a header row");
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 4;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(cbHeader, gbcWindow);
			add(cbHeader);

			btImportFile = new JButton("Import File");
			btImportFile.setForeground(new Color(0, 255, 0));
			btImportFile.setRolloverEnabled(true);
			btImportFile.setToolTipText("Import selected file to database");
			btImportFile.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 3;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btImportFile, gbcWindow);
			add(btImportFile);

			btQuit = new JButton("Quit");
			btQuit.setRolloverEnabled(true);
			btQuit.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 5;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btQuit, gbcWindow);
			add(btQuit);

			btAdmin = new JButton("Admin");
			btAdmin.setRolloverEnabled(true);
			btAdmin.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 7;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.HORIZONTAL;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btAdmin, gbcWindow);
			add(btAdmin);

			btStop = new JButton("Sort Stop Number");
			btStop.setRolloverEnabled(true);
			btStop.addActionListener(this);
			gbcWindow.gridx = 1;
			gbcWindow.gridy = 8;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.NONE;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btStop, gbcWindow);
			add(btStop);

			btNameLocal = new JButton("Sort Name without Locality");
			btNameLocal.setRolloverEnabled(true);
			btNameLocal.addActionListener(this);
			gbcWindow.gridx = 2;
			gbcWindow.gridy = 8;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.NONE;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 1;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btNameLocal, gbcWindow);
			add(btNameLocal);

			btLocality = new JButton("Sort Locaity");
			btLocality.setRolloverEnabled(true);
			btLocality.addActionListener(this);
			gbcWindow.gridx = 3;
			gbcWindow.gridy = 8;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.NONE;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btLocality, gbcWindow);
			add(btLocality);

			btName = new JButton("Sort Name");
			btName.setRolloverEnabled(true);
			btName.addActionListener(this);
			gbcWindow.gridx = 4;
			gbcWindow.gridy = 8;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.NONE;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btName, gbcWindow);
			add(btName);

			lbOutput = new JLabel("");
			JScrollPane scpOutput = new JScrollPane(lbOutput);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 6;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.NONE;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 1;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(scpOutput, gbcWindow);
			add(scpOutput);
		}

		/**
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btFilter) {
				// Action for btFilter
			}
			if (e.getSource() == btClearDB) {
				// Action for btClearDB
			}
			if (e.getSource() == btSelectFile) {
				// Action for btSelectFile
				new LoadData(tbTable, lbOutput);
			}
			if (e.getSource() == btImportFile) {
				new SaveData(tbTable);
			}
			if (e.getSource() == btQuit) {
				// Action for btQuit
			}
			if (e.getSource() == btAdmin) {
				// Action for btAdmin
			}
			if (e.getSource() == btStop) {
				// Action for btStop
			}
			if (e.getSource() == btNameLocal) {
				// Action for btNameLocal
			}
			if (e.getSource() == btLocality) {
				// Action for btLocality
			}
			if (e.getSource() == btName) {
				// Action for btName
			}
		}
	}

	Window pnWindow;
	

	/**
	 */
	public GUI() {
		super("TITLE");

		pnWindow = new Window();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnWindow);
		pack();
		setVisible(true);
	}
}
