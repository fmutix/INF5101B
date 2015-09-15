package models;

import controllers.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean
@RequestScoped
public class Support {
    
    @ManagedProperty(value = "#{account}")
    private Account account;

    private String software, os, issue;

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
            "INSERT INTO Support" +
            "(Software, Os, Issue, AccountId) " +
            "Values (?, ?, ?, ?)"
        );

        query.setString(1, software);
        query.setString(2, os);
        query.setString(3, issue);
        query.setInt(4, account.getId());
        query.executeUpdate();

        return "summary";
    }
    
    public void setAccount(Account account) { this.account = account; }
    
    public String getSoftware() { return software; }
    public void setSoftware(String software) { this.software = software; }

    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }
}
