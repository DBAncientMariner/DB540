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
}
