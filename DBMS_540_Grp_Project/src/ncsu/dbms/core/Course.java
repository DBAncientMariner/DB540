/**
 * 
 */
package ncsu.dbms.core;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author ravi
 *
 */
public class Course {
	
	public int CSC_COURSE_Course_ID =0;
	public String CSC_COURSE_Course_Name ="";
	public String CSC_COURSE_token="";
	public Date CSC_COURSE_StartDate  =new Date();
	public Date CSC_COURSE_EndDate  =new Date();
	public int CSC_COURSE_Max_Enroll_No =0;
	public int CSC_COURSE_Number_Of_Students =0;
	public CourseLevel CSC_COURSE_LEVEL=new CourseLevel();
	public ArrayList<Topic> CourseTopic=new ArrayList<Topic>();

}
