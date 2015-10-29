package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entities.MessageEntity;
import entities.MessageRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@RequestScoped
public class Guestbook {
    
    @ManagedProperty(value="#{account}")
    private Account account;
    
    @EJB private MessageRepository messageRepository;
    
    private MessageEntity entity;
    
    public Guestbook() { entity = new MessageEntity(); }
    
    public String send() {
        entity.setAccount(account.getEntity());
        messageRepository.persist(entity);

        return "";
    }
    
    public void removeMessage(MessageEntity m) {
        if (account.getEntity().isAdmin()) {
            messageRepository.remove(m);
        }
    }
    
    public List<MessageEntity> getMessages() {
        return messageRepository.findAll();
    }

    public MessageEntity getEntity() { return entity; }
    public void setEntity(MessageEntity entity) { this.entity = entity; }
    
    public void setAccount(Account account) { this.account = account; }
}
