package dataAnalyser;

import java.util.ArrayList;
import java.util.List;

import data.TrainingDataSet;
import data.Fruit;
import data.Property;

public class Analyser {
	//This Class Creates the Questions that will build the tree
	public static QuestionList getQuestions(TrainingDataSet ds){
		List<Question> questionList = new ArrayList<Question>();
		
		ArrayList<Property> checkValues = (ArrayList<Property>) ds.getProperties();
		for(int i = 0; i<checkValues.size(); i++){
			Property p = checkValues.get(i);
			//System.out.println("   At the moment checking:"+p);
			questionList = Analyser.addUniqueString(questionList, ((Fruit)p).getColor()); //Liefer echt Mappen!!!
			questionList = Analyser.addUniqueNumber(questionList, ((Fruit)p).getDiameter());
			//numberValues = Analyser.addUniqueNumber(numberValues, ((Fruit)p).getDiameter());
			//numberValues = Analyser.addUniqueNumber(numberValues, ((Fruit)p).getWhateverValue());
		}
		return new QuestionList(questionList);
	}
	
	public static List<String> getUniqueLabels(TrainingDataSet ds){ //Sollte man
		List<String> labelList = new ArrayList<String>();
		
		ArrayList<Property> checkValues = (ArrayList<Property>) ds.getProperties();
		for(int i = 0; i<checkValues.size(); i++){
			Property p = checkValues.get(i);
			String label = p.getLabel();
			//System.out.println("   At the moment checking Label: "+label);
			if(!labelList.contains(label)){
				labelList.add(label);
			}
		}
		return labelList;
	}
	
	private static List<Question> addUniqueString(List<Question> uniqueValues, String value){
		//Vor dem hinzufügen in die Liste auf Uniqe geprüft
		//Dafür wird durch jede Frage iteriert
		StringQuestion sq = new StringQuestion(value);
		//System.out.println("Gerade checke ich: "+value);
		if(uniqueValues.size()==0){
			uniqueValues.add(sq);
		}
		else{	
			for(int i = 0; i<uniqueValues.size();i++){
				//Iteriert durch jede bisherige Frage durch
				Question q = uniqueValues.get(i);
				if(q instanceof StringQuestion){
					//Ausgabe der aktuell betrachteten Frage
					String testStr = ((StringQuestion)q).getValue();
					//System.out.println("Hier der Teststr:" +testStr);
					if(((StringQuestion)q).getValue().equals(value)){
						//System.out.println(value+" war nicht unique, for wird verlassen!");
						return uniqueValues;
					}
				}
				else{
					//Wenn die Frage eine Nummer ist kann weitergegangen werden
					continue;
				}
			}
			uniqueValues.add(sq);
		}
        return uniqueValues;
	}
	
	private static List<Question> addUniqueNumber(List<Question> uniqueValues, Number value){
		//Vor dem hinzufügen in die Liste auf Uniqe geprüft
		//Dafür wird durch jede Frage iteriert
		NumberQuestion sq = new NumberQuestion(value);
		//System.out.println("Gerade checke ich: "+value);
		if(uniqueValues.size()==0){ //Wenn leer, füge direkt eine Frage hinzu
			uniqueValues.add(sq);
		}
		else{	
			for(int i = 0; i<uniqueValues.size();i++){
				//Iteriert durch jede bisherige Frage durch
				Question q = uniqueValues.get(i);
				if(q instanceof NumberQuestion){
					//Ausgabe der aktuell betrachteten Frage
					Number testStr = ((NumberQuestion)q).getValue();
					//System.out.println("Hier die Testnummer: " +testStr);
					if(((NumberQuestion)q).getValue().equals(value)){
						//System.out.println(value+" war nicht unique, for wird verlassen!");
						return uniqueValues;
					}
				}
				else{
					//Wenn die Frage eine Nummer ist kann weitergegangen werden
					continue;
				}
			}
			uniqueValues.add(sq);
		}
        return uniqueValues;
	}	
	
	public static Question getBestGiniQuestion(TrainingDataSet ds, QuestionList dl){
		/*
		counts = class_counts(rows)
			    impurity = 1
			    for lbl in counts:
			        prob_of_lbl = counts[lbl] / float(len(rows))
			        impurity -= prob_of_lbl**2
			    return impurity
		*/
		//Iteriert durch alle Fragen und teilt Anzahl Elemente mit dem KEy durch Anzahl Antworten
		ArrayList<Question> questionlist = (ArrayList<Question>) dl.getQuestionList();
		Question bestQuestion = null; 
		double minGini = 1.0;
		for(int i = 0; i<questionlist.size();i++){
			Question q = questionlist.get(i);
			System.out.println("[TRACE]Aktuelle Frage: "+q);
			double impurity = 1.0;
			TrainingDataSet tds = null;
			if(q instanceof StringQuestion){ //Can be done in one Line it Property handles Questions
				tds = ds.getMatch("color", ((StringQuestion)q).getValue());
			}
			else{
				tds = ds.getMatch("diameter", ((NumberQuestion)q).getValue());
			}
			System.out.println(tds);
			List<String> uniqueLabels =  Analyser.getUniqueLabels(tds);
			int countDatarows = tds.getSize();
			for(int j = 0; j<uniqueLabels.size();j++){
				int countMatches = tds.countMatches("label", (uniqueLabels.get(j))); 
				System.out.println("[TRACE]CountMatches: "+countMatches+" | CountLabels: "+uniqueLabels.size());
				//Randomly draw one label and what are the chances of hitting it? label/max_rows
				double probabilityOfMatch = (double)countMatches/(double)countDatarows;
				impurity -= probabilityOfMatch*probabilityOfMatch;
			}
			System.out.println("[DEBUG]Calculated Impurity = "+impurity);
			q.setGini(impurity);

			if(q.getGini()<minGini){
				bestQuestion = q;
				minGini = q.getGini();
			}
		}
		System.out.println("[DEBUG]Best Question: "+bestQuestion+" with Gini "+minGini);
		return bestQuestion;
	}
	
	public static double getGiniValue(TrainingDataSet ds, QuestionList dl){
		/*
		counts = class_counts(rows)
			    impurity = 1
			    for lbl in counts:
			        prob_of_lbl = counts[lbl] / float(len(rows))
			        impurity -= prob_of_lbl**2
			    return impurity
		*/
		//Iteriert durch alle Fragen und teilt Anzahl Elemente mit dem KEy durch Anzahl Antworten
		ArrayList<Question> questionlist = (ArrayList<Question>) dl.getQuestionList();
		Question bestQuestion = null; 
		double minGini = 1.0;
		for(int i = 0; i<questionlist.size();i++){
			Question q = questionlist.get(i);
			System.out.println("[DEBUG]Aktuelle Frage: "+q);
			double impurity = 1.0;
			TrainingDataSet tds = null;
			if(q instanceof StringQuestion){ //Can be done in one Line it Property handles Questions
				tds = ds.getMatch("color", ((StringQuestion)q).getValue());
			}
			else{
				tds = ds.getMatch("diameter", ((NumberQuestion)q).getValue());
			}
			System.out.println("[TRACE]"+tds);
			List<String> uniqueLabels =  Analyser.getUniqueLabels(tds);
			int countDatarows = tds.getSize();
			for(int j = 0; j<uniqueLabels.size();j++){
				int countMatches = tds.countMatches("label", (uniqueLabels.get(j))); 
				System.out.println("[TRACE]CountMatches: "+countMatches+" | CountLabels: "+uniqueLabels.size());
				//Randomly draw one label and what are the chances of hitting it? label/max_rows
				double probabilityOfMatch = (double)countMatches/(double)countDatarows;
				impurity -= probabilityOfMatch*probabilityOfMatch;
			}
			System.out.println("[DEBUG]Calculated Impurity = "+impurity);
			q.setGini(impurity);

			if(q.getGini()<minGini){
				bestQuestion = q;
				minGini = q.getGini();
			}
		} 
		System.out.println("[DEBUG]Best Question: "+bestQuestion+" with Gini "+minGini);
		return minGini;
	}
	
	public static boolean isQuestionTrue(Fruit f, Question q){
		boolean match = false;
		if(q instanceof StringQuestion){ //Can be done in one Line it Property handles Questions
			match = f.isMatch("color", q.getStringValue());
		}
		else{
			match = f.isMatch("diameter", q.getNumberValue());
		}
		System.out.println("[DEBUG] Question "+q+" for Fruit "+f+"is "+match);
		return match;
	}
}
