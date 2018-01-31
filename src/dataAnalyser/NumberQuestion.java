package dataAnalyser;

public class NumberQuestion extends Question{
	private Number value;

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}

	public NumberQuestion(Number value) {
		super(); 
		this.value = value;
	}

	@Override
	public String toString() {
		return "NumberQuestion [value=" + value + "]";
	}

	@Override
	public String getStringValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Number getNumberValue() {
		// TODO Auto-generated method stub
		return value;
	}
	
	@Override
    public boolean equals(Object o) {
		boolean eq = false;
		if(o instanceof NumberQuestion){
			Double val1 = (Double)this.value;
			Double val2 =  (Double)((NumberQuestion)o).getNumberValue();
			eq = (Double.compare(val1, val2))==0?true:false;
		}
		return eq;
    }	
}
