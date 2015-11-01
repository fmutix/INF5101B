package controllers;

import entities.OrderEntity;
import entities.OrderRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Order {
	
	@ManagedProperty(value="#{account}")
	private Account account;
	@ManagedProperty(value="#{cart}")
	private Cart cart;
	private OrderEntity orderEntity;
	
	@EJB private OrderRepository orderRepository;

	public Order() {
		orderEntity = new OrderEntity();
	}
	
	public void submit(){
		orderEntity.setAccount(account.getEntity());
		orderEntity.setCart(cart.getFoodList());
		orderRepository.persist(orderEntity);
	}
	
	public List<OrderEntity> getOrders() {
		return orderRepository.findAll();
	}
	
	public void setAccount(Account account) { this.account = account; }
	public void setCart(Cart cart) {this.cart = cart; }
}
