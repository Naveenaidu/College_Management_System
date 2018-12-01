/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author theprophet
 */
public class CourseDetails {
    private final StringProperty cid;   
    private final StringProperty title;
    private final StringProperty dname;
    private final IntegerProperty credits;
    
    public CourseDetails(String cid,String title,String dname,Integer credits){
        this.cid = new SimpleStringProperty(cid);
        this.title = new SimpleStringProperty(title);
        this.dname = new SimpleStringProperty(dname);
        this.credits =new SimpleIntegerProperty(credits);
    }
    
    // Getters
    
    public String getCid(){return cid.get();}
    public String getTitle(){return title.get();}
    public String getDname(){return dname.get();}
    public Integer getCredits(){return credits.get();}
    
    // Setters
    public void setCid(String value){cid.set(value);}
    public void setTitle(String value){title.set(value);}
    public void setDname(String value){dname.set(value);}
    public void setCredits(Integer value){credits.set(value);}
    
    // Property
    public StringProperty cidProperty(){return cid;}
    public StringProperty titleProperty(){return title;}
    public StringProperty dnameProperty(){return dname;}
    public IntegerProperty creditsProperty(){return credits;}
   
    
}
