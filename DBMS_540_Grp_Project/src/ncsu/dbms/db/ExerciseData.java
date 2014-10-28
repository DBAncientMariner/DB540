package ncsu.dbms.db;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import ncsu.dbms.core.AnswerBank;
import ncsu.dbms.core.Course;
import ncsu.dbms.core.Exercise;
import ncsu.dbms.core.LocalSession;
import ncsu.dbms.core.Options;
import ncsu.dbms.core.OracleDataAdapter;
import ncsu.dbms.core.Question;
import ncsu.dbms.core.QuestionBank;

public class ExerciseData {

	public static List<String> getAssigmentList(String course) {
		List<String> list = new LinkedList<String>();
		list.add("Assignment 1");
		list.add("Assignment 2");
		list.add("Assignment 3");
		return list;
	}
	
	public static String getExerciseName(int id) {
		return "Exercise";
	}
	
	public static List<Exercise> getExerciseList() {
		OracleDataAdapter adp = new OracleDataAdapter();
		Course currentSelectedCourseObject = LocalSession.getCurrentSelectedCourseObject();
		List<Exercise> exerciseList = adp.GetActiveExerciseForCourse(currentSelectedCourseObject.CSC_COURSE_Course_ID);
		return exerciseList;
	}
	
	public static List<String> getExerciseName(List<Exercise> exercise) {
		List<String> list = new LinkedList<String>();
		for (Exercise exercise2 : exercise) {
			list.add(exercise2.EXERCISE_NAME);
		}
		return list;
	}
	
	public static List<Question> getExerciseQuestions(int exerciseId) {
		OracleDataAdapter adp = new OracleDataAdapter();
		List<QuestionBank> questionBankList = adp.GetQuestionBankForExerciseId(exerciseId);
		List<Question> questionsList = new LinkedList<Question>();
		for (QuestionBank questionBank : questionBankList) {
			List<Options> options = new LinkedList<Options>();
			List<AnswerBank> correctOption = adp.GetCorrectAnswerBank(questionBank.QUESTIONBANK_ID, 1);
			for (AnswerBank answerBank : correctOption) {
				Options op = new Options(answerBank.ANSWERBANK_ID, false, true);
				op.setAnswer(answerBank.ANSWERBANK_TEXT);
				options.add(op);
			}
			List<AnswerBank> incorrectOption = adp.GetInCorrectAnswerBank(questionBank.QUESTIONBANK_ID, 3);
			for (AnswerBank answerBank : incorrectOption) {
				Options op = new Options(answerBank.ANSWERBANK_ID, false, false);
				op.setAnswer(answerBank.ANSWERBANK_TEXT);
				options.add(op);
			}
			Collections.shuffle(options);
			Question q = new Question(questionBank.QUESTIONBANK_ID, questionBank.QUESTIONBANK_TEXT, options, false);
			questionsList.add(q);
		}
		Collections.shuffle(questionsList);
		return questionsList;
	}
	
	public static void saveExercise(List<Question> exerciseQuestions) {
		
	}
	
	public static void submitExercise(List<Question> exerciseQuestions) {
		
	}
}
