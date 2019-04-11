package com.main.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class EraseData {
	public EraseData() {
		Connection connect = null;
		Statement s = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connect = DriverManager.getConnection(ConnectionInfo.getDburl(), ConnectionInfo.getDbuser(), ConnectionInfo.getDbpass());

			s = connect.createStatement();

				// SQL Insert

				String sql = "DELETE FROM `" + ConnectionInfo.getDbtable() + "` WHERE `StopNumber` > 0";
				s.execute(sql);
			

		JOptionPane.showMessageDialog(null, "Data Erased Successfully");

	} catch (Exception ex) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, ex.getMessage());
		ex.printStackTrace();
	}

	try {
		if (s != null) {
			s.close();
			connect.close();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
}
}
