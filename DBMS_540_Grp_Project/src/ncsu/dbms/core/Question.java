package ncsu.dbms.core;

import java.util.List;

public class Question {
	private String question;
	private List<String> options;
	private boolean isMultipleChoice;

	public Question(String question, List<String> options,
			boolean isMultipleChoice) {
		super();
		this.question = question;
		this.options = options;
		this.isMultipleChoice = isMultipleChoice;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public boolean isMultipleChoice() {
		return isMultipleChoice;
	}

	public void setMultipleChoice(boolean isMultipleChoice) {
		this.isMultipleChoice = isMultipleChoice;
	}
}
