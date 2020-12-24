<%@page import="org.projet.db.NotificationDB"%>
<%@page import="org.projet.beans.Notification"%>
<%@page import="org.projet.db.ProjetDB"%>
<%
   String projid=request.getParameter("projetid");
   int i = Integer.parseInt(projid);
   ProjetDB.deleteProject(i);
   
        Notification notif=new Notification();
        String notification=(String) session.getAttribute("NOM")+" || projet supprimé";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        notif.setID_DESTINATION("*");
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);
        
   String redirectURL = "AfficherProjets.jsp";
   response.sendRedirect(redirectURL);
%>