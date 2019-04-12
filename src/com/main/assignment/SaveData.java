package com.main.assignment;

/**
 * SaveData Class for Java Assignment
 * 	Class used to store database connection information
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class SaveData {
	public SaveData(JTable tbTable) {
		Connection connect = null;
		Statement state = null;

		try {
			// Connect to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(ConnectionInfo.getDburl(), ConnectionInfo.getDbuser(), ConnectionInfo.getDbpass());
			state = connect.createStatement();

			// Convert to strings
			for (int i = ConnectionInfo.getHeader(); i < tbTable.getRowCount(); i++) {
				String StopNumber = tbTable.getValueAt(i, 0).toString();
				String NamewithoutLocality = tbTable.getValueAt(i, 1).toString();
				String Locality = tbTable.getValueAt(i, 2).toString();
				String Name = tbTable.getValueAt(i, 3).toString();
				String Easting = tbTable.getValueAt(i, 4).toString();
				String Northing = tbTable.getValueAt(i, 5).toString();

				// SQL Insert
				String sql = "INSERT INTO " + ConnectionInfo.getDbtable()
						+ " (StopNumber,NamewithoutLocality,Locality,Name,Easting,Northing) " + "VALUES ('" + StopNumber
						+ "','" + NamewithoutLocality + "','" + Locality + "'" + ",'" + Name + "','" + Easting + "','"
						+ Northing + "') ";
				state.execute(sql);
			}
			JOptionPane.showMessageDialog(null, "Import Data Successfully");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}

		try {
			if (state != null) {
				state.close();
				connect.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
