package data;

public abstract class Property {
	public String label;
	 
	public String getLabel(){
		return this.label;
	}
	public abstract boolean isMatch(String property, String value);
	public abstract boolean isMatch(String property, Number value);
	public abstract Property getMatch(String property, String value);
	public abstract Property getMatch(String property, Number value);
}
