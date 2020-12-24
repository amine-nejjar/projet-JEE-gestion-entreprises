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
public class ReclamationBean {
    private int  id_rec;
    private String objet;
    private String description;
    private String idsrc;
    private String iddest;
    private String reponse;

    public ReclamationBean() {
    }

    public String getIddest() {
        return iddest;
    }

    public void setIddest(String iddest) {
        this.iddest = iddest;
    }

    
    public String getObjet() {
        return objet;
    }

    public String getDescription() {
        return description;
    }

    public String getIdsrc() {
        return idsrc;
    }

    public String getReponse() {
        return reponse;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdsrc(String id_src) {
        this.idsrc = id_src;
    }

    public void setReponse(String statut) {
        this.reponse = statut;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }
    
    
}
