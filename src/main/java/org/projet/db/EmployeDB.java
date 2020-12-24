package org.projet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.projet.beans.Employe;

public class EmployeDB {
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
    public static String getIdChef(String cin)
    {
        String id="";
        
        try{
            Connection cnx=EmployeDB.getConnection();
            Statement st=cnx.createStatement();
            try (ResultSet rs = st.executeQuery("select id_chef from employe where cin like '"+cin+"'")){
                while(rs.next())
                {
                    id=rs.getString("id_chef");
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return id;
    }
    
    public static int Ajouter(Employe emp){
        
        int status=0;
        
        try{
            try (Connection cnx = EmployeDB.getConnection()) {
                PreparedStatement ps;
                ps = cnx.prepareStatement("insert into employe (CIN, NOM, PRENOM, DT_NAISSANCE, DT_RECRUTEMENT, SALAIRE, EMAIL, PASSWORD, CHANGED, ID_CHEF) values(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, emp.getCIN());
                ps.setString(2, emp.getNOM());
                ps.setString(3, emp.getPRENOM());
                ps.setString(4, emp.getDT_NAISSANCE() );
                ps.setString(5, emp.getDT_RECRUTEMENT());
                ps.setDouble(6, emp.getSALAIRE());
                ps.setString(7, emp.getEMAIL());
                ps.setString(8, "");
                ps.setInt(9, 0);
                ps.setString(10, emp.getID_CHEF());
                
                
                status=ps.executeUpdate();
            }
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return status;
    }


    public static ArrayList<Employe> afficher(String chef)
    {
        ArrayList<Employe> employes= new ArrayList<>();
        
        
        try{
            Connection cnx=ProjetDB.getConnection();
            Statement st=cnx.createStatement();
            String req="select * from employe where ID_CHEF like '"+chef+"'";
            try (ResultSet rs = st.executeQuery(req)) {
                while(rs.next())
                {
                    Employe emp=new Employe();
                    
                    emp.setCIN(rs.getString("cin"));
                    emp.setNOM(rs.getString("nom"));
                    emp.setPRENOM(rs.getString("prenom"));
                    emp.setDT_NAISSANCE(rs.getString("dt_naissance"));
                    emp.setDT_RECRUTEMENT(rs.getString("dt_recrutement"));
                    emp.setSALAIRE(Double.parseDouble(rs.getString("salaire")));
                    emp.setJR_VACANCES(Integer.parseInt(rs.getString("jr_vacances")));
                    emp.setTELEPHONE(rs.getString("telephone"));
                    emp.setEMAIL(rs.getString("email"));
                    emp.setADRESSE(rs.getString("adresse"));
                    emp.setID_CHEF(chef);
                    
                    employes.add(emp);   
                }
            }
        }catch(SQLException e){
            System.out.print(e);
        }
        return employes;
    }
    public static int modifier(String cin, String tel, String adr, String pwd)
    {
        
        
        try{
            Connection cnx=EmployeDB.getConnection();
            PreparedStatement ps;
            
            ps= cnx.prepareStatement("update employe set telephone=? ,ADRESSE=?, PASSWORD=? ,CHANGED=? where CIN like '"+cin+"'");
            ps.setString(1,tel);
            ps.setString(2,adr);
            ps.setString(3,pwd);
            ps.setInt(4,1);
            
            ps.executeUpdate();
            ps.close();
            
        }catch(SQLException e){
            System.out.print(e);
        }
        return 1;
    }
    public static String getNameByCin(String cin)
    {
        String name="";
        try{
            Connection cnx=ChefProjetDB.getConnection();
            Statement st=cnx.createStatement();
        
            ResultSet rs=st.executeQuery("select nom, prenom from EMPLOYE where CIN like '"+cin+"'");
            
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
}
