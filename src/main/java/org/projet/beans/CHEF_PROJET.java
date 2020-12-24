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
public class CHEF_PROJET extends Personne{

    public CHEF_PROJET(String CIN, String NOM, String PRENOM, String DT_NAISSANCE, String TELEPHONE, String EMAIL, String ADRESSE, String STATU, String PASSWORD) {
        super(CIN, NOM, PRENOM, DT_NAISSANCE, TELEPHONE, EMAIL, ADRESSE, STATU, PASSWORD);
    }

    public CHEF_PROJET() {
    }
    
}
