package controllers;

import entities.OrderEntity;
import javax.faces.bean.ManagedBean;
import entities.OrderRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ShopAdm {
   
    @EJB private OrderRepository orderRepository;
    
    private OrderEntity order;

	public ShopAdm() {}
    
    public String orderDetails(OrderEntity o) {
        order = o;
        
        return "orderDetails";
    }

    public List<OrderEntity> getOrders() { return orderRepository.findAll(); }
    public OrderEntity getOrder() { return order; }
}
