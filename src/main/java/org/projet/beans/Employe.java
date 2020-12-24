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
public class Employe extends Personne{
    private String CIN;
    private String NOM;
    private String PRENOM;
    private String DT_NAISSANCE;
    private String DT_RECRUTEMENT;
    private double SALAIRE;
    private int JR_VACANCES;
    private String TELEPHONE;
    private String EMAIL;
    private String ADRESSE;
    private String PASSWORD;
    private String ID_CHEF;

    public String getID_CHEF() {
        return ID_CHEF;
    }

    public void setID_CHEF(String ID_CHEF) {
        this.ID_CHEF = ID_CHEF;
    }

    public String getDT_RECRUTEMENT() {
        return DT_RECRUTEMENT;
    }

    public void setDT_RECRUTEMENT(String DT_RECRUTEMENT) {
        this.DT_RECRUTEMENT = DT_RECRUTEMENT;
    }

    public double getSALAIRE() {
        return SALAIRE;
    }

    public void setSALAIRE(double SALAIRE) {
        this.SALAIRE = SALAIRE;
    }

    public int getJR_VACANCES() {
        return JR_VACANCES;
    }

    public void setJR_VACANCES(int JR_VACANCES) {
        this.JR_VACANCES = JR_VACANCES;
    }

    public Employe(String DT_RECRUTEMENT, double SALAIRE, int JR_VACANCES, String CIN, String NOM, String PRENOM, String DT_NAISSANCE, String TELEPHONE, String EMAIL, String ADRESSE, String STATU, String PASSWORD,String ID_CHEF) {
        super(CIN, NOM, PRENOM, DT_NAISSANCE, TELEPHONE, EMAIL, ADRESSE, STATU, PASSWORD);
        this.DT_RECRUTEMENT = DT_RECRUTEMENT;
        this.SALAIRE = SALAIRE;
        this.JR_VACANCES = JR_VACANCES;
        this.ID_CHEF=ID_CHEF;
    }

    

    public Employe() {
    }
 
}
