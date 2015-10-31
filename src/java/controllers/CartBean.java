package controllers;

import entities.FoodEntity;
import java.util.HashMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CartBean {
	
	private HashMap<FoodEntity, Integer> foodList = new HashMap<>();

	/**
	 * Creates a new instance of CartBean
	 */
	public CartBean() {}
	
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
}
