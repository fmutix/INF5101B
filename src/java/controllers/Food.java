package controllers;

import javax.faces.bean.ManagedBean;
import entities.FoodEntity;
import entities.FoodRepository;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class Food {
    
    @ManagedProperty(value="#{msg}")
    private ResourceBundle msg;
    
    @EJB private FoodRepository foodRepository;
    
    private FoodEntity entity;
    
    public Food() {
        entity = new FoodEntity();
    }
    
    public String saveFood() {
        try {
            foodRepository.persist(entity);
            entity = new FoodEntity();
            return "";
        } catch(EJBException ejbe) {
            saveFoodFail();
            return null;
        }
    }
    
    public void saveFoodFail() {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        FacesMessage errorMsg = new FacesMessage(
            FacesMessage.SEVERITY_ERROR,
            msg.getString("shopFoodSaveError"),
            null
        );
        fc.addMessage("email", errorMsg);
        fc.renderResponse();
    }
    
    public FoodEntity getEntity() { return entity; }
    public void setEntity(FoodEntity entity) { this.entity = entity; }
    
    public void setMsg(ResourceBundle msg) { this.msg = msg; }
    
    public List<FoodEntity> getFoodList() { return foodRepository.findAll(); }
    
}
