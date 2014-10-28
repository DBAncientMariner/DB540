package ncsu.dbms.core;

public class Options {
	int answerId;
	String answer;
	boolean isMarked;
	boolean isCorrect;
	
	public Options(int answerId, boolean isMarked, boolean isCorrect) {
		super();
		this.answerId = answerId;
		this.isMarked = isMarked;
		this.isCorrect = isCorrect;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public boolean isMarked() {
		return isMarked;
	}
	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	public boolean isCorrect() {
		return isCorrect;
	}
	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
