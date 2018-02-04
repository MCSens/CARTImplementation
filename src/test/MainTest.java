package test;

import giniValidator.Validator;

import java.util.ArrayList;

import data.TrainingDataSet;
import data.Fruit;
import dataAnalyser.Analyser;
import dataAnalyser.Question;
import dataAnalyser.QuestionList;

public class MainTest {

	public static void main(String[] args) {
		/*
		 * Das Verwenden von Questions macht es möglich, weniger Funktionen für beides aufzurufen
		 * Dennoch muss aufgrund des Datentyps auseinander gestuert werden.
		 */
		// TODO Auto-generated method stub 015116700179
		String[] existing_parameters={"diameter", "color"};
			//Vielleicht kann man damit noch was besser machen??
		//TODO Method for fetching unique values
		//TODO Method for sorting criterias
		Fruit apple1 = new Fruit("Apple", 5.5, "green");
		Fruit apple2 = new Fruit("Apple", 6.5, "green");
		Fruit apple3 = new Fruit("Apple", 5.5, "yellow");
		Fruit apple4 = new Fruit("Apple", 6.0, "red");
		Fruit lemon1 = new Fruit("Lemon", 5.5, "green");
		Fruit lemon2 = new Fruit("Lemon", 7.5, "yellow");
		Fruit lemon3 = new Fruit("Lemon", 9.0, "yellow");
		Fruit lemon4 = new Fruit("Lemon", 7.0, "yellow");
		//System.out.println(apple2.match("diameter", 3.0));
		//System.out.println(apple2.match("diameter", 5.5)); 
		//System.out.println(apple2.match("color", "green"));
		TrainingDataSet ds = new TrainingDataSet("Fruits", apple1, apple2, apple3, apple4, lemon1, lemon2, lemon3, lemon4);
		
		
		//System.out.println("ANZAHL MATCHES: "+ds.countMatches("diameter",5.5));
		//System.out.println(ds);
		
		//ds.isMatch("diameter", 6.5);
		TrainingDataSet ds2 = ds.getMatch("color", "yellow");
		//System.out.println(ds2);
		QuestionList ql = Analyser.getQuestions(ds);
		//System.out.println(ql);
		ArrayList<String> labelList = (ArrayList<String>) Analyser.getUniqueLabels(ds);
		Question q = Analyser.getBestGiniQuestion(ds, ql);
		System.out.println(q);
		//System.out.println(l1);
		//ql.sort();
		//System.out.println(ql);
	}
}
