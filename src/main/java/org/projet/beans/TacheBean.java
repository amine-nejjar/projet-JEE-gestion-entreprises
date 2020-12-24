/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.beans;

/**
 *
 * @author Test
 */
public class TacheBean {
    private int idtache;
    private int idprojet;
    private String idemp;
    private String description;
    private String statut;
    private String dtcreation;
    private String dtlivraison;
    private String Designation;
    private String commentaire;

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public TacheBean() {
    }

    public int getIdtache() {
        return idtache;
    }

    public int getIdprojet() {
        return idprojet;
    }

    public String getIdemp() {
        return idemp;
    }

    public String getDescription() {
        return description;
    }

    public String getStatut() {
        return statut;
    }

    public String getDtcreation() {
        return dtcreation;
    }

    public String getDtlivraison() {
        return dtlivraison;
    }

    public void setIdtache(int idtache) {
        this.idtache = idtache;
    }

    public void setIdprojet(int idprojet) {
        this.idprojet = idprojet;
    }

    public void setIdemp(String idemp) {
        this.idemp = idemp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setDtcreation(String dtcreation) {
        this.dtcreation = dtcreation;
    }

    public void setDtlivraison(String dtlivraison) {
        this.dtlivraison = dtlivraison;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }
    
    
    
}
