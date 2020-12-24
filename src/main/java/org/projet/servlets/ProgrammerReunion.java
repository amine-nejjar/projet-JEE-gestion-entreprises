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
import org.projet.beans.Reunion;
import org.projet.db.NotificationDB;
import org.projet.db.ReunionDB;

/**
 *
 * @author HP
 */
public class ProgrammerReunion extends HttpServlet {

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
            HttpSession session = request.getSession();
            String date_reunion=request.getParameter("date_reunion");
            String salle=request.getParameter("salle");
            String hr_debut=request.getParameter("hr_debut");
            String hr_fin=request.getParameter("hr_fin");
            Reunion reunion=new Reunion();
            reunion.setDate_reunion(date_reunion);
            reunion.setSalle(salle);
            reunion.setHr_debut(hr_debut);
            reunion.setHr_fin(hr_fin);
            ReunionDB.inserer(reunion,(String) session.getAttribute("CIN"));
            int id=ReunionDB.getlastid();
            for(String emp: request.getParameterValues("to[]")){
                ReunionDB.insererRemp(emp, id);
                Notification notif=new Notification();
                String notification="reunion || "+date_reunion;
                notif.setID_SOURCE((String) session.getAttribute("CIN"));
                notif.setID_DESTINATION(emp);
                notif.setMESSAGE(notification);
                NotificationDB.inserer(notif);
                
            }            
            session.setAttribute("msg","Success");
            String redirectURL = "ProgrammerReunion.jsp";
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
