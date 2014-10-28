package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class OracleDataAdapter1 {

	public static boolean InsertIntoUserAttempExercise(int ua_id, int Q_id,
			int A_id, char is_select) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		boolean resultset = oracleDb
				.InsertQuery("Insert into CSC_USERATTEMPT_EXERCISE(UE_UA_ID,UE_QUESTION_ID ,UE_ANSWER_ID ,UE_ISSELECTED) Values("
						+ ua_id
						+ ","
						+ Q_id
						+ ","
						+ A_id
						+ ",'"
						+ is_select
						+ "')");
		return resultset;
	}

	public static boolean UpdateIntoUserAttempExercise(int ua_id, int Q_id,
			int A_id, char is_select) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		boolean resultset = oracleDb
				.InsertQuery("Update CSC_USERATTEMPT_EXERCISE SET UE_ISSELECTED ='"
						+ is_select
						+ "' where UE_QUESTION_ID ="
						+ Q_id
						+ " and UE_ANSWER_ID ="
						+ A_id
						+ " and UE_UA_ID ="
						+ ua_id);
		return resultset;
	}

	public static List<UserAttemptExercise> GetUserAttemptExerciseForSaved(
			int ExerciseId) {
		UserAttemptExercise userAttemptExercise = new UserAttemptExercise();
		List<UserAttemptExercise> listUserAttemptExercise = new LinkedList<UserAttemptExercise>();
		User user = LocalSession.GetCurrentUser();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select UAE.* from CSC_USERATTEMPT_EXERCISE UAE,CSC_USER_ATTEMPT UA where UA.UA_ID  = UAE.UE_UA_ID and UA.UA_EXERCISE_ID = "
						+ ExerciseId
						+ " and UA.UA_USER_ID = "
						+ user.UserId
						+ " and UA_SUBMITTED = 'F'");
		try {
			while (resultset.next()) {
				userAttemptExercise = new UserAttemptExercise();

				userAttemptExercise.UE_UA_ID = resultset.getInt("UE_UA_ID");
				userAttemptExercise.UE_QUESTION_ID = resultset
						.getInt("UE_QUESTION_ID");
				userAttemptExercise.UE_ANSWER_ID = resultset
						.getInt("UE_ANSWER_ID");
				userAttemptExercise.UE_ISSELECTED = resultset
						.getBoolean("UE_ISSELECTED");
				listUserAttemptExercise.add(userAttemptExercise);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listUserAttemptExercise;
	}

	public static String IsCorrectAnswer(int Q_id, int A_id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("select QABANK_ISCORRECT from CSC_QuestionBank_AnswerBank where QABANK_QUESITON_ID = "
						+ Q_id + " and QABANK_ANSWER_ID =" + A_id);

		try {
			String str = resultset.getString("QABANK_ISCORRECT");
			return str;
		} catch (Exception e) {
		}
		return null;
	}

	public static List<QuestionBank> GetQuestionBankFromQid(int Q_id) {
		QuestionBank questionBank;
		List<QuestionBank> listQuestionBank = new LinkedList<QuestionBank>();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_QUESTIONBANK where QUESTIONBANK_ID"
						+ Q_id);
		try {
			while (resultset.next()) {
				questionBank = new QuestionBank();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				questionBank.QUESTIONBANK_ID = resultset
						.getInt("QUESTIONBANK_ID");
				questionBank.QUESTIONBANK_TEXT = resultset
						.getString("QUESTIONBANK_TEXT");
				questionBank.QUESTIONBANK_HINT = resultset
						.getString("QUESTIONBANK_HINT");
				questionBank.QUESTIONBANK_EXPLANATION = resultset
						.getString("QUESTIONBANK_EXPLANATION");
				questionBank.QUESTIONBANK_DIFFICULTYLEVEL = resultset
						.getInt("QUESTIONBANK_DIFFICULTYLEVEL");
				questionBank.QUESTIONBANK_CREATEDBY = resultset
						.getInt("QUESTIONBANK_CREATEDBY");
				questionBank.QUESTIONBANK_MODIFIEDBY = resultset
						.getInt("QUESTIONBANK_MODIFIEDBY");
				questionBank.CSC_QB_IS_PARAMETERIZED = resultset.getString(
						"CSC_QB_IS_PARAMETERIZED").equalsIgnoreCase("f") ? false
						: true;
				questionBank.CSC_QUESTIONBANK_TOPIC_ID = resultset
						.getInt("CSC_QUESTIONBANK_TOPIC_ID");
				try {
					questionBank.QUESTIONBANK_CREATEDDATE = simpleDateFormat
							.parse(resultset
									.getString("QUESTIONBANK_CREATEDDATE"));
					questionBank.QUESTIONBANK_MODIFIEDDATE = simpleDateFormat
							.parse(resultset
									.getString("QUESTIONBANK_MODIFIEDDATE"));
				} catch (Exception e) {
				}
				listQuestionBank.add(questionBank);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listQuestionBank;
	}

	public static List<AnswerBank> GetAnswerBankFromAid(int A_id) {
		AnswerBank answerBank = new AnswerBank();
		List<AnswerBank> listAnswerBank = new LinkedList<AnswerBank>();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_ANSWERBANK where ANSWERBANK_ID="
						+ A_id);
		try {
			while (resultset.next()) {
				answerBank = new AnswerBank();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				answerBank.ANSWERBANK_ID = resultset.getInt("ANSWERBANK_ID");
				answerBank.ANSWERBANK_TEXT = resultset
						.getString("ANSWERBANK_TEXT");
				answerBank.ANSWERBANK_EXPLANATION = resultset
						.getString("ANSWERBANK_EXPLANATION");
				answerBank.ANSWERBANK_CREATEDBY = resultset
						.getInt("ANSWERBANK_CREATEDBY");
				answerBank.ANSWERBANK_MODIFIEDBY = resultset
						.getInt("ANSWERBANK_MODIFIEDBY");
				try {
					answerBank.ANSWERBANK_CREATEDDATE = simpleDateFormat
							.parse(resultset
									.getString("ANSWERBANK_CREATEDDATE"));
					answerBank.ANSWERBANK_MODIFIEDDATE = simpleDateFormat
							.parse(resultset
									.getString("ANSWERBANK_MODIFIEDDATE"));
				} catch (Exception e) {
				}
				listAnswerBank.add(answerBank);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listAnswerBank;
	}
}
