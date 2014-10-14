/**
 * 
 */
package ncsu.dbms.core;

/**
 * @author ravi
 *
 * Local store
 */
public  class LocalSession {

	
	private static User _currentUser=new User();
	public static User GetCurrentUser()
	{
		return _currentUser;
	}
	public static void SetCurrentUser(User user)
	{
		_currentUser=user;
	}
	
	private static int _currentSelectedCourse=0;
	public static int GetCurrentSelectedCourse()
	{
		return _currentSelectedCourse;
	}
	public static void SetCurrentSelectedCourse(int courseId)
	{
		_currentSelectedCourse=courseId;
	}
	
}
