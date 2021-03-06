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
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import model.Tabelajpa;
import model.Translator;
import view.View;

/**
 * Servlet, that translate user input to morse or english
 *
 * @author Patryk Domin
 * @version 2.0
 */
public class Translate extends HttpServlet {

    @PersistenceContext(unitName = "MorseTranslatorHTMLPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
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
        Translator translator;
        View view;        
        HttpSession session = request.getSession(true);
        Object sessionModel = session.getAttribute("translator");
        Object sessionView = session.getAttribute("view");
        if (sessionModel == null) {
            translator = new Translator();
        } else {
            translator = (Translator) sessionModel;
        }
        if (sessionView == null) {
            view = new View();
        } else {
            view = (View) sessionView;
        }
        useCookies(view, request, response);
        String inputText = request.getParameter("text1");
        view.setUserInput(inputText);
        doTranslation(translator, view, inputText);
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
     * insert data to the data base 
     * @param input - text to translate
     * @param output - trasnlated text
     */
    private void insertData(String input, String output) {
        Tabelajpa element = new Tabelajpa();
        element.setUserinput(input);
        element.setUseroutput(output);
        persist(element);
    }
    
    /**
     * Translate input text (from english to morse and from morse to english)
     *
     * @param translator reference to model
     * @param view reference to view
     * @param inputText text to translate
     */
    private void doTranslation(Translator translator, View view, String inputText) {
        view.setTranslatedText("");
        translator.setTranslatedText("");
        boolean engToMorse = translator.getTranslateDirection();
        char[] tmpUserInput = inputText.toCharArray(); 
        String translatedText = "";
        boolean correct = false;
        if (!"".equals(inputText)) {
            if (engToMorse) {
                if (translator.checkInputEnglish(inputText)) {
                    correct = true;
                    translator.setUserInput(inputText);
                    view.setUserInput(inputText);
                    for (char e : tmpUserInput) {
                        translatedText += String.valueOf(translator.changeSignFromEnglish(translator.signIndexEnglish(e)));
                    } 
                    view.setErrorMsg("");   
                } else {
                    view.setErrorMsg("Wrong input");
                }
            } else {
                if (translator.morseToEnglish(inputText).equals("")) {
                    view.setErrorMsg("Wrong input!");
                } else {
                    translator.setUserInput(inputText);
                    view.setUserInput(inputText);
                    correct = true;
                    translatedText = translator.morseToEnglish(inputText); 
                } 
            }
            if (correct) {
                view.setTranslatedText(translatedText);
                translator.setTranslatedText(translatedText);
                insertData(inputText, translatedText);
            } else {
                view.setTranslatedText("");
                translator.setTranslatedText("");
            }
        }
    }

    /**
     * Reads saved cookies to see how many times user translate text
     *
     * @param view reference to view
     * @param request servlet request
     * @param response servlet response
     */
    private void useCookies(View view, HttpServletRequest request, HttpServletResponse response) {
        int transltionNumber = 0;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("convertions")) {
                transltionNumber = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        view.setTranslationNumber(transltionNumber);
        transltionNumber++;
        Cookie cookie = new Cookie("convertions", String.valueOf(transltionNumber));
        response.addCookie(cookie);
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
