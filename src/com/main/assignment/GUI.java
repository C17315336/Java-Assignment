package com.main.assignment;

import javax.swing.*;

import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;

import net.proteanit.sql.DbUtils;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
		private JTextArea taOutput;
		private JButton btPullDB;
		private TableRowSorter<DefaultTableModel> sorter;

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
			model.addColumn("StopNumber");
			model.addColumn("NamewithoutLocality");
			model.addColumn("Locality");
			model.addColumn("Name");
			model.addColumn("Easting");
			model.addColumn("Northing");
			tbTable.setRowSorter(sorter);
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
			tfFilter = RowFilterUtil.createRowFilter(tbTable);
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
			gbcWindow.gridy = 3;
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
			cbHeader.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 5;
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
			gbcWindow.gridy = 4;
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
			gbcWindow.gridy = 6;
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
			gbcWindow.gridy = 8;
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
			gbcWindow.gridy = 9;
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
			gbcWindow.gridy = 9;
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
			gbcWindow.gridy = 9;
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
			gbcWindow.gridy = 9;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.NONE;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btName, gbcWindow);
			add(btName);

			taOutput = new JTextArea("*** LOG ***\nApplication Started\nPlease pull from database first\n\n", 2, 10);
			JScrollPane scpOutput = new JScrollPane(taOutput);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 7;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 1;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(scpOutput, gbcWindow);
			add(scpOutput);

			btPullDB = new JButton("Pull Database");
			btPullDB.setForeground(new Color(255, 165, 0));
			btPullDB.addActionListener(this);
			gbcWindow.gridx = 0;
			gbcWindow.gridy = 2;
			gbcWindow.gridwidth = 1;
			gbcWindow.gridheight = 1;
			gbcWindow.fill = GridBagConstraints.BOTH;
			gbcWindow.weightx = 1;
			gbcWindow.weighty = 0;
			gbcWindow.anchor = GridBagConstraints.NORTH;
			gbWindow.setConstraints(btPullDB, gbcWindow);
			add(btPullDB);
		}

		/**
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btFilter) {
				// Action for btFilter
				new Filter(tbTable, tfFilter.getText());
				taOutput.append("Total rows of: " + tbTable.getRowCount() + "\nFor keyword '" + tfFilter.getText() + "'\n\n");
			}
			if (e.getSource() == btClearDB) {
				// Action for btClearDB
				taOutput.append("Erase Requested\n\n");
				new EraseData();
			}
			if (e.getSource() == btSelectFile) {
				// Action for btSelectFile
				new LoadData(tbTable, taOutput);
			}
			if (e.getSource() == btImportFile) {
				new SaveData(tbTable);
				taOutput.append("\nTotal rows of: " + tbTable.getRowCount() + " imported\n\n");
			}
			if (e.getSource() == btQuit) {
				// Action for btQuit
				System.exit(0); // stop program
				theGUI.dispose(); // close window
				theGUI.setVisible(false);
			}
			if (e.getSource() == btAdmin) {
				// Action for btAdmin
				new Admin();
				taOutput.append("\n*** Admin panel accessed ***\n\n\n");
			}
			if (e.getSource() == btStop) {
				// Action for btStop
				new SortDataAs(tbTable, 0);
			}
			if (e.getSource() == btNameLocal) {
				// Action for btNameLocal
				new SortDataAs(tbTable, 1);
			}
			if (e.getSource() == btLocality) {
				// Action for btLocality
				new SortDataAs(tbTable, 2);
			}
			if (e.getSource() == btName) {
				// Action for btName
				new SortDataAs(tbTable, 3);
			}
			if (e.getSource() == btPullDB) {
				// Action for btPullDB
				new PullData(tbTable);
				taOutput.append("Pull Requested\n");
				taOutput.append("Total rows of: " + tbTable.getRowCount() + " pulled\n\n");
			}
			if (e.getSource() == cbHeader) {
				// Action for cbHeader
				if (cbHeader.isSelected()) {
					ConnectionInfo.setHeader(1);
				} else {
					ConnectionInfo.setHeader(0);
				}
			}

			btStop.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 0);
					}
				}
			});

			btNameLocal.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 1);
					}
				}
			});

			btLocality.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 2);
					}
				}
			});

			btName.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 3);
					}
				}
			});

		}
		

	}

	Window pnWindow;

	/**
	 */
	public GUI() {
		super("DEMO");

		pnWindow = new Window();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnWindow);
		pack();
		setVisible(true);
	}
}
