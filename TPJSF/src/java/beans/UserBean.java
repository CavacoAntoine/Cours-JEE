/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.DBConnection;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.UnavailableException;

/**
 *
 * @author extra
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private String prenom;
    private String nom;
    private String telephone;
    private String email;
    
    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public boolean isClientRegistered() throws UnavailableException, SQLException {
        DBConnection connection = DBConnection.getInstance();
        Map<String, String> conditions = new HashMap<>();
        conditions.put("email", this.email);
        ResultSet result  = connection.selectStatement(conditions, "clients");
        boolean isRegistered = result.next();
        connection.close();
        return isRegistered;
    }
    
    public void register() throws SQLException, UnavailableException {
        DBConnection connection = DBConnection.getInstance();
        List<String> attributs = new ArrayList<>();
        attributs.add(this.email);
        attributs.add(this.nom);
        attributs.add(this.prenom);
        attributs.add(this.telephone);
        connection.insertStatement(attributs, "clients");
    }
    
}
