/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_course_newController implements Initializable {
    
    private DbConnection dc;
    private Connection conn;
    
    @FXML
    private TextField cidTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField dnameTextField;
    @FXML
    private TextField creditsTextField;
    @FXML
    private Button saveButon;
    @FXML
    private Button clearButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private Label userNameLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        conn = dc.connect();
    }    

    
    @FXML
    private void saveButtonPushed(ActionEvent event) throws SQLException, IOException {
        System.out.println("Connection established!!");
        
        // MySql query
        String query = "insert into course values(?,?,?,?)";
        
        // Create a preparedStatement 
        PreparedStatement pstmt = conn.prepareStatement(query);
        
        pstmt.setString(1,cidTextField.getText());
        pstmt.setString(2,titleTextField.getText());
        pstmt.setString(3,dnameTextField.getText());
        pstmt.setInt(4,new Integer(creditsTextField.getText()));
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                pstmt.execute();
                conn.close();
                changeScene(event,"admin_course_new.fxml");
             }
        
    }

    
    @FXML
    private void clearButtonPushed(ActionEvent event) throws SQLException, IOException {
        conn.close();
        changeScene(event, "admin_course_new.fxml");
    }

    
    @FXML
    private void backButtonPushed(ActionEvent event) throws SQLException, IOException {
        conn.close();
        changeScene(event, "admin_courses.fxml");
    }

    
    @FXML
    private void homeButtonPushed(ActionEvent event) throws SQLException, IOException {
        conn.close();
        changeScene(event, "admin_homepage.fxml");
    }
    
    public void changeScene(ActionEvent event, String resource) throws IOException {
        Parent adminHomeParent = FXMLLoader.load(getClass().getResource(resource));
        Scene adminHomeScene = new Scene(adminHomeParent);

        //Now we need to get the information of the Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(adminHomeScene);
        window.show();
    }

    
    
}
