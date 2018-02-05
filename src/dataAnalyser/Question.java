package dataAnalyser;

public abstract class Question implements Comparable{
	private Double gini;
	private String property;
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public abstract String getStringValue();
	public abstract Number getNumberValue();
	public abstract boolean equals(Object o);
	
	public Double getGini(){
		return gini;
	}
	
	public void setGini(Double gini){
		this.gini = gini;
	}

	public Question(String property) {
		super();
		this.property = property;
	}

    @Override
    public int compareTo(Object o) {
        Question q = (Question) o;
        return this.gini.compareTo(q.getGini());
    }	
}
