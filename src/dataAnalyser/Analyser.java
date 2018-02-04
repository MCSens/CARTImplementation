/**
 * This Class is responsible for Analysing the DataSets 
 * It generates unique Labels/Questions, Calculates Gini Value etc.
 * 
 * @author Sandro Veiga Perez
 */

package dataAnalyser;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import tree.CART;
import data.TrainingDataSet;
import data.Fruit;
import data.Property;

public class Analyser {
	//This Class Creates the Questions that will build the tree
	private static Logger log = (Logger) LoggerFactory.getLogger("Analyser");
	
	public static QuestionList getQuestions(TrainingDataSet ds){
		List<Question> questionList = new ArrayList<Question>();
		
		ArrayList<Property> checkValues = (ArrayList<Property>) ds.getProperties();
		for(int i = 0; i<checkValues.size(); i++){
			Property p = checkValues.get(i);
			StringQuestion sq = new StringQuestion(((Fruit)p).getColor());
			NumberQuestion nq = new NumberQuestion(((Fruit)p).getDiameter());
			if(!questionList.contains(sq)){
				questionList.add(sq);
				log.trace("Question {} has been added to the list",sq);
			}
			if(!questionList.contains(nq)){
				questionList.add(nq);
				log.trace("Question {} has been added to the list",nq);
			}
		}
		log.debug("Returning generated Questionlist {}",questionList);
		return new QuestionList(questionList);
	}
	
	public static List<String> getUniqueLabels(TrainingDataSet ds){ //Sollte man
		List<String> labelList = new ArrayList<String>();
		
		ArrayList<Property> checkValues = (ArrayList<Property>) ds.getProperties();
		for(int i = 0; i<checkValues.size(); i++){
			Property p = checkValues.get(i);
			String label = p.getLabel();
			if(!labelList.contains(label)){
				log.trace("Label {} is unique and thus added to Lable List", label);
				labelList.add(label);
			}
		}
		log.debug("Returning generated Label List {}",labelList);
		return labelList;
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
		//Iteriert durch alle Fragen und teilt Anzahl Elemente mit dem Key durch Anzahl Antworten
		ArrayList<Question> questionlist = (ArrayList<Question>) dl.getQuestionList();
		log.debug("Finding BestGini Question for {} with questions {}",ds.getKey(), dl);
		Question bestQuestion = null; 
		double minGini = 1.0;
		for(int i = 0; i<questionlist.size();i++){
			Question q = questionlist.get(i);
			log.trace("Current Question: "+q);
			double impurity = 1.0;
			TrainingDataSet tds = null;
			if(q instanceof StringQuestion){ //Can be done in one Line it Property handles Questions
				tds = ds.getMatch("color", ((StringQuestion)q).getValue());
			}
			else{
				tds = ds.getMatch("diameter", ((NumberQuestion)q).getValue());
			}
			log.trace("Resulting SubSet: "+tds);
			List<String> uniqueLabels =  Analyser.getUniqueLabels(tds);
			int countDatarows = tds.getSize();
			for(int j = 0; j<uniqueLabels.size();j++){
				String currentLabel = uniqueLabels.get(j);
				int countMatches = tds.countMatches("label", currentLabel); 
				log.trace("Found {} matches for label  {}", countMatches, currentLabel);
				//Randomly draw one label and what are the chances of hitting it? label/max_rows
				double probabilityOfMatch = (double)countMatches/(double)countDatarows;
				impurity -= probabilityOfMatch*probabilityOfMatch;
				log.trace("Current Impurity = "+impurity);
			}
			log.debug("Calculated Impurity = "+impurity);
			q.setGini(impurity);

			if(q.getGini()<minGini){
				bestQuestion = q;
				minGini = q.getGini();
			}
		}
		log.debug("Best Question: "+bestQuestion+" with Gini "+minGini);
		return bestQuestion;
	}
	
	public static double getGiniValue(TrainingDataSet ds, Question q){
		/*
		counts = class_counts(rows)
			    impurity = 1
			    for lbl in counts:
			        prob_of_lbl = counts[lbl] / float(len(rows))
			        impurity -= prob_of_lbl**2
			    return impurity
		*/
		//Iteriert durch alle Fragen und teilt Anzahl Elemente mit dem KEy durch Anzahl Antworten
		log.debug("Calculate GiniValue of Question {} in DataSet {}",q, ds.getKey());
		double impurity=1.0;
		double minGini = 1.0;
		TrainingDataSet tds = null;
		if(q instanceof StringQuestion){ //Can be done in one Line it Property handles Questions
			tds = ds.getMatch("color", ((StringQuestion)q).getValue());
		}
		else{
			tds = ds.getMatch("diameter", ((NumberQuestion)q).getValue());
		}
		log.debug("Created Subset {} for Question {}",tds,q);
		List<String> uniqueLabels =  Analyser.getUniqueLabels(tds);
		int countDatarows = tds.getSize();
		for(int j = 0; j<uniqueLabels.size();j++){
			int countMatches = tds.countMatches("label", (uniqueLabels.get(j))); 
			log.trace("CountMatches: "+countMatches+" | CountLabels: "+uniqueLabels.size());
			//Randomly draw one label and what are the chances of hitting it? label/max_rows
			double probabilityOfMatch = (double)countMatches/(double)countDatarows;
			impurity -= probabilityOfMatch*probabilityOfMatch;
		}
		minGini = impurity;
		log.debug("Calculated Impurity = "+impurity);
		q.setGini(impurity);
		log.debug("Best Question with Gini "+minGini);
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
		log.debug("Question "+q+" for Fruit "+f+"is "+match);
		return match;
	}
}
