package data;

import java.util.*;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import dataAnalyser.Analyser;
import dataAnalyser.Question;
import dataAnalyser.StringQuestion;

public class TrainingDataSet {
	private static Logger log = (Logger) LoggerFactory.getLogger("TrainingDataSet");
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
		log.debug("Created a TrainingDataSet {} with properties: {}", key, properties);
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
	
	public String getKey(){
		return this.key;
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
	
	public int countMatches(Question q){
		int countMatches = 0;
		boolean isMatch;
		for(int i = 0; i<properties.size(); i++){
			isMatch = properties.get(i).isMatch(q);
			if(isMatch){
				log.trace("The Property {} matches the Question {}", properties.get(i), q);
				countMatches++;
			}
		}
		log.debug("For Question {} we found {} matches", q, countMatches);
		return countMatches;
	}
	
	public boolean isMatch(Question q){
		boolean exists;
		for(int i = 0; i<properties.size(); i++){
			exists = properties.get(i).isMatch(q);
			if(exists){
				log.trace("Contains value for Question "+q);
				return exists;
			} 
		}
		//System.out.println("Wert ist nicht enthalten");
		return false;
	}
	
	//beide getMatch sollten zusammengefasst werden
	public TrainingDataSet getMatch(Question q){ //Hier sollte eigentlidch eine Frage rein
		ArrayList<Property> subset = new ArrayList<Property>();
		for(int i = 0; i<properties.size(); i++){
			 Property p = properties.get(i).getMatch(q); //getMatch of Property
			 if(p!=null)subset.add(p);
		}
		TrainingDataSet subDataset = new TrainingDataSet(this.getKey(), subset);
		log.debug("For Question {} found Matches: {}",q,subDataset);
		return subDataset;
	}
	
	public TrainingDataSet removeMatch(Question q){
		ArrayList<Property> subset = (ArrayList<Property>) this.properties;
		for(int i = 0; i<properties.size(); i++){
			 Property p = properties.get(i).getMatch(q); //getMatch of Property FUNZT NICHT GESCHEID!
			 if(p!=null){
				 subset.remove(p);
				 i--;
			 }
		}
		TrainingDataSet subDataset = new TrainingDataSet(key, subset);
		log.debug("Question {} matches removed, returning this DataSet: {}",q,subDataset);
		return subDataset;
	}
}
