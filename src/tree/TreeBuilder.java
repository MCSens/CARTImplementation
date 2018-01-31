package tree;

import tree.Node;
import tree.CART;
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
	
	public static CART buildTree(Node n, TrainingDataSet tds){
		//1. Create List of questions for the particular Training Set
		QuestionList ql = Analyser.getQuestions(tds);
		
		//2. Get Best Question
		Question q = Analyser.getBestGiniQuestion(tds, ql);
		System.out.println("[DEBUG]Gini Value: "+q.getGini());
		if(q.getGini() != 1.0){
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
			
			if(matchingData.getProperties().size()==0 || unmatchingData.getProperties().size()==0){
				return new Leaf("LEAF "+Analyser.getUniqueLabels(matchingData));
			}
			else{
				Node trueNode = new Node(); 
				Node falseNode = new Node();
				n.setTrueNode(TreeBuilder.buildTree(new Node(), matchingData));
				n.setFalseNode(TreeBuilder.buildTree(new Node(), unmatchingData));
				System.out.println("[INFO]Hier gebe ich eine neue Node zurück");
				System.out.println("[INFO]"+n);
				return n;
			}
		}
		else{
			//5. Falls Gini sind wir am Ende angelangt und setzen ein Leaf
			Leaf l = new Leaf("LEAF "+q.getStringValue());
			return l;
		}
	}
}
