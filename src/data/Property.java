package data;

import dataAnalyser.Question;

public abstract class Property {
	public String label;
	 
	public String getLabel(){
		return this.label;
	}
	public abstract boolean isMatch(Question q);
	public abstract Property getMatch(Question q);
}
