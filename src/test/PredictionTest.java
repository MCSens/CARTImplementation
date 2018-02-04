package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import tree.Node;
import tree.CART;
import tree.TreeBuilder;
import data.TrainingDataSet;
import data.Fruit;

public class PredictionTest {

	@Test
	public void predictionTest(){
		Logger log = (Logger) LoggerFactory.getLogger("TreeBuilderTest");
		//log.getRootLogger().setLevel(Level.DEBUG);
		/*
		 * Das Verwenden von Questions macht es möglich, weniger Funktionen für beides aufzurufen
		 * Dennoch muss aufgrund des Datentyps auseinander gestuert werden.
		 */
		// TODO Auto-generated method stub 015116700179
		String[] existing_parameters={"diameter", "color"};
			//Vielleicht kann man damit noch was besser machen??
		Fruit apple1 = new Fruit("Apple", 5.5, "green");
		Fruit apple2 = new Fruit("Apple", 6.5, "green");
		Fruit apple3 = new Fruit("Apple", 5.5, "yellow");
		Fruit apple4 = new Fruit("Apple", 6.0, "red"); 
		Fruit lemon1 = new Fruit("Lemon", 5.5, "green");
		Fruit lemon2 = new Fruit("Lemon", 7.5, "yellow");
		Fruit lemon3 = new Fruit("Lemon", 9.0, "yellow");
		Fruit lemon4 = new Fruit("Lemon", 7.0, "yellow");
		Fruit lemon5 = new Fruit("Lemon", 6.5, "yellow");
		TrainingDataSet ds = new TrainingDataSet("Fruits", apple1, apple2, apple3, apple4, lemon1, lemon2, lemon3, lemon4, lemon5);
		log.info("TrainingDataSet: {}", ds);
		Node n = new Node();
		CART tree = TreeBuilder.buildTree(n, ds);
		log.info("Resulting Cart: {}", tree);
		 
		Fruit test1 = new Fruit("?",6.0,"red");
		String prediction = tree.validate(test1);
		assertEquals(prediction, "Apple");
		log.info("Prediction for {} is {}",test1,prediction);
	}
}
