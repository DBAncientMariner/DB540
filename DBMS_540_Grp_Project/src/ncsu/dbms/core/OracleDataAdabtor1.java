package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDataAdabtor1 {

	
	public static boolean InsertIntoUserAttempExercise(int ua_id,int Q_id, int A_id,char is_select)
	{
		OracleDb oracleDb = new OracleDb();
		try{
				oracleDb.OpenConnection();
		boolean resultset = oracleDb.InsertQuery("Insert into CSC_USERATTEMPT_EXERCISE(UE_UA_ID,UE_QUESTION_ID ,UE_ANSWER_ID ,UE_ISSELECTED) Values("+ua_id+","+Q_id+","+A_id+",'"+is_select+"');");
		return resultset;
		}
		
		finally {
			oracleDb.CloseConnection();
		}
		
	}
}
