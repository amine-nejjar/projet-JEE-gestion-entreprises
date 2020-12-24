
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
              <span>Projets</span>
              </a>
            <ul class="sub">
              <li><a href="AjouterProjet.jsp">Ajouter un projet</a></li>
              <li><a href="AfficherAvancement.jsp">Suivre l'avancement</a></li>
              <li><a href="AfficherProjets.jsp">Afficher les projets</a></li>
            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Taches</span>
              </a>
            <ul class="sub">
              <li><a href="AjouterTache.jsp">Ajouter une tache</a></li>
              <li><a href="AfficherTaches.jsp">Afficher les taches</a></li>
            </ul>
          </li>
            <li class="sub-menu">
                <a href="javascript:;">
                  <i class="fa fa-calendar"></i>
                  <span>Reunions</span>
                  </a>
                <ul class="sub">
                  <li><a href="ProgrammerReunion.jsp">Programmer une reunion</a></li>
                  <li><a href="AfficherReunions.jsp">Afficher les reunions</a></li>
                </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Reclamations</span>
              </a>
            <ul class="sub">
              <li><a href="AjouterReclamation.jsp">Faire une reclamation</a></li>
              <li><a href="AfficherReclamations.jsp?rec=1">Afficher mes reclamations</a></li>
              <li><a href="AfficherReclamations.jsp?rec=2">Répondre aux reclamations</a></li>
              <li><a href="AfficherReclamationsTraitees.jsp?rec=1">Mes reclamations Traitées</a></li>
              <li><a href="AfficherReclamationsTraitees.jsp?rec=2">Les reclamations Traitées</a></li>
            </ul>
          </li>

        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
