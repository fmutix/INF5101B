package controllers;

import javax.faces.bean.ManagedBean;
import entities.FoodEntity;
import entities.FoodRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Food {
	
	private List<FoodEntity> foodList;
	
	@ManagedProperty(value="#{cart}")
	private Cart cart = new Cart();
	
	@EJB private FoodRepository foodRepository;

	public Food() {}
	
	public List<FoodEntity> findAll() {
		return foodRepository.findAll();
	}
	
	public void addToCart(FoodEntity food) {
		getCart().add(food);
	}
	
	public void removeFromCart(FoodEntity food) {
		getCart().remove(food);
	}
	
	public void deleteFromCart(FoodEntity food) {
		getCart().delete(food);
	}

	/**
	 * @return the cart
	 */
	public Cart getCart() {
		return cart;
	}

	/**
	 * @param cart the cart to set
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
