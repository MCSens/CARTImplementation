package dataAnalyser;

public class NumberQuestion extends Question{
	private Number value;

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public NumberQuestion(Number value, Double gini) {
		super(gini);
		this.value = value;
	}

	@Override
	public String toString() {
		return "NumberQuestion [value=" + value + "]";
	}
}
