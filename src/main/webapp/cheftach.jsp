<%@page import="org.projet.db.TacheDB"%>
<%
    
   String vaca=request.getParameter("tache");
   String str=vaca.substring(0,5);
   if(str.equals("refus")){
        String vacid=vaca.substring(5,vaca.length());
        int id=Integer.parseInt(vacid);
        TacheDB.changestate(id, "non achevée");
}
    else{
        String vacid=vaca.substring(5,vaca.length());
        int id=Integer.parseInt(vacid);
        TacheDB.changestate(id,"achevée");

    }
   String redirectURL = "AfficherTaches.jsp";
   response.sendRedirect(redirectURL);
%>