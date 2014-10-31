/**
 * 
 */
package ncsu.dbms.core;

/**
 * @author ravi
 *
 */
public class Notification {
	private String notificationTitle;
	private int exerciseId;
	private int courseId;
	private boolean isStudent;
	
	public Notification(String notificationTitle, int exerciseId, int courseId,
			boolean isStudent) {
		super();
		this.notificationTitle = notificationTitle;
		this.exerciseId = exerciseId;
		this.courseId = courseId;
		this.isStudent = isStudent;
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public int getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(int exerciseId) {
		this.exerciseId = exerciseId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}
	
}
