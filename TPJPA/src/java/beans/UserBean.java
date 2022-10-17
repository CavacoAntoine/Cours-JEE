/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import controller.UserJPAJpaController;
import entity.UserJPA;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    
    public boolean isClientRegistered() {
        UserJPAJpaController controller = new UserJPAJpaController();
        UserJPA user = controller.findUserJPA(email);
        
        return user != null;
        
    }
    
    public UserJPA getUserJPA() {
        UserJPA userJpa = new UserJPA();
        userJpa.setEmail(this.email);
        userJpa.setNom(this.nom);
        userJpa.setPrenom(this.prenom);
        userJpa.setTelephone(this.telephone);
        
        return userJpa;
    }
    
    public void register() throws Exception {
        UserJPAJpaController controller = new UserJPAJpaController();
        UserJPA userJpa = this.getUserJPA();
        controller.create(userJpa);
    }
    
}
