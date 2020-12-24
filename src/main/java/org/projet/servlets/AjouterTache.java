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
import org.projet.beans.TacheBean;
import org.projet.db.TacheDB;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
import javax.servlet.http.HttpSession;
import org.projet.beans.Notification;
import org.projet.db.NotificationDB;
import org.projet.db.ProjetDB;
import org.projet.db.ReclamationDB;

/**
 *
 * @author Test
 */
@WebServlet(name = "AjouterTache", urlPatterns = {"/AjouterTache"})
public class AjouterTache extends HttpServlet {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now(); 
        TacheBean task=new TacheBean();
        
        
        
        task.setIdprojet(Integer.parseInt(request.getParameter("idprojet")));
        task.setIdemp(request.getParameter("idemp"));
        task.setDesignation(request.getParameter("designation"));
        task.setDescription(request.getParameter("description"));
        task.setStatut("non achevée");
        task.setDtcreation(dtf.format(now));
        task.setDtlivraison(request.getParameter("dtLivraison"));
        TacheDB.inserer(task);
        
        Notification notif=new Notification();
        HttpSession session = request.getSession();
        String prj=ProjetDB.getNameById(Integer.parseInt(request.getParameter("idprojet")));
        String notification=prj+" || nouvelle tache";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        notif.setID_DESTINATION(request.getParameter("idemp"));
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);

        session.setAttribute("msgtache","Success");
        String redirectURL = "AjouterTache.jsp";
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
