<%@page import="org.projet.db.EmployeDB"%>
<%@page import="org.projet.beans.Employe"%>
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
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-fileupload/bootstrap-fileupload.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-datepicker/css/datepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-daterangepicker/daterangepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-timepicker/compiled/timepicker.css" />
  <link rel="stylesheet" type="text/css" href="lib/bootstrap-datetimepicker/datertimepicker.css" />
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
            <h4><i class="fa fa-angle-right"></i>Programmer une reunion</h4>
            <div class="form-panel">
              <div class=" form">
                <form class="cmxform form-horizontal style-form" id="commentForm" action="ProgrammerReunion" method="post">
                          
                  <div class="form-group">
                  <label class="control-label col-lg-2" for="date_reunion">*Date de reunion</label>
                  <div class="col-md-3 col-xs-11">
                      <div data-date-viewmode="days" data-date-format="yyyy-mm-dd" data-date="<%out.print((String) session.getAttribute("todaydate"));%>" class="input-append date dpYears">
                      <input type="text" id="date" readonly=""  size="16" value="entrer une date" class="form-control" name="date_reunion" required />
                      <span class="input-group-btn add-on">
                        <button class="btn btn-theme" type="button"><i class="fa fa-calendar"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                <div class="form-group ">
                    <label for="salle" class="control-label col-lg-2">*Salle </label>
                    <div class="col-md-3 col-xs-11">
                      <input class=" form-control" name="salle" minlength="2" type="text" required />
                    </div>
                  </div>
                <div class="form-group">
                  <label class="control-label col-lg-2" for="hr_debut">*Heure de debut</label>
                  <div class="col-md-3 col-xs-11">
                    <div class="input-group bootstrap-timepicker">
                      <input type="text" class="form-control timepicker-default" name="hr_debut" required />
                      <span class="input-group-btn">
                        <button class="btn btn-theme04" type="button"><i class="fa fa-clock-o"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-lg-2" for="hr_debut">*Heure de finition</label>
                  <div class="col-md-3 col-xs-11">
                    <div class="input-group bootstrap-timepicker">
                      <input type="text" class="form-control timepicker-default" name="hr_fin">
                      <span class="input-group-btn">
                        <button class="btn btn-theme04" type="button"><i class="fa fa-clock-o"></i></button>
                        </span>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="control-label col-lg-2" for="employes">Employes concernes</label>
                    <div class="col-md-3 col-xs-11">
                      <select name="from[]" id="search" class="form-control" size="8" multiple="multiple">
                          <%ArrayList<Employe> emps=EmployeDB.afficher((String) session.getAttribute("CIN"));%>
                            <%
                            for(Employe emp: emps){%>
                            <option value="<%=emp.getCIN()%>"><%=emp.getNOM()+" "+emp.getPRENOM()%></option>
                            <% } %>
                        </select>
                  </div>
                  <div class="col-md-3 col-xs-11">
                    <button type="button" id="search_rightAll" class="btn btn-block">Selectoinner tous</button>
                    <button type="button" id="search_rightSelected" class="btn btn-block">ajouter</button>
                    <button type="button" id="search_leftSelected" class="btn btn-block">supprimer</button>
                    <button type="button" id="search_leftAll" class="btn btn-block">supprimer tous</button>
                  </div>
                  <div class="col-md-3 col-xs-11">
                    <select name="to[]" id="search_to" class="form-control" size="8" multiple="multiple" ></select>
                </div>
                        
                </div>
                
                  <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                      <button class="btn btn-theme" type="submit">Save</button>
                      <button class="btn btn-theme04" type="reset">Reset</button>
                    </div>
                  </div>
                  <%
                      String mssg=(String) session.getAttribute("msg");
                      if(mssg!="" && mssg!=null){%>
                            <div class="alert alert-success alert-dismissable">
                              <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                              <strong>Succes!</strong> La réunion à été programmée. <a href="AfficherReunions">Consulter vos réunions</a>
                            </div>                      <%
                      }
                      session.setAttribute("msg","");
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
   
        <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

	<script type="text/javascript" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/prettify/r298/prettify.min.js"></script>
	<script type="text/javascript" src="lib/multiselect.min.js"></script>

	<script>
	    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	    (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	    m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	    
	    ga('create', 'UA-39934286-1', 'github.com');
	    ga('send', 'pageview');
	</script>

	<script type="text/javascript">
	$(document).ready(function() {
	    // make code pretty
	    window.prettyPrint && prettyPrint();

	    $('#search').multiselect({
	        search: {
	            left: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
	            right: '<input type="text" name="q" class="form-control" placeholder="Search..." />',
	        },
	        fireSearch: function(value) {
	            return value.length > 3;
	        }
	    });
	});
    </script>
  <!-- js placed at the end of the document so the pages load faster -->

<!--  <script src="lib/jquery/jquery.min.js"></script>-->

  <script src="lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <script src="lib/jquery.scrollTo.min.js"></script>
  <script src="lib/jquery.nicescroll.js" type="text/javascript"></script>
  <script src="lib/jquery.sparkline.js"></script>
<!--  common script for all pages-->
  <script src="lib/common-scripts.js"></script>
  <script type="text/javascript" src="lib/gritter/js/jquery.gritter.js"></script>
  <script type="text/javascript" src="lib/gritter-conf.js"></script>
<!--  script for this page-->
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
  <script type="text/javascript">
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
