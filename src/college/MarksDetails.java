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
public class MarksDetails {
    private final StringProperty name;   
    private final StringProperty usn;
    private final StringProperty course;
    private final StringProperty dname;
    private final IntegerProperty t1;
    private final IntegerProperty t2;
    private final IntegerProperty t3;
    private final IntegerProperty total;
    
    public MarksDetails(String name,String usn,String course,String dname,
            Integer t1,Integer t2,Integer t3,Integer total){
        this.name = new SimpleStringProperty(name);
        this.usn = new SimpleStringProperty(usn);
        this.course = new SimpleStringProperty(course);
        this.dname = new SimpleStringProperty(dname);
        this.t1 = new SimpleIntegerProperty(t1);
        this.t2 = new SimpleIntegerProperty(t2);
        this.t3 = new SimpleIntegerProperty(t3);
        this.total = new SimpleIntegerProperty(total);
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getUsn() {
        return usn;
    }

    public StringProperty getCourse() {
        return course;
    }

    public StringProperty getDname() {
        return dname;
    }

    public IntegerProperty getT1() {
        return t1;
    }

    public IntegerProperty getT2() {
        return t2;
    }

    public IntegerProperty getT3() {
        return t3;
    }

    public IntegerProperty getTotal() {
        return total;
    }
    
    // Property
    public StringProperty nameProperty(){return name;}
    public StringProperty usnProperty(){return usn;}
    public StringProperty courseProperty(){return course;}
    public StringProperty dnameProperty(){return dname;}
    public IntegerProperty t1Property(){return t1;}
    public IntegerProperty t2Property(){return t2;}
    public IntegerProperty t3Property(){return t3;}
    public IntegerProperty totalProperty(){return total;}
    
}
