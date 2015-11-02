package controllers;

import entities.OrderEntity;
import javax.faces.bean.ManagedBean;
import entities.FoodEntity;
import entities.FoodRepository;
import entities.OrderRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Shop {
    
    @ManagedProperty(value="#{account}")
    private Account account;
    
	@EJB private FoodRepository foodRepository;
    @EJB private OrderRepository orderRepository;
    
    private OrderEntity order;

	public Shop() {
        order = new OrderEntity();
    }
    
    public void submitOrder() {
        order.setAccount(account.getEntity());
        orderRepository.persist(order);
        order = new OrderEntity();
    }
	
    public List<FoodEntity> getFoodList() { return foodRepository.findAll(); }
    public OrderEntity getOrder() { return order; }
    
    public void setAccount(Account account) { this.account = account; }
    
}
