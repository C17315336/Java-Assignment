package com.main.assignment;

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

public class LoadData {
	public LoadData(JTable tbTable, JTextArea taOutput) {
		final DefaultTableModel model = (DefaultTableModel) tbTable.getModel();
		JFileChooser fileopen = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("Text/CSV file", "txt", "csv");
		fileopen.addChoosableFileFilter(filter);

		int ret = fileopen.showDialog(null, "Choose file");

		if (ret == JFileChooser.APPROVE_OPTION) {

			// Read Text file
			File file = fileopen.getSelectedFile();

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
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

			taOutput.append("File selected: " + fileopen.getSelectedFile().toString() + "\nProceed to request import\n");
		}
	}

	

}

