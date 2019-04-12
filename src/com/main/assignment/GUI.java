package com.main.assignment;

/**
 * GUI Class for Java Assignment
 * 	Class used to display interaction to user
 * 	Action primarily called to other classes
 * 	Definition explained in README
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

import javax.swing.*;

import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;

public class GUI extends JFrame {
	static GUI theGUI;

	class Window extends JPanel implements ActionListener {
		/**
		 * Attributes for the Window object
		 *
		 * tb = Table
		 * tf = Text Field
		 * lb = Label
		 * bt = Button
		 * cb = Check Box
		 * ta = Text Area
		 */
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

			// Layout set to grid mode
			GridBagLayout gbWindow = new GridBagLayout();
			GridBagConstraints gbcWindow = new GridBagConstraints();
			setLayout(gbWindow);


			// Label just to show program title
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


			// Table
			tbTable = new JTable();
			tbTable.setAutoCreateRowSorter(true);
			tbTable.setDragEnabled(true);
			tbTable.setGridColor(new Color(238, 238, 238));
			tbTable.setSelectionBackground(new Color(212, 212, 212));
			tbTable.setSelectionForeground(new Color(0, 0, 0));
			tbTable.setRowSorter(sorter);

			final DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
			model.addColumn("StopNumber");
			model.addColumn("NamewithoutLocality");
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


			// Text Field for user to input filter criteria
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


			// Button to action the filter request
			btFilter = new JButton("Filter");
			btFilter.setRolloverEnabled(true);
			btFilter.setToolTipText("Filter table");
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


			// Button to action deleting databases content
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


			// Button to action pulling databases content
			btPullDB = new JButton("Pull Database");
			btPullDB.setForeground(new Color(255, 165, 0));
			btPullDB.setRolloverEnabled(true);
			btPullDB.setToolTipText("Pull contents of database at present");
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


			// Button to select file to import to database
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


			// Button to import selected file into database
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


			// Check box for CSV that contains a header row
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


			// Button to exit interface
			btQuit = new JButton("Quit");
			btQuit.setRolloverEnabled(true);
			btQuit.setRolloverEnabled(true);
			btQuit.setToolTipText("Exit program");
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


			// Text area to output to i.e. LOG
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


			// Button to access admin interface
			btAdmin = new JButton("Admin");
			btAdmin.setRolloverEnabled(true);
			btAdmin.setToolTipText("Configure database connection settings");
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


			// Sorting button with left/right action
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


			// Sorting button with left/right action
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


			// Sorting button with left/right action
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


			// Sorting button with left/right action
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
		}

		/**
		 * Methods
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btFilter) {
				// Action for btFilter
				// Calls on filter and passes the table and contents of the filter input
				// Updates table and prints to log
				new Filter(tbTable, tfFilter.getText());
				taOutput.append(
						"Total rows of: " + tbTable.getRowCount() + "\nFor keyword '" + tfFilter.getText() + "'\n\n");
			}
			if (e.getSource() == btClearDB) {
				// Action for btClearDB
				// Calls to delete
				taOutput.append("Erase Requested\n\n");
				new EraseData();
			}
			if (e.getSource() == btPullDB) {
				// Action for btPullDB
				new PullData(tbTable);
				taOutput.append("Pull Requested\n");
				taOutput.append("Total rows of: " + tbTable.getRowCount() + " pulled\n\n");
			}
			if (e.getSource() == btSelectFile) {
				// Action for btSelectFile
				// Select data
				new LoadData(tbTable, taOutput);
			}
			if (e.getSource() == btImportFile) {
				// Action for btImportFile
				// Imports data
				new SaveData(tbTable);
				taOutput.append("\nTotal rows of: " + tbTable.getRowCount() + " imported\n\n");
			}
			if (e.getSource() == cbHeader) {
				// Action for cbHeader
				if (cbHeader.isSelected()) {
					ConnectionInfo.setHeader(1);
				} else {
					ConnectionInfo.setHeader(0);
				}
			}
			if (e.getSource() == btQuit) {
				// Action for btQuit
				System.exit(0); // Stop program
				theGUI.dispose(); // Close window
				theGUI.setVisible(false);
			}
			if (e.getSource() == btAdmin) {
				// Action for btAdmin
				// Calls to edit connection details
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


			// Right click actions for bottom buttons
			btStop.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 0);
					}
				}
			});

			// Right click actions for bottom buttons
			btNameLocal.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 1);
					}
				}
			});

			// Right click actions for bottom buttons
			btLocality.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON3) {
						new SortDataDs(tbTable, 2);
					}
				}
			});

			// Right click actions for bottom buttons
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
		super("Data Explorer - Eoghan Byrne");

		pnWindow = new Window();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setContentPane(pnWindow);
		pack();
		setVisible(true);
	}
}
