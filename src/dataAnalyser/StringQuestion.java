package dataAnalyser;

public class StringQuestion extends Question{
	private String value;

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "StringQuestion [value=" + value + "]";
	}

	public StringQuestion(String property, String value) {
		super(property);
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
	
	@Override
    public boolean equals(Object o) {
		boolean eq = false;
		if(o instanceof StringQuestion){
			eq = this.value.equals(((StringQuestion)o).getStringValue());
		}
		return eq;
    }	
}
