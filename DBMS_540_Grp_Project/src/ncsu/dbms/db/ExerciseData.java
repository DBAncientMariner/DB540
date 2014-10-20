package ncsu.dbms.db;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ncsu.dbms.core.Question;

public class ExerciseData {

	public static List<Question> getExercise(String assignment) {
		List<Question> exercises = new ArrayList<>();
		if(assignment != null && (assignment.equalsIgnoreCase("Assignment 1") || assignment.equalsIgnoreCase("Assignment 3"))) {
			List<String> options = new ArrayList<String>();
			options.add("One");
			options.add("two");
			options.add("three");
			options.add("four");
			exercises.add(new Question("Question1", options, false));
			exercises.add(new Question("Question2", options, true));
			exercises.add(new Question("Question3", options, false));
			exercises.add(new Question("Question4", options, false));
			exercises.add(new Question("Question5", options, false));
			exercises.add(new Question("Question6", options, true));
			exercises.add(new Question("Question7", options, false));
		} else {
			List<String> options = new ArrayList<String>();
			options.add("One");
			options.add("two");
			options.add("three");
			options.add("four");
			exercises.add(new Question("Question1", options, false));
			exercises.add(new Question("Question2", options, true));
			exercises.add(new Question("Question3", options, false));
			exercises.add(new Question("Question4", options, false));
		}
		return exercises;
	}
	
	public static List<String> getAssigmentList(String course) {
		List<String> list = new LinkedList<String>();
		list.add("Assignment 1");
		list.add("Assignment 2");
		list.add("Assignment 3");
		return list;
	}
}
