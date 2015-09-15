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
import models.Account;

@ManagedBean
@RequestScoped
public class Login {
    
    @ManagedProperty(value = "#{account}")
    private Account account;
    
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

    public String login() throws ClassNotFoundException, SQLException {
        Connection c = DB.getConnection();

        PreparedStatement query = c.prepareStatement(
            "SELECT Id, FirstName, LastName, Email, Phone " +
            "FROM Accounts " +
            "WHERE Email = ?"
        );

        query.setString(1, account.getEmail());

        ResultSet rs = query.executeQuery();
        rs.next();
        account.setId(rs.getInt("Id"));
        account.setFirstName(rs.getString("FirstName"));
        account.setLastName(rs.getString("LastName"));
        account.setPhone("Phone");

        return "index";
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .invalidateSession();

        return "index?faces-redirect=true";
    }
    
    public void setAccount(Account account) { this.account = account; }
    public boolean isLogged() { return account.getFirstName() != null; }
    
}
