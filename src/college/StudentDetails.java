// This class acts as a model class,holding getter,setter and properties
package college;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author theprophet
 */
public class StudentDetails {
    
    private final StringProperty usn;   
    private final StringProperty name;
    private final StringProperty ssid;
    private final StringProperty dname;
    private final StringProperty gender;
    private final StringProperty email;
    private final IntegerProperty phone;
    private final StringProperty dob;
    
    // Constructor
    public StudentDetails(String usn,String name,String ssid,String dname,
            String dob,String gender,Integer phone,String email){
        
        this.usn    = new SimpleStringProperty(usn);
        this.name   = new SimpleStringProperty(name);
        this.ssid   = new SimpleStringProperty(ssid);
        this.dname  = new SimpleStringProperty(dname);
        this.dob    = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
        this.email  = new SimpleStringProperty(email);
        this.phone  = new SimpleIntegerProperty(phone);        
        
    }
    
    // Getters
    
    public String getUsn(){return usn.get();}
    public String getName(){return name.get();}
    public String getSsid(){return ssid.get();}
    public String getDname(){return dname.get();}
    public String getDob(){return dob.get();}
    public String getGender(){return gender.get();}
    public String getEmail(){return email.get();}
    public Integer getPhone(){return phone.get();}
    
    // Setters
    
    public void setUsn(String value){usn.set(value);}
    public void setName(String value){name.set(value);}
    public void setSsid(String value){ssid.set(value);}
    public void setDname(String value){dname.set(value);}
    public void setDob(String value){dob.set(value);}
    public void setGender(String value){gender.set(value);}
    public void setEmail(String value){email.set(value);}
    public void setPhone(Integer value){phone.set(value);}
    
    // Property
    
    public StringProperty usnProperty(){return usn;}
    public StringProperty nameProperty(){return name;}
    public StringProperty ssidProperty(){return ssid;}
    public StringProperty dnameProperty(){return dname;}
    public StringProperty dobProperty(){return dob;}
    public StringProperty genderProperty(){return gender;}
    public StringProperty emailProperty(){return email;}
    public IntegerProperty phoneProperty(){return phone;}
    
    
    
}
