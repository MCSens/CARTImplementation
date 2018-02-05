package data;

import dataAnalyser.Question;
import dataAnalyser.StringQuestion;

public class Fruit extends Property{ 
	//THIS WILL CAUSE ISSUES ALONG THE LINE!!!
	//IT's not feasible to do it like this, the parameters must be completely flexible, 
	//otherwise it's useless
	public String label;
	public double diameter;
	public String color;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public double getDiameter() {
		return diameter;
	}
	public Fruit(String label, double diameter, String color) {
		super();
		this.label = label;
		this.diameter = diameter;
		this.color = color;
	}
	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Fruit [label=" + label + ", diameter=" + diameter + ", color="
				+ color + "]";
	}
	
	@Override
	public boolean isMatch(Question q) {
		// TODO Auto-generated method stub
		if(q instanceof StringQuestion){
			switch(q.getProperty()){
			case "label": 
				return this.label.equals(q.getStringValue());
			case "color": 
				return this.color.equals(q.getStringValue());	
			default:
				return false;
			}
		}
		else{
			switch(q.getProperty()){
			case "diameter":
				return this.diameter >= q.getNumberValue().doubleValue();
			default:
				return false;
			}
		}
	}
	
	@Override
	public Property getMatch(Question q) {
		// TODO Auto-generated method stub
		if(isMatch(q)){
			return this;
		}
		else{
			return null;
		}
	}
}
	
	

