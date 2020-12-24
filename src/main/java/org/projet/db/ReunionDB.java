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
import org.projet.beans.Reunion;

/**
 *
 * @author HP
 */
public class ReunionDB {
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
    public static int inserer(Reunion r,String chef){
        int status=0;
        try{
            try (Connection conn = ReunionDB.getConnection()) {
                PreparedStatement ps=conn.prepareStatement("insert into reunion(DT_REUNION,HR_DEBUT,HR_FIN,SALLE,ID_CHEF) values(?,?,?,?,?)");
               	ps.setString(1, r.getDate_reunion());
                ps.setString(2, r.getHr_debut());
                ps.setString(3, r.getHr_fin());
                ps.setString(4, r.getSalle());
                ps.setString(5, chef);
                status=ps.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) 
                   { System.out.print(e);}
        return status;      
        }
    public static int getlastid()
        {
        int nbr=0;
        
        try{
            Connection cnx=ReunionDB.getConnection();
            Statement st=cnx.createStatement();
            
            int flag;
            try (ResultSet rs = st.executeQuery("select * from REUNION")) {
                while(rs.next())
                {
                    flag=rs.getInt("ID_REUNION");
                    if(flag>nbr){
                        nbr=flag;
                    }
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return nbr;
    }
    public static int insererRemp(String emp,int id){
        int status=0;
        try{
            try (Connection conn = ReunionDB.getConnection()) {
                PreparedStatement ps=conn.prepareStatement("insert into REUNION_EMP(ID_REUNION,ID_EMP) values(?,?)");
               	ps.setInt(1,id);               
                ps.setString(2, emp);
                status=ps.executeUpdate();
                conn.close();
            }
        } catch (SQLException e) 
                   { System.out.print(e);}
        return status;      
        }
        public static ArrayList<Reunion> afficher()
    {
        ArrayList<Reunion> reunion= new ArrayList<>();
        
        try{
            Connection cnx=VacanceDB.getConnection();
            Statement st=cnx.createStatement();
            try (ResultSet rs = st.executeQuery("select * from REUNION")) {
                while(rs.next())
                {
                    Reunion r=new Reunion();
                    r.setDate_reunion(rs.getString("DT_REUNION"));
                    r.setSalle(rs.getString("SALLE"));
                    r.setHr_debut(rs.getString("HR_DEBUT"));
                    r.setHr_fin(rs.getString("HR_FIN"));
                    reunion.add(r);   
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return reunion;
    }
}
