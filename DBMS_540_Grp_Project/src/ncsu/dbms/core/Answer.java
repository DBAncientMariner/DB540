package ncsu.dbms.core;

import java.util.List;

public class Answer {
	private String question;
	private List<CorrectAnswer> options;
	private boolean isMultipleChoice;
	
	public Answer(String question, List<CorrectAnswer> options,
			boolean isMultipleChoice) {
		super();
		this.question = question;
		this.options = options;
		this.isMultipleChoice = isMultipleChoice;
	}

	public void setOptions(List<CorrectAnswer> options) {
		this.options = options;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<CorrectAnswer> getOptions() {
		return options;
	}

	public boolean isMultipleChoice() {
		return isMultipleChoice;
	}

	public void setMultipleChoice(boolean isMultipleChoice) {
		this.isMultipleChoice = isMultipleChoice;
	}
}
