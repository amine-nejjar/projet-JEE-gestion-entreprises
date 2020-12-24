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
import java.util.ArrayList;
import java.util.List;
import org.projet.beans.Vacance;

/**
 *
 * @author HP
 */
public class VacanceDB {
        public static Connection getConnection(){
        Connection conn=null;
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn=DriverManager.getConnection("jdbc:derby://localhost:1527/Entreprise","root","root");
        } 
        catch(ClassNotFoundException | SQLException e){
            System.out.print(e);
        }
        return conn;
    }
    public static int inserer(Vacance v){
        int status=0;
        try{
            try (Connection conn = VacanceDB.getConnection()) {
                PreparedStatement ps=conn.prepareStatement("insert into vacance(DT_DEBUT,DT_FIN,ID_EMP,DESIGNATION) values(?,?, ?, ?)");
                ps.setString(1, v.getDate_debut());
                ps.setString(2, v.getDate_fin());
                ps.setString(3, v.getId_emp());
                ps.setString(4, v.getDesignation());
                
                status=ps.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) 
                   { System.out.print(e);}
        return status;      
        }
    public static ArrayList<Vacance> afficher(String cin)
    {
        ArrayList<Vacance> vacance= new ArrayList<>();
        
        try{
            Connection cnx=VacanceDB.getConnection();
            Statement st=cnx.createStatement();
            String req;
            if(cin.equals("*")){
                req="select * from vacance";            
            }
            else{
            
            req="select * from vacance where ID_EMP like '"+ cin+"'";
            }
            try (ResultSet rs = st.executeQuery(req)) {
                while(rs.next())
                {
                    Vacance vac=new Vacance();
                    vac.setDesignation(rs.getString("DESIGNATION"));
                    vac.setDate_debut(rs.getString("DT_DEBUT"));
                    vac.setDate_fin(rs.getString("DT_FIN"));
                    vac.setId_emp(rs.getString("ID_EMP"));
                    vac.setStatu(rs.getString("STATU")); 
                    vac.setId(rs.getInt("id_vac"));
                    vacance.add(vac);   
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        
        return vacance;
    }
    public static int traiter(String statu, int id)
    {
        
        
        try{
            Connection cnx=EmployeDB.getConnection();
            PreparedStatement ps;
            String req="update VACANCE set STATU='"+statu+"' where ID_VAC="+id;
            ps= cnx.prepareStatement(req);
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    
    public static String getCinbyID(int id)
    {
        String cin="";
        try{
            Connection cnx=ChefProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select ID_EMP from VACANCE where ID_VAC="+id);
            
            if(rs.next())
            {
                cin=rs.getString("ID_EMP");
            }
           
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return cin;
    }
}
