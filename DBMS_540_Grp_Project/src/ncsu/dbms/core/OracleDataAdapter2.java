package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class OracleDataAdapter2 {

	public static User getUserDetails(int userId) {
		User user = new User();
		OracleDb oracleDb = new OracleDb();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from csc_User where User_ID=" + userId);
		try {
			while (resultset.next()) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				user.UserId = resultset.getInt("User_ID");
				user.UserFName = resultset.getString("USER_FNAME");
				user.UserMName = resultset.getString("USER_MNAME");
				user.UserLName = resultset.getString("USER_LNAME");
				user.UserEmail = resultset.getString("USER_EMAIL");
				user.user_id_char = resultset.getString("user_id_char");
				user.UserIsActive = resultset.getString("User_ID") == "T" ? true
						: false;
				try {
					user.UserCreatedDate = simpleDateFormat.parse(resultset
							.getString("User_CREATEDDATE"));
					user.UserLastModifiedDate = simpleDateFormat
							.parse(resultset.getString("USER_LASTMODIFIEDDATE"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				user.UserLastModifiedBy = resultset.getInt("USER_MODIFIEDBY");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			oracleDb.CloseConnection();
		}
		return user;
	}
}
