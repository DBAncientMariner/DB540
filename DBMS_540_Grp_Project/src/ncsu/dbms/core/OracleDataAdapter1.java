package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OracleDataAdapter1 {

	
	public static boolean InsertIntoUserAttempExercise(int ua_id,int Q_id, int A_id,char is_select)
	{
		OracleDb oracleDb = new OracleDb();
			oracleDb.OpenConnection();
			boolean resultset = oracleDb.InsertQuery("Insert into CSC_USERATTEMPT_EXERCISE(UE_UA_ID,UE_QUESTION_ID ,UE_ANSWER_ID ,UE_ISSELECTED) Values("+ua_id+","+Q_id+","+A_id+",'"+is_select+"')");
			return resultset;
	}
	
	public static boolean UpdateIntoUserAttempExercise(int ua_id,int Q_id, int A_id,char is_select)
	{
		OracleDb oracleDb = new OracleDb();
			oracleDb.OpenConnection();
			boolean resultset = oracleDb.InsertQuery("Update CSC_USERATTEMPT_EXERCISE SET UE_ISSELECTED ='"+is_select+"' where UE_QUESTION_ID ="+Q_id+" and UE_ANSWER_ID ="+A_id+" and UE_UA_ID ="+ua_id);
			return resultset;
	}
	
	public static List<UserAttemptExercise> GetUserAttemptExerciseForSaved(int ExerciseId) {
		UserAttemptExercise userAttemptExercise = new UserAttemptExercise();
		List<UserAttemptExercise> listUserAttemptExercise = new LinkedList<UserAttemptExercise>();
		User user = LocalSession.GetCurrentUser();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select UAE.* from CSC_USERATTEMPT_EXERCISE UAE,CSC_USER_ATTEMPT UA where UA.UA_ID  = UAE.UE_UA_ID and UA.UA_EXERCISE_ID = "+ExerciseId+" and UA.UA_USER_ID = "+user.UserId+" and UA_SUBMITTED = 'F'");
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

}
