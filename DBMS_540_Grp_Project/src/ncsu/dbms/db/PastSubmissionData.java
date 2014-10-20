package ncsu.dbms.db;

import java.util.LinkedList;
import java.util.List;

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
}
