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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import org.projet.beans.Notification;

/**
 *
 * @author HP
 */
public class NotificationDB {
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
    public static int inserer(Notification n)
    {
        int status=0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter htf = DateTimeFormatter.ofPattern("hh:mm"); 
        LocalDateTime now = LocalDateTime.now();  
        
        
        try{
            try (Connection cnx = NotificationDB.getConnection()) {
                PreparedStatement ps;
                ps = cnx.prepareStatement("insert into NOTIFICATION (ID_SOURCE, ID_DESTINATION, MESSAGE, DT_NOTIFICATION, HR_NOTIFICATION) values(?,?,?,?,?)");
                ps.setString(1, n.getID_SOURCE());
                ps.setString(2, n.getID_DESTINATION());
                ps.setString(3, n.getMESSAGE());
                ps.setString(4,dtf.format(now));
                ps.setString(5,htf.format(now));
                
                status=ps.executeUpdate();
            }
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return status;
    }
    public static ArrayList<Notification> afficher(String id_dest)
    {
        ArrayList<Notification> notification= new ArrayList<>();
        
        try{
            Connection cnx=VacanceDB.getConnection();
            Statement st=cnx.createStatement();
            String requete="select * from NOTIFICATION where ID_DESTINATION like '"+id_dest+"'";
            try (ResultSet rs = st.executeQuery(requete)) {
                while(rs.next())
                {
                    Notification notif=new Notification();
                    notif.setID(rs.getInt("ID"));
                    notif.setID_SOURCE(rs.getString("ID_SOURCE"));
                    notif.setID_DESTINATION(rs.getString("ID_DESTINATION"));
                    notif.setMESSAGE(rs.getString("MESSAGE"));
                    notif.setDT_NOTIFICATION(rs.getString("DT_NOTIFICATION")); 
                    notif.setHR_NOTIFICATION(rs.getString("HR_NOTIFICATION")); 

                    notification.add(notif);   
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return notification;
    }
        public static ArrayList<Notification> afficher(String id_dest,String chef)
    {
        ArrayList<Notification> notification= new ArrayList<>();
        
        try{
            Connection cnx=VacanceDB.getConnection();
            Statement st=cnx.createStatement();
            
            String requete="select * from NOTIFICATION where ID_DESTINATION like '"+id_dest+"' or ( ID_DESTINATION like '*' and ID_SOURCE like '"+chef+"') ";
            try (ResultSet rs = st.executeQuery(requete)) {
                while(rs.next())
                {
                    Notification notif=new Notification();
                    notif.setID(rs.getInt("ID"));
                    notif.setID_SOURCE(rs.getString("ID_SOURCE"));
                    notif.setID_DESTINATION(rs.getString("ID_DESTINATION"));
                    notif.setMESSAGE(rs.getString("MESSAGE"));
                    notif.setDT_NOTIFICATION(rs.getString("DT_NOTIFICATION")); 
                    notif.setHR_NOTIFICATION(rs.getString("HR_NOTIFICATION")); 

                    notification.add(notif);   
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return notification;
    }
    public static String duree(String date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter htf = DateTimeFormatter.ofPattern("hh:mm"); 
        LocalDateTime now = LocalDateTime.now();
        LocalDate dat = LocalDate.parse(date);
        LocalDate datt = LocalDate.parse(dtf.format(now));
        long p = ChronoUnit.DAYS.between(dat,datt);
        if(p==0){
            return "aujourdhui";
        }
        else if(p<7){
            return p+"jours";
        }
        else{
            long cal=p%7;int sem=(int) cal;
            return sem + "sem";
        }
        
    }
    
}
