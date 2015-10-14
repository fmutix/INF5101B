package entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.xml.bind.DatatypeConverter;

@Stateless
@LocalBean
public class AccountRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Finds one AccountEntity entry in the database by filtering by email and
     * only returns it if the given password matches the one stored in the 
     * database.
     * @param email Value with which the column should be filtered.
     * @param pwd Password typed by the user that should be checked against
     * the database entry.
     * @return AccountEntity instance.
     */
    public AccountEntity login(String email, String pwd) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<AccountEntity> cq = cb.createQuery(AccountEntity.class);
        Root<AccountEntity> rt = cq.from(AccountEntity.class);

        ParameterExpression<String> pEmail = cb.parameter(String.class);
        ParameterExpression<String> pPwd = cb.parameter(String.class);
        cq.select(rt)
          .where(cb.equal(rt.get("email"), pEmail))
          .where(cb.equal(rt.get("password"), pPwd));

        TypedQuery<AccountEntity> q = em.createQuery(cq);
        q.setParameter(pEmail, email);
        q.setParameter(pPwd, hash(pwd));
        
        return q.getSingleResult();
    }
    
    /**
     * Hashes the Account's password before persisting it into the database.
     * @param a Account to save in the database.
     */
    public void persist(AccountEntity a) {
       String hash = hash(a.getPassword());
       a.setPassword(hash);
       em.persist(a);
    }
    
    /**
     * Hashes the specified password (not secure, no salt: no protection against 
     * dictionary attacks if the hashed passwords are obtained by someone).
     * @param pwd
     * @return sha512 hashed password
     */
    private String hash(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(pwd.getBytes());
            return DatatypeConverter.printHexBinary(md.digest());
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
