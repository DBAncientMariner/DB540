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
			e.printStackTrace();
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
			while(resultset != null && resultset.next()) {
			String str = resultset.getString("QABANK_ISCORRECT");
			return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
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
					e.printStackTrace();
				}
				listQuestionBank.add(questionBank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
					e.printStackTrace();
				}
				listAnswerBank.add(answerBank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
					e.printStackTrace();
				}
				listAnswerBank.add(answerBank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
					e.printStackTrace();
				}
				listAnswerBank.add(answerBank);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return listAnswerBank;
	}
	
	
	public static List<VarParam> GetVarParamForSet(int setId) {
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			while(resultset != null && resultset.next()) {
			String str = resultset.getString("QABANK_ISCORRECT");
			return str;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<UserAttempt> GetUserAttemptForPastSubmission() {
		UserAttempt userAttempt = new UserAttempt();
		List<UserAttempt> listUserAttempt = new ArrayList<UserAttempt>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		int course_id = LocalSession.GetCurrentSelectedCourse();
		Course course = LocalSession.getCurrentSelectedCourseObject();
		User user = LocalSession.GetCurrentUser(); 
		String query = "select * from CSC_USER_ATTEMPT where UA_EXERCISE_ID IN ( Select EXERCISE_ID From CSC_EXERCISE where EXERCISE_COURSE = "+course.CSC_COURSE_Course_ID +") "+
				"and UA_USER_ID = "+user.UserId +" and UA_SUBMITTED = 'T'";
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		try {
			while (resultset.next()) {
				userAttempt = new UserAttempt();

				userAttempt.UA_ID = resultset.getInt("UA_ID");
				userAttempt.UA_USER_ID = resultset.getInt("UA_USER_ID");
				userAttempt.UA_EXERCISE_ID = resultset.getInt("UA_EXERCISE_ID");
				String submit = resultset.getString("UA_SUBMITTED");
				if("T".equalsIgnoreCase(submit)){
					userAttempt.UA_SUBMITTED = true;
				}
				else
				{
					userAttempt.UA_SUBMITTED = false;
							
				}
				userAttempt.UA_SCORE = resultset.getDouble("UA_SCORE");
				userAttempt.ATTEMP_ID = resultset.getInt("ATTEMP_ID");
				try {
					userAttempt.UA_STARTATTEMPT_DATE = simpleDateFormat
							.parse(resultset.getString("UA_STARTATTEMPT_DATE"));
					userAttempt.UA_LASTATTEMPT_DATE = simpleDateFormat
							.parse(resultset.getString("UA_LASTATTEMPT_DATE"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				listUserAttempt.add(userAttempt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return listUserAttemptExercise;
	}
	
	public static List<QbVariable> GetQbVariableForSet(int Q_id) {
		QbVariable qbVariable = new QbVariable();
		List<QbVariable> listQbVariable = new ArrayList<QbVariable>();
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
			e.printStackTrace();
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
	
	public static int GetSetForUSERATTEMPTEXERCISEPRM(int UA_Id, int Q_id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "select CSC_UA_EX_PARM_SET_ID from CSC_USERATTEMPT_EXERCISE_PRM where CSC_UA_EX_UA_ID ="+UA_Id+
				" and CSC_UA_EX_QUES_ID =" +Q_id;
		ResultSet resultset = oracleDb
				.GetResultSet(query);

		int setId = -1;
		try {
			while(resultset != null && resultset.next()) {
				setId = resultset.getInt("CSC_UA_EX_PARM_SET_ID");
				break;
			}
				return setId;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return setId;
	}
	
	public static int GetUAIdForExId(int Exercise_Id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		User user = LocalSession.GetCurrentUser();
		String query = "Select UA_ID from CSC_USER_ATTEMPT where UA_SUBMITTED = 'F' and UA_USER_ID = "+user.UserId+" and UA_EXERCISE_ID ="+Exercise_Id ;
		ResultSet resultset = oracleDb
				.GetResultSet(query);

		int UA_Id = -1;
		try {
			while(resultset != null && resultset.next()) {
				UA_Id = resultset.getInt("UA_ID");
				break;
			}
				return UA_Id;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return UA_Id;
	}
	
	public static boolean IsActive(int Exercise_Id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("select count(*) from Csc_Exercise where EXERCISE_ENDDATE >= SYSDATE AND EXERCISE_STARTDATE<= SYSDATE and EXERCISE_ID ="  + Exercise_Id);

		try {
			while(resultset != null && resultset.next()) {
			int count = resultset.getInt("COUNT(*)");
			if(count == 0)
				return false;
			else
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return false;
	}
	
	public static String getExerciseName(int exercise_Id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		String query = "select EXERCISE_NAME from CSC_EXERCISE where EXERCISE_ID = "+exercise_Id;
		ResultSet resultset = oracleDb.GetResultSet(query);

		try {
			while(resultset != null && resultset.next()) {
				String name = resultset.getString("EXERCISE_NAME");
				return name;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return "";
	}
	
	public ArrayList<Course> GetCourseForUser(User user) {

		int roleId = 0;
		//for (UserRole userRole : user.UserRoles) {
		//	if (userRole.Roles.Role_Name.equalsIgnoreCase("student")) {
		//		roleId = userRole.Roles.Role_ID;
		//		break;
		//	}
		//}
		OracleDb oracleDb = new OracleDb();
		Course course = new Course();
		ArrayList<Course> listCourse = new ArrayList<Course>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		oracleDb.OpenConnection();
		String query = "select * from csc_course where CSC_COURSE_COURSE_ID IN "+
			"(select CSC_CLASS_COURSE_ID from CSC_CLASS where CSC_CLASS_SURR_KEY IN(select CSC_U_R_CLASS_SURR_KEY "+
			"from csc_user_role where USER_ROLE_USER_ID =" + user.UserId +"))";
		
		ResultSet resultset = oracleDb.GetResultSet(query);
		try {
			while (resultset.next()) {
				course = new Course();

				course.CSC_COURSE_Course_ID = resultset
						.getInt("CSC_COURSE_Course_ID");
				course.CSC_COURSE_Course_Name = resultset
						.getString("CSC_COURSE_Course_Name");
				course.CSC_COURSE_token = resultset
						.getString("CSC_COURSE_token");
				course.CSC_COURSE_Max_Enroll_No = resultset
						.getInt("CSC_COURSE_Max_Enroll_No");
				course.CSC_COURSE_Number_Of_Students = resultset
						.getInt("CSC_COURSE_Number_Of_Students");
				try {
					course.CSC_COURSE_StartDate = simpleDateFormat
							.parse(resultset.getString("CSC_COURSE_StartDate"));
					course.CSC_COURSE_EndDate = simpleDateFormat
							.parse(resultset.getString("CSC_COURSE_EndDate"));
				} catch (Exception e) {
				}
				listCourse.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return listCourse;

	}
	
	public static ArrayList<Exercise> GetExerciseForCourseNotifi(int Course_id) {
		Exercise exercise = new Exercise();
		ArrayList<Exercise> listExercise = new ArrayList<Exercise>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("select * from csc_exercise where EXERCISE_ENDDATE = SYSDATE and EXERCISE_COURSE ="+Course_id);
		try {
			while (resultset.next()) {
				exercise = new Exercise();

				exercise.EXERCISE_ID = resultset.getInt("EXERCISE_ID");
				exercise.EXERCISE_NAME = resultset.getString("EXERCISE_NAME");
				exercise.EXERCISE_COURSE = resultset.getInt("EXERCISE_COURSE");
				exercise.EXERCISE_NAME = resultset.getString("EXERCISE_NAME");
				exercise.EXERCISE_DIFFICULTY_RANGE1 = resultset
						.getInt("EXERCISE_DIFFICULTY_RANGE1");
				exercise.EXERCISE_DIFFICULTY_RANGE2 = resultset
						.getInt("EXERCISE_DIFFICULTY_RANGE2");
				exercise.EXERCISE_RETRYLIMIT = resultset
						.getInt("EXERCISE_RETRYLIMIT");
				exercise.EXERCISE_CORRECTPT = resultset
						.getInt("EXERCISE_CORRECTPT");
				exercise.EXERCISE_PENALTYPT = resultset
						.getInt("EXERCISE_PENALTYPT");
				exercise.EXERCISE_SCORINGTYPE = resultset
						.getInt("EXERCISE_SCORINGTYPE");
				exercise.EXERCISE_CREATEDBY = resultset
						.getInt("EXERCISE_CREATEDBY");
				exercise.EXERCISE_MODIFIEDBY = resultset
						.getInt("EXERCISE_MODIFIEDBY");

				try {
					exercise.EXERCISE_STARTDATE = simpleDateFormat
							.parse(resultset.getString("EXERCISE_STARTDATE"));
					exercise.EXERCISE_ENDDATE = simpleDateFormat
							.parse(resultset.getString("EXERCISE_ENDDATE"));
					exercise.EXERCISE_LASTMODIFIEDDATE = simpleDateFormat
							.parse(resultset
									.getString("EXERCISE_LASTMODIFIEDDATE"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				listExercise.add(exercise);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return listExercise;
	}
	
	public static boolean ExerciseAttempNotifici(int Exercise_Id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		User user = LocalSession.GetCurrentUser();
		String query = "select count(*) from csc_user_attempt where UA_SUBMITTED = 'T' and UA_EXERCISE_ID =" + Exercise_Id +" and UA_USER_ID =" +user.UserId;
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		boolean IsAttempted = false;
		int count_attemp = 1;
		try {
			while(resultset != null && resultset.next()) {
				count_attemp = resultset.getInt("count(*)");
				break;
			}
			if(count_attemp == 0)
			{
				IsAttempted = true;
			}
			else
			{
				IsAttempted = false;
				
			}
			return IsAttempted;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return IsAttempted;
	}
	
	public static boolean ISNotifiedStudent(int Exercise_Id,int Course_id) {
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		User user = LocalSession.GetCurrentUser();
		String query = "select count(*) from NOTIFICATION_STUDENT where EXERCISE_ID = "+Exercise_Id+ "and COURSE_ID ="+Course_id+" and USER_ID =" +user.UserId ;
		ResultSet resultset = oracleDb
				.GetResultSet(query);
		boolean IsAttempted = false;
		int count_attemp = 1;
		try {
			while(resultset != null && resultset.next()) {
				count_attemp = resultset.getInt("count(*)");
				break;
			}
			if(count_attemp == 0)
			{
				IsAttempted = false;
			}
			else
			{
				IsAttempted = true;
				
			}
			return IsAttempted;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return IsAttempted;
	}
	
	public static boolean InsertIntoNotificationStudent(int Exercise_Id,int Course_id) {
		OracleDb oracleDb = new OracleDb();
		User user = LocalSession.GetCurrentUser();
		oracleDb.OpenConnection();
		String query = "INSERT INTO NOTIFICATION_STUDENT (EXERCISE_ID,COURSE_ID,USER_ID) VALUES ("+Exercise_Id+","+Course_id+","+user.UserId+")";
		boolean resultset = oracleDb
				.InsertQuery(query);
		return resultset;
	}
}