<%@page import="org.projet.db.ProjetDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.projet.beans.ProjetBean"%>
<%@page import="org.projet.db.EmployeDB"%>
<%@page import="org.projet.beans.Employe"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
  <title>Ajouter une tache</title>

  <!-- Favicons -->
  <link href="img/favicon.png" rel="icon">
  <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!--external css-->
  <link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <link rel="stylesheet" type="text/css" href="css/zabuto_calendar.css">
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-datepicker/css/datepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-daterangepicker/daterangepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-timepicker/compiled/timepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-datetimepicker/datertimepicker.css" />
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
 <%@include file="SidebarChefProjet.jsp" %>
    <!--sidebar end-->
    
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row mt">
          <div class="col-lg-12">
            <h4><i class="fa fa-angle-right"></i>Ajouter une tache</h4>
            <div class="form-panel">
              <div class=" form">
                <form class="cmxform form-horizontal style-form" id="commentForm" action="AjouterTache" method="post">
                    
                  <div class="form-group ">
                    <label for="designation" class="control-label col-lg-2">Projet</label>
                    <div class="col-md-3 col-xs-11">
                      <select multiple class="form-control" name="idprojet">
                            <%  String cin=(String) session.getAttribute("CIN");
                                ArrayList<ProjetBean> list2=ProjetDB.afficher(cin,(String) session.getAttribute("STATUT"));%>
                            <%
                            for(ProjetBean prj: list2){%>
                            <option value="<%=prj.getId_projet()%>"><%=prj.getDesignation()%></option>
                            <% } %>
                        </select>
                    </div>
                  </div>
                  
                 <div class="form-group ">
                    <label for="designation" class="control-label col-lg-2">Employe</label>
                    <div class="col-md-3 col-xs-11">
                      <select class="form-control" name="idemp">
                          <%ArrayList<Employe> emps=EmployeDB.afficher(cin);%>
                            <%
                            for(Employe emp: emps){%>
                            <option value="<%=emp.getCIN()%>"><%=emp.getNOM()+" "+emp.getPRENOM()%></option>
                            <% } %>
                        </select>
                    </div>
                  </div>
                    
                  <div class="form-group ">
                    <label for="designation" class="control-label col-lg-2">Designation</label>
                    <div class="col-md-3 col-xs-11">
                      <input class=" form-control" name="designation" minlength="2" type="text" required />
                    </div>
                  </div>
                    
                 <div class="form-group ">
                    <label for="description" class="control-label col-lg-2">Description</label>
                    <div class="col-md-3 col-xs-11">
                      <input class=" form-control" name="description" minlength="2" type="text" required />
                    </div>
                 </div>
                  <div class="form-group">
                  <label class="control-label col-lg-2" for="date_debut">Date de livraison</label>
                  <div class="col-md-3 col-xs-11">
                    <div data-date-viewmode="DAYS" data-date-format="yyyy-mm-dd" data-date="<%out.print((String) session.getAttribute("todaydate"));%>" class="input-append date dpYears">
                      <input type="text" readonly=""  size="16" value="entrer une date" class="form-control" name="dtLivraison">
                      <span class="input-group-btn add-on">
                        <button class="btn btn-theme" type="button"><i class="fa fa-calendar"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                  
                  <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                      <button class="btn btn-theme" type="submit">Save</button>
                      <button class="btn btn-theme04" type="reset">Reset</button>
                    </div>
                  </div>
                  
                  <%
                      String msg=(String) session.getAttribute("msgtache");
                      if(msg!="" && msg!=null){%>
                            <div class="alert alert-success alert-dismissable">
                              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                              <strong>Succes!</strong> La tache à été définie avec succes. <a href="AfficherTaches.jsp">consulter les taches<a>
                            </div>                      <%
                      }
                      session.setAttribute("msgtache","");
                      %>
                      
                </form>
              </div>
            </div>
            <!-- /form-panel -->
          </div>
          <!-- /col-lg-12 -->
        </div>
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
  <script src="lib/jquery-ui-1.9.2.custom.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap-fileupload/bootstrap-fileupload.js"></script>
  <script type="text/javascript" src="lib/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
  <script type="text/javascript" src="lib/bootstrap-daterangepicker/moment.min.js"></script>
  <script type="text/javascript" src="lib/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
  <script src="lib/advanced-form-components.js"></script>
  
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


        