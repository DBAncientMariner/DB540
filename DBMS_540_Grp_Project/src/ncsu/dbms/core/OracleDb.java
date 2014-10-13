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
	
	private String UserName="";
	private String Password="";
	private String Url="";
	private Connection Conn;
	ResultSet ResultSet;
	Statement Stmt;
	public OracleDb(){
<<<<<<< HEAD
		UserName="SYSTEM";
		Password="aCZ6q7F";
		Url="jdbc:oracle:thin:System@//AM-THINKPAD:1521/xe";
=======
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = "system";
		String password = "rambo";
		String url="jdbc:oracle:thin:System@//Sajal-PC:1521/xe";
		
>>>>>>> 892db12d0b8db06891014cd36a2df0f47ffe29be
		
	}
	public boolean OpenConnection()
	{
		try
		{
			if(Conn==null ||  Conn.isClosed())
			{
				try {
					Class.forName("oracle.jdbc.OracleDriver");
				} catch (ClassNotFoundException e) {
				}
				Conn = DriverManager.getConnection(Url, UserName, Password);
			}
		}
		catch(SQLException e)
		{
			return false;
		}
		return true;
	}
	public void CloseConnection()
	{
		try {
			ResultSet.close();
			Stmt.close();
			Conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet GetResultSet(String sql)
	{
		ResultSet=null;
		if(OpenConnection())
		{
			try
			{
				Stmt= Conn.createStatement();
				ResultSet = Stmt.executeQuery(sql);
			}
			catch(SQLException e)
			{
			}
		}
		return ResultSet;
	}
	public void InsertQuery(String query)
	{
		
	}
}
