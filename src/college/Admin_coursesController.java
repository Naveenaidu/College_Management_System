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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_coursesController implements Initializable {

    @FXML
    private Button homeButton;
    @FXML
    private Button newCoursesButton;
    @FXML
    private Button manageCoursesButton;
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
    private void newCoursesButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_course_new.fxml");
    }


    @FXML
    private void manageCoursesButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_course_edit.fxml");
    }


    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException {
        changeScene(event,"admin_homepage.fxml");
    }
    
}
