package ncsu.dbms.core;

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
}
