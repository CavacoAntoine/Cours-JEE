/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbtpappclient;

import beans.EchoRemote;
import javax.ejb.EJB;
import javax.swing.JOptionPane;

/**
 *
 * @author extra
 */
public class Main {

    @EJB
    private static EchoRemote echo;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JOptionPane.showMessageDialog(null, echo.echo("Antoine"));
    }
    
}
