package data;

import java.util.*;

public class TrainingDataSet {
	private String key;
	private List<Property> properties;
	private int size;
	private String[] uniqueLabels;
	
	//Eine Datarow soll identifiziert werden können über ein Key 
	//und beliebig viele Eigenschaften mit Key/Value erhalten
	public TrainingDataSet(String key, Property... properties){
		this.properties = new ArrayList<Property>();
		for(Property p: properties){
			this.properties.add(p);
		}
		this.key = key;
		this.size = this.properties.size();
	}
	
	public TrainingDataSet(String key, ArrayList<Property> properties){
		this.properties = properties;
		this.key = key;
		this.size = this.properties.size();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Property> getProperties(){
		return properties;
	}
	
	public String toString(){
		String str="Key: "+key;
		//Iterator i
		str+=properties.toString();
		return str;
	}
	
	public int countMatches(String key, String value){
		int countMatches = 0;
		for(int i = 0; i<properties.size(); i++){
			 if(properties.get(i).isMatch(key, value)){
				 countMatches++;
			 }
		}
		return countMatches;
	}
	public int countMatches(String key, Number value){
		int countMatches = 0;
		for(int i = 0; i<properties.size(); i++){
			 if(properties.get(i).isMatch(key, value)){
				 countMatches++;
			 }
		}
		return countMatches;
	}
	
	public boolean isMatch(String key, String value){
		for(int i = 0; i<properties.size(); i++){
			 boolean exists = properties.get(i).isMatch(key, value);
				 if(exists){
					 System.out.println("[TRACE]Wert ist enthalten");
					 return exists;
				 } 
		}
		//System.out.println("Wert ist nicht enthalten");
		return true;
	}
	
	public boolean isMatch(String key, Number value){
		for(int i = 0; i<properties.size(); i++){
			 boolean exists = properties.get(i).isMatch(key, value);
				 if(exists){
					 System.out.println("[TRACE]Wert ist enthalten");
					 return exists;
				 }
		}
		//System.out.println("Wert ist nicht enthalten");
		return true;
	}
	
	//beide getMatch sollten zusammengefasst werden
	public TrainingDataSet getMatch(String key, String value){ //Hier sollte eigentlidch eine Frage rein
		ArrayList<Property> subset = new ArrayList<Property>();
		for(int i = 0; i<properties.size(); i++){
			 Property p = properties.get(i).getMatch(key, value); //getMatch of Property
			 if(p!=null)subset.add(p);
		}
		TrainingDataSet subDataset = new TrainingDataSet(key, subset);
		return subDataset;
	}
	public TrainingDataSet getMatch(String key, Number value){
		ArrayList<Property> subset = new ArrayList<Property>();
		for(int i = 0; i<properties.size(); i++){
			 Property p = properties.get(i).getMatch(key, value);
			 if(p!=null)subset.add(p);
		}
		TrainingDataSet subDataset = new TrainingDataSet(key, subset);
		return subDataset;
	}	
	public TrainingDataSet removeMatch(String key, String value){
		ArrayList<Property> subset = (ArrayList<Property>) this.properties;
		for(int i = 0; i<properties.size(); i++){
			 Property p = properties.get(i).getMatch(key, value); //getMatch of Property FUNZT NICHT GESCHEID!
			 if(p!=null){
				 subset.remove(p);
				 i--;
			 }
		}
		TrainingDataSet subDataset = new TrainingDataSet(key, subset);
		return subDataset;
	}
	public TrainingDataSet removeMatch(String key, Number value){
		ArrayList<Property> subset = (ArrayList<Property>) this.properties;
		for(int i = 0; i<properties.size(); i++){
			 Property p = properties.get(i).getMatch(key, value);
			 if(p!=null){
				 subset.remove(p);
			 }
		}
		TrainingDataSet subDataset = new TrainingDataSet(key, subset);
		return subDataset;
	}
}
