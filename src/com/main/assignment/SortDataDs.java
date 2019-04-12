package com.main.assignment;

/**
 * SortDataAs Class for Java Assignment
 * 	Class used to sort table contents DESCENDING
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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SortDataDs {
	public SortDataDs(JTable tbTable, int i) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbTable.getModel());
		tbTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = i;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));

		sorter.setSortKeys(sortKeys);
		sorter.sort();
	}
}
