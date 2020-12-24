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
import org.projet.beans.TacheBean;
import org.projet.other.tools;

/**
 *
 * @author Test
 */
public class TacheDB {
    
    
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
    
    public static int inserer(TacheBean task)
    {
        int status=0;
        
        try{
            try (Connection cnx = TacheDB.getConnection()) {
                PreparedStatement ps;
                ps = cnx.prepareStatement("insert into tache (ID_PROJET, ID_EMP, DESCRIPTION, STATUT, DT_LIVRAISON, DESIGNATION, DT_CREATION) values(?,?,?,?,?,?,?)");
                ps.setString(1, Integer.toString(task.getIdprojet()));
                ps.setString(2, task.getIdemp());
                ps.setString(3, task.getDescription());
                ps.setString(4, task.getStatut());
                ps.setString(5, task.getDtlivraison());
                ps.setString(6, task.getDesignation());
                ps.setString(7, task.getDtcreation());
                
                status=ps.executeUpdate();
            }
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return status;
    }
    
    public static TacheBean select(int id_tache)
    {
        TacheBean task= new TacheBean();
        
        try{
            Connection cnx=TacheDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select * from tache where id_tache="+id_tache+" ");
            
            if(rs.next())
            {
                task.setIdtache(rs.getInt("id_tache"));
                task.setIdprojet(rs.getInt("id_projet"));
                task.setIdemp(rs.getString("id_emp"));
                task.setDescription(rs.getString("description"));
                task.setDesignation(rs.getString("designation"));
                task.setStatut(rs.getString("statut"));
                task.setDtlivraison(rs.getString("dt_livraison"));
                task.setDtcreation(rs.getString("dt_creation"));
            }
           
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return task;
    }
    
    public static ArrayList<TacheBean> afficher(String cin)
    {
        ArrayList<TacheBean> taches= new ArrayList<>();
        
        
        try{
            Connection cnx=TacheDB.getConnection();
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery("select t.id_tache,t.id_projet,t.id_emp,t.designation,t.description, t.dt_livraison,t.dt_creation,t.statut,t.COMMENTAIRE from tache t, projet p where t.id_projet=p.id_projet and p.id_chef like '"+cin+"'");
            while(rs.next())
            {
                TacheBean task=new TacheBean();
                
                task.setIdtache(Integer.parseInt(rs.getString("id_tache")));
                task.setIdprojet(Integer.parseInt(rs.getString("id_projet")));
                task.setIdemp(rs.getString("id_emp"));
                task.setDescription(rs.getString("description"));
                task.setDesignation(rs.getString("designation"));
                task.setDtlivraison(rs.getString("dt_livraison"));
                task.setDtcreation(rs.getString("dt_creation"));
                task.setStatut(rs.getString("statut"));
                task.setCommentaire(rs.getString("COMMENTAIRE"));
                
                taches.add(task);   
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return taches;
    }
    
    public static int modifier(TacheBean task)
    {
        
        
        try{
            Connection cnx=TacheDB.getConnection();
            PreparedStatement ps;
            
            ps= cnx.prepareStatement("update tache set designation=? ,description=?, statut=? ,dt_livraison=? where id_tache=?");
            ps.setString(1,task.getDesignation());
            ps.setString(2,task.getDescription());
            ps.setString(3,task.getStatut());
            ps.setString(4,task.getDtlivraison());
            ps.setInt(5,task.getIdtache());
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    public static int changestate(int id,String commentaire)
    {
        
        
        try{
            Connection cnx=TacheDB.getConnection();
            PreparedStatement ps;
            
            ps= cnx.prepareStatement("update tache set statut=? where ID_TACHE=?");   
            ps.setString(1,commentaire);
            ps.setInt(2,id);
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    public static int valider(int id,String commentaire)
    {
        
        
        try{
            Connection cnx=TacheDB.getConnection();
            PreparedStatement ps;
            
            ps= cnx.prepareStatement("update tache set statut='Attente de validation',COMMENTAIRE=? where ID_TACHE=?");   
            ps.setString(1,commentaire);
            ps.setInt(2,id);
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    
        
    public static ArrayList<TacheBean> tacheDe(String cin)
    {
        ArrayList<TacheBean> taches= new ArrayList<>();
        
        
        try{
            Connection cnx=TacheDB.getConnection();
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery("select * from tache where id_emp like '"+cin+"'");
            while(rs.next())
            {
                TacheBean task=new TacheBean();
                
                task.setIdtache(Integer.parseInt(rs.getString("id_tache")));
                task.setIdprojet(Integer.parseInt(rs.getString("id_projet")));
                task.setIdemp(rs.getString("id_emp"));
                task.setDescription(rs.getString("description"));
                task.setDesignation(rs.getString("designation"));
                task.setDtlivraison(rs.getString("dt_livraison"));
                task.setDtcreation(rs.getString("dt_creation"));
                task.setStatut(rs.getString("statut"));
                
                taches.add(task);   
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return taches;
    }
    
    public static String getTacheColor(int id) 
    {
        TacheBean task= new TacheBean();
        String color="";
        String orange="#FEBE5D";
        String rouge="#F97272";
        double days=0,rest=0;
        
        double pourcentage=100;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        LocalDateTime now = LocalDateTime.now();
        
        try{
            Connection cnx=TacheDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select * from tache where id_tache="+id+" ");
            
            if(rs.next())
            {
                task.setStatut(rs.getString("statut"));
                task.setDtlivraison(rs.getString("dt_livraison"));
                task.setDtcreation(rs.getString("dt_creation"));
                
                if(task.getStatut().equals("Pas encore"))
                {
                    
                    days=tools.getDiffDays(task.getDtlivraison(),task.getDtcreation());
                    rest=tools.getDiffDays(task.getDtlivraison(),dtf.format(now)); 

                    if(rest==days)
                    {
                        pourcentage=0;
                    }else{
                        
                        pourcentage=(rest/days)*100;
                    }

                    if(pourcentage<10)
                    {
                        //add notification if it wasn't before
                        return rouge;
                    }else if(pourcentage<30)
                    {
                        //add notification if it wasn't before
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
}
