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
public class Admin_teacherController implements Initializable {

    @FXML
    private Label userNameLabel;
    
    @FXML
    private Button homeButtin;
    @FXML
    private Button newTeacherButton;
    @FXML
    private Button manageTeacherButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

     public void changeScene(ActionEvent event,String resource) throws IOException{
        Parent adminHomeParent = FXMLLoader.load(getClass().getResource(resource));
        Scene adminHomeScene = new Scene(adminHomeParent);
            
        //Now we need to get the information of the Stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(adminHomeScene);
        window.show();
    }

    @FXML
    private void homeButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_homepage.fxml");
    }

    @FXML
    private void newTeacherButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_teacher_new.fxml");
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_homepage.fxml");
    }

    @FXML
    private void manageTeacherButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_teacher_edit.fxml");
    }
    
}
