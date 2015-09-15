package models;

import controllers.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
        Connection c = DB.getConnection();

        PreparedStatement query = c.prepareStatement(
            "INSERT INTO Users" +
            "(FirstName, LastName, Email, Phone, Software, Os, Issue) " +
            "Values (?, ?, ?, ?, ?, ?, ?)"
        );

        query.setString(1, firstName);
        query.setString(2, lastName);
        query.setString(3, email);
        query.setString(4, phone);
        query.setString(5, software);
        query.setString(6, os);
        query.setString(7, issue);
        query.executeUpdate();

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
