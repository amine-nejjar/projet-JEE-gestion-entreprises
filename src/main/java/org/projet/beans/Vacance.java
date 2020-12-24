/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.beans;

/**
 *
 * @author HP
 */
public class Vacance {
    private int id;
    private String date_debut;
    private String date_fin;
    private String designation;
    private String statu;
    private String id_emp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getId_emp() {
        return id_emp;
    }

    public void setId_emp(String id_emp) {
        this.id_emp = id_emp;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getDesignation() {
        return designation;
    }

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Vacance(String date_debut, String date_fin, String designation, String statu,String id_em,int id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.designation = designation;
        this.statu = statu;
        this.id_emp=id_em;
        this.id=id;
    }



    public Vacance() {
    }
    
}
