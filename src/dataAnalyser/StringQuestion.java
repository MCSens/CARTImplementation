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

	public StringQuestion(String value, Double gini) {
		super(gini);
		this.value = value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
