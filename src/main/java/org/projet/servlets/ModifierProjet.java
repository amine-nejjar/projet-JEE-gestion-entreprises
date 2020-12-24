/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.projet.beans.Notification;
import org.projet.beans.ProjetBean;
import org.projet.db.NotificationDB;
import org.projet.db.ProjetDB;

/**
 *
 * @author Test
 */
@WebServlet(name = "ModifierProjet", urlPatterns = {"/ModifierProjet"})
public class ModifierProjet extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
        
        ProjetBean proj= new ProjetBean();
        HttpSession session = request.getSession();
        proj.setId_projet(Integer.parseInt(request.getParameter("m_id_projet")));
        proj.setDesignation(request.getParameter("m_designation"));
        proj.setDescription(request.getParameter("m_description"));
        //proj.setDuree(request.getParameter("m_duree"));
        proj.setStatut(request.getParameter("m_statut"));
        proj.setDtLivraison(request.getParameter("m_dtLivraison"));

        ProjetDB.modifier(proj);
        
        Notification notif=new Notification();
        String notification=(String) session.getAttribute("NOM")+" || projet modifié";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        notif.setID_DESTINATION("*");
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);
        
        session.setAttribute("msgModif","Success");
        String redirectURL = "AfficherProjets.jsp";
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
