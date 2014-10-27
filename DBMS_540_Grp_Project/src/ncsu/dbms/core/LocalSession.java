/**
 * 
 */
package ncsu.dbms.core;

import javax.swing.DefaultListModel;

/**
 * @author ravi Local store
 */
public class LocalSession {

	private static User _currentUser = new User();
	private static String _currentSelectedCourse = "";

	public static User GetCurrentUser() {
		return _currentUser;
	}

	public static void SetCurrentUser(User user) {
		_currentUser = user;
	}

	public static String GetCurrentSelectedCourse() {
		return _currentSelectedCourse;
	}

	public static void SetCurrentSelectedCourse(String courseId) {
		_currentSelectedCourse = courseId;
	}

	public static DefaultListModel CourseListModel=new DefaultListModel();
}
