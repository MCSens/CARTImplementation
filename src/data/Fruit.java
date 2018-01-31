package data;

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
	public boolean isMatch(String property, Number value) {
		// TODO Auto-generated method stub
		switch(property){
		case "diameter":
			return this.diameter >= value.doubleValue();
		default:
			return false;
		}
	}
	
	@Override
	public boolean isMatch(String property, String value) {
		// TODO Auto-generated method stub
		switch(property){ 
			case "label": 
				return this.label.equals(value);
			case "color": 
				return this.color.equals(value);
			default:
				return false;
		}
	}
	@Override
	public Property getMatch(String property, String value) {
		// TODO Auto-generated method stub
		if(isMatch(property,value)){
			return this;
		}
		else{
			return null;
		}
	}
	@Override
	public Property getMatch(String property, Number value) {
		// TODO Auto-generated method stub
		if(isMatch(property,value)){
			return this;
		}
		else{
			return null;
		}
		//Some change
	}
	@Override
	public boolean comparator(Property properties) {
		// TODO Auto-generated method stub
		Fruit f = (Fruit)properties;
		return false;
	}
}
	
	

