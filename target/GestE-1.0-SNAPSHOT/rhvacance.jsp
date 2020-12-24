<%@page import="org.projet.db.NotificationDB"%>
<%@page import="org.projet.beans.Notification"%>
<%@page import="org.projet.db.VacanceDB"%>
<%
    
   String vaca=request.getParameter("vacance");
   String str=vaca.substring(0,5);
   if(str.equals("refus")){
        String vacid=vaca.substring(5,vaca.length());
        int id=Integer.parseInt(vacid);
        VacanceDB.traiter("refusée", id);
        Notification notif=new Notification();
        String notification=(String) session.getAttribute("NOM")+" || reponse de vacance";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        notif.setID_DESTINATION(VacanceDB.getCinbyID(id));
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);
}
    else{
        String vacid=vaca.substring(5,vaca.length());
        int id=Integer.parseInt(vacid);
        VacanceDB.traiter("acceptée", id);
        
        Notification notif=new Notification();
        String notification=(String) session.getAttribute("NOM")+" || reponse de vacance";
        notif.setID_SOURCE((String) session.getAttribute("CIN"));
        notif.setID_DESTINATION(VacanceDB.getCinbyID(id));
        notif.setMESSAGE(notification);
        NotificationDB.inserer(notif);
    }
        
   String redirectURL = "TraiterVacances.jsp";
   response.sendRedirect(redirectURL);
%>