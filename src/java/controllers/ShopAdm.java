package controllers;

import entities.FoodEntity;
import entities.FoodRepository;
import entities.OrderEntity;
import javax.faces.bean.ManagedBean;
import entities.OrderRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ShopAdm {
   
    @EJB private FoodRepository foodRepository;
    @EJB private OrderRepository orderRepository;
    
    private OrderEntity order;

    public ShopAdm() {}
    
    public String orderDetails(OrderEntity o) {
        order = o;
        
        return "orderDetails";
    }
    
    public void removeFood(FoodEntity f) {
        foodRepository.remove(f);
    }

    public List<OrderEntity> getOrders() { return orderRepository.findAll(); }
    public OrderEntity getOrder() { return order; }
}
