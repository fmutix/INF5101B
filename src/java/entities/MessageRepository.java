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
public class MessageRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * finds all MessageEntity entries in the database.
     * @return List of all the guestbook messages contained in the database.
     */
    public List<MessageEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<MessageEntity> cq = cb.createQuery(MessageEntity.class);
        Root<MessageEntity> root = cq.from(MessageEntity.class);
        cq.select(root);
        cq.orderBy(cb.desc(root.get("messageTime")));
        Query q = em.createQuery(cq);
        
        return q.getResultList();
    }
    
    /**
     * Persists a message in the database.
     * @param m Message to persist.
     */
    public void persist(MessageEntity m) {
        m.setMessageTime(new Date());
        em.persist(m);
    }
    
}
