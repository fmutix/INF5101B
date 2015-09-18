package controllers;

import com.sun.org.glassfish.gmbal.ManagedAttribute;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import entities.IssueEntity;
import entities.Repository;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@RequestScoped
public class Issue {
    
    @ManagedProperty(value="#{account}")
    private Account account;
    
    @EJB private Repository repository;
    
    private IssueEntity entity;
    
    public Issue() { entity = new IssueEntity(); }

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
    
    public String send() {
        entity.setAccount(account.getEntity());
        repository.persist(entity);

        return "summary";
    }

    public IssueEntity getEntity() { return entity; }
    public void setEntity(IssueEntity entity) { this.entity = entity; }
    
    public void setAccount(Account account) { this.account = account; }
}
