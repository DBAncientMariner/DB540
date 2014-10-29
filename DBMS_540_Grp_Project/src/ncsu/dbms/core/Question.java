package ncsu.dbms.core;

import java.util.List;

public class Question {
	private int questionId;
	private String question;
	private List<Options> options;
	private boolean isMultipleChoice;
	private boolean isParameterized;
	private List<Parameter> parameterList;
	private QbParamSet paramset;
	private String questionExplanation;
	private String questionHint;
	
	public Question(int questionId, String question, List<Options> options,
			boolean isMultipleChoice) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.options = options;
		this.isMultipleChoice = isMultipleChoice;
		isMultipleChoice = false;
		isParameterized = false;
	}

	public String getQuestionExplanation() {
		return questionExplanation;
	}

	public void setQuestionExplanation(String questionExplanation) {
		this.questionExplanation = questionExplanation;
	}

	public String getQuestionHint() {
		return questionHint;
	}

	public void setQuestionHint(String questionHint) {
		this.questionHint = questionHint;
	}

	public boolean isParameterized() {
		return isParameterized;
	}

	public void setParameterized(boolean isParameterized) {
		this.isParameterized = isParameterized;
	}

	public List<Parameter> getParameterList() {
		return parameterList;
	}

	public void setParameterList(List<Parameter> parameterList) {
		this.parameterList = parameterList;
	}

	public QbParamSet getParamset() {
		return paramset;
	}

	public void setParamset(QbParamSet paramset) {
		this.paramset = paramset;
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
