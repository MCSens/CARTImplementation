package dataAnalyser;

import java.util.Comparator;

public abstract class Question implements Comparable{
	private Double gini;
	
	public abstract String getStringValue();
	public abstract Number getNumberValue();
	public abstract boolean equals(Object o);
	
	public Double getGini(){
		return gini;
	}
	
	public void setGini(Double gini){
		this.gini = gini;
	}

	public Question() {
		super();
	}

    @Override
    public int compareTo(Object o) {
        Question q = (Question) o;
        return this.gini.compareTo(q.getGini());
    }	
}
