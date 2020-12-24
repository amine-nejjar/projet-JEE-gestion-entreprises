package org.projet.beans;

import java.util.Date;

public class ProjetBean {
    
    private int id_projet;
    private String idchef;
    private String designation;
    private String duree;
    private String description;
    private String dtLivraison;
    private String DtCreation;
    private String statut;
    
    public ProjetBean() {
    }

    public int getId_projet() {
        return id_projet;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public String getIdchef() {
        return idchef;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDuree() {
        return duree;
    }

    public String getDescription() {
        return description;
    }

    public String getDtLivraison() {
        return dtLivraison;
    }

    public String getStatut() {
        return statut;
    }

    public void setIdchef(String idchef) {
        this.idchef = idchef;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDtLivraison(String dtLivraison) {
        this.dtLivraison = dtLivraison;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getDtCreation() {
        return DtCreation;
    }

    public void setDtCreation(String DtCreation) {
        this.DtCreation = DtCreation;
    }
    
    
    
   
}

