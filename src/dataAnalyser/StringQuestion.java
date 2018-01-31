package dataAnalyser;

public class StringQuestion extends Question{
	private String value;
	//private Double gini;

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "StringQuestion [value=" + value + "]";
	}

	public StringQuestion(String value) {
		super();
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String getStringValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Number getNumberValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
