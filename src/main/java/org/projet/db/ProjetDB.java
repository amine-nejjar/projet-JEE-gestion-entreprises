/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.projet.beans.ProjetBean;
import org.projet.other.tools;

public class ProjetDB {
    
    public static Connection getConnection(){
        
        Connection cnx=null;
    
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cnx=DriverManager.getConnection("jdbc:derby://localhost:1527/Entreprise","root","root");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return cnx;
    }
    
    public static int inserer(ProjetBean p)
    {
        int status=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();  
        
        
        try{
            try (Connection cnx = ProjetDB.getConnection()) {
                PreparedStatement ps;
                ps = cnx.prepareStatement("insert into projet (ID_CHEF, DESIGNATION, DUREE, STATUT, DATE_LIVRAISION, DATE_CREATION, DESCRIPTION) values(?,?,?,?,?,?,?)");
                ps.setString(1, p.getIdchef());
                ps.setString(2, p.getDesignation());
                ps.setString(3, p.getDuree());
                ps.setString(4, p.getStatut());
                ps.setString(5, p.getDtLivraison());
                 ps.setString(6,dtf.format(now));
                ps.setString(7, p.getDescription());
                status=ps.executeUpdate();
            }
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return status;
    }
    public static ArrayList<ProjetBean> afficher(String cin,String statut)
    {
        ArrayList<ProjetBean> projets= new ArrayList<>();
        
        
        try{
            Connection cnx=ProjetDB.getConnection();
            Statement st=cnx.createStatement();
            if(statut=="CHEF_PROJET"){
                try (ResultSet rs = st.executeQuery("select * from projet where id_chef like '"+cin+"'")) {
                    while(rs.next())
                    {
                        ProjetBean prj=new ProjetBean();

                        prj.setId_projet(Integer.parseInt(rs.getString("ID_PROJET")));
                        prj.setIdchef(rs.getString("ID_CHEF"));
                        prj.setDesignation(rs.getString("DESIGNATION"));
                        prj.setDtCreation(rs.getString("DATE_CREATION"));
                        prj.setDescription(rs.getString("DESCRIPTION"));
                        prj.setStatut(rs.getString("STATUT"));
                        prj.setDtLivraison(rs.getString("DATE_LIVRAISION"));
                        projets.add(prj);   
                    }
                }catch(SQLException e){
                    System.out.print(e);
                }
            }else{
                try (ResultSet rs = st.executeQuery("select DISTINCT p.id_projet, p.id_chef, p.designation, p.description, p.statut, p.date_livraision from projet p, tache t, chef_projet cp where t.id_emp like '"+cin+"' and t.id_projet=p.id_projet and p.id_chef=cp.cin")) {
                    while(rs.next())
                    {
                        ProjetBean prj=new ProjetBean();

                        prj.setId_projet(Integer.parseInt(rs.getString("ID_PROJET")));
                        prj.setIdchef(rs.getString("ID_CHEF"));
                        prj.setDesignation(rs.getString("DESIGNATION"));
                        prj.setDescription(rs.getString("DESCRIPTION"));
                        prj.setStatut(rs.getString("STATUT"));
                        prj.setDtLivraison(rs.getString("DATE_LIVRAISION"));
                        projets.add(prj);   
                    }
                }catch(SQLException e){
                    System.out.print(e);
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return projets;
    }
    
    
    public static ArrayList<ProjetBean> getAll()
    {
        ArrayList<ProjetBean> projets= new ArrayList<>();
        
        try{
            Connection cnx=ProjetDB.getConnection();
            Statement st=cnx.createStatement();
            ResultSet rs = st.executeQuery("select * from projet");
                while(rs.next()){
                    ProjetBean prj=new ProjetBean();

                    prj.setId_projet(Integer.parseInt(rs.getString("ID_PROJET")));
                    prj.setIdchef(rs.getString("ID_CHEF"));
                    prj.setDesignation(rs.getString("DESIGNATION"));
                    prj.setDtCreation(rs.getString("DATE_CREATION"));
                    prj.setDescription(rs.getString("DESCRIPTION"));
                    prj.setStatut(rs.getString("STATUT"));
                    prj.setDtLivraison(rs.getString("DATE_LIVRAISION"));
                    projets.add(prj);   
                }
            }catch(SQLException e){
                    System.out.print(e);
            }
        return projets;
    }
    public static ProjetBean select(int id_proj)
    {
        ProjetBean proj= new ProjetBean();
        
        try{
            Connection cnx=ProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select * from projet where id_projet="+id_proj+" ");
            
            if(rs.next())
            {
                proj.setId_projet(rs.getInt("id_projet"));
                proj.setIdchef(rs.getString("id_Chef"));
                proj.setDesignation(rs.getString("designation"));
                proj.setDescription(rs.getString("description"));
                proj.setDuree(rs.getString("duree"));
                proj.setDtLivraison(rs.getString("DATE_LIVRAISION"));
                proj.setStatut(rs.getString("statut"));
            }
           
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return proj;
    }
    
    public static int modifier(ProjetBean proj)
    {
        
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            PreparedStatement ps;
            
            ps= cnx.prepareStatement("update projet set DESIGNATION=? ,DESCRIPTION=?, STATUT=? ,DATE_LIVRAISION=? where ID_PROJET=?");
            ps.setString(1,proj.getDesignation());
            ps.setString(2,proj.getDescription());
            ps.setString(3,proj.getStatut());
            ps.setString(4,proj.getDtLivraison());
            ps.setInt(5,proj.getId_projet());
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    
    public static double avancement(int id_projet){
        double r=0.0;
        double termine,somme;
        try{
            Connection cnx=ProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet all=st.executeQuery("select count(*) as somme from tache where id_projet="+id_projet);
            if(all.next()){
                somme=all.getDouble("somme");
            }else{ somme=1; }
            
            all.close();
            
            ResultSet done=st.executeQuery("select count(*) as termine from tache where id_projet="+id_projet+" and statut='achevée'");
            if(done.next()){
                termine=done.getDouble("termine");
            }else{ termine=0; }
            done.close();
            
            r=(termine/somme)*100;
            r = (double) Math.round(r * 100) / 100;
        }catch(SQLException e){
            System.out.print(e);
        }
        return r;
    }
    
    public static String getNameById(int id)
    {
        String name="";
        try{
            Connection cnx=ProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select designation from projet where id_projet="+id);
            
            if(rs.next())
            {
                name=rs.getString("designation");
            }
           
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return name;
    }
    public static String getProjetColor(int id) 
    {
        ProjetBean projet= new ProjetBean();
        String color="";
        String orange="#FEBE5D";
        String rouge="#F97272";
        double days=0,rest=0;
        double avancement=ProjetDB.avancement(id);
        
        double pourcentage=100;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();
        
        try{
            Connection cnx=TacheDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select * from projet where id_projet="+id+" ");
            
            if(rs.next())
            {
                projet.setStatut(rs.getString("statut"));
                projet.setDtLivraison(rs.getString("date_livraision"));
                projet.setDtCreation(rs.getString("date_creation"));
                
                    
                if(projet.getStatut().equals("Pas encore"))
                {
                    days=tools.getDiffDays(projet.getDtLivraison(),projet.getDtCreation());
                    rest=tools.getDiffDays(projet.getDtLivraison(),dtf.format(now)); 

                    if(rest==days)
                    {
                        pourcentage=100;
                    }else{
                        
                        pourcentage=100-((rest/days)*100);
                    }
                    
                    
                    if(avancement<pourcentage && pourcentage>90)
                    {
                        return rouge;
                    }else if(avancement<pourcentage)
                    {
                        return orange;
                    }
                }
            }
        rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return color;
    }
    public static int deleteProject(int id){
        int status=0;

        try{
            try (Connection cnx = ProjetDB.getConnection()) {
                PreparedStatement ps;
                String reqQ="delete from TACHE where ID_PROJET="+id;
                String req="delete from PROJET where ID_PROJET="+id;
                ps = cnx.prepareStatement(reqQ);
                status=ps.executeUpdate();
                ps = cnx.prepareStatement(req);
                status=ps.executeUpdate();
            }
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return status;
    }
}
