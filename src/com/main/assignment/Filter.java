package com.main.assignment;

/**
 * Filter Class for Java Assignment
 * 	Class used to filter table contents
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

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Filter {
	public Filter(JTable tbTable, String text) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbTable.getModel());
		tbTable.setRowSorter(sorter);

		if (text.length() == 0) {
			sorter.setRowFilter(null);
		} else {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		}

		sorter.sort();
	}
}
