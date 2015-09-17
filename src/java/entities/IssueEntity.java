package entities;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Issues")
public class IssueEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String software;
    
    private String os;
    
    private String description;
    
    /*@Embedded
    private AccountEntity account;*/

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getSoftware() { return software; }
    public void setSoftware(String software) { this.software = software; }

    public String getOs() { return os; }
    public void setOs(String os) { this.os = os; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    /*public AccountEntity getAccount() { return account; }
    public void setAccount(AccountEntity account) { this.account = account; }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IssueEntity)) {
            return false;
        }
        IssueEntity other = (IssueEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IssueEntity[ id=" + id + " ]";
    }
    
}
