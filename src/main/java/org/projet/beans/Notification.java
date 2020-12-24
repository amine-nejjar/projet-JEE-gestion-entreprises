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
public class Notification {
    private int ID; 
    private String ID_SOURCE; 
    private String ID_DESTINATION ;
    private String MESSAGE; 
    private String DT_NOTIFICATION; 
    private String HR_NOTIFICATION; 
    private int ISREAD; 

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getID_SOURCE() {
        return ID_SOURCE;
    }

    public void setID_SOURCE(String ID_SOURCE) {
        this.ID_SOURCE = ID_SOURCE;
    }

    public String getID_DESTINATION() {
        return ID_DESTINATION;
    }

    public void setID_DESTINATION(String ID_DESTINATION) {
        this.ID_DESTINATION = ID_DESTINATION;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getDT_NOTIFICATION() {
        return DT_NOTIFICATION;
    }

    public void setDT_NOTIFICATION(String DT_NOTIFICATION) {
        this.DT_NOTIFICATION = DT_NOTIFICATION;
    }

    public String getHR_NOTIFICATION() {
        return HR_NOTIFICATION;
    }

    public void setHR_NOTIFICATION(String HR_NOTIFICATION) {
        this.HR_NOTIFICATION = HR_NOTIFICATION;
    }

    public int getISREAD() {
        return ISREAD;
    }

    public void setISREAD(int ISREAD) {
        this.ISREAD = ISREAD;
    }

    public Notification(int ID, String ID_SOURCE, String ID_DESTINATION, String MESSAGE, String DT_NOTIFICATION, String HR_NOTIFICATION) {
        this.ID = ID;
        this.ID_SOURCE = ID_SOURCE;
        this.ID_DESTINATION = ID_DESTINATION;
        this.MESSAGE = MESSAGE;
        this.DT_NOTIFICATION = DT_NOTIFICATION;
        this.HR_NOTIFICATION = HR_NOTIFICATION;
    }

    public Notification() {
    }
    
}
