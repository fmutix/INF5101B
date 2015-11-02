package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity(name = "Orders")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @JoinColumn(name="AccountId")
    @NotNull
    private AccountEntity account;
    
    @ElementCollection
    @CollectionTable(
        name = "Orders_Food",
        joinColumns = @JoinColumn(name = "order_id")
    )
    @MapKeyJoinColumn(name = "food_id")
    private Map<FoodEntity, Integer> cart;
    
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date orderTime;
    
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
    
    public int quantity(FoodEntity food) {
        if (!cart.containsKey(food)) { return 0; }
        
        return cart.get(food);
    }
    
    public double getTotal() {
        double total = 0.0;
        
        for (Map.Entry<FoodEntity, Integer> entry : cart.entrySet()) {
            double price = entry.getKey().getPrice();
            int quantity = entry.getValue();
            total += price * quantity;
        }
        return total;
    }
    
    public Set<Map.Entry<FoodEntity, Integer>> getCartSet() {
        return cart.entrySet();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderEntity)) {
            return false;
        }
        OrderEntity other = (OrderEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderEntity[ id=" + id + " ]";
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public AccountEntity getAccount() { return account; }
    public void setAccount(AccountEntity account) { this.account = account; }
    
    public Map<FoodEntity, Integer> getCart() { return cart; }
    public void setCart(Map<FoodEntity, Integer> cart) { this.cart = cart; }
    
    public Date getOrderTime() { return orderTime; }
    public void setOrderTime(Date orderTime) { this.orderTime = orderTime; }
    
}
