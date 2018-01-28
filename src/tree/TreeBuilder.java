package tree;

import data.TrainingDataSet;
import dataAnalyser.Analyser;
import dataAnalyser.NumberQuestion;
import dataAnalyser.Question;
import dataAnalyser.QuestionList;
import dataAnalyser.StringQuestion;

public class TreeBuilder {
	//Get Best GiniQuestion, this is root node.
	//Divide Subset by fitting rule, not fitting rule
	//If result has 1 entry -> Leaf
	//How do I know I am at the end??
	
	public static Prediction buildTree(Node n, TrainingDataSet tds){
		//1. Create List of questions for the particular Training Set
		QuestionList ql = Analyser.getQuestions(tds);
		
		//2. Get Best Question
		Question q = Analyser.getBestGiniQuestion(tds, ql);
		
		//3. Create Root Node
		n.setQuestion(q);
		
		//4. Build Subtrees depending on true/false
		TrainingDataSet matchingData;
		TrainingDataSet unmatchingData; 
		if(q instanceof StringQuestion){ //Unnecessary, store label in Question
			matchingData = tds.getMatch("color", ((StringQuestion)q).getValue());
			unmatchingData = tds.removeMatch("color", ((StringQuestion)q).getValue());
		}
		else{
			matchingData = tds.getMatch("diameter", ((NumberQuestion)q).getValue());
			unmatchingData = tds.removeMatch("diameter", ((NumberQuestion)q).getValue());	
		}
		
		Node trueNode = new Node();
		Node falseNode = new Node();
		n.setTrueNode(TreeBuilder.buildTree(new Node(), matchingData));
		n.setFalseNode(TreeBuilder.buildTree(new Node(), unmatchingData));
		return n;
	}
}
