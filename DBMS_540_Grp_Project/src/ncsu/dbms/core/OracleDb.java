/**
 * 
 */
package ncsu.dbms.core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
		UserName="SYSTEM";
		Password="rambo";
		Url="jdbc:oracle:thin:System@//Sajal-PC:1521/xe";
		
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
			if(ResultSet!=null)
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
	public boolean InsertQuery(String query)
	{
		try
		{
			if(OpenConnection())
			{
				Stmt=Conn.createStatement();
				Stmt.executeUpdate(query);
				Conn.commit();
				
			}
			else
				return false;
		}
		catch(SQLException e)
		{
			return false;
		}
		CloseConnection();
		return true;
	}
}
