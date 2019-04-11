package com.main.assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class SaveData {
	public SaveData(JTable tbTable) {
		Connection connect = null;
		Statement s = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection("jdbc:mysql://localhost:8889/java", "Eoghan", "letmein");

			s = connect.createStatement();

			for (int i = 0; i < tbTable.getRowCount(); i++) {
				String StopNumber = tbTable.getValueAt(i, 0).toString();
				String NamewithoutLocality = tbTable.getValueAt(i, 1).toString();
				String Locality = tbTable.getValueAt(i, 2).toString();
				String Name = tbTable.getValueAt(i, 3).toString();
				String Easting = tbTable.getValueAt(i, 4).toString();
				String Northing = tbTable.getValueAt(i, 5).toString();

				// SQL Insert

				String sql = "INSERT INTO Assignment "
						+ "(StopNumber,NamewithoutLocality,Locality,Name,Easting,Northing) " + "VALUES ('" + StopNumber
						+ "','" + NamewithoutLocality + "','" + Locality + "'" + ",'" + Name + "','" + Easting + "','"
						+ Northing + "') ";
				s.execute(sql);
			}

			JOptionPane.showMessageDialog(null, "Import Data Successfully");

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


