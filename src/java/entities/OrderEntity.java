package entities;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Orders")
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
    @JoinColumn(name="AccountId")
    @NotNull
	private AccountEntity account;
	@ElementCollection
	@MapKeyColumn(name="food")
	@Column(name="quantity")
	private Map<FoodEntity, Integer> cart;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public AccountEntity getAccount() { return account; }
	public void setAccount(AccountEntity account) { this.account = account; }
	
	public Map<FoodEntity, Integer> getCart() { return cart; }
	public void setCart(Map<FoodEntity, Integer> cart) { this.cart = cart; }

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
	
}
