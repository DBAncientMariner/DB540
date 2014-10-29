/**
 * 
 */
package ncsu.dbms.core;

import java.util.Date;

/**
 * @author ravi
 *
 */
public class UserAttempt {

	int UA_ID =0;
	int UA_USER_ID =0;
	int UA_EXERCISE_ID =0;
	Date UA_STARTATTEMPT_DATE =new Date();
	Date UA_LASTATTEMPT_DATE =new Date();
	boolean UA_SUBMITTED =false;
	double UA_SCORE =0.00;
	int ATTEMP_ID;
	
	public int getATTEMP_ID() {
		return ATTEMP_ID;
	}
	public void setATTEMP_ID(int aTTEMP_ID) {
		ATTEMP_ID = aTTEMP_ID;
	}
	public int getUA_ID() {
		return UA_ID;
	}
	public void setUA_ID(int uA_ID) {
		UA_ID = uA_ID;
	}
	public int getUA_USER_ID() {
		return UA_USER_ID;
	}
	public void setUA_USER_ID(int uA_USER_ID) {
		UA_USER_ID = uA_USER_ID;
	}
	public int getUA_EXERCISE_ID() {
		return UA_EXERCISE_ID;
	}
	public void setUA_EXERCISE_ID(int uA_EXERCISE_ID) {
		UA_EXERCISE_ID = uA_EXERCISE_ID;
	}
	public Date getUA_STARTATTEMPT_DATE() {
		return UA_STARTATTEMPT_DATE;
	}
	public void setUA_STARTATTEMPT_DATE(Date uA_STARTATTEMPT_DATE) {
		UA_STARTATTEMPT_DATE = uA_STARTATTEMPT_DATE;
	}
	public Date getUA_LASTATTEMPT_DATE() {
		return UA_LASTATTEMPT_DATE;
	}
	public void setUA_LASTATTEMPT_DATE(Date uA_LASTATTEMPT_DATE) {
		UA_LASTATTEMPT_DATE = uA_LASTATTEMPT_DATE;
	}
	public boolean isUA_SUBMITTED() {
		return UA_SUBMITTED;
	}
	public void setUA_SUBMITTED(boolean uA_SUBMITTED) {
		UA_SUBMITTED = uA_SUBMITTED;
	}
	public double getUA_SCORE() {
		return UA_SCORE;
	}
	public void setUA_SCORE(double uA_SCORE) {
		UA_SCORE = uA_SCORE;
	}
}
