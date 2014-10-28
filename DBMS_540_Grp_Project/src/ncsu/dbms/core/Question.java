package ncsu.dbms.core;

import java.util.List;

public class Question {
	private int questionId;
	private String question;
	private List<Options> options;
	private boolean isMultipleChoice;
	
	public Question(int questionId, String question, List<Options> options,
			boolean isMultipleChoice) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.options = options;
		this.isMultipleChoice = isMultipleChoice;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public boolean isMultipleChoice() {
		return isMultipleChoice;
	}

	public void setMultipleChoice(boolean isMultipleChoice) {
		this.isMultipleChoice = isMultipleChoice;
	}

	
}
