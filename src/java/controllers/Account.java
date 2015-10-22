package controllers;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import entities.AccountEntity;
import entities.AccountRepository;
import entities.Repository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;

@ManagedBean
@SessionScoped
public class Account {
    
    @EJB private AccountRepository accountRepository;
    
    private AccountEntity entity;
    
    public Account() { entity = new AccountEntity(); }
    
    /**
     * Redirects the user to the login page if he is not logged in yet.
     */
    public void loginRedirect() {
        try {
            if (!isLogged()) {
                    ExternalContext ec = FacesContext.getCurrentInstance()
                            .getExternalContext();
                    ec.redirect("/tps/faces/login.xhtml");    
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     * Connects to an account using partial information stored in the account's 
     * entity (email).
     * @return index page if the login succeeded.
     */
    public String login() {
        try {    
            String email = entity.getEmail();
            String pwd = entity.getPassword();
            AccountEntity ae = accountRepository.login(email, pwd);
            if (ae == null) {
                loginFail();
                return null;
            }
            
            setEntity(ae);
            return "index";
        } catch(EJBException nre) {
            loginFail();
        }
        
        return null;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance()
            .getExternalContext()
            .invalidateSession();

        return "index?faces-redirect=true";
    }
    
    public String register() {
        accountRepository.persist(entity);

        return "index";
    }
    
    private void loginFail() {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        FacesMessage errorMsg = new FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            "Login failed. Email/password error, or this account does not exist.",
            null
        );
        fc.addMessage("email", errorMsg);
        fc.renderResponse();
    }
    
    public AccountEntity getEntity() { return entity; }
    public void setEntity(AccountEntity entity) { this.entity = entity; }

    public boolean isLogged() {  return entity.getFirstName() != null; }
}
