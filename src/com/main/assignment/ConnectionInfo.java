package com.main.assignment;

public class ConnectionInfo {
	private static String dbdomain = "localhost";
	private static String dbport = "8889";
	private static String dbuser = "Eoghan";
	private static String dbpass = "letmein";
	private static String dbdata = "java";
	private static String dbtable = "assignment";
	private static String dburl = "jdbc:mysql://" + dbdomain + ":" + dbport + "/" + dbdata;
	private static int header = 0;
	
	
	public static String getDbdomain() {
		return dbdomain;
	}
	public static void setDbdomain(String dbdomain) {
		ConnectionInfo.dbdomain = dbdomain;
	}
	public static String getDbport() {
		return dbport;
	}
	public static void setDbport(String dbport) {
		ConnectionInfo.dbport = dbport;
	}
	public static String getDbuser() {
		return dbuser;
	}
	public static void setDbuser(String dbuser) {
		ConnectionInfo.dbuser = dbuser;
	}
	public static String getDbpass() {
		return dbpass;
	}
	public static void setDbpass(String dbpass) {
		ConnectionInfo.dbpass = dbpass;
	}
	public static String getDbdata() {
		return dbdata;
	}
	public static void setDbdata(String dbdata) {
		ConnectionInfo.dbdata = dbdata;
	}
	public static String getDbtable() {
		return dbtable;
	}
	public static void setDbtable(String dbtable) {
		ConnectionInfo.dbtable = dbtable;
	}
	public static String getDburl() {
		return dburl;
	}
	public static void setDburl(String dburl) {
		ConnectionInfo.dburl = dburl;
	}
	public static int getHeader() {
		return header;
	}
	public static void setHeader(int header) {
		ConnectionInfo.header = header;
	}
	
	
}
