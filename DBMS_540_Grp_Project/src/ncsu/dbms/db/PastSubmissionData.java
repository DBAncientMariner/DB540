package ncsu.dbms.db;

import java.util.LinkedList;
import java.util.List;

import ncsu.dbms.core.Answer;
import ncsu.dbms.core.CorrectAnswer;
import ncsu.dbms.core.UserAttempt;

public class PastSubmissionData {
	
	public static List<String> getAllAssignments() {
		List<String> assignments = new LinkedList<String>();
		assignments.add("Assignment 1");
		assignments.add("Assignment 2");
		assignments.add("Assignment 3");
		assignments.add("Assignment 4");
		return assignments;
	}
	
	public static List<String> getExercisesAttempted(String assignment) {
		List<String> attempts = new LinkedList<String>();
		attempts.add("Attempt 1");
		attempts.add("Attempt 2");
		attempts.add("Attempt 3");
		return attempts;
	}
	
	public static List<UserAttempt> getInctiveExercise(String userId) {
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
	
	public static List<UserAttempt> getActiveExercise(String userId) {
		List<UserAttempt> allAttempts = new LinkedList<>();
		UserAttempt ua1 = new UserAttempt();
		ua1.setUA_EXERCISE_ID(3);
		ua1.setUA_SCORE(50);
		ua1.setUA_ID(1);
		allAttempts.add(ua1);
		UserAttempt ua2 = new UserAttempt();
		ua2.setUA_EXERCISE_ID(3);
		ua2.setUA_SCORE(90);
		ua2.setUA_ID(2);
		allAttempts.add(ua2);
		UserAttempt ua3 = new UserAttempt();
		ua3.setUA_EXERCISE_ID(4);
		ua3.setUA_SCORE(94);
		ua3.setUA_ID(1);
		allAttempts.add(ua3);
		return allAttempts;
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
