/**
 * 
 */
package ncsu.dbms.core;
import java.io.*;
import java.sql.*;
import oracle.jdbc.driver.*;


/**
 * @author ravi
 *
 */
public class OracleDb {
	
	
	public OracleDb(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = "SYSTEM";
		String password = "aCZ6q7F";
		String url="jdbc:oracle:thin:System@//AM-THINKPAD:1521/xe";
		
		
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			//STEP 4: Execute a query
		     Statement stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM CSC_User";
		      ResultSet rs = stmt.executeQuery(sql);
		     
		      rs.close();
		      stmt.close();
		      conn.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
