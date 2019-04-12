package com.main.assignment;

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
