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
import org.projet.beans.Personne;

/**
 *
 * @author HP
 */
public class LoginDB {
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
        public Personne checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
            Personne personne;
        try (Connection conn = LoginDB.getConnection()) {
            String sql1 = "SELECT * FROM CHEF_PROJET WHERE EMAIL = ? and PASSWORD = ?";
            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setString(1, email);
            statement1.setString(2, password);
            ResultSet result1 = statement1.executeQuery();
            personne = null;
            if (result1.next()) {
                personne = new Personne();
                personne.setNOM(result1.getString("NOM"));
                personne.setPRENOM(result1.getString("PRENOM"));
                personne.setCIN(result1.getString("CIN"));
                personne.setTELEPHONE(result1.getString("NOM"));
                personne.setADRESSE(result1.getString("ADRESSE"));
                personne.setEMAIL(email);
                personne.setSTATU("CHEF_PROJET");
            }
            else{
            String sql2 = "SELECT * FROM EMPLOYE WHERE EMAIL = ? and PASSWORD = ?";
            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, email);
            statement2.setString(2, password);
            ResultSet result2 = statement2.executeQuery();
            if (result2.next()) {
                personne = new Personne();
                personne.setNOM(result2.getString("NOM"));
                personne.setPRENOM(result2.getString("PRENOM"));
                personne.setCIN(result2.getString("CIN"));
                personne.setTELEPHONE(result2.getString("NOM"));
                personne.setADRESSE(result2.getString("ADRESSE"));
                personne.setEMAIL(email);
                personne.setCHANGED(result2.getInt("CHANGED"));
                personne.setSTATU("EMPLOYE");
            }else{
                String sql3 = "SELECT * FROM CEO WHERE EMAIL = ? and PASSWORD = ?";
                PreparedStatement statement3 = conn.prepareStatement(sql3);
                statement3.setString(1, email);
                statement3.setString(2, password);
                ResultSet result3 = statement3.executeQuery();
                if (result3.next()) {
                    personne = new Personne();
                    personne.setNOM(result3.getString("NOM"));
                    personne.setPRENOM(result3.getString("PRENOM"));
                    personne.setCIN(result3.getString("CIN"));
                    personne.setTELEPHONE(result3.getString("NOM"));
                    personne.setADRESSE(result3.getString("ADDRESS"));
                    personne.setEMAIL(email);
                    personne.setSTATU("CEO");
                }else{
                    String sql4 = "SELECT * FROM RH WHERE EMAIL = ? and PASSWORD = ?";
                    PreparedStatement statement4 = conn.prepareStatement(sql4);
                    statement4.setString(1, email);
                    statement4.setString(2, password);
                    ResultSet result4 = statement4.executeQuery();
                    if (result4.next()) {
                        personne = new Personne();
                        personne.setNOM(result4.getString("NOM"));
                        personne.setPRENOM(result4.getString("PRENOM"));
                        personne.setCIN(result4.getString("CIN"));
                        personne.setTELEPHONE(result4.getString("NOM"));
                        personne.setADRESSE(result4.getString("ADRESSE"));
                        personne.setEMAIL(email);
                        personne.setSTATU("RH");}
                }
              }
            }
        }
 
        return personne;
    }
       
}
