/**
 * 
 */
package ncsu.dbms.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ravi
 *
 */
public class NotificationData {
	
	public static List<Notification> getNotifications() {
		List<Notification> notificationList = new LinkedList<Notification>();
		if(User.IsStudentOnCourse(LocalSession.GetCurrentUser(), LocalSession.getCurrentSelectedCourseObject())) {
			notificationList = getNotificationsForStudents();
		} else if(User.IsFacultyOnCourse(LocalSession.GetCurrentUser(), LocalSession.getCurrentSelectedCourseObject())) {
			notificationList = getNotificationForFaculty();
		} 
		return notificationList;
	}
	
	public static List<Notification> getNotificationsForStudents() {
		List<Notification> notificationList = new LinkedList<Notification>();
		Course course = LocalSession.getCurrentSelectedCourseObject();
		ArrayList<Exercise> ExerciseList = OracleDataAdapter1.GetExerciseForCourseNotifi(course.CSC_COURSE_Course_ID);
		for (Exercise exercise : ExerciseList) {
			boolean isToShow = OracleDataAdapter1.ExerciseAttempNotifici(exercise.EXERCISE_ID);
			if(isToShow) {
				boolean isNotifiedStudent = OracleDataAdapter1.ISNotifiedStudent(exercise.EXERCISE_ID, course.CSC_COURSE_Course_ID);
				if(!isNotifiedStudent) {
					String exerciseName = OracleDataAdapter1.getExerciseName(exercise.EXERCISE_ID);
					String nText = exerciseName + " needs attention"; 
					Notification n = new Notification(nText, exercise.EXERCISE_ID, course.CSC_COURSE_Course_ID, true);
					notificationList.add(n);
					OracleDataAdapter1.InsertIntoNotificationStudent(exercise.EXERCISE_ID, course.CSC_COURSE_Course_ID);
				}
			}
		}
		return notificationList;
	}
	
	public static List<Notification> getNotificationForFaculty() {
		List<Notification> notificationList = new LinkedList<Notification>();
		List<NotificationFaculty> list = OracleDataAdapter1.getNotificationForFaculty();
		for (NotificationFaculty notificationFaculty : list) {
			String text = notificationFaculty.getStudentId() + " has conflicting topics with TA courses";
			Notification n = new Notification(text, 0, 0, false);
			notificationList.add(n);
			User user = LocalSession.GetCurrentUser();
			Course course = LocalSession.getCurrentSelectedCourseObject();
			OracleDataAdapter1.DeleteFromNotificationFaculty(notificationFaculty.getStudentId(), user.UserId, course.CSC_COURSE_Course_ID);
		}
		return notificationList;
	}
}
