package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class User {

    private String firstName, lastName, email, phone, software, os, issue;

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
