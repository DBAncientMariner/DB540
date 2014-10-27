package ncsu.dbms.core;

public class CorrectAnswer {
	private String option;
	private boolean isCorrect;
	private boolean isMarked;
	private String shortExplanation;
	private String detailExplanation;
	
	public CorrectAnswer(String option, boolean isCorrect, boolean isMarked) {
		super();
		this.option = option;
		this.isCorrect = isCorrect;
		this.isMarked = isMarked;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}
	
	public String getShortExplanation() {
		return shortExplanation;
	}

	public void setShortExplanation(String shortExplanation) {
		this.shortExplanation = shortExplanation;
	}

	public String getDetailExplanation() {
		return detailExplanation;
	}

	public void setDetailExplanation(String detailExplanation) {
		this.detailExplanation = detailExplanation;
	}

}
