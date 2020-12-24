/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.projet.other;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class tools {
    
    public static long getDiffDays(String dt1, String dt2){
        
        int an1, an2, m1, m2, j1, j2;
        
        an1=Integer.parseInt(dt1.substring(0, 4));
        an2=Integer.parseInt(dt2.substring(0, 4));
        
        m1=Integer.parseInt(dt1.substring(5,7));
        m2=Integer.parseInt(dt2.substring(5,7));
        
        j1=Integer.parseInt(dt1.substring(dt1.length()-2));
        j2=Integer.parseInt(dt2.substring(dt2.length()-2));
        
        LocalDate livraison = LocalDate.of(an1, m1, j1);
        LocalDate creation = LocalDate.of(an2, m2, j2);
       
        long days = ChronoUnit.DAYS.between(creation,livraison);
        
        return days;
    }
    
    public static int verifierDates(String dt1, String dt2){
        
        int an1, an2, m1, m2, j1, j2;
        
        an1=Integer.parseInt(dt1.substring(0, 4));
        an2=Integer.parseInt(dt2.substring(0, 4));
        
        m1=Integer.parseInt(dt1.substring(5,7));
        m2=Integer.parseInt(dt2.substring(5,7));
        
        j1=Integer.parseInt(dt1.substring(dt1.length()-2));
        j2=Integer.parseInt(dt2.substring(dt2.length()-2));
        
        if(an1==an2){
            if(m1==m2){
                if(j1>j2){
                    return 0;
                }else{
                    return 1;
                }
            }else if(m1>m2){
                return 0;
            }else{
                return 1;
            }
        }else if(an1>an2){
            return 0;
        }else{
            return 1;
        }
        
        //LocalDate livraison = LocalDate.of(an1, m1, j1);
        //LocalDate creation = LocalDate.of(an2, m2, j2);
       
        //long days = ChronoUnit.DAYS.between(creation,livraison);
    }
}
