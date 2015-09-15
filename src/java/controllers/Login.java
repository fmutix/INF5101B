package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import models.User;

@ManagedBean
@RequestScoped
public class Login {
    
    @ManagedProperty(value = "#{user}")
    private User user;
    
    /**
     * Redirects the user to the login page if he is not logged in yet.
     * @throws java.io.IOException
     */
    public void redirect() throws IOException {
        if (!isLogged()) {
            ExternalContext ec = FacesContext.getCurrentInstance()
                .getExternalContext();
            ec.redirect("/tps/faces/login.xhtml");
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .invalidateSession();

        return "index?faces-redirect=true";
    }
    
    public void setUser(User user) { this.user = user; }
    public boolean isLogged() { return user.getFirstName() != null; }
    
}
