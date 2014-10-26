package ncsu.dbms.db;

import java.util.LinkedList;
import java.util.List;

import ncsu.dbms.core.UserAttempt;

public class ScoresData {
	public static List<UserAttempt> getAllAttempts(String userId) {
		List<UserAttempt> allAttempts = new LinkedList<>();
		UserAttempt ua1 = new UserAttempt();
		ua1.setUA_EXERCISE_ID(1);
		ua1.setUA_SCORE(100);
		ua1.setUA_ID(1);
		allAttempts.add(ua1);
		UserAttempt ua2 = new UserAttempt();
		ua2.setUA_EXERCISE_ID(1);
		ua2.setUA_SCORE(75);
		ua2.setUA_ID(2);
		allAttempts.add(ua2);
		UserAttempt ua3 = new UserAttempt();
		ua3.setUA_EXERCISE_ID(2);
		ua3.setUA_SCORE(80);
		ua3.setUA_ID(1);
		allAttempts.add(ua3);
		return allAttempts;
	}
}
