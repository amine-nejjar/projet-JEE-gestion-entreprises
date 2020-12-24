/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.db;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.projet.beans.ReclamationBean;

/**
 *
 * @author Test
 */
public class ReclamationDB {
    public static Connection getConnection(){
        
        Connection cnx=null;
    
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cnx=DriverManager.getConnection("jdbc:derby://localhost:1527/entreprise","root","root");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
        return cnx;
    }
     public static int inserer(ReclamationBean r)
    {
        int status=0;
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            PreparedStatement ps; 
            ps = cnx.prepareStatement("insert into reclamation (objet, description, id_src, reponse, id_dest) values(?,?,?,?,?)");
            ps.setString(1, r.getObjet());
            ps.setString(2, r.getDescription());
            ps.setString(3, r.getIdsrc());
            ps.setString(4, r.getReponse());
            
            if(r.getIddest().equals("CEO")){
                ps.setString(5,"CEO");
            }else{
                ps.setString(5,EmployeDB.getIdChef(r.getIdsrc()));
            }
            status=ps.executeUpdate();
            cnx.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return status;
    }
    
    public static ArrayList<ReclamationBean> afficherMesReclamations(String cin, int done)
    {
        ArrayList<ReclamationBean> reclamations= new ArrayList<>();
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            Statement st=cnx.createStatement();
            ResultSet rs;
            
            if(done==0){
                rs=st.executeQuery("select * from reclamation where id_src like '"+cin+"' and reponse like 'Pas de reponse' ");
            }else{
                rs=st.executeQuery("select * from reclamation where id_src like '"+cin+"' and reponse not like 'Pas de reponse' ");
            }
            while(rs.next())
            {
                ReclamationBean rec=new ReclamationBean();
                rec.setId_rec(rs.getInt("id_rec"));
                rec.setObjet(rs.getString("objet"));
                rec.setDescription(rs.getString("description"));
                rec.setIdsrc(rs.getString("id_src"));
                rec.setIddest(rs.getString("id_dest"));
                rec.setReponse(rs.getString("reponse"));
                reclamations.add(rec);   
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return reclamations;
    }
    
    public static ArrayList<ReclamationBean> afficherReclamations(String cin,int done)
    {
        ArrayList<ReclamationBean> reclamations= new ArrayList<>();
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            Statement st=cnx.createStatement();
            ResultSet rs;
            
            if(done==0){
                rs=st.executeQuery("select * from reclamation where id_dest like '"+cin+"' and reponse like 'Pas de reponse'");
            }else{
                rs=st.executeQuery("select * from reclamation where id_dest like '"+cin+"' and reponse not like 'Pas de reponse'");
            }
            
            while(rs.next())
            {
                ReclamationBean rec=new ReclamationBean();
                rec.setId_rec(rs.getInt("id_rec"));
                rec.setObjet(rs.getString("objet"));
                rec.setDescription(rs.getString("description"));
                rec.setIdsrc(rs.getString("id_src"));
                rec.setIddest(rs.getString("id_dest"));
                rec.setReponse(rs.getString("reponse"));
                reclamations.add(rec);   
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return reclamations;
    }
    
    public static ArrayList<ReclamationBean> afficher()
    {
        ArrayList<ReclamationBean> reclamations= new ArrayList<>();
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery("select * from reclamation");
            while(rs.next())
            {
                ReclamationBean rec=new ReclamationBean();
                rec.setId_rec(rs.getInt("id_rec"));
                rec.setObjet(rs.getString("objet"));
                rec.setDescription(rs.getString("description"));
                rec.setIdsrc(rs.getString("id_src"));
                rec.setReponse(rs.getString("reponse"));
                reclamations.add(rec);   
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return reclamations;
    }
    
    public static ReclamationBean select(int id_rec)
    {
        ReclamationBean rec= new ReclamationBean();
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select * from reclamation where id_rec="+id_rec+" ");
            
            if(rs.next())
            {
                rec.setId_rec(rs.getInt("id_rec"));
                rec.setObjet(rs.getString("objet"));
                rec.setDescription(rs.getString("description"));
                rec.setIdsrc(rs.getString("id_src"));
                rec.setReponse(rs.getString("reponse"));  
            }
           
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return rec;
    }
    
    public static int modifier(ReclamationBean rec)
    {
        
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            PreparedStatement ps;
            
            if(!rec.getObjet().equals("") && !rec.getDescription().equals("")){
                ps= cnx.prepareStatement("update reclamation set objet=? ,description=? where id_rec=?");
                ps.setString(1,rec.getObjet());
                ps.setString(2,rec.getDescription());
                ps.setInt(3,rec.getId_rec());
                
                ps.executeUpdate();
                ps.close();
            }else if(!rec.getObjet().equals("")){
                ps = cnx.prepareStatement("update reclamation set objet=? where id_rec=?");
                ps.setString(1,rec.getObjet());
                ps.setInt(2,rec.getId_rec());
                
                ps.executeUpdate();
                ps.close();
            }else if(!rec.getDescription().equals("")){
                ps = cnx.prepareStatement("update reclamation set description=? where id_rec=?");
                ps.setString(1,rec.getDescription());
                ps.setInt(2,rec.getId_rec());
                
                ps.executeUpdate();
                ps.close();
            }
      
           
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    
     public static int repondre(int id, String reponse)
    {
        
        
        try{
            Connection cnx=ReclamationDB.getConnection();
            PreparedStatement ps;
            
            
            ps= cnx.prepareStatement("update reclamation set reponse=? where id_rec=?");
            ps.setString(1,reponse);
            ps.setString(2, Integer.toString(id));
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
}
