
<aside>
    
      <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered"><a href="profile.html"><img src="img/ui-sam.jpg" class="img-circle" width="80"></a></p>
          <h5 class="centered"><% String nom =(String) session.getAttribute("NOM");
                                  String prenom =(String) session.getAttribute("PRENOM");
                                  out.print(prenom+" "+nom);
                                %></h5>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Equipes</span>
              </a>
            <ul class="sub">
              <li><a href="AfficherEquipes.jsp">Afficher les equipes</a></li>
              
            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Projets</span>
              </a>
            <ul class="sub">
              <li><a href="AfficherProjets.jsp">Afficher les projets</a></li>
              <li><a href="AfficherAvancement.jsp">L'avancement des projets</a></li>
            </ul>
          </li>
            <li class="sub-menu">
                <a href="javascript:;">
                  <i class="fa fa-calendar"></i>
                  <span>Performances</span>
                  </a>
                <ul class="sub">
                  <li><a href="AfficherPerformances.jsp">Par employes</a></li>
                  <li><a href="AfficherPerformances.jsp">Par equipe</a></li>
                </ul>
          </li>
            <li class="sub-menu">
                <a href="javascript:;">
                  <i class="fa fa-calendar"></i>
                  <span>Reclamations</span>
                  </a>
                <ul class="sub">
                  <li><a href="AfficherReclamations.jsp">Les reclamations</a></li>
                  <li><a href="AfficherReclamationsTraitees.jsp">Les reclamations Traitées</a></li>
                </ul>
          </li>
          

        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
