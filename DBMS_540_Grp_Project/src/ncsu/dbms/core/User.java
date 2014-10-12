/**
 * 
 */
package ncsu.dbms.core;

/**
 * @author ravi
 *
 */
public class User {
	public int UserId;
	public String UserFName;
	public String UserMName;
	public String UserLName;
	public String UserEmail;
	public String UserPassword;
	public String UserIsActive;
	public String UserCreatedDate;
	public String UserLastModifiedDate;
	public String UserLastModifiedBy;
	
	public static boolean IsActiveUser(String userName, String password)
	{
		
		return false;
	}
}
