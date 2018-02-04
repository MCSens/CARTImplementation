package test;


import static org.junit.Assert.*;
import org.junit.Test;

import data.Fruit;
import dataAnalyser.Analyser;
import dataAnalyser.NumberQuestion;
import dataAnalyser.StringQuestion;

public class QuestionMatchTest {
	
	@Test
	public void QuestionMatchTest(){
		Fruit redApple = new Fruit("Apple",5.5,"red");
		StringQuestion redQuestion = new StringQuestion("red");
		StringQuestion yellowQuestion = new StringQuestion("yellow");
		NumberQuestion question55 = new NumberQuestion(5.5);
		NumberQuestion question60 = new NumberQuestion(6.0);
		
		assertTrue(Analyser.isQuestionTrue(redApple, redQuestion));
		assertTrue(Analyser.isQuestionTrue(redApple, question55));
		assertFalse(Analyser.isQuestionTrue(redApple, yellowQuestion));
		assertFalse(Analyser.isQuestionTrue(redApple, question60));
	}
}
