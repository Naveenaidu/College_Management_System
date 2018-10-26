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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import java.text.SimpleDateFormat;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_student_newController implements Initializable {

    private DbConnection dc;
    private Connection conn;

    @FXML
    private Label userNameLabel;
    @FXML
    private Label insertionMessageLabel;

    @FXML
    private Button homeButton;
    @FXML
    private Button backButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button submitButton;

    @FXML
    private TextField usnTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ssidTextField;
    @FXML
    private TextField dnameTextField;
    @FXML
    private TextField dobTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;

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

    // Home Button Pushed
    public void homeButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_homepage.fxml");
    }

    // Back Button Pushed
    public void backButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_student.fxml");
    }

    // Clear Button Pushed
    public void clearButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_student_new.fxml");

        /* Try implementing this approach
        for (Node node : anchorPane.getChildren()) {
            if (node instanceof TextField) {
                System.out.println("Inside here");
                ((TextField)node).setText("");
            }
        }
         */
    }

    // Submit Button Pushed - Connect to database and insert into `student`
    public void submitButtonPushed(ActionEvent event) throws Exception {
        
        try (Connection conn = dc.connect()) {
            System.out.println("Connection established");
            
            // Convert string to sql Date;
            String dobString = dobTextField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.sql.Date dob = new java.sql.Date((sdf.parse(dobString)).getTime());
            
            // Mysql Query
            String query = "insert into student values(?,?,?,?,?,?,?,?)";
            
            // Create a preparedstatement Object - It represents SQL statement
            PreparedStatement pstmt = conn.prepareStatement(query);
            
            // Enter the values in the query
            pstmt.setString(1, usnTextField.getText());
            pstmt.setString(2, nameTextField.getText());
            pstmt.setString(3, ssidTextField.getText());
            pstmt.setString(4, dnameTextField.getText());
            pstmt.setDate(5, dob);
            pstmt.setString(6, genderTextField.getText());
            pstmt.setInt(7, new Integer(phoneTextField.getText()));
            pstmt.setString(8, emailTextField.getText());
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                pstmt.execute();
                conn.close();
                changeScene(event,"admin_student_new.fxml");
             }
            // Execute the preparedstatement
            
            
            // Print the message
            insertionMessageLabel.setText("Successfully Inserted New Student");
        }
        System.out.println("Connection Closed");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DbConnection();
        conn = dc.connect();
        insertionMessageLabel.setText("");
    }

}
