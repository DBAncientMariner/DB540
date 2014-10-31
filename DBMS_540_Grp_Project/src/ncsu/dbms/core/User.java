/**
 * 
 */
package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public Date UserCreatedDate=new Date();
	public Date UserLastModifiedDate=new Date();
	public int UserLastModifiedBy=1;
	public String user_id_char="";
	public ArrayList<UserRole> UserRoles=new ArrayList<UserRole>();
	public ArrayList<Course> UserCourses=new ArrayList<Course>();
	public double AverageScore=0;
	public String ExerciseName="";
	
	public static boolean IsActiveUser(String userName, String password)
	{
		return false;
	}
	public static User GetUser(String userEmail)
	{
		OracleDataAdapter oracleDataAdapter=new OracleDataAdapter();
		User user=new User();
		user=oracleDataAdapter.GetUserDetails(userEmail);
		if(user.UserId!=0)
		{
			user.UserRoles=oracleDataAdapter.GetUserRole(user.UserId);
		}
		return user;
	}
	public static boolean IsValidUser(String username, String password )
	{
		OracleDataAdapter oracleDataAdapter=new OracleDataAdapter();
		User user=new User();
		user=oracleDataAdapter.GetUserDetails(username,password);
		return user.UserId==0?false:true;
	}
	
	public static boolean AddUser(User user)
	{
		OracleDataAdapter oracleDbAdapter=new OracleDataAdapter();
		return oracleDbAdapter.InsertUser(user);	
	}
	public static boolean IsFaculty(User user)
	{
		for(UserRole userRole:user.UserRoles)
		{
			if(userRole.Roles.Role_Name.equalsIgnoreCase("Faculty"))
				return true;
		}
		return false;
	}
	public static boolean IsTA(User user)
	{
		for(UserRole userRole:user.UserRoles)
		{
			if(userRole.Roles.Role_Name.equalsIgnoreCase("Teaching Assistant"))
				return true;
		}
		return false;
	}
	public static boolean IsStudent(User user)
	{
		for(UserRole userRole:user.UserRoles)
		{
			if(userRole.Roles.Role_Name.equalsIgnoreCase("Student"))
				return true;
		}
		return false;
	}
	public static boolean IsStudentOnCourse(User user, Course course)
	{
		OracleDataAdapter oracleDataAdapter=new OracleDataAdapter();
		return oracleDataAdapter.IsRoleOnCourse(user, course, 2);
		
	}
	public static boolean IsFacultyOnCourse(User user, Course course)
	{
		OracleDataAdapter oracleDataAdapter=new OracleDataAdapter();
		return oracleDataAdapter.IsRoleOnCourse(user, course, 1);
		
	}
	public static boolean IsTAOnCourse(User user, Course course)
	{
		OracleDataAdapter oracleDataAdapter=new OracleDataAdapter();
		return oracleDataAdapter.IsRoleOnCourse(user, course, 3);
		
	}
	
}
