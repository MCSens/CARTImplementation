package dataAnalyser;

import java.util.*;

public class QuestionList {
	private List<Question> questionList;

	public List<Question> getQuestionList() {
		return questionList;
	}
 
	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	@Override
	public String toString() {
		return "DataLists [questionList=" + questionList + "]";
	}

	public QuestionList(List<Question> questionList) {
		super();
		this.questionList = questionList;
	}
	
	public void sort(){
		Collections.sort(questionList);
	}
	

}
