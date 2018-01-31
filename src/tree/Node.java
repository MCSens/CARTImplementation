package tree;

import data.Fruit;
import dataAnalyser.Question;
import dataAnalyser.StringQuestion;

public class Node implements CART{
	//?!?! Push all Information through and delete the question that gain no information at the end?
	private Question question;
	private CART trueNode; //May be a Node or a Leaf
	private CART falseNode;
	
	public Node(){
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public CART getTrueNode() {
		return trueNode;
	}

	public void setTrueNode(CART trueNode) {
		this.trueNode = trueNode;
	}

	public CART getFalseNode() {
		return falseNode;
	}

	public void setFalseNode(CART falseNode) {
		this.falseNode = falseNode;
	}

	@Override
	public String toString() {
		return "Node [question=" + question + ",\n trueNode=" + trueNode 
				+ ",\n falseNode=" + falseNode + "]";
	}

	@Override
	public String validate(Fruit f) {
		boolean match = false;
		if(question instanceof StringQuestion){ //Can be done in one Line it Property handles Questions
			match = f.isMatch("color", question.getStringValue());
		}
		else{
			match = f.isMatch("diameter", question.getNumberValue());
		}
		
		if(match){
			return trueNode.validate(f);
		}
		else{
			return falseNode.validate(f);
		}
	}
}
