package com.main.assignment;

/**
 * PullData Class for Java Assignment
 * 	Class used to pull data from database
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class PullData {
	public PullData(JTable tbTable) {
		Connection connect = null;
		Statement state = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(ConnectionInfo.getDburl(), ConnectionInfo.getDbuser(), ConnectionInfo.getDbpass());
			state = connect.createStatement();
			PreparedStatement st = connect.prepareStatement("Select * from " + ConnectionInfo.getDbtable() + ";");
			ResultSet rs = st.executeQuery();
			tbTable.setModel(DbUtils.resultSetToTableModel(rs));
			connect.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
}
