package controllers;

import entities.OrderEntity;
import javax.faces.bean.ManagedBean;
import entities.FoodEntity;
import entities.FoodRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Shop {
    
    private OrderEntity order;
    
	@EJB private FoodRepository foodRepository;

	public Shop() {
        order = new OrderEntity();
    }
	
    public List<FoodEntity> getFoodList() { return foodRepository.findAll(); }
    public OrderEntity getOrder() { return order; }
}
