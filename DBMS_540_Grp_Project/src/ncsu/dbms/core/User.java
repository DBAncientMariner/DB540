/**
 * 
 */
package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ravi
 *
 */
public class User {
	public int UserId=0;
	public String UserFName="";
	public String UserMName="";
	public String UserLName="";
	public String UserEmail="";
	public String UserPassword="";
	public boolean UserIsActive=false;
	public Date UserCreatedDate;
	public Date UserLastModifiedDate;
	public int UserLastModifiedBy=1;
	
	
	public static boolean IsActiveUser(String userName, String password)
	{
		return false;
	}
	public void GetUser(String userEmail)
	{
		OracleDb oracleDb=new OracleDb();
		UserEmail=userEmail.replace("'","''");
		ResultSet rs=oracleDb.GetResultSet("Select * from CSC_User where User_Email='"+userEmail+"'");
		try {
			while(rs.next())
			{
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				UserId=rs.getInt("User_ID");
				UserFName=rs.getString("USER_FNAME");
				UserMName=rs.getString("USER_MNAME");
				UserLName=rs.getString("USER_LNAME");
				UserEmail=rs.getString("USER_EMAIL");
				UserIsActive=rs.getString("User_ID")=="T"?true:false;
				try
				{
				UserCreatedDate= simpleDateFormat.parse(rs.getString("User_CREATEDDATE"));
				UserLastModifiedDate=simpleDateFormat.parse(rs.getString("USER_LASTMODIFIEDDATE"));
				}
				catch(Exception e)
				{
					
				}
				UserLastModifiedBy=rs.getInt("USER_MODIFIEDBY");
			}
			oracleDb.CloseConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	public static boolean AddUser(User user)
	{
		OracleDb oracleDb=new OracleDb();
		User tempUser=new User();
		tempUser.GetUser(user.UserEmail);
		if(tempUser.UserId==0)
		{
			String query="insert into csc_user (User_ID,USER_FNAME,USER_MNAME,USER_LNAME,USER_EMAIL,USER_PASSWORD,USER_ACTIVE,USER_CREATEDDATE,USER_MODIFIEDBY,USER_LASTMODIFIEDDATE)";
			query =query + " values(csc_user_sequence.nextval,'"+user.UserFName+"','"+user.UserMName+"','"+user.UserLName+"','"+user.UserEmail+"','"+user.UserPassword+"','T',TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),1,TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'))";
			return oracleDb.InsertQuery(query);
		}
		else
			return false;
	}
	public static boolean IsFaculty(User user)
	{
		return false;
	}
	public static boolean IsTA(User user)
	{
		return false;
	}
	public static boolean IsStudent(User user)
	{
		return false;
	}
}
