package giniValidator;

import java.util.ArrayList;
import java.util.List;

import data.TrainingDataSet;
import dataAnalyser.Analyser;
import dataAnalyser.NumberQuestion;
import dataAnalyser.Question;
import dataAnalyser.QuestionList;
import dataAnalyser.StringQuestion;

public class Validator {
	//This class will create the GINI Factor to build a usefull tree
	public static void bestGiniQuestion(TrainingDataSet ds, QuestionList dl){
		/*
		
		counts = class_counts(rows)
			    impurity = 1
			    for lbl in counts:
			        prob_of_lbl = counts[lbl] / float(len(rows))
			        impurity -= prob_of_lbl**2
			    return impurity
		
		//Iteriert durch alle Fragen und teilt Anzahl Elemente mit dem KEy durch Anzahl Antworten
		ArrayList<Question> questionlist = (ArrayList<Question>) dl.getQuestionList();
		Question bestQuestion = null; 
		double minGini = 1.0;
		for(int i = 0; i<questionlist.size();i++){
			Question q = questionlist.get(i);
			System.out.println("Aktuelle Frage: "+q);
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
				System.out.println("CountMatches: "+countMatches+" | CountLabels: "+uniqueLabels.size());
				//Randomly draw one label and what are the chances of hitting it? label/max_rows
				double probabilityOfMatch = (double)countMatches/(double)countDatarows;
				impurity -= probabilityOfMatch*probabilityOfMatch;
			}
			System.out.println("Calculated Impurity = "+impurity);
			q.setGini(impurity);

			if(q.getGini()<minGini){
				bestQuestion = q;
				minGini = q.getGini();
			}
		}
		System.out.println("Best Question: "+bestQuestion+" with Gini "+minGini);
	}
	
	public static void calculateGiniImpurity(TrainingDataSet ds, QuestionList dl){
		ArrayList<Question> questionlist = (ArrayList<Question>) dl.getQuestionList();
		Question bestQuestion = null; 
		double maxGini = 0.0;
		for(int i = 0; i<questionlist.size();i++){
			Question q = questionlist.get(i);
			System.out.println("Aktuelle Frage: "+q);
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
				System.out.println("CountMatches: "+countMatches+" | CountLabels: "+uniqueLabels.size());
				//Randomly draw one label and what are the chances of hitting it? label/max_rows
				double probabilityOfMatch = (double)countMatches/(double)countDatarows;
				impurity -= probabilityOfMatch*probabilityOfMatch;
			}
			System.out.println("Calculated Impurity = "+impurity);
			q.setGini(impurity);

			if(q.getGini()>maxGini){
				bestQuestion = q;
				maxGini = q.getGini();
			}
		}
		System.out.println("Best Question: "+bestQuestion+" with Gini "+maxGini);
		//return tds;
		*/
	}
}
