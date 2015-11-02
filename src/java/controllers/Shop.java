package controllers;

import entities.OrderEntity;
import javax.faces.bean.ManagedBean;
import entities.FoodEntity;
import entities.FoodRepository;
import entities.OrderRepository;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class Shop {
    
    @ManagedProperty(value="#{msg}")
    private ResourceBundle msg;
    
    @ManagedProperty(value="#{account}")
    private Account account;
    
    @EJB private FoodRepository foodRepository;
    @EJB private OrderRepository orderRepository;
    
    private OrderEntity order;

    public Shop() {
        order = new OrderEntity();
    }
    
    public String submitOrder() {
        if (order.getCart().isEmpty()) {
            submitFail();
            return null;
        }
        
        order.setAccount(account.getEntity());
        orderRepository.persist(order);
        order = new OrderEntity();
        
        return "";
    }
    
    public void submitFail() {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        FacesMessage errorMsg = new FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            msg.getString("shopSubmitError"),
            null
        );
        fc.addMessage(null, errorMsg);
        fc.renderResponse();
    }
    
    public List<FoodEntity> getFoodList() { return foodRepository.findAll(); }
    public OrderEntity getOrder() { return order; }
    
    public void setMsg(ResourceBundle msg) { this.msg = msg; }
    
    public void setAccount(Account account) { this.account = account; }
    
}
