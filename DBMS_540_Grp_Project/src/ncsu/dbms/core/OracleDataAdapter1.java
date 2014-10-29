package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		List<UserAttemptExercise> listUserAttemptExercise = new ArrayList<UserAttemptExercise>();
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
			if(resultset == null) {
				return listUserAttemptExercise;
			}
			while (resultset.next()) {
				userAttemptExercise = new UserAttemptExercise();

				userAttemptExercise.UE_UA_ID = resultset.getInt("UE_UA_ID");
				userAttemptExercise.UE_QUESTION_ID = resultset
						.getInt("UE_QUESTION_ID");
				userAttemptExercise.UE_ANSWER_ID = resultset
						.getInt("UE_ANSWER_ID");
				String selected = resultset.getString("UE_ISSELECTED");
				if("t".equalsIgnoreCase(selected)) {
					userAttemptExercise.UE_ISSELECTED = true;
				} else {
					userAttemptExercise.UE_ISSELECTED = false;
				}
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
				.GetResultSet("Select * from CSC_QUESTIONBANK where QUESTIONBANK_ID = "
						+ Q_id);
		try {
			while (resultset != null && resultset.next()) {
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
			while (resultset != null && resultset.next()) {
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
		} 
		return listAnswerBank;
	}
	
	public static int GetSetForParamQues(int Q_id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "select * from"+
				"(select DISTINCT CSC_QB_PARAMETER_SET_ID from CSC_QB_PARAMETER_SET where CSC_QB_PARAMETER_SET_PARM_ID IN("+
						"select CSC_VAR_PARM_SURR_KEY From CSC_VAR_PARM "+
						"where CSC_VAR_PARM_VAR_SURR_KEY IN ( Select CSC_QB_VARIABLE_SURR_KEY From CSC_QB_VARIABLE "+
						"where CSC_QB_VAR_Q_ID = "+Q_id+")) ORDER BY DBMS_RANDOM.RANDOM) " +
						"where ROWNUM <= 1";
		ResultSet resultset = oracleDb
				.GetResultSet(query);

		int setId = -1;
		try {
			while(resultset != null && resultset.next()) {
				setId = resultset.getInt("CSC_QB_PARAMETER_SET_ID");
				break;
			}
				return setId;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			oracleDb.CloseConnection();
		}
		return setId;
	}
	
	public List<AnswerBank> GetCorrectAnswerBankParam(int Set_Id, int no_of_correct) {
		AnswerBank answerBank = new AnswerBank();
		List<AnswerBank> listAnswerBank = new LinkedList<AnswerBank>();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "Select * From (Select AB.* from CSC_Answerbank AB, CSC_QB_AB_PARM QBAB where "+
				"AB.ANSWERBANK_ID = QBAB.CSC_QB_AB_PARM_ANSWER_ID and QBAB.CSC_QB_AB_PARM_ISCORRECT = 'T' "+
				"and QBAB.CSC_QBANK_ANSBANK_PARM_SET_ID = "+Set_Id+" ORDER BY DBMS_RANDOM.RANDOM) where ROWNUM <= "+no_of_correct;
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		try {
			if(resultset == null) {
				return listAnswerBank;
			}
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
	
	public ArrayList<AnswerBank> GetInCorrectAnswerBankParam(int Set_Id, int no_of_incorrect) {
		AnswerBank answerBank = new AnswerBank();
		ArrayList<AnswerBank> listAnswerBank = new ArrayList<AnswerBank>();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "Select * From (Select AB.* from CSC_Answerbank AB, CSC_QB_AB_PARM QBAB where AB.ANSWERBANK_ID = QBAB.CSC_QB_AB_PARM_ANSWER_ID and "+ 
				"QBAB.CSC_QB_AB_PARM_ISCORRECT = 'F' and QBAB.CSC_QBANK_ANSBANK_PARM_SET_ID = "+Set_Id+" ORDER BY DBMS_RANDOM.RANDOM) where ROWNUM <= "+no_of_incorrect;
		
		ResultSet resultset = oracleDb
					.GetResultSet(query);
		try {
			if(resultset == null) {
				return listAnswerBank;
			}
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
	
	
	public List<VarParam> GetVarParamForSet(int setId) {
		VarParam varParam = new VarParam();
		List<VarParam> listVarParam = new ArrayList<VarParam>();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "select * from CSC_VAR_PARM where CSC_VAR_PARM_SURR_KEY IN (Select CSC_QB_PARAMETER_SET_PARM_ID "+
				"From CSC_QB_PARAMETER_SET where CSC_QB_PARAMETER_SET_ID ="+ setId+")";
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		try {
			while (resultset.next()) {
				varParam = new VarParam();

				varParam.CSC_VAR_PARM_SURR_KEY = resultset
						.getInt("CSC_VAR_PARM_SURR_KEY");
				varParam.CSC_VAR_PARM__ID = resultset
						.getInt("CSC_VAR_PARM__ID");
				varParam.CSC_VAR_PARM_VAR_SURR_KEY = resultset
						.getInt("CSC_VAR_PARM_VAR_SURR_KEY");
				varParam.CSC_VAR_PARM_VALUE = resultset
						.getString("CSC_VAR_PARM_VALUE");
				listVarParam.add(varParam);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listVarParam;
	}
	
	public ArrayList<QbVariable> GetQbVariableFromParam(int Parm_key) {
		QbVariable qbVariable = new QbVariable();
		ArrayList<QbVariable> listQbVariable = new ArrayList<QbVariable>();
		OracleDb oracleDb = new OracleDb();
		String query = "select * from CSC_QB_VARIABLE where CSC_QB_VARIABLE_SURR_KEY IN (Select CSC_VAR_PARM_VAR_SURR_KEY "+
				"from CSC_VAR_PARM where CSC_VAR_PARM_SURR_KEY = "+Parm_key+")";
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		try {
			while (resultset.next()) {
				qbVariable = new QbVariable();

				qbVariable.CSC_QB_VARIABLE_SURR_KEY = resultset
						.getInt("CSC_QB_VARIABLE_SURR_KEY");
				qbVariable.CSC_QB_VARIABLE_ID = resultset
						.getInt("CSC_QB_VARIABLE_ID");
				qbVariable.CSC_QB_VAR_Q_ID = resultset
						.getInt("CSC_QB_VAR_Q_ID");
				qbVariable.CSC_QB_VARIABLE_NAME = resultset
						.getString("CSC_QB_VARIABLE_NAME");
				listQbVariable.add(qbVariable);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listQbVariable;
	}
	
	public static String IsCorrectAnswerParam(int Set_id, int A_id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "select CSC_QB_AB_PARM_ISCORRECT from CSC_QB_AB_PARM where CSC_QBANK_ANSBANK_PARM_SET_ID = "+
				+ Set_id + " and CSC_QB_AB_PARM_ANSWER_ID =" + A_id;
		ResultSet resultset = oracleDb
				.GetResultSet(query);

		try {
			String str = resultset.getString("QABANK_ISCORRECT");
			return str;
		} catch (Exception e) {
		}
		return null;
	}
	
	public ArrayList<UserAttempt> GetUserAttemptForPastSubmission() {
		UserAttempt userAttempt = new UserAttempt();
		ArrayList<UserAttempt> listUserAttempt = new ArrayList<UserAttempt>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		int course_id = LocalSession.GetCurrentSelectedCourse();
		User user = LocalSession.GetCurrentUser(); 
		String query = "select * from CSC_USER_ATTEMPT where UA_EXERCISE_ID IN ( Select EXERCISE_ID From CSC_EXERCISE where EXERCISE_COURSE = "+course_id+") "+
				"and UA_USER_ID = "+user.UserId +" and UA_SUBMITTED = 'T'";
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		try {
			while (resultset.next()) {
				userAttempt = new UserAttempt();

				userAttempt.UA_ID = resultset.getInt("UA_ID");
				userAttempt.UA_USER_ID = resultset.getInt("UA_USER_ID");
				userAttempt.UA_EXERCISE_ID = resultset.getInt("UA_EXERCISE_ID");
				userAttempt.UA_SUBMITTED = resultset.getBoolean("UA_SUBMITTED");
				userAttempt.UA_SCORE = resultset.getDouble("UA_SCORE");
				try {
					userAttempt.UA_STARTATTEMPT_DATE = simpleDateFormat
							.parse(resultset.getString("UA_STARTATTEMPT_DATE"));
					userAttempt.UA_LASTATTEMPT_DATE = simpleDateFormat
							.parse(resultset.getString("UA_LASTATTEMPT_DATE"));
					userAttempt.UA_STARTATTEMPT_DATE = simpleDateFormat
							.parse(resultset.getString("UA_STARTATTEMPT_DATE"));
				} catch (Exception e) {
				}
				listUserAttempt.add(userAttempt);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listUserAttempt;
	}
	
	public static List<UserAttemptExercise> GetUserAttemptExerciseForPast(int UA_id) {
		UserAttemptExercise userAttemptExercise = new UserAttemptExercise();
		List<UserAttemptExercise> listUserAttemptExercise = new ArrayList<UserAttemptExercise>();
		User user = LocalSession.GetCurrentUser();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "select * from CSC_USERATTEMPT_EXERCISE where UE_UA_ID = "+UA_id;
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		try {
			if(resultset == null) {
				return listUserAttemptExercise;
			}
			while (resultset.next()) {
				userAttemptExercise = new UserAttemptExercise();

				userAttemptExercise.UE_UA_ID = resultset.getInt("UE_UA_ID");
				userAttemptExercise.UE_QUESTION_ID = resultset
						.getInt("UE_QUESTION_ID");
				userAttemptExercise.UE_ANSWER_ID = resultset
						.getInt("UE_ANSWER_ID");
				String selected = resultset.getString("UE_ISSELECTED");
				if("t".equalsIgnoreCase(selected)) {
					userAttemptExercise.UE_ISSELECTED = true;
				} else {
					userAttemptExercise.UE_ISSELECTED = false;
				}
				listUserAttemptExercise.add(userAttemptExercise);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listUserAttemptExercise;
	}
	
	public ArrayList<QbVariable> GetQbVariableForSet(int Q_id) {
		QbVariable qbVariable = new QbVariable();
		ArrayList<QbVariable> listQbVariable = new ArrayList<QbVariable>();
		OracleDb oracleDb =  new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_QB_VARIABLE where CSC_QB_VAR_Q_ID ="+Q_id);
		try {
			while (resultset.next()) {
				qbVariable = new QbVariable();

				qbVariable.CSC_QB_VARIABLE_SURR_KEY = resultset
						.getInt("CSC_QB_VARIABLE_SURR_KEY");
				qbVariable.CSC_QB_VARIABLE_ID = resultset
						.getInt("CSC_QB_VARIABLE_ID");
				qbVariable.CSC_QB_VAR_Q_ID = resultset
						.getInt("CSC_QB_VAR_Q_ID");
				qbVariable.CSC_QB_VARIABLE_NAME = resultset
						.getString("CSC_QB_VARIABLE_NAME");
				listQbVariable.add(qbVariable);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listQbVariable;
	}
	
	public static boolean CSC_USERATTEMPT_EXERCISE_PRM(int ua_id, int Q_id,
			int Set_id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		boolean resultset = oracleDb
				.InsertQuery("Insert into CSC_USERATTEMPT_EXERCISE_PRM(CSC_UA_EX_UA_ID,CSC_UA_EX_QUES_ID,CSC_UA_EX_PARM_SET_ID) "+
						"Values("+ua_id+","+Q_id+","+Set_id+")");
		return resultset;
	}
}