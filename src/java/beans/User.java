package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.UnavailableException;

@ManagedBean
@RequestScoped
public class User {
    
    private String firstName, lastName, email, phone, software, os, issue;
    
    public void validateSoftwareOS(ComponentSystemEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        
        UIInput softwareInput = (UIInput) components.findComponent("software");
        UIInput osInput = (UIInput) components.findComponent("os");
        
        String software_ = softwareInput.getLocalValue().toString();
        String os_ = osInput.getLocalValue().toString();
        
        if (software_.equals("Microsoft Word") && os_.equals("Linux")) {
            FacesMessage errorMsg = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "This software cannot be used with this OS.", 
                null
            );
            fc.addMessage("software", errorMsg);
            fc.renderResponse();
        }
    }
    
    public String save() throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO SUPP_REQUEST values (" +
           "'" + firstName + "'," +
           "'" + lastName + "'," +
           "'" + email + "'," +
           "'" + phone + "'," +
           "'" + software + "'," +
           "'" + os + "'," +
           "'" + issue + "'" +
        ")";
        
        Connection c = DriverManager.getConnection(
            "jdbc:derby://localhost:1527/hotline",
            "test", "test"
        );

        java.sql.Statement insertStatement = c.createStatement();
        insertStatement.executeUpdate(query);
        
        return "summary";
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSoftware() { return software; }
    public void setSoftware(String software) { this.software = software; }

    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }
}
