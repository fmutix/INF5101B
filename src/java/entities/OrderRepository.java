package entities;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
@LocalBean
public class OrderRepository {
    
    @PersistenceContext
    private EntityManager em;

    public List<OrderEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<OrderEntity> cq = cb.createQuery(OrderEntity.class);
        Root<OrderEntity> root = cq.from(OrderEntity.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get("orderTime")));
        Query q = em.createQuery(cq);
        
        return q.getResultList();
    }
    
    public void persist(OrderEntity o) {
        o.setOrderTime(new Date());
        em.persist(o);
    }
}
