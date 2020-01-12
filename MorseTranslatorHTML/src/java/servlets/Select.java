/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Translator;
import view.View;

/**
 * Servlet, that clear translations history
 *
 * @author Patryk Domin
 * @version 2.0
 */
public class Select extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Select from db - translation history
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Translator translator;
        View view;
        HttpSession session = request.getSession(true);
        Object sessionTranslator = session.getAttribute("translator");
        Object sessionView = session.getAttribute("view");
        if (sessionTranslator == null) {
            translator = new Translator();
        } else {
            translator = (Translator) sessionTranslator;
        }
        if (sessionView == null) {
            view = new View();
        } else {
            view = (View) sessionView;
        }
        view.setErrorMsg("");
        selectData(view, translator);
        try (PrintWriter out = response.getWriter()) {
            view.createResponse();
            out.println(view.getResponse());
        }
        session.setAttribute("translator", translator);
        session.setAttribute("view", view);  
    }
    
    /**
     * selec data from the database to display it
     * @param translator reference to model
     * @param view reference to view 
     */
    private void selectData(View view, Translator translator) {
       try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MorseEnglishDB", "patryk", "patryk")) {
            Statement statement = con.createStatement();
           // Przeglądamy otrzymane wyniki
           translator.clearHistory();
           view.clearHistory();
           try (ResultSet rs = statement.executeQuery("SELECT * FROM tabela")) {
               // Przeglądamy otrzymane wyniki
               while (rs.next()) {
                   String in = rs.getString("userinput");
                   String ou = rs.getString("useroutput");
                   String listItem = translator.addNewTranslation(in, ou);
                   view.addNewTranslation(listItem);
               }
               System.out.println("-----------------------------------");
           }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
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