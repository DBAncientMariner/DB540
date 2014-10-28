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
import ncsu.dbms.core.OracleDataAdapter1;
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
	
	public static void saveExercise(List<Question> exerciseQuestions, Exercise exercise) {
		double score = 0;
		OracleDataAdapter adp = new OracleDataAdapter();
		int uaId = adp.InsertUserAttempSubmit(exercise.EXERCISE_ID, score, "F");
		for (Question question : exerciseQuestions) {
			List<Options> options = question.getOptions();
			for (Options op : options) {
				OracleDataAdapter1.InsertIntoUserAttempExercise(uaId, question.getQuestionId(), op.getAnswerId(), 'F');
			}
		}
	}
	
	public static void submitExercise(List<Question> exerciseQuestions, Exercise exercise) {
		double score = calculateScore(exerciseQuestions, exercise);
		OracleDataAdapter adp = new OracleDataAdapter();
		int uaId = adp.UpdateUserAttempSubmit(exercise.EXERCISE_ID, score, "T");
		for (Question question : exerciseQuestions) {
			List<Options> options = question.getOptions();
			for (Options op : options) {
				if(op.isMarked()) {
					OracleDataAdapter1.UpdateIntoUserAttempExercise(uaId, question.getQuestionId(), op.getAnswerId(), 'T');
				} else {
					OracleDataAdapter1.UpdateIntoUserAttempExercise(uaId, question.getQuestionId(), op.getAnswerId(), 'F');
				}
			}
		}
	}
	
	public static double calculateScore(List<Question> exerciseQuestions, Exercise exercise) {
		double score = 0;
		double correctscore = exercise.EXERCISE_CORRECTPT;
		double penaltyscore = exercise.EXERCISE_PENALTYPT;
		for (Question question : exerciseQuestions) {
			boolean isCorrect = true;
			List<Options> options = question.getOptions();
			for (Options op : options) {
				if(op.isCorrect() != op.isMarked()) {
					score -= penaltyscore;
					isCorrect = false;
					break;
				}
			}
			if(isCorrect) {
				score += correctscore;
			}
		}
		return score;
	}
}
