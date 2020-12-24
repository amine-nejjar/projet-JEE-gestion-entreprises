
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
              <span>Mes projets</span>
             </a>
               <ul class="sub">
                <li><a href="AfficherProjets.jsp">Afficher mes projets</a></li>
               </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Mes taches</span>
             </a>
               <ul class="sub">
                <li><a href="AfficherTaches.jsp">Afficher mes taches</a></li>
                <li><a href="ValiderTache.jsp">Valider une tache</a></li>
               </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-calendar"></i>
              <span>Vacances</span>
              </a>
            <ul class="sub">
              <li><a href="DemanderVacance.jsp">Demander une vacance</a></li>
              <li><a href="AfficherVacances.jsp">Afficher vos demandes</a></li>
            </ul>
          </li>
          <li class="sub-menu">
            <a href="javascript:;">
              <i class="fa fa-desktop"></i>
              <span>Reclamations</span>
             </a>
               <ul class="sub">
                <li><a href="AjouterReclamation.jsp">Faire une reclamation</a></li>
                <li><a href="AfficherReclamations.jsp">Mes réclamations</a></li>
                  <li><a href="AfficherReclamationsTraitees.jsp">Les reclamations Traitées</a></li>
               </ul>
          </li>
        </ul>
        <!-- sidebar menu end-->
      </div>
    </aside>
