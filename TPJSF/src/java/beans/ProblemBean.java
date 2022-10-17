/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import database.DBConnection;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.UnavailableException;

/**
 *
 * @author extra
 */
@ManagedBean
@SessionScoped
public class ProblemBean implements Serializable {

    @ManagedProperty(value = "#{userBean}")
    private UserBean user;
    
    private String systeme;
    private String logiciel;
    private String probleme;
    
    /**
     * Creates a new instance of ProblemBean
     */
    public ProblemBean() {
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getSysteme() {
        return systeme;
    }

    public void setSysteme(String systeme) {
        this.systeme = systeme;
    }

    public String getLogiciel() {
        return logiciel;
    }

    public void setLogiciel(String logiciel) {
        this.logiciel = logiciel;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }
    
    public String validation() throws UnavailableException, SQLException {
        if(this.user.isClientRegistered()) return sauvegarde();
        return "register";
    }
    
    public String registerClient() throws UnavailableException, SQLException {
        this.user.register();
        return sauvegarde();
    }
    
    public String sauvegarde() throws UnavailableException, SQLException {        
        DBConnection connection = DBConnection.getInstance();
        List<String> attributs = new ArrayList<>();
        attributs.add(this.user.getEmail());
        attributs.add(this.systeme);
        attributs.add(this.logiciel);
        attributs.add(this.probleme);
        connection.insertStatement(attributs, "requests");
        return "ok";
    }
    
}
