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
public class TeacherDetails {
    
    private final StringProperty sid;   
    private final StringProperty name;
    private final StringProperty dname;
    private final StringProperty gender;
    private final StringProperty email;
    private final IntegerProperty phone;
    private final IntegerProperty salary;
    
    // Constructor
    public TeacherDetails(String sid,String name,String dname,
           String gender,Integer phone,String email,Integer salary){
        
        this.sid    = new SimpleStringProperty(sid);
        this.name   = new SimpleStringProperty(name);
        this.dname  = new SimpleStringProperty(dname);
        this.gender = new SimpleStringProperty(gender);
        this.email  = new SimpleStringProperty(email);
        this.phone  = new SimpleIntegerProperty(phone); 
        this.salary =  new SimpleIntegerProperty(salary);
        
    }
    
    // Getters
    
    public String getSid(){return sid.get();}
    public String getName(){return name.get();}
    public String getDname(){return dname.get();}
    public String getGender(){return gender.get();}
    public String getEmail(){return email.get();}
    public Integer getPhone(){return phone.get();}
    public Integer getSalary(){return salary.get();}
    
    // Setters
    
    public void setSid(String value){sid.set(value);}
    public void setName(String value){name.set(value);}
    public void setDname(String value){dname.set(value);}
    public void setGender(String value){gender.set(value);}
    public void setEmail(String value){email.set(value);}
    public void setPhone(Integer value){phone.set(value);}
    public void setSalary(Integer value){salary.set(value);}
    
    // Property
    
    public StringProperty sidProperty(){return sid;}
    public StringProperty nameProperty(){return name;}
    public StringProperty dnameProperty(){return dname;}
    public StringProperty genderProperty(){return gender;}
    public StringProperty emailProperty(){return email;}
    public IntegerProperty phoneProperty(){return phone;}
    public IntegerProperty salaryProperty(){return salary;}
}
