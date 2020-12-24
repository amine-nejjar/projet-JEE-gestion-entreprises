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
import org.projet.beans.ReclamationBean;
import org.projet.db.NotificationDB;
import org.projet.db.ReclamationDB;

/**
 *
 * @author Test
 */
@WebServlet(name = "AjouterReclamation", urlPatterns = {"/AjouterReclamation"})
public class AjouterReclamation extends HttpServlet {

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
        
        ReclamationBean reclamation=new ReclamationBean();
        
        reclamation.setObjet(request.getParameter("objet"));
        reclamation.setDescription(request.getParameter("description"));
        reclamation.setIdsrc(request.getParameter("idsrc"));
        reclamation.setIddest(request.getParameter("iddest"));
        reclamation.setReponse("Pas de reponse");
        ReclamationDB.inserer(reclamation);
        
        Notification notif=new Notification();
        HttpSession session = request.getSession();
        String notification=(String) session.getAttribute("NOM")+" || Reclamation";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        if((String) session.getAttribute("STATU")=="EMPLOYE"){
        notif.setID_DESTINATION((String) session.getAttribute("ID_CHEF"));}
        else{
            notif.setID_DESTINATION((String) session.getAttribute("JE111111"));
        }
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);
        
        request.getRequestDispatcher("AjouterReclamation.jsp").include(request, response);
        
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
