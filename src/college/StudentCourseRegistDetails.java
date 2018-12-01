/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author theprophet
 */
public class StudentCourseRegistDetails {

    private final StringProperty name;   
    private final StringProperty usn;
    private final StringProperty dname;
    private final StringProperty title;
    private final StringProperty cid;

    
    
    public StudentCourseRegistDetails(String name,String usn,String dname,String cid,String title){
        this.name = new SimpleStringProperty(name);
        this.usn = new SimpleStringProperty(usn);
        this.dname = new SimpleStringProperty(dname);
        this.cid =new SimpleStringProperty(cid);
        this.title =new SimpleStringProperty(title);
    }
    
    // Getters
    
    public String getName(){return name.get();}
    public String getUsn(){return usn.get();}
    public String getDname(){return dname.get();}
    public String getCourse(){return title.get();}
    public String getCid(){return cid.get();}
    
    // Setters
    public void setName(String value){name.set(value);}
    public void setUsn(String value){usn.set(value);}
    public void setDname(String value){dname.set(value);}
    public void setCourse(String value){title.set(value);}
    public void setCid(String value){title.set(value);}
    
    // Property
    public StringProperty nameProperty(){return name;}
    public StringProperty usnProperty(){return usn;}
    public StringProperty dnameProperty(){return dname;}
    public StringProperty titleProperty(){return title;}
    public StringProperty cidProperty(){return cid;}
   
    
}
