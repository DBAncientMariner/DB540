package ncsu.dbms.db;

import java.util.ArrayList;
import java.util.List;

import ncsu.dbms.core.Question;

public class ExerciseData {

	public static List<Question> getExercise() {
		List<Question> exercises = new ArrayList<>();
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
		return exercises;
	}
}
