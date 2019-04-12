package com.main.assignment;

/**
 * EraseData Class for Java Assignment
 * 	Class used to erase database contents
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

public class EraseData {
	public EraseData() {
		Connection connect = null;
		Statement state = null;

		try {
			// Connecting to database
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(ConnectionInfo.getDburl(), ConnectionInfo.getDbuser(), ConnectionInfo.getDbpass());
			state = connect.createStatement();

				// SQL Insert
				String sql = "DELETE FROM `" + ConnectionInfo.getDbtable() + "` WHERE `StopNumber` > 0";
				state.execute(sql);
				
		// Feedback to user
		JOptionPane.showMessageDialog(null, "Data Erased Successfully");
	} catch (Exception ex) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, ex.getMessage());
		ex.printStackTrace();
	}

	try {
		if (state != null) {
			state.close();
			connect.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
}
}
