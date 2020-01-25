/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import model.Translator;
import view.View;

/**
 * Servlet, that clear translations history
 *
 * @author Patryk Domin
 * @version 2.0
 */
public class Clear extends HttpServlet {

    @PersistenceContext(unitName = "MorseTranslatorHTMLPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Clear translation history
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
        translator.clearHistory();
        view.clearHistory();
        deleteData();
        try (PrintWriter out = response.getWriter()) {
            view.createResponse();
            out.println(view.getResponse());
        }
        session.setAttribute("translator", translator);
        session.setAttribute("view", view);  
    }
    
    /**
     * func generated with Use Entity Manager
     * @param object - object to commit
     */
    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    /**
     * delete data from the database
     */
    private void deleteData() {

        //Query q = em.createQuery("DELETE FROM Tabelajpa");
        //q.executeUpdate();

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
