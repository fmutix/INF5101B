package entities;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class OrderEntity {
	
	private final HashMap<FoodEntity, Integer> cart;

	public OrderEntity() {
        cart = new HashMap<>();
    }
	
	public void add(FoodEntity food) {
		if (!cart.containsKey(food)) {
			cart.put(food, 1);
		}
		else {
			cart.put(food, cart.get(food) + 1);
		}
	}
	
	public void decrease(FoodEntity food) {
		if (cart.containsKey(food)) {
			if (cart.get(food)-1 > 0) {
				cart.put(food, cart.get(food) - 1);
			}
			else {
				remove(food);
			}
		}
	}
	
	public void remove(FoodEntity food) {
		cart.remove(food);
	}
	
	public double getTotal() {
		double total = 0.0;
		
		for (Entry<FoodEntity, Integer> entry : cart.entrySet()) {
			double price = entry.getKey().getPrice();
			int quantity = entry.getValue();
			total += price * quantity;
		}
		return total;
	}
    
    public Set<Entry<FoodEntity, Integer>> getCart() { 
        return cart.entrySet(); 
    }
}
