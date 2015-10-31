package entities;

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
public class FoodRepository {
	
	@PersistenceContext
	private EntityManager em;

	public List<FoodEntity> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<FoodEntity> cq = cb.createQuery(FoodEntity.class);
        Root<FoodEntity> root = cq.from(FoodEntity.class);
        cq.select(root);
        cq.orderBy(cb.asc(root.get("type")));
        Query q = em.createQuery(cq);
        
        return q.getResultList();
	}
	
	public FoodEntity update(FoodEntity f) {
		return em.merge(f);
	}
	
	public void persist(FoodEntity f) {
		em.persist(f);
	}
}
