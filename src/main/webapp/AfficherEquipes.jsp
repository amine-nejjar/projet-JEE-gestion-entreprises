<%@page import="org.projet.beans.Employe"%>
<%@page import="org.projet.db.ChefProjetDB"%>
<%@page import="org.projet.db.ProjetDB"%>
<%@page import="org.projet.beans.ProjetBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.projet.db.VacanceDB"%>
<%@page import="org.projet.beans.Vacance"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Afficher vos projet</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

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
    <%}else if(session.getAttribute("STATUT")=="CEO"){%>
       <%@include file="SidebarCEO.jsp" %>
    <%}%>
    <!--sidebar end-->
  
    
    <section id="main-content">
      <section class="wrapper">
           <%  
                    ArrayList<String> chefs=ChefProjetDB.getAllCP();
                    %>
                    <br/><br/>
                    <div class="dropdown">
                    <span>
                    <button class="dropdown-toggle " type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Choisir un chef d'equipe</button>
                        <div class="dropdown-menu ">
                          <% for(String cin: chefs){ 
                          String param="";
                          ArrayList<Employe> emps=ChefProjetDB.getTeam(cin);
                          for(Employe emp : emps){
                          param=param+"<option>"+emp.getNOM()+" "+emp.getPRENOM()+" ( "+emp.getCIN()+" )"+"</option>";}%>
                          <a class="dropdown-item" onclick="<%out.print("myFunction('"+param+"')");%>"><% out.print(ChefProjetDB.getNameByCin(cin)+" ( "+cin+" )"); %></a>
                        <%}%>
                        </div></span></div>
                        
          <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i>membres de l'equipe</h4>
            <div class="form-panel">
              <div class=" form">
                     
          <div class="form-group " id="demo"></div>
          <script>
            function myFunction(parameter) {
              document.getElementById("demo").innerHTML = "<select multiple class='form-control'>"+parameter+"</select>";
            }
        </script>         
              </div>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>
    
      </section>
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
