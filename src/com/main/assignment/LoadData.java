package com.main.assignment;

/**
 * LoadData Class for Java Assignment
 * 	Class used to import data into the database
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

// Code based of Java Code Geeks
// https://examples.javacodegeeks.com/core-java/sql/import-csv-file-to-mysql-table-java-example/
public class LoadData {
	public LoadData(JTable tbTable, JTextArea taOutput) {
		// File selector
		final DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
		JFileChooser fileopen = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
		fileopen.addChoosableFileFilter(filter);

		int ret = fileopen.showDialog(null, "Choose file");
		if (ret == JFileChooser.APPROVE_OPTION) {
			// Read Text file
			File file = fileopen.getSelectedFile();

			// Populate table
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				int row = 0;
				while ((line = br.readLine()) != null) {
					String[] arr = line.split(",");
					model.addRow(new Object[0]);
					model.setValueAt(arr[0], row, 0);
					model.setValueAt(arr[1], row, 1);
					model.setValueAt(arr[2], row, 2);
					model.setValueAt(arr[3], row, 3);
					model.setValueAt(arr[4], row, 4);
					model.setValueAt(arr[5], row, 5);
					row++;
				}
				br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			taOutput.append("File selected: " + fileopen.getSelectedFile().toString() + "\nProceed to request import\n");
		}
	}



}
