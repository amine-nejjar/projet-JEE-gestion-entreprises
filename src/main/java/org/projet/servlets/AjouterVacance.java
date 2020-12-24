/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.projet.beans.Notification;
import org.projet.beans.Vacance;
import org.projet.db.NotificationDB;
import org.projet.db.ProjetDB;
import org.projet.db.VacanceDB;

/**
 *
 * @author HP
 */
public class AjouterVacance extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            String date_debut=request.getParameter("date_debut");
            String date_fin=request.getParameter("date_fin");
            String designation=request.getParameter("designation");
            Vacance vacance=new Vacance();
            vacance.setDate_debut(date_debut);
            vacance.setDate_fin(date_fin);
            vacance.setDesignation(designation);
            VacanceDB.inserer(vacance);
            
            Notification notif=new Notification();
            
            HttpSession session = request.getSession();
            String notification=(String) session.getAttribute("NOM")+" || Demande de vacance";
            notif.setID_SOURCE((String) session.getAttribute("CIN"));
            notif.setID_DESTINATION("JE12");
            notif.setMESSAGE(notification);
            NotificationDB.inserer(notif);
            
        
            session.setAttribute("msgvac","Success");
            String redirectURL = "DemanderVacance.jsp";
            response.sendRedirect(redirectURL);
        }
        catch(Exception e){System.out.println(e);}
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
