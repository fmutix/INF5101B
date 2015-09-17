package entities;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class Repository {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Persists an object in the database.
     * @param o Entity to persist.
     */
    public void persist(Object o) {
        em.persist(o);
    }
    
}
