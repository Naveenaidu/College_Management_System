/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_studentController implements Initializable {

    @FXML private Label userNameLabel;
    
    @FXML private Button homeButton; 
    @FXML private Button backButton;
    @FXML private Button newStudentButton;
    @FXML private Button manageStudentButton;
    
    public void changeScene(ActionEvent event,String resource) throws IOException{
        Parent adminHomeParent = FXMLLoader.load(getClass().getResource(resource));
        Scene adminHomeScene = new Scene(adminHomeParent);
            
        //Now we need to get the information of the Stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(adminHomeScene);
        window.show();
    }    
    
    // Home Button Pushed
    public void homeButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_homepage.fxml");
    }
    
    // Back Button Pushed
    public void backButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_homepage.fxml");
    }
    
    // New Student Button Pushed
    public void newStudentButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_student_new.fxml");
    }
    
    // Edit Student Button Pushed
    public void editStudentButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_student_edit.fxml");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
