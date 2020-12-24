<%@page import="org.projet.db.EmployeDB"%>
<header class="header black-bg">
<%@page import="java.util.ArrayList"%>
<%@page import="org.projet.db.NotificationDB"%>
<%@page import="org.projet.beans.Notification"%>
<%
   if (session.getAttribute("NOM")==null) {
            response.sendRedirect("login.jsp");
      }
      else{
%>
      <div class="sidebar-toggle-box">
        <div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      </div>
      <!--logo start-->
      <a href="index.jsp" class="logo"><b>COMP<span>MANAGER</span></b></a>
      <!--logo end-->
      <div class="nav notify-row" id="top_menu">
        <!--  notification start -->
        <ul class="nav top-menu">
          <!-- inbox dropdown start-->
          <!-- notification dropdown start-->
          <li id="header_notification_bar" class="dropdown">
            <%  String stt=(String) session.getAttribute("STATUT");
                ArrayList<Notification> list;
                if(stt.equals("EMPLOYE")){
                list=NotificationDB.afficher((String) session.getAttribute("CIN"),EmployeDB.getIdChef((String) session.getAttribute("CIN")));
                }
                else{
                list=NotificationDB.afficher((String) session.getAttribute("CIN"));
                }
            %>
            
                <a data-toggle="dropdown" class="dropdown-toggle" >
              <i class="fa fa-bell-o"></i>
              <%if(list.size()>0){%> 
              <span class="badge bg-warning"><% out.print(list.size()); %></span><%}%>
              </a>
            
            <ul class="dropdown-menu extended notification">
              <div class="notify-arrow notify-arrow-yellow"></div>
              <li>
                  <p class="yellow">You have <% out.print(list.size());%> new notifications</p>
              </li>
              <%if(list.size()>0){%> 
              <%for(Notification notif: list){%>
              <li>
                <a href="index.jsp#">
                  <span class="label label-warning"><i class="fa fa-bell"></i></span>
                  <%out.print(notif.getMESSAGE());%><br/>
                  <span class="small italic"><%out.print(NotificationDB.duree(notif.getDT_NOTIFICATION()));%> | <% out.print(notif.getHR_NOTIFICATION());%></span>
                  </a>
              </li>
              <%}}%>
              <li>
                <a href="index.jsp#">See all notifications</a>
              </li>
            </ul>
          </li>
          <!-- notification dropdown end -->
        </ul>
        <!--  notification end -->
      </div>
      <div class="top-menu">
        <ul class="nav pull-right top-menu">
          <li><a class="logout" href="logout">Logout</a></li>
        </ul>
      </div>
    </header>
 
<% } %>