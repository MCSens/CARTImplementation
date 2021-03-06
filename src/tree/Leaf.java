package tree;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

import tree.CART;
import data.Fruit;
import data.Property;

public class Leaf implements CART{
	public ArrayList<String> predictions;
	private static Logger log =(Logger) LoggerFactory.getLogger("CART");
	
	public Leaf(String ... properties){
		predictions = new ArrayList<String>();
		for(String prop: properties){
			predictions.add(prop); 
		}
	}
	
	public ArrayList<String> getPedictions(){
		return predictions;
	}

	@Override
	public String toString() {
		return "Leaf [predictions=" + predictions + "]";
	}

	@Override
	public String validate(Fruit f) {
		// TODO Auto-generated method stub
		return predictions.toString();
	}
}
