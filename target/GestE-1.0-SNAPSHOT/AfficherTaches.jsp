<%@page import="org.projet.db.ProjetDB"%>
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
    <% if(session.getAttribute("STATUT")=="CHEF_PROJET"){%>
        <%@include file="SidebarChefProjet.jsp" %>
    <%}else if(session.getAttribute("STATUT")=="EMPLOYE"){%>
       <%@include file="SidebarEmploye.jsp" %>
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
                    ArrayList<TacheBean> lisst;
                    String cin=(String)session.getAttribute("CIN");
                    String stat=(String) session.getAttribute("STATUT");
                    if(stat.equals("EMPLOYE"))
                    {
                        lisst=TacheDB.tacheDe(cin);
                    }else
                    {
                        lisst=TacheDB.afficher(cin);
                    }
                %>
                <h4><i class="fa fa-angle-right"></i> Les Taches</h4>
                <hr>
                <thead>
                  <tr>
                    <th><i class="fa fa-bullhorn"></i> La designation du projet</th>
                    <%if(session.getAttribute("STATUT")=="CHEF_PROJET"){%><th class="hidden-phone"><i class="fa fa-question-circle"></i> L'id de l'employe</th><%}%>
                    <th><i class="fa fa-bookmark"></i> La designation de la tache</th>
                    <th><i class="fa fa-bookmark"></i> La description</th>
                    <th><i class="fa fa-bookmark"></i> La date de creation</th>
                    <th><i class="fa fa-bookmark"></i> La date de livraison</th>
                    <th><i class=" fa fa-edit"></i> Status</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                   <% for(TacheBean task: lisst){ %>
                   <tr bgcolor=<% out.println(TacheDB.getTacheColor(task.getIdtache())); %>> 
                    <td><% out.print(ProjetDB.getNameById(task.getIdprojet())); %></td>
                    <%if(session.getAttribute("STATUT")=="CHEF_PROJET"){%><td class="hidden-phone"><% out.print(task.getIdemp()); %></td><%}%>
                    <td><% out.print(task.getDesignation()); %></td>
                    <td><% out.print(task.getDescription()); %></td>
                    <td><% out.print(task.getDtcreation()); %></td>
                    <td><% out.print(task.getDtlivraison()); %></td>
                   
                    <td>
                        <%if(task.getStatut().equals("non achevée")){ %>
                        <span class="label label-danger label-mini">
                         <% }else if(task.getStatut().equals("Attente de validation")){%>
                         <span class="label label-warning label-mini">
                        <%}else{%>
                         <span class="label label-success label-mini"><%}%>   
                            <% out.print(task.getStatut()); %></span>                   
                    </td>
                    
                    <%if(session.getAttribute("STATUT")=="CHEF_PROJET"){%>
                    <td>
                      <%if(task.getStatut().equals("non achevée")){%>
                      <button class="btn btn-primary btn-xs"><a href='ModifierTache.jsp?id_tache=<%=task.getIdtache()%>' style="text-decoration : none; color : #000000;"><i class="fa fa-pencil"></i></a></button>
                      <button class="btn btn-danger btn-xs"><i class="fa fa-trash-o "></i></button>
                      <%}else if(task.getStatut().equals("Attente de validation")){%>
                        <button class="btn btn-success btn-xs"><a href="cheftach.jsp?tache=<%="accep"+task.getIdtache()%>" style="text-decoration : none; color : #000000;">valider</a></button>
                        <button class="btn btn-danger btn-xs"><a href="cheftach.jsp?tache=<%="refus"+task.getIdtache()%>">à refaire</a></button>  
                        
                        <button class="btn btn-primary btn-xs" data-toggle="modal" data-target="#myModal1">commentaire</button>
                        <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                              <div class="modal-content">
                                <div class="modal-header">
                                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                  <h4 class="modal-title" id="myModalLabel">Commentaires sur la tache par l'employé</h4>
                                </div>
                                <div class="modal-body">
                                    <%out.print(task.getCommentaire());%>
                                </div>
                                <div class="modal-footer">
                                  <button type="button" class="btn btn-default" data-dismiss="modal">fermer</button>
                                </div>
                              </div>
                            </div>
                     </div>
                             <% } %>
                    </td>
                    <%}%>
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
