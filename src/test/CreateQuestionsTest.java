package test;

import data.Fruit;
import data.TrainingDataSet;
import dataAnalyser.Analyser;
import dataAnalyser.QuestionList;

import org.junit.*;
import static org.junit.Assert.*;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;


public class CreateQuestionsTest {
	private static Logger log = (Logger) LoggerFactory.getLogger("CreateQuestionsTest");

	@Test
	public void timeForCreation(){
		Fruit apple1 = new Fruit("Apple", 5.5, "green");
		Fruit apple2 = new Fruit("Apple", 6.0, "red"); 
		Fruit lemon1 = new Fruit("Lemon", 4.5, "yellow");
		Fruit lemon2 = new Fruit("Lemon", 4.5, "orange");
		TrainingDataSet ds = new TrainingDataSet("Fruits", apple1, apple2,lemon1,lemon2);
				
		long startTimeQL = System.nanoTime();    
		// ... the code being measured ...   
		for(int i = 0;i<1000;i++){
			QuestionList ql = Analyser.getQuestions(ds);
		}
		long estimatedTimeQL = System.nanoTime() - startTimeQL;
		double timeMiliSeconds = estimatedTimeQL/1000000;
		assertTrue(timeMiliSeconds<30.0);
		System.out.println(timeMiliSeconds);
		log.info(""+timeMiliSeconds);
	}
}
