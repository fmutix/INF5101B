package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repository {
    private static final EntityManagerFactory emf;
    
    static {
        emf = Persistence.createEntityManagerFactory("tpsPU");
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    /**
     * Persists an object in the database.
     * @param o Entity to persist.
     */
    public static void persist(Object o) {
        EntityManager em = getEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
