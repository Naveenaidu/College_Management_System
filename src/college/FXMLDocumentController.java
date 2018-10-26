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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author theprophet
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML private TextField UserNameField;
    @FXML private PasswordField PasswordField;
    @FXML private Button SubmitButton; 
    @FXML private Label ErrorMessageLabel;
    
    //Submit method
    public void submitButtonPushed(ActionEvent event) throws IOException{
        ErrorMessageLabel.setText("");
        String errorMessage = "Wrong Credentials";
        String userName = UserNameField.getText();
        String password = PasswordField.getText();
        
        //Check for credentials
        if(userName.equals("admin") && password.equals("admin")){
            
            //Change the scene to admin_homepage
            
            Parent adminHomeParent = FXMLLoader.load(getClass().getResource("admin_homepage.fxml"));
            Scene adminHomeScene = new Scene(adminHomeParent);
            
            //Now we need to get the information of the Stage
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
            window.setScene(adminHomeScene);
            window.show();
        }
        else{
            ErrorMessageLabel.setText(errorMessage);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ErrorMessageLabel.setText("");
    }    
    
}
