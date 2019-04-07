import java.sql.*;
import java.io.*;


public class Control 
{

  // main method to run (normally in a separate class, as per MVC)	
  public static void main (String args [])
	throws SQLException, IOException 
  {
	
    try {
	  Class.forName("oracle.jdbc.driver.OracleDriver");
      System.out.println("driver loaded");
    } catch (ClassNotFoundException e) {
	    System.out.println ("Could not load the driver");
	}

    String servername = "localhost";
    String portnumber = "1521";
    String sid = "ORA11GDB";
    String url = "jdbc:oracle:thin:@" + servername + ":" + portnumber + ":" + sid;
    String user, pass;
	user = readEntry("userid  : ");
	pass = readEntry("password: ");
    System.out.println(url);

    // register the driver
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	System.out.println ("before connection");


    // Connect to the database, passing the credentials
    Connection conn = DriverManager.getConnection(url, user, pass);
	System.out.println ("after connection");

    // Then, get a statement object (which will be used to execute queries, I/U/D etc)
    Statement stmt = conn.createStatement ();


    // The example here is of executing a query (which returns a result set)
    ResultSet rset = stmt.executeQuery
	("select staff_no, staff_name from builder2.staff");
    while (rset.next()) {
	System.out.println(rset.getString(1) + " " +
	                   rset.getString(2));
    }
    stmt.close();
    conn.close();
  }


   //readEntry function -- to read input data
   static String readEntry(String prompt) 
  {
      try {
		 StringBuffer buffer = new StringBuffer();
		 System.out.print(prompt);
		 System.out.flush();
		 int c = System.in.read();
		 while (c != '\n' && c != -1) {
		   buffer.append((char)c);
		   c = System.in.read();
	     }
	     return buffer.toString().trim();
      }  catch (IOException e) {
		 return "";
	     }
  }
}