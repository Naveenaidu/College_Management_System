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
import java.text.SimpleDateFormat;
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
public class Admin_teacher_newController implements Initializable {
    
    private DbConnection dc;
    private Connection conn;
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField sidTextField;
    @FXML
    private TextField dnameTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField salaryTextField;
    
    @FXML
    private Button submitButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button backButton;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        conn = dc.connect();
    }

    // This method is used to change the scene. Pass the resource file from
    // the button pressed.
    public void changeScene(ActionEvent event, String resource) throws IOException {
        Parent adminHomeParent = FXMLLoader.load(getClass().getResource(resource));
        Scene adminHomeScene = new Scene(adminHomeParent);

        //Now we need to get the information of the Stage
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(adminHomeScene);
        window.show();
    }
    
    

    @FXML
    private void submitButtonPushed(ActionEvent event) throws SQLException, IOException {
        
        try (Connection conn = dc.connect()) {
            System.out.println("Connection established");
            
            // Mysql Query
            String query = "insert into staff values(?,?,?,?,?,?,?)";
            
            // Create a preparedstatement Object - It represents SQL statement
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            // Enter the values in the query
            pstmt.setString(1, sidTextField.getText());
            pstmt.setString(2, nameTextField.getText());
            pstmt.setString(3, dnameTextField.getText());
            pstmt.setString(4, genderTextField.getText());
            pstmt.setInt(5, new Integer(phoneTextField.getText()));
            pstmt.setString(6, emailTextField.getText());
            pstmt.setInt(7, new Integer(salaryTextField.getText()));
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                // Execute the preparedstatement
                pstmt.execute();
                conn.close();
                changeScene(event,"admin_teacher_new.fxml");
             }
            
            
        }
        System.out.println("Connection Closed");
        
    }

    @FXML
    private void clearButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_teacher_new.fxml");
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws SQLException, IOException {
        conn.close();
        changeScene(event, "admin_teacher.fxml");
    }
    
    
    
}
