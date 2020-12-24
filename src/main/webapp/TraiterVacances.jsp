<%@page import="java.util.ArrayList"%>
<%@page import="org.projet.db.VacanceDB"%>
<%@page import="org.projet.db.EmployeDB"%>
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
        <%@include file="SidebarRH.jsp" %>

    <!--sidebar end-->
    
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
          <div class="row mt">
          <div class="col-md-12">
            <div class="content-panel">
              <table class="table table-striped table-advance table-hover">
                <% ArrayList<Vacance> liste = VacanceDB.afficher("*"); %>
                <h4><i class="fa fa-angle-right"></i> Traiter les demandes de vacances</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i> Employer</th>
                    <th><i class="fa fa-bullhorn"></i> Raison</th>
                    <th class="hidden-phone"><i class="fa fa-question-circle"></i> Date debut</th>
                    <th><i class="fa fa-bookmark"></i> Date fin</th>
                    <th><i class=" fa fa-edit"></i> Etat</th>
                    <th><i class="fa fa-edit"></i> Décision</th>
                  </tr>
                </thead>
                <tbody>
                   <% for(Vacance v : liste){ %>
                   <tr>                   
                    <td class="hidden-phone"><% out.print(EmployeDB.getNameByCin(v.getId_emp())); %></td>
                    <td><% out.print(v.getDesignation()); %></td>
                    <td class="hidden-phone"><% out.print(v.getDate_debut()); %></td>
                    <td><% out.print(v.getDate_fin()); %></td>
                    <% String st=v.getStatu();%>
                    <% if(st.equals("demande non traitée")){ %>
                    <td><span class="label label-warning label-mini">
                    <%}else if(st.equals("refusée")){%>
                    <td><span class="label label-danger label-mini">
                    <%}else{%>
                    <td><span class="label label-success label-mini"><%}%>
                            
                    <% out.print(st); %></span></td>
                   
                    <td>
                        <% if(st.equals("demande non traitée")){ %>
                        <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal2">valider</button>
                        <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                  <h4 class="modal-title" id="myModalLabel">Affectation des vacances</h4>
                                </div>
                                <div class="modal-body">
                                  Etes vous sur de valider ces vacances ? cette acction est irrevesible !
                                </div>
                                <div class="modal-footer"> 
                                  <a href="rhvacance.jsp?vacance=<%="accep"+v.getId()%>"><button type="button" class="btn btn-success" >Valider la demande</button></a>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                </div>
                              </div>
                            </div>
                     </div>
                        
                        
                        
                        
                        <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#myModal1">refuser</button>
                        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                  <h4 class="modal-title" id="myModalLabel">Affectation des vacances</h4>
                                </div>
                                <div class="modal-body">
                                  Etes vous sur d'annuler ces vacances ? ! Cette action est irreversible !
                                </div>
                                <div class="modal-footer">
                                  <a href="rhvacance.jsp?vacance=<%="refus"+v.getId()%>"><button type="button" class="btn btn-danger" >Refuser la demande</button></a>
                                  <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                                </div>
                              </div>
                            </div>
                     </div>
                                        <% } %>

                    </td>
                  </tr>
                  <% } %>
                </tbody>
              </table>
        <!-- /row -->
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
