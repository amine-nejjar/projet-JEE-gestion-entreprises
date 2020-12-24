/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.projet.beans.Employe;

public class ChefProjetDB {
    
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
    
    
    public static String getNameByCin(String cin)
    {
        String name="";
        try{
            Connection cnx=ChefProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select nom, prenom from chef_projet where cin like '"+cin+"'");
            
            if(rs.next())
            {
                name=rs.getString("nom")+" "+rs.getString("prenom");
            }
           
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return name;
    }
    
     public static ArrayList<String> getAllCP(){
        ArrayList<String> chefs= new ArrayList<String>();
        try{
            Connection cnx=ChefProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select cin from chef_projet");
            
            while(rs.next())
            {
                chefs.add(rs.getString("cin"));
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return chefs;
    }
    
    public static ArrayList<Employe> getTeam(String cin){
        ArrayList<Employe> emps= new ArrayList<Employe>();    
        
        try{
            Connection cnx=ChefProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select DISTINCT cin, nom, prenom from employe where id_chef like '"+cin+"'");
           
            while(rs.next())
            {
                Employe emp= new Employe();
                emp.setCIN(rs.getString("CIN"));
                emp.setNOM(rs.getString("NOM"));
                emp.setPRENOM(rs.getString("PRENOM"));
                //emp.setDT_NAISSANCE(rs.getString("DT_NAISSANCE"));
                //emp.setDT_RECRUTEMENT(rs.getString("DT_RECRUTEMENT"));
                //emp.setSALAIRE(Double.parseDouble(rs.getString("SALAIRE")));
                //emp.setTELEPHONE(rs.getString("TELEPHONE"));
                //emp.setEMAIL(rs.getString("EMAIL"));
                //emp.setADRESSE(rs.getString("ADRESSE"));
                
                emps.add(emp);
            }
            rs.close();
        }catch(SQLException e){
            System.out.print(e);
        }
        return emps;
    }
}
