<%@page import="java.util.ArrayList"%>
<%@page import="org.projet.db.ReclamationDB"%>
<%@page import="org.projet.beans.ReclamationBean"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Dashio - Bootstrap Admin Template</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="css/zabuto_calendar.css">
  <link rel="stylesheet" type="text/css" href="lib/gritter/css/jquery.gritter.css" />
  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">
  <script src="lib/chart-master/Chart.js"></script>

  <!-- =======================================================
    Template Name: Dashio
    Template URL: https://templatemag.com/dashio-bootstrap-admin-template/
    Author: TemplateMag.com
    License: https://templatemag.com/license/
  ======================================================= -->
</head>

<body>
  <section id="container">
    <!--header start-->
 <%@include file="header.jsp" %>
    <!--header end-->
    <!--sidebar start-->
    <% if(session.getAttribute("STATUT")=="CHEF_PROJET"){%>
        <%@include file="SidebarChefProjet.jsp" %>
    <%}else if(session.getAttribute("STATUT")=="EMPLOYE"){%>
       <%@include file="SidebarEmploye.jsp" %>
    <%} else if(session.getAttribute("STATUT")=="CEO"){%>
       <%@include file="SidebarCEO.jsp" %>
    <%}%>
    <!--sidebar end-->
    
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
          <div class="row mt">
          <div class="col-md-12">
            <div class="content-panel">
              <table class="table table-advance table-hover"> <!--table-striped--> 
                  <%  
                      ArrayList<ReclamationBean> list= new ArrayList<ReclamationBean>();
                      String cin;
                      int option=0;
                      if(session.getAttribute("STATUT").equals("CEO")){
                        list=ReclamationDB.afficherReclamations("CEO",1);
                      }else if(session.getAttribute("STATUT").equals("EMPLOYE")){
                        cin = (String)session.getAttribute("CIN");
                        list=ReclamationDB.afficherMesReclamations(cin,1);
                      }else{
                        cin = (String)session.getAttribute("CIN");
                        option=Integer.parseInt(request.getParameter("rec"));
                        if(option==1){
                            list=ReclamationDB.afficherMesReclamations(cin,1);
                        }else if(option==2){
                            list=ReclamationDB.afficherReclamations(cin,1);
                        }
                      }
                  %>
                <% if(session.getAttribute("STATUT").equals("Employe") || (session.getAttribute("STATUT").equals("CHEF_PROJET") && option==1 )){%>
                    <h4><i class="fa fa-angle-right"></i> Mes Reclamations</h4>
                    <%}else{%>
                    <h4><i class="fa fa-angle-right"></i> Les Reclamations</h4>
                    <%}
                %>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bookmark"></i> L'objet</th>
                    <th><i class="fa fa-bookmark"></i> La description</th>
                    <%if(session.getAttribute("STATUT").equals("EMPLOYE") || (session.getAttribute("STATUT").equals("CHEF_PROJET") && option==1 )){%>
                        <th><i class="fa fa-bookmark"></i> Le destinataire</th>
                        <th><i class="fa fa-bookmark"></i> La réponse</th>
                    <%}else{%>
                        <th><i class="fa fa-bookmark"></i> L'émetteur</th>
                        <th><i class="fa fa-bookmark"></i> Répondre</th>
                    <%}
                    %>
                </tr>
                </thead>
                <tbody>
                   <% for(ReclamationBean rec: list){ %>
                   <tr>
                    <td><% out.print(rec.getObjet()); %></td>
                    <td><% out.print(rec.getDescription()); %></td>
                    <%if(session.getAttribute("STATUT").equals("EMPLOYE") || (session.getAttribute("STATUT").equals("CHEF_PROJET") && option==1 )){%>
                        <td><% out.print(rec.getIddest()); %></td>
                    <%}else{%>
                        <td><% out.print(rec.getIdsrc()); %></td>
                    <%}
                    %>
                    <td><% out.print(rec.getReponse()); %></td>
                  </tr>
                  <% } %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </section>
    </section>
    <!--main content end-->
  </section>
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="lib/jquery/jquery.min.js"></script>

  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <script src="lib/jquery.sparkline.js"></script>
  <!--common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <script type="text/javascript" src="lib/gritter/js/jquery.gritter.js"></script>
  <script type="text/javascript" src="lib/gritter-conf.js"></script>
  <!--script for this page-->
  <script src="lib/sparkline-chart.js"></script>
  <script src="lib/zabuto_calendar.js"></script>

  <script type="application/javascript">
    $(document).ready(function() {
      $("#date-popover").popover({
        html: true,
        trigger: "manual"
      });
      $("#date-popover").hide();
      $("#date-popover").click(function(e) {
        $(this).hide();
      });

      $("#my-calendar").zabuto_calendar({
        action: function() {
          return myDateFunction(this.id, false);
        },
        action_nav: function() {
          return myNavFunction(this.id);
        },
        ajax: {
          url: "show_data.php?action=1",
          modal: true
        },
        legend: [{
            type: "text",
            label: "Special event",
            badge: "00"
          },
          {
            type: "block",
            label: "Regular event",
          }
        ]
      });
    });

    function myNavFunction(id) {
      $("#date-popover").hide();
      var nav = $("#" + id).data("navigation");
      var to = $("#" + id).data("to");
      console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
    }
  </script>
</body>

</html>
