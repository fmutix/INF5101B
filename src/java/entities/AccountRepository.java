package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class AccountRepository {
    
    /**
     * Find one AccountEntity entry in the database by filtering by email. 
     * @param email Value with which the column should be filtered.
     * @return AccountEntity instance.
     */
    public static AccountEntity findUniqueByEmail(String email) 
    {
        EntityManager em = Repository.getEntityManager();
        
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            
            CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);
            Root<AccountEntity> rt = cq.from(AccountEntity.class);
            
            ParameterExpression<String> p = cb.parameter(String.class);
            cq.select(rt).where(cb.equal(rt.get("email"), p));
            
            TypedQuery<AccountEntity> q = em.createQuery(cq);
            q.setParameter(p, email);
            
            return q.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
