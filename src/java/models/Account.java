package models;

import controllers.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Account {

    private int id;
    private String firstName, lastName, email, phone;

    public String save() throws ClassNotFoundException, SQLException {
        Connection c = DB.getConnection();

        PreparedStatement query = c.prepareStatement(
            "INSERT INTO Accounts" +
            "(FirstName, LastName, Email, Phone) " +
            "Values (?, ?, ?, ?)"
        );

        query.setString(1, firstName);
        query.setString(2, lastName);
        query.setString(3, email);
        query.setString(4, phone);
        query.executeUpdate();
        
        setIdFromDB();

        return "index";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    private void setIdFromDB() throws ClassNotFoundException, SQLException {
        Connection c = DB.getConnection();

        PreparedStatement query = c.prepareStatement(
            "SELECT Id FROM Accounts WHERE Email = ?"
        );

        query.setString(1, email);
        ResultSet rs = query.executeQuery();
        rs.next();
        id = rs.getInt("Id");
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

}
