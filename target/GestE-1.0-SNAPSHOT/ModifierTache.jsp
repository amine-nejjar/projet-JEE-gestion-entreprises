<%@page import="java.util.ArrayList"%>
<%@page import="org.projet.db.TacheDB"%>
<%@page import="org.projet.beans.TacheBean"%>
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
 <%@include file="SidebarChefProjet.jsp" %>
    <!--sidebar end-->
    
    <!--main content start-->
    <section id="main-content">
      <section class="wrapper">
        <div class="row">
            <%
                int id=Integer.parseInt(request.getParameter("id_tache"));
                TacheBean task=TacheDB.select(id);
            %>
            <center>
                <h1>Modifier une tache: </h1></br>
                <form method="post" action="ModifierTache">
                    <table border="1" style="width: 500.0pt; border-collapse: collapse; border: medium none">
                        <input type="hidden" name="m_id_tache" value="<%=id%>"/>
                        <tr>
                            <td><strong>La designation</strong></td>
                            <td><input type="text" value="<%=task.getDesignation()%>" name="m_designation"/></td>
                        </tr>
                        <tr>
                            <td><strong>La description</strong></td>
                            <td><input type="text" value="<%=task.getDescription()%>" name="m_description"/></td>
                        </tr>
                        <tr>
                            <td><strong>la date de livraison</strong></td>
                            <td><input type="date" value="<%=task.getDtlivraison()%>" name="m_dtLivraison"/></td>
                        </tr>
                        <tr>
                            <td><strong>Le statut</strong></td>
                            <td>
                                <select name="m_statut" >
                                    <option value="Pas encore">Pas encore</option> 
                                    <option value="Termine" selected>Terminé</option>
                              </select>
                            </td>
                        </tr>
                        <tr><td colspan="2"><input type="submit" value="Valider" name="m_valider"/></td></tr>
                    </table>
                </form>
            </center>
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