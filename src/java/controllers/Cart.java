package controllers;

import entities.FoodEntity;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Cart {
	
	private HashMap<FoodEntity, Integer> foodList = new HashMap<>();

	public Cart() {}
	
	public HashMap<FoodEntity, Integer> getFoodList() {
		return foodList;
	}
	
	public void add(FoodEntity food) {
		if (!foodList.containsKey(food)) {
			getFoodList().put(food, 1);
		}
		else {
			getFoodList().put(food, getFoodList().get(food)+1);
		}
	}
	
	public void remove(FoodEntity food) {
		if (getFoodList().containsKey(food)) {
			if (getFoodList().get(food)-1 > 0) {
				getFoodList().put(food, getFoodList().get(food)-1);
			}
			else {
				delete(food);
			}
		}
	}
	
	public void delete(FoodEntity food) {
		getFoodList().remove(food);
	}
	
	public double getTotal() {
		double total = 0.0;
		
		for (Entry<FoodEntity, Integer> entry : getFoodList().entrySet()) {
			double price = entry.getKey().getPrice();
			int quantity = entry.getValue();
			total += price * quantity;
		}
		return total;
	}
}
