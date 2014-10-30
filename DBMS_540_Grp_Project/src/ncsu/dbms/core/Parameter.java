package ncsu.dbms.core;

/**
 * @author parth
 */
public class Parameter {
	private String parameterText;
	private int paramId;
	private String variableName;
	private int variableId;

	public Parameter(String parameterText, int paramId, String variableName,
			int variableId) {
		super();
		this.parameterText = parameterText;
		this.paramId = paramId;
		this.variableName = variableName;
		this.variableId = variableId;
	}

	public String getParameterText() {
		return parameterText;
	}

	public void setParameterText(String parameterText) {
		this.parameterText = parameterText;
	}

	public int getParamId() {
		return paramId;
	}

	public void setParamId(int paramId) {
		this.paramId = paramId;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public int getVariableId() {
		return variableId;
	}

	public void setVariableId(int variableId) {
		this.variableId = variableId;
	}
}
