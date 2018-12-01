/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TODO:- Change the TextField to Labels for Name,Course,Dept etc
*/
package college;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_marksController implements Initializable {
    
    private DbConnection dc;
    private Connection conn;

    @FXML
    private  ChoiceBox<String> dnameChoiceBox = new ChoiceBox();
    @FXML
    private ChoiceBox<String> semesterChoiceBox;
    @FXML
    private ChoiceBox<String> sectionChoiceBox;
    @FXML
    private ChoiceBox<String> courseChoiceBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dc = new DbConnection();
        conn = dc.connect();
        
        try {
            // Get the list of all Departments
            ResultSet dnameResult = conn.createStatement().executeQuery("SELECT dname from department");
            
            while(dnameResult.next()){
                dnameChoiceBox.getItems().add(dnameResult.getString("dname"));
            }
            
            dnameChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    String selectedDept = dnameChoiceBox.getValue().toString();
                    
                    // Get the list of all courses depending on the Department
                    courseChoiceBox.getItems().clear();
                    try { 
                        ResultSet courseResult = conn.createStatement().executeQuery("SELECT title from "
                                + "course WHERE dname = '" + selectedDept + "'");
                        
                        while(courseResult.next()){
                            courseChoiceBox.getItems().add(courseResult.getString("title"));
                        }
                        
                    } 
                    catch (SQLException ex) {
                        System.err.println("Error: " + ex);
                    }
        
                }
            });
            
            // List of all Semesters
            
            ResultSet semesterResult = conn.createStatement().executeQuery("SELECT  distinct semester "
                    + "from semsec order by semester");
            
            while(semesterResult.next()){
               semesterChoiceBox.getItems().add(semesterResult.getString("semester"));
            }
            
            //List of all Sections
            
            ResultSet sectionResult = conn.createStatement().executeQuery("SELECT  distinct section from semsec"
                    + " order by section");
            
            while(sectionResult.next()){
               sectionChoiceBox.getItems().add(sectionResult.getString("section"));
            }
     
            
        } 
        
        
        catch (SQLException ex) {
             System.err.println("Error: " + ex);
        }
        
    }

    public void submitButtonPushed(ActionEvent event) throws SQLException, IOException{
        String semester = semesterChoiceBox.getValue().toString();
        String section = sectionChoiceBox.getValue().toString();
        String courseName = courseChoiceBox.getValue().toString();
        
        // Find the ssid(semsec id) from these value and transfer it to next scene
        ResultSet ssidResult = conn.createStatement().executeQuery("SELECT ssid FROM semsec "
                + "WHERE semester = '" + semester +"' and section = '" + section + "'");
        
        while(ssidResult.next()){
            String semsec = ssidResult.getString("ssid");
            MarksInitData.semsec = semsec;
            
        }
        
        // Find the CID of selected subject
        ResultSet cidResult = conn.createStatement().executeQuery("SELECT cid FROM course "
                + "WHERE title = '" + courseName + "'");
        
        while(cidResult.next()){
            String cid = ssidResult.getString("ssid");
            MarksInitData.cid = cid;
            
        }
        
        
        changeScene(event,"admin_marks_edit.fxml");
        
        
    }
    
    public void homeButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_homepage.fxml");
    }
    
    public void changeScene(ActionEvent event,String resource) throws IOException{
        Parent adminHomeParent = FXMLLoader.load(getClass().getResource(resource));
        Scene adminHomeScene = new Scene(adminHomeParent);
            
        //Now we need to get the information of the Stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(adminHomeScene);
        window.show();
    }
    
}
