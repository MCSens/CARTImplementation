package dataAnalyser;

import java.util.Comparator;

public class Question implements Comparable{
	private Double gini;
	
	public String getStringValue(){
		return "";
	}
	public Number getNumberValue(){
		return 0.0;
	}
	
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
