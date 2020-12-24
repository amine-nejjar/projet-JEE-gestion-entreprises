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
public class Personne {
    private String CIN;
    private String NOM;
    private String PRENOM;
    private String DT_NAISSANCE;
    private String TELEPHONE;
    private String EMAIL;
    private String ADRESSE;
    private String STATU;
    private String PASSWORD;
    private int CHANGED;

    public int getCHANGED() {
        return CHANGED;
    }

    public void setCHANGED(int CHANGED) {
        this.CHANGED = CHANGED;
    }
    
    public String getCIN() {
        return CIN;
    }

    public String getSTATU() {
        return STATU;
    }

    public void setSTATU(String STATU) {
        this.STATU = STATU;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getNOM() {
        return NOM;
    }

    public void setNOM(String NOM) {
        this.NOM = NOM;
    }

    public String getPRENOM() {
        return PRENOM;
    }

    public void setPRENOM(String PRENOM) {
        this.PRENOM = PRENOM;
    }

    public String getDT_NAISSANCE() {
        return DT_NAISSANCE;
    }

    public void setDT_NAISSANCE(String DT_NAISSANCE) {
        this.DT_NAISSANCE = DT_NAISSANCE;
    }

    public String getTELEPHONE() {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE) {
        this.TELEPHONE = TELEPHONE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getADRESSE() {
        return ADRESSE;
    }

    public void setADRESSE(String ADRESSE) {
        this.ADRESSE = ADRESSE;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Personne(String CIN, String NOM, String PRENOM, String DT_NAISSANCE, String TELEPHONE, String EMAIL, String ADRESSE, String STATU, String PASSWORD) {
        this.CIN = CIN;
        this.NOM = NOM;
        this.PRENOM = PRENOM;
        this.DT_NAISSANCE = DT_NAISSANCE;
        this.TELEPHONE = TELEPHONE;
        this.EMAIL = EMAIL;
        this.ADRESSE = ADRESSE;
        this.STATU = STATU;
        this.PASSWORD = PASSWORD;
        this.CHANGED=1;
    }

    



    public Personne() {
        this.CHANGED=1;
    }
    
}
