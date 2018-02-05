/**
 * This Test generates a DataSet with two Bananas
 * After applying a question, the resulting subsets has two Entries with two different Labels
 * If you pick one randomly and randomly assign a label, you have a 50% chance of being correct
 * Thus the Gini Value of this Question is 0.5
 * 
 * @author Sandro Veiga Perez
 * 
 */

package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import data.Fruit;
import data.TrainingDataSet;
import dataAnalyser.Analyser;
import dataAnalyser.Question;
import dataAnalyser.QuestionList;
import dataAnalyser.StringQuestion;

import org.junit.*;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class GiniTest {
	private static Logger log = (Logger) LoggerFactory.getLogger("GiniTest");
	
	@Test
	public void giniCalculationTest(){
		((Logger) LoggerFactory.getLogger("GiniTest")).setLevel(Level.DEBUG);
		Fruit banana1 = new Fruit("Banana1", 5.5, "yellow");
		Fruit banana2 = new Fruit("Banana2", 6.5, "yellow"); 
		TrainingDataSet ds = new TrainingDataSet("Fruits", banana1,banana2);
		
		//After applying that question you have a 50% change if you randomly assign a lable
		List<Question> list = new ArrayList<Question>();
		list.add(new StringQuestion("color","yellow"));
		
		QuestionList ql = new QuestionList(list);
		double gini = Analyser.getGiniValue(ds, new StringQuestion("color","yellow"));
		assertEquals(0.5, Analyser.getGiniValue(ds, new StringQuestion("color","yellow")), 0.001);
		log.trace("Calculated Gini Value: "+gini);
	}
}
