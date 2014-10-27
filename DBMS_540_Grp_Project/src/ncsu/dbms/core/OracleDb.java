/**
 * 
 */
package ncsu.dbms.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

/**
 * @author ravi
 *
 */
public class OracleDb {

	private String UserName = "";
	private String Password = "";
	private String Url = "";
	private Connection Conn;
	ResultSet ResultSet;
	Statement Stmt;

	public OracleDb() {
		UserName = "SYSTEM";
		Password = "aCZ6q7F";
		Url = "jdbc:oracle:thin:System@//AM-THINKPAD:1521/xe";

	}

	public boolean OpenConnection() {
		try {
			if (Conn == null || Conn.isClosed()) {
				try {
					Class.forName("oracle.jdbc.OracleDriver");
				} catch (ClassNotFoundException e) {
				}
				Conn = DriverManager.getConnection(Url, UserName, Password);
			}
			
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public void CloseConnection() {
		try {
			if (ResultSet != null)
				ResultSet.close();
			if(Stmt!=null)
				Stmt.close();
			Conn.commit();
			Conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResultSet GetResultSet(String sql) {
		ResultSet = null;
		if (OpenConnection()) {
			try {
				Stmt = Conn.createStatement();
				ResultSet = Stmt.executeQuery(sql);
			} catch (SQLException e) {
			}
		}
		return ResultSet;
	}

	public boolean InsertQuery(String query) {
		try {
			if (OpenConnection()) {
				Stmt = Conn.createStatement();
				Stmt.executeUpdate(query);
				Conn.commit();

			} else
				return false;
		} catch (SQLException e) {
			return false;
		}
		CloseConnection();
		return true;
	}

	//no output stored procedure.
	public boolean ExecuteStoredProcedure2Param(String storedProcedure,ArrayList<Object> Param)
	{
		try
		{
			if(OpenConnection())
			{
				Stmt = Conn.createStatement();
			    CallableStatement proc =
			    		Conn.prepareCall("{call "+storedProcedure+"(?, ?)}");
			    int index=1;
			    for(Object param:Param)
			    {
			    	 proc.setObject(index, param);
			    	 index++;
			    }
			    proc.execute();
				Conn.commit();
			}
		}
		catch (SQLException e)
		{
		    return false;
		}
		CloseConnection();
		return true;
	}
	public boolean ExecuteStoredProcedure3Param(String storedProcedure,ArrayList<String> Param)
	{
		try
		{
			if(OpenConnection())
			{
				Stmt = Conn.createStatement();
			    CallableStatement proc =
			    		Conn.prepareCall("{call "+storedProcedure+"(?, ?,?)}");
			    int index=1;
			    for(String param:Param)
			    {
			    	 proc.setString(index, param);
			    	 index++;
			    }
			    proc.execute();
				Conn.commit();
			}
		}
		catch (SQLException e)
		{
		    return false;
		}
		CloseConnection();
		return true;
	}
	public boolean ExecuteStoredProcedure1Param(String storedProcedure,ArrayList<String> Param)
	{
		try
		{
			if(OpenConnection())
			{
				Stmt = Conn.createStatement();
			    CallableStatement proc =
			    		Conn.prepareCall("{call "+storedProcedure+"(?)}");
			    int index=1;
			    for(String param:Param)
			    {
			    	 proc.setString(index, param);
			    	 index++;
			    }
			    proc.execute();
				Conn.commit();
			}
		}
		catch (SQLException e)
		{
		    return false;
		}
		CloseConnection();
		return true;
	}
	public int ExecuteStoredProcedureExerciseReturn(String storedProcedure,ArrayList<String> Param)
	{
		try
		{
			if(OpenConnection())
			{
				Stmt = Conn.createStatement();
			    CallableStatement proc =
			    		Conn.prepareCall("{call "+storedProcedure+"(?)}");
			    int index=1;
			    for(String param:Param)
			    {
			    	 proc.setString(index, param);
			    	 index++;
			    }
			    proc.registerOutParameter(1, Types.INTEGER);
			    proc.execute();
				Conn.commit();
				int retval = proc.getInt(1);
				return retval;
			}
		}
		catch (SQLException e)
		{
		    return 0;
		}
		CloseConnection();
		return 0;
	}
}
