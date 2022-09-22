/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.testjsp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.UnavailableException;

/**
 *
 * @author antoine
 */

public class Client {
    
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isRegistered;
    
    public Client() {
        
    }

    public Client(String email, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Client(String email) throws UnavailableException, SQLException {
        this.email = email;
        this.isRegistered = this.isClientRegistered(email);
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isIsRegistered() {
        return isRegistered;
    }
    
    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    private boolean isClientRegistered(String email) throws UnavailableException, SQLException {
        DBConnection connection = DBConnection.getInstance();
        Map<String, String> conditions = new HashMap<>();
        conditions.put("email", email);
        ResultSet result  = connection.selectStatement(conditions, "clients");
        this.isRegistered = result.next();
        connection.close();
        return this.isRegistered;
    }
    
    public void register() throws SQLException, UnavailableException {
        DBConnection connection = DBConnection.getInstance();
        List<String> attributs = new ArrayList<>();
        attributs.add(this.email);
        attributs.add(this.firstName);
        attributs.add(this.lastName);
        attributs.add(this.phoneNumber);
        connection.insertStatement(attributs, "clients");
    }
}
