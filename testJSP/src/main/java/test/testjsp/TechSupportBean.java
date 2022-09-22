package test.testjsp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.UnavailableException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author antoine
 */

public class TechSupportBean {
    
    private Client client;
    private String software;
    private String os;
    private String problem;
    
    public TechSupportBean(){
    }

    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public void setClient(String email) throws UnavailableException, SQLException {
        System.out.println("je suis passé par là");
        this.client = new Client(email);        
    }
    
    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
    
    public void register() throws UnavailableException, SQLException{
        DBConnection connection = DBConnection.getInstance();
        List<String> attributs = new ArrayList<>();
        attributs.add(this.client.getEmail());
        attributs.add(this.os);
        attributs.add(this.software);
        attributs.add(this.problem);
        connection.insertStatement(attributs, "requests");
    }
}
