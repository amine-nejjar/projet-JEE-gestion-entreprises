<%@page import="org.projet.db.TacheDB"%>
<%
    
   String vaca=request.getParameter("tache");
   String str=vaca.substring(0,5);
   if(str.equals("refus")){
        String vacid=vaca.substring(5,vaca.length());
        int id=Integer.parseInt(vacid);
        TacheDB.changestate(id, "non achev�e");
}
    else{
        String vacid=vaca.substring(5,vaca.length());
        int id=Integer.parseInt(vacid);
        TacheDB.changestate(id,"achev�e");

    }
   String redirectURL = "AfficherTaches.jsp";
   response.sendRedirect(redirectURL);
%>