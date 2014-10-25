/**
 * 
 */
package ncsu.dbms.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ravi
 *
 */
public class OracleDataAdapter {
	OracleDb oracleDb = new OracleDb();

	public OracleDataAdapter() {

	}

	public ArrayList<User> GetUsers() {
		ArrayList<User> listUsers = new ArrayList<User>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb.GetResultSet("Select * from csc_User");
		try {
			while (resultset.next()) {
				User user = new User();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-mm-dd HH:mm:ss");
				user.UserId = resultset.getInt("User_ID");
				user.UserFName = resultset.getString("USER_FNAME");
				user.UserMName = resultset.getString("USER_MNAME");
				user.UserLName = resultset.getString("USER_LNAME");
				user.UserEmail = resultset.getString("USER_EMAIL");
				user.UserIsActive = resultset.getString("User_ID") == "T" ? true
						: false;
				try {
					user.UserCreatedDate = simpleDateFormat.parse(resultset
							.getString("User_CREATEDDATE"));
					user.UserLastModifiedDate = simpleDateFormat
							.parse(resultset.getString("USER_LASTMODIFIEDDATE"));
				} catch (Exception e) {

				}
				user.UserLastModifiedBy = resultset.getInt("USER_MODIFIEDBY");
				listUsers.add(user);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listUsers;
	}

	public User GetUserDetails(String userName) {
		User user = new User();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb.GetResultSet("Select * from csc_User");
		try {
			while (resultset.next()) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-mm-dd HH:mm:ss");
				user.UserId = resultset.getInt("User_ID");
				user.UserFName = resultset.getString("USER_FNAME");
				user.UserMName = resultset.getString("USER_MNAME");
				user.UserLName = resultset.getString("USER_LNAME");
				user.UserEmail = resultset.getString("USER_EMAIL");
				user.UserIsActive = resultset.getString("User_ID") == "T" ? true
						: false;
				try {
					user.UserCreatedDate = simpleDateFormat.parse(resultset
							.getString("User_CREATEDDATE"));
					user.UserLastModifiedDate = simpleDateFormat
							.parse(resultset.getString("USER_LASTMODIFIEDDATE"));
				} catch (Exception e) {
				}
				user.UserLastModifiedBy = resultset.getInt("USER_MODIFIEDBY");
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return user;
	}

	// returns question bank
	// CSC_QUESTIONBANK
	public ArrayList<QuestionBank> GetQuestionBank() {
		QuestionBank questionBank;
		ArrayList<QuestionBank> listQuestionBank = new ArrayList<QuestionBank>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_QUESTIONBANK");
		try {
			while (resultset.next()) {
				questionBank = new QuestionBank();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-mm-dd HH:mm:ss");
				questionBank.QUESTIONBANK_ID = resultset
						.getInt("QUESTIONBANK_ID ");
				questionBank.QUESTIONBANK_TEXT = resultset
						.getString("QUESTIONBANK_TEXT ");
				questionBank.QUESTIONBANK_HINT = resultset
						.getString("QUESTIONBANK_HINT ");
				questionBank.QUESTIONBANK_EXPLANATION = resultset
						.getString("QUESTIONBANK_EXPLANATION ");
				questionBank.QUESTIONBANK_DIFFICULTYLEVEL = resultset
						.getInt("QUESTIONBANK_DIFFICULTYLEVEL ");
				questionBank.QUESTIONBANK_CREATEDBY = resultset
						.getInt("QUESTIONBANK_CREATEDBY ");
				questionBank.QUESTIONBANK_MODIFIEDBY = resultset
						.getInt("QUESTIONBANK_MODIFIEDBY");
				try {
					questionBank.QUESTIONBANK_CREATEDDATE = simpleDateFormat
							.parse(resultset
									.getString("QUESTIONBANK_CREATEDDATE "));
					questionBank.QUESTIONBANK_MODIFIEDDATE = simpleDateFormat
							.parse(resultset
									.getString("QUESTIONBANK_MODIFIEDDATE "));
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

	// returns answer bank
	// CSC_ANSWERBANK
	public ArrayList<AnswerBank> GetAnswerBank() {
		AnswerBank answerBank = new AnswerBank();
		ArrayList<AnswerBank> listAnswerBank = new ArrayList<AnswerBank>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_ANSWERBANK");
		try {
			while (resultset.next()) {
				answerBank = new AnswerBank();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-mm-dd HH:mm:ss");
				answerBank.ANSWERBANK_ID = resultset.getInt("ANSWERBANK_ID");
				answerBank.ANSWERBANK_TEXT = resultset
						.getString("ANSWERBANK_TEXT ");
				answerBank.ANSWERBANK_EXPLANATION = resultset
						.getString("ANSWERBANK_EXPLANATION ");
				answerBank.ANSWERBANK_CREATEDBY = resultset
						.getInt("ANSWERBANK_CREATEDBY  ");
				answerBank.ANSWERBANK_MODIFIEDBY = resultset
						.getInt("ANSWERBANK_MODIFIEDBY  ");
				try {
					answerBank.ANSWERBANK_CREATEDDATE = simpleDateFormat
							.parse(resultset
									.getString("ANSWERBANK_CREATEDDATE "));
					answerBank.ANSWERBANK_MODIFIEDDATE = simpleDateFormat
							.parse(resultset
									.getString("ANSWERBANK_MODIFIEDDATE  "));
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

	public ArrayList<QuestionBank_AnswerBank> GetQuestionAnswerBank() {
		QuestionBank_AnswerBank questionAnswerBank = new QuestionBank_AnswerBank();
		ArrayList<QuestionBank_AnswerBank> listQABank = new ArrayList<QuestionBank_AnswerBank>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_QUESTIONBANK_ANSWERBANK");
		try {
			while (resultset.next()) {
				questionAnswerBank = new QuestionBank_AnswerBank();
				questionAnswerBank.QABANK_QUESITON_ID = resultset
						.getInt("QABANK_QUESITON_ID");
				questionAnswerBank.QABANK_ANSWER_ID = resultset
						.getInt("QABANK_ANSWER_ID ");
				questionAnswerBank.QABANK_ISCORRECT = resultset
						.getBoolean("ANSWERBANK_EXPLANATION ");
				listQABank.add(questionAnswerBank);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listQABank;
	}

	public ArrayList<Course> GetCourse() {

		Course course = new Course();
		ArrayList<Course> listCourse = new ArrayList<Course>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-mm-dd HH:mm:ss");
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb.GetResultSet("Select * from CSC_COURSE");
		try {
			while (resultset.next()) {
				course = new Course();

				course.CSC_COURSE_Course_ID = resultset
						.getInt("CSC_COURSE_Course_ID");
				course.CSC_COURSE_Course_Name = resultset
						.getString("CSC_COURSE_Course_Name ");
				course.CSC_COURSE_Max_Enroll_No = resultset
						.getInt("ANSWERBANK_CREATEDBY  ");
				course.CSC_COURSE_Number_Of_Students = resultset
						.getInt("ANSWERBANK_MODIFIEDBY  ");
				try {
					course.CSC_COURSE_StartDate = simpleDateFormat
							.parse(resultset.getString("CSC_COURSE_StartDate "));
					course.CSC_COURSE_EndDate = simpleDateFormat
							.parse(resultset.getString("CSC_COURSE_EndDate  "));
				} catch (Exception e) {
				}
				listCourse.add(course);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listCourse;

	}

}
