package ncsu.dbms.core;

public class NotificationFaculty {
	private int courseId;
	private int facultyId;
	private int studentId;
	public NotificationFaculty(int courseId, int facultyId, int studentId) {
		super();
		this.courseId = courseId;
		this.facultyId = facultyId;
		this.studentId = studentId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
