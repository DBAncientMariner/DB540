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

	// returns question answer bank
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
						.getInt("QABANK_ANSWER_ID");
				questionAnswerBank.QABANK_ISCORRECT = resultset
						.getBoolean("ANSWERBANK_EXPLANATION");
				listQABank.add(questionAnswerBank);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listQABank;
	}

	// returns course list.
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
						.getString("CSC_COURSE_Course_Name");
				course.CSC_COURSE_Max_Enroll_No = resultset
						.getInt("ANSWERBANK_CREATEDBY");
				course.CSC_COURSE_Number_Of_Students = resultset
						.getInt("ANSWERBANK_MODIFIEDBY");
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
		} finally {
			oracleDb.CloseConnection();
		}
		return listCourse;

	}

	public ArrayList<CSCClass> GetClass() {

		CSCClass cscClass = new CSCClass();
		ArrayList<CSCClass> listClass = new ArrayList<CSCClass>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb.GetResultSet("Select * from CSC_CLASS");
		try {
			while (resultset.next()) {
				cscClass = new CSCClass();

				cscClass.CSC_CLASS_SURR_KEY = resultset
						.getInt("CSC_CLASS_SURR_KEY");
				cscClass.CSC_CLASS_CLASS_ID = resultset
						.getInt("CSC_CLASS_CLASS_ID");
				cscClass.CSC_CLASS_COURSE_ID = resultset
						.getInt("CSC_CLASS_COURSE_ID");
				listClass.add(cscClass);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listClass;
	}

	public ArrayList<Level> GetLevel() {
		Level level = new Level();
		ArrayList<Level> listLevel = new ArrayList<Level>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb.GetResultSet("Select * from CSC_Level");
		try {
			while (resultset.next()) {
				level = new Level();

				level.CSC_LEVEL_LEVEL_ID = resultset
						.getInt("CSC_LEVEL_LEVEL_ID");
				level.CSC_LEVEL_LEVEL_TYPE = resultset
						.getString("CSC_LEVEL_LEVEL_TYPE ");
				listLevel.add(level);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listLevel;
	}

	public ArrayList<TextBook> GetTextBook() {
		TextBook textBook = new TextBook();
		ArrayList<TextBook> listTextBook = new ArrayList<TextBook>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_TEXTBOOK");
		try {
			while (resultset.next()) {
				textBook = new TextBook();

				textBook.CSC_TEXTBOOK_ISBN = resultset
						.getString("CSC_TEXTBOOK_ISBN");
				textBook.CSC_TEXTBOOK_Title = resultset
						.getString("CSC_TEXTBOOK_Title");
				textBook.CSC_TEXTBOOK_Author = resultset
						.getString("CSC_TEXTBOOK_Author");
				listTextBook.add(textBook);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listTextBook;
	}

	// returns chapter
	public ArrayList<Chapter> GetChapter() {
		Chapter chapter = new Chapter();
		ArrayList<Chapter> listChapter = new ArrayList<Chapter>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_CHAPTER");
		try {
			while (resultset.next()) {
				chapter = new Chapter();

				chapter.CSC_CHAPTER_Chapter_Surr_Key = resultset
						.getInt("CSC_CHAPTER_Chapter_Surr_Key");
				chapter.CSC_CHAPTER_Chapter_Id = resultset
						.getInt("CSC_CHAPTER_Chapter_Id");
				chapter.CSC_CHAPTER_Title = resultset
						.getString("CSC_CHAPTER_Title");
				chapter.CSC_CHAPTER_ISBN = resultset
						.getString("CSC_CHAPTER_ISBN");
				listChapter.add(chapter);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listChapter;
	}

	// returns Section
	public ArrayList<Section> GetSection() {
		Section section = new Section();
		ArrayList<Section> listSection = new ArrayList<Section>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_SECTION");
		try {
			while (resultset.next()) {
				section = new Section();

				section.CSC_SECTION_SECTION_SURR_KEY = resultset
						.getInt("CSC_SECTION_SECTION_SURR_KEY");
				section.CSC_SECTION_SECTION_ID = resultset
						.getInt("CSC_SECTION_SECTION_ID");
				section.CSC_SECTION_SUPER_SECTION_ID = resultset
						.getInt("CSC_SECTION_SUPER_SECTION_ID");
				section.CSC_SECTION_TITLE = resultset
						.getString("CSC_SECTION_TITLE");
				section.CSC_SECTION_CONTENT = resultset
						.getString("CSC_SECTION_CONTENT");
				section.CSC_SECTION_Chapter_Surr_Key = resultset
						.getInt("CSC_SECTION_Chapter_Surr_Key ");
				listSection.add(section);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listSection;
	}

	//returns CourseTextbook List
	public ArrayList<CourseTextbook> GetCourseTextbook() 
	{
		CourseTextbook courseTextbook = new CourseTextbook();
		ArrayList<CourseTextbook> listCourseTextbook = new ArrayList<CourseTextbook>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_COURSE_TEXTBOOK");
		try {
			while (resultset.next()) {
				courseTextbook = new CourseTextbook();

				courseTextbook.CSC_COURSE_TEXTBOOK_SURR_KEY  = resultset
						.getInt("CSC_COURSE_TEXTBOOK_SURR_KEY");
				courseTextbook.CSC_CRS_TXTBK_CSC_CRS_CRS_ID  = resultset
						.getInt("CSC_CRS_TXTBK_CSC_CRS_CRS_ID");
				courseTextbook.CSC_CRs_TXTBK_CSC_TXTBK_ISBN  = resultset
						.getString("CSC_CRs_TXTBK_CSC_TXTBK_ISBN");
				listCourseTextbook.add(courseTextbook);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listCourseTextbook;
	}

	
	public ArrayList<ScoringType> GetScoringType()
	{
		ScoringType scoringType = new ScoringType();
		ArrayList<ScoringType> listScoringType = new ArrayList<ScoringType>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_SCORINGTYPE");
		try {
			while (resultset.next()) {
				scoringType = new ScoringType();

				scoringType.SCORINGTYPE_ID  = resultset
						.getInt("SCORINGTYPE_ID");
				scoringType.SCORINGTYPE_DESCRIPTION  = resultset
						.getString("SCORINGTYPE_DESCRIPTION");
				scoringType.SCORINGTYPE_LOGIC  = resultset
						.getString("SCORINGTYPE_LOGIC");
				listScoringType.add(scoringType);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listScoringType;
	}
	
	public ArrayList<Exercise> GetExercise()
	{
		Exercise exercise = new Exercise();
		ArrayList<Exercise> listExercise = new ArrayList<Exercise>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-mm-dd HH:mm:ss");
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb.GetResultSet("Select * from CSC_EXERCISE");
		try {
			while (resultset.next()) {
				exercise = new Exercise();

				exercise.EXERCISE_ID  = resultset
						.getInt("EXERCISE_ID");
				exercise.EXERCISE_NAME  = resultset
						.getString("EXERCISE_NAME");
				exercise.EXERCISE_TOPICS   = resultset
						.getString("EXERCISE_TOPICS");
				exercise.EXERCISE_NAME  = resultset
						.getString("EXERCISE_NAME");
				exercise.EXERCISE_DIFFICULTY_RANGE1  = resultset
						.getInt("EXERCISE_DIFFICULTY_RANGE1");
				exercise.EXERCISE_DIFFICULTY_RANGE2  = resultset
						.getInt("EXERCISE_DIFFICULTY_RANGE2");
				exercise.EXERCISE_RETRYLIMIT   = resultset
						.getInt("EXERCISE_RETRYLIMIT");
				exercise.EXERCISE_CORRECTPT   = resultset
						.getInt("EXERCISE_CORRECTPT");
				exercise.EXERCISE_PENALTYPT   = resultset
						.getInt("EXERCISE_PENALTYPT");
				exercise.EXERCISE_SCORINGTYPE   = resultset
						.getInt("EXERCISE_SCORINGTYPE");
				exercise.EXERCISE_CREATEDBY    = resultset
						.getInt("EXERCISE_CREATEDBY");
				exercise.EXERCISE_MODIFIEDBY  = resultset
						.getInt("EXERCISE_MODIFIEDBY");
				
				try {
					exercise.EXERCISE_STARTDATE  = simpleDateFormat
							.parse(resultset.getString("EXERCISE_STARTDATE"));
					exercise.EXERCISE_ENDDATE  = simpleDateFormat
							.parse(resultset.getString("EXERCISE_ENDDATE"));
					exercise.EXERCISE_LASTMODIFIEDDATE  = simpleDateFormat
							.parse(resultset.getString("EXERCISE_LASTMODIFIEDDATE"));
				} catch (Exception e) {
				}
				listExercise.add(exercise);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listExercise;
	}
	
	
	public ArrayList<Topic> GetTopic()
	{
		Topic topic = new Topic();
		ArrayList<Topic> listTopic = new ArrayList<Topic>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-mm-dd HH:mm:ss");
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_TOPIC");
		try {
			while (resultset.next()) {
				topic = new Topic();

				topic.TOPIC_ID   = resultset
						.getInt("TOPIC_ID");
				topic.TOPIC_KEYWORD   = resultset
						.getString("TOPIC_KEYWORD");
				topic.TOPIC_CREATEDBY   = resultset
						.getInt("TOPIC_CREATEDBY");
				try {
					topic.TOPIC_CREATEDDATE   = simpleDateFormat
							.parse(resultset.getString("TOPIC_CREATEDDATE"));
				} catch (Exception e) {
				}
				listTopic.add(topic);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listTopic;
	}
	public ArrayList<QuestionBank_Topic> GetQuestionBankTopic()
	{
		QuestionBank_Topic questionBankTopic = new QuestionBank_Topic();
		ArrayList<QuestionBank_Topic> listQuestionBankTopic = new ArrayList<QuestionBank_Topic>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_QUESTIONBANK_TOPIC");
		try {
			while (resultset.next()) {
				questionBankTopic = new QuestionBank_Topic();

				questionBankTopic.QT_TOPIC_ID    = resultset
						.getInt("QT_TOPIC_ID");
				questionBankTopic.QT_QUESTIONBANK_ID    = resultset
						.getInt("QT_QUESTIONBANK_ID");
				listQuestionBankTopic.add(questionBankTopic);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listQuestionBankTopic;
	}
	public ArrayList<ExerciseQuestion> GetExerciseQuestion()
	{
		ExerciseQuestion exerciseQuestion = new ExerciseQuestion();
		ArrayList<ExerciseQuestion> listExerciseQuestion = new ArrayList<ExerciseQuestion>();
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_EXERCISE_QUESTION");
		try {
			while (resultset.next()) {
				exerciseQuestion = new ExerciseQuestion();

				exerciseQuestion.EA_ID     = resultset
						.getInt("EA_ID");
				exerciseQuestion.EA_EXERCISE_ID     = resultset
						.getInt("EA_EXERCISE_ID");
				exerciseQuestion.EA_QUESTION_ID      = resultset
						.getInt("EA_QUESTION_ID");
				listExerciseQuestion.add(exerciseQuestion);
			}
		} catch (SQLException e) {
		} finally {
			oracleDb.CloseConnection();
		}
		return listExerciseQuestion;
	}
	public ArrayList<UserAttempt> GetUserAttempt()
	{
		UserAttempt userAttempt = new UserAttempt();
		ArrayList<UserAttempt> listUserAttempt = new ArrayList<UserAttempt>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-mm-dd HH:mm:ss");
		oracleDb.OpenConnection();
		ResultSet resultset = oracleDb
				.GetResultSet("Select * from CSC_USER_ATTEMPT");
		try {
			while (resultset.next()) {
				userAttempt = new UserAttempt();

				userAttempt.UA_ID    = resultset
						.getInt("UA_ID");
				userAttempt.UA_USER_ID    = resultset
						.getInt("UA_USER_ID");
				userAttempt.UA_EXERCISE_ID    = resultset
						.getInt("UA_EXERCISE_ID");
				userAttempt.UA_SUBMITTED     = resultset
						.getBoolean("UA_SUBMITTED");
				userAttempt.UA_SCORE=resultset.getDouble("UA_SCORE");
				try {
					userAttempt.UA_STARTATTEMPT_DATE    = simpleDateFormat
							.parse(resultset.getString("UA_STARTATTEMPT_DATE"));
					userAttempt.UA_LASTATTEMPT_DATE     = simpleDateFormat
							.parse(resultset.getString("UA_LASTATTEMPT_DATE"));
					userAttempt.UA_STARTATTEMPT_DATE    = simpleDateFormat
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
