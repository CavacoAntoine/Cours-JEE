/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author extra
 */

@WebServlet(name = "TechSupportServlet", urlPatterns = {"/TechSupportServlet"})
public class TechSupportServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            throw new UnavailableException("Le driver n'est pas trouvé dans le classpath.");
        }
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String logiciel = request.getParameter("logiciel");
        String os = request.getParameter("os");
        String probleme = request.getParameter("probleme");
        Connection connection = null;
        String insertStatementStr = "insert into "
                + "supp_requests values ('" + prenom + "','" + nom + "','" + email + "','" + telephone
                + "','" + logiciel + "','" + os + "','" + probleme + "')";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jee",
                    "root", "root");
            Statement insertStatement = connection.createStatement();

            insertStatement.executeUpdate(insertStatementStr);
        } catch (SQLException sqle) {
            throw new ServletException("Erreur dans la base de données", sqle);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                }
            }
        }
        PrintWriter out = response.getWriter();

        response.setContentType(
                "text/html");
        out.println(
                "<HTML><HEAD><TITLE>");
        out.println(
                "Support technique: Confirmation de la demande");
        out.println(
                "</TITLE></HEAD>");
        out.println(
                "<BODY>");
        out.println(
                "<H1>Support technique: Confirmation de la demande</H1>");
        out.println(
                "Prenom : " + prenom + "</br>");
        out.println(
                "Nom : " + nom + "</br>");
        out.println(
                "Email : " + email + "</br>");
        out.println(
                "Telephone : " + telephone + "</br>");
        out.println(
                "Logiciel : " + logiciel + "</br>");
        out.println(
                "Systeme d'exploitation : " + os + "</br>");
        out.println(
                "Problème : " + probleme + "</br></br>");
        out.println(
                "Merci pour ces in formations.</br>");
        out.println(
                "Votre problème sera résolue dans les 24 heures</br>");
        out.println(
                "</BODY></HTML>");
        out.close();
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
