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
              <i class="fa fa-user"></i>
              <span>Employ�s</span>
             </a>
               <ul class="sub">
                <li><a href="AjouterEmploye.jsp">Ajouter un employ�</a></li>
                <li><a href="AfficherEmployes.jsp">Afficher les employ�s</a></li>
               </ul>
          </li>
          <li class="sub-menu">
            <a href="TraiterVacances.jsp">
              <i class="fa fa-calendar"></i>
              <span>Traiter les Vacances</span>
             </a>
          </li>
        </ul>
        <!-- sidebar menu end-->
      </div>
      
    </aside>
