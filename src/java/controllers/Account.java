package controllers;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import entities.AccountEntity;
import entities.AccountRepository;
import entities.Repository;
import javax.ejb.EJB;

@ManagedBean
@SessionScoped
public class Account {
    
    @EJB private Repository repository;
    @EJB private AccountRepository accountRepository;
    
    private AccountEntity entity;
    
    public Account() { entity = new AccountEntity(); }
    
    /**
     * Redirects the user to the login page if he is not logged in yet.
     * @throws java.io.IOException
     */
    public void loginRedirect() throws IOException {
        if (!isLogged()) {
            ExternalContext ec = FacesContext.getCurrentInstance()
                .getExternalContext();
            ec.redirect("/tps/faces/login.xhtml");
        }
    }
    
    /**
     * Connects to an account using partial information stored in the account's 
     * entity (email).
     * @return 
     */
    public String login() {
        // TODO handle authentification failure        
        String email = entity.getEmail();
        AccountEntity ae = accountRepository.findUniqueByEmail(email);
        setEntity(ae);
        
        return "index";
    }
    
    public String logout() {
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .invalidateSession();

        return "index?faces-redirect=true";
    }
    
    public String register() {
        repository.persist(entity);

        return "index";
    }
    
    public AccountEntity getEntity() { return entity; }
    public void setEntity(AccountEntity entity) { this.entity = entity; }

    public boolean isLogged() {  return entity.getFirstName() != null; }
}
