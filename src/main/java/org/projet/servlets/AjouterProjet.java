/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.servlets;

import org.projet.beans.ProjetBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.projet.beans.ProjetBean;
import java.sql.*;
import javax.servlet.http.HttpSession;
import org.projet.beans.Notification;
import org.projet.db.NotificationDB;
import org.projet.db.ProjetDB;


/**
 *
 * @author Test
 */
@WebServlet(name = "AjouterProjet", urlPatterns = {"/AjouterProjet"})
public class AjouterProjet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        ProjetBean projet=new ProjetBean();
        
        projet.setIdchef(request.getParameter("idchef"));
        projet.setDesignation(request.getParameter("designation"));
        projet.setDuree(request.getParameter("duree"));
        projet.setDescription(request.getParameter("description"));
        projet.setDtLivraison(request.getParameter("dtLivraison"));
        projet.setStatut("Pas encore"); 
        ProjetDB.inserer(projet);
        
        Notification notif=new Notification();
        HttpSession session = request.getSession();
        String notification=(String) session.getAttribute("NOM")+" || nouveau projet";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        notif.setID_DESTINATION("*");
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);
        session.setAttribute("msg","Success");
        String redirectURL = "AjouterProjet.jsp";
        response.sendRedirect(redirectURL);
        
        
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
