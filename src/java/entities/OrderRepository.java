package entities;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@LocalBean
public class OrderRepository{

	@PersistenceContext
    private EntityManager em;

	public List<MessageEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<MessageEntity> cq = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = cq.from(MessageEntity.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get("account")));
        Query q = em.createQuery(cq);
        
        return q.getResultList();
    }
    
    public void remove(OrderEntity o) {
        em.remove(em.merge(o));
    }
	
    public void persist(OrderEntity o) {
        em.persist(o);
    }
    
}
