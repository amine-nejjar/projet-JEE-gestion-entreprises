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
public class Reunion {
    private String date_reunion;
    private String salle;
    private String hr_debut;
    private String hr_fin;

    public String getDate_reunion() {
        return date_reunion;
    }

    public void setDate_reunion(String date_reunion) {
        this.date_reunion = date_reunion;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getHr_debut() {
        return hr_debut;
    }

    public void setHr_debut(String hr_debut) {
        this.hr_debut = hr_debut;
    }

    public String getHr_fin() {
        return hr_fin;
    }

    public void setHr_fin(String hr_fin) {
        this.hr_fin = hr_fin;
    }

    public Reunion() {
    }

    public Reunion(String date_reunion, String salle, String hr_debut, String hr_fin) {
        this.date_reunion = date_reunion;
        this.salle = salle;
        this.hr_debut = hr_debut;
        this.hr_fin = hr_fin;
    }
    
}
