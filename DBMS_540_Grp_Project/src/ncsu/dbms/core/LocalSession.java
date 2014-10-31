/**
 * 
 */
package ncsu.dbms.core;

import java.util.ArrayList;

/**
 * @author ravi Local store
 */
public class LocalSession {

	private static User _currentUser = new User();
	private static int _currentSelectedCourse =0;
	public static ArrayList<Course> CourseListModel = new ArrayList<Course>();

	public static User GetCurrentUser() {
		return _currentUser;
	}

	public static void SetCurrentUser(User user) {
		_currentUser = user;
	}

	public static int GetCurrentSelectedCourse() {
		return _currentSelectedCourse;
	}

	public static void SetCurrentSelectedCourse(int courseId) {
		_currentSelectedCourse = courseId;
	}
	
	public static Course getCurrentSelectedCourseObject() {
		Course course = CourseListModel.get(_currentSelectedCourse);
		return course;
	}
	
	public static void clearSession() {
		_currentUser = null;
		_currentSelectedCourse = 0;
	}
}
