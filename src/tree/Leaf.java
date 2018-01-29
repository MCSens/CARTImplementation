package tree;

import java.util.ArrayList;

import tree.Prediction;
import data.Property;

public class Leaf implements Prediction{
	public ArrayList<String> predictions;
	
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
}
