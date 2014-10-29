package ncsu.dbms.db;

import java.util.LinkedList;
import java.util.List;

import ncsu.dbms.core.Answer;
import ncsu.dbms.core.CorrectAnswer;
import ncsu.dbms.core.OracleDataAdapter1;
import ncsu.dbms.core.UserAttempt;

public class PastSubmissionData {
	
	public static List<UserAttempt> getExercise(boolean active) {
		List<UserAttempt> userAttemptList = OracleDataAdapter1.GetUserAttemptForPastSubmission();
		for (UserAttempt userAttempt : userAttemptList) {
			boolean isActive = OracleDataAdapter1.IsActive(userAttempt.getUA_EXERCISE_ID());
			if(isActive != active) {
				userAttemptList.remove(userAttempt);
			}
		}
		return userAttemptList;
	}
	
	public static String getExerciseName(int exerciseId) {
		String exerciseName = OracleDataAdapter1.getExerciseName(exerciseId);
		return exerciseName;
	}
	
	public static List<Answer> getExerciseWithAnswer(int UA_id, int exerciseId, boolean isActive) {
		List<Answer> list = new LinkedList<Answer>();
		List<CorrectAnswer> options = new LinkedList<CorrectAnswer>();
		CorrectAnswer ca1 = new CorrectAnswer("1", true, false);
		ca1.setShortExplanation("Short Explanation");
		ca1.setDetailExplanation("Detailed Explanation");
		CorrectAnswer ca2 = new CorrectAnswer("2", false, true);
		CorrectAnswer ca3 = new CorrectAnswer("3", false, false);
		CorrectAnswer ca4 = new CorrectAnswer("4", false, false);
		options.add(ca1);
		options.add(ca2);
		options.add(ca3);
		options.add(ca4);
		Answer ans1 = new Answer("question1", options, false);
		list.add(ans1);
		options = new LinkedList<CorrectAnswer>();
		ca1 = new CorrectAnswer("1", false, false);
		ca1.setShortExplanation("Short Explanation");
		ca1.setDetailExplanation("Detailed Explanation");
		ca2 = new CorrectAnswer("2", true, true);
		ca3 = new CorrectAnswer("3", false, false);
		ca4 = new CorrectAnswer("4", false, false);
		options.add(ca1);
		options.add(ca2);
		options.add(ca3);
		options.add(ca4);
		Answer ans2 = new Answer("question2", options, false);
		list.add(ans2);
		options = new LinkedList<CorrectAnswer>();
		ca1 = new CorrectAnswer("1", false, false);
		ca1.setShortExplanation("Short Explanation");
		ca1.setDetailExplanation("Detailed Explanation");
		ca2 = new CorrectAnswer("2", true, true);
		ca3 = new CorrectAnswer("3", true, true);
		ca4 = new CorrectAnswer("4", false, false);
		options.add(ca1);
		options.add(ca2);
		options.add(ca3);
		options.add(ca4);
		Answer ans3 = new Answer("question3", options, true);
		list.add(ans3);
		options = new LinkedList<CorrectAnswer>();
		ca1 = new CorrectAnswer("1", true, false);
		ca1.setShortExplanation("Short Explanation");
		ca1.setDetailExplanation("Detailed Explanation");
		ca2 = new CorrectAnswer("2", true, true);
		ca3 = new CorrectAnswer("3", true, true);
		ca4 = new CorrectAnswer("4", false, false);
		options.add(ca1);
		options.add(ca2);
		options.add(ca3);
		options.add(ca4);
		Answer ans4 = new Answer("question4", options, true);
		list.add(ans4);
		return list;
	}
}
