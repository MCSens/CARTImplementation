package tree;

import dataAnalyser.Question;

public class Node implements Prediction{
	//?!?! Push all Information through and delete the question that gain no information at the end?
	private Question question;
	private Prediction trueNode; //May be a Node or a Leaf
	private Prediction falseNode;
	
	public Node(){
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Prediction getTrueNode() {
		return trueNode;
	}

	public void setTrueNode(Prediction trueNode) {
		this.trueNode = trueNode;
	}

	public Prediction getFalseNode() {
		return falseNode;
	}

	public void setFalseNode(Prediction falseNode) {
		this.falseNode = falseNode;
	}

	@Override
	public String toString() {
		return "Node [question=" + question + ",\n trueNode=" + trueNode
				+ ",\n falseNode=" + falseNode + "]";
	}
	
	
	
}
