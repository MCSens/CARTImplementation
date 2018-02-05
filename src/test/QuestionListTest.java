package test;

import java.util.ArrayList;
import java.util.List;

import dataAnalyser.NumberQuestion;
import dataAnalyser.Question;
import dataAnalyser.StringQuestion;

import org.junit.*;
import static org.junit.Assert.*;

public class QuestionListTest {

	@Test
	public void questionTest(){
		// TODO Auto-generated method stub
		Question q1 = new StringQuestion("color","red");
		Question q2 = new StringQuestion("color","green");
		Question q3 = new StringQuestion("color","yellow");
			
		ArrayList<Question> ql = new ArrayList<Question>();
		ql.add(q1);
		ql.add(q2);
		boolean containsRed = ql.contains(new StringQuestion("color","red"));
		boolean containsGreen = ql.contains(new StringQuestion("color","green"));
		boolean containsYellow = ql.contains(new StringQuestion("color","yellow"));
		assertTrue(containsRed);
		assertTrue(containsGreen);
		assertFalse(containsYellow);
		
		NumberQuestion q4 = new NumberQuestion("diameter",5.5);
		NumberQuestion q5 = new NumberQuestion("diameter",6.0);
		ArrayList<NumberQuestion> ql2 = new ArrayList<NumberQuestion>();
		ql2.add(q4);
		ql2.add(q5);
		boolean contains56 = ql2.contains(new NumberQuestion("diameter",5.6));
		boolean contains60 = ql2.contains(new NumberQuestion("diameter",6.0));
		assertFalse(contains56);
		assertTrue(contains60);
	}
}
