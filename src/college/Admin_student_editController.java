/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_student_editController implements Initializable {
    
    @FXML private Label MessageLabel;

    @FXML private Button homeButton;
    @FXML private Button backButton;
    @FXML private Button clearButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    @FXML private TextField usnTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField ssidTextField;
    @FXML private TextField dnameTextField;
    @FXML private TextField dobTextField;
    @FXML private TextField genderTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField emailTextField;

    // We tell the table that it is expecting a Student Object
    @FXML private TableView<StudentDetails> tableStudent;

    /*
     * TableColumn<S,T> 
     * S -> The type of the TableView generic type (i.e. S == TableView<S>)
     * S basically is the parent class    
     * T -> The type of content in each cell
     */
    @FXML private TableColumn<StudentDetails, String> columnPointer;
    @FXML private TableColumn<StudentDetails, String> columnUsn;
    @FXML private TableColumn<StudentDetails, String> columnName;
    @FXML private TableColumn<StudentDetails, String> columnSsid;
    @FXML private TableColumn<StudentDetails, String> columnDname;
    @FXML private TableColumn<StudentDetails, String> columnGender;
    @FXML private TableColumn<StudentDetails, Integer> columnPhone;
    @FXML private TableColumn<StudentDetails, String> columnEmail;
    @FXML private TableColumn<StudentDetails, String> columnDob;
    

    // Initialize Observable list to hold data
    /*
    * ObservableList adds a way to listen to the changes on a list which ArrayList<>
    * doesn't provide. It is generally used when we have to interact with the view.
    * For eg:- We might want to interact with the tableView and hence we use a
    * ObservableList
     */
    private ObservableList<StudentDetails> data;
    private DbConnection dc;
    private Connection conn;

    // Change scene
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
        changeScene(event, "admin_student_edit.fxml");
    }
    
    // When user mouse selects a particular row. Update the corresponding values
    // When you select a table row, it returns a Student Object. You can do whatever
    // you want with it
    public void tableStudent_MouseClicked(MouseEvent event){
        StudentDetails student = tableStudent.getSelectionModel().getSelectedItem();
        usnTextField.setText(student.getUsn());
        nameTextField.setText(student.getName());
        ssidTextField.setText(student.getSsid());
        dnameTextField.setText(student.getDname());
        dobTextField.setText(student.getDob());
        genderTextField.setText(student.getGender());
        phoneTextField.setText(student.getPhone().toString());
        emailTextField.setText(student.getEmail());
        
    }
    
    public void updateStudent(ActionEvent event) throws SQLException, ParseException, IOException{
        
        // Store the old USN( Primary key) for our updation query
        /*
        We take the usn from the student object by selecting the row and then
        update it
        */
        StudentDetails student = tableStudent.getSelectionModel().getSelectedItem();
        String origUSN = student.getUsn();
        
        // Convert string to sql Date;
        String dobString = dobTextField.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dob = new java.sql.Date((sdf.parse(dobString)).getTime());
        
        
        
        String sqlUpdate = "UPDATE student" + " SET usn = ? " + ",name = ?" + ",ssid = ?" + ",dname = ?"
                            + ",dob = ?" + ",gender = ?" + ",phone_no = ?" + ",email = ?" +
                            "WHERE usn = ?"  ;
        
                            
                
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        
        // Enter the values in the query
            pstmt.setString(1, usnTextField.getText());
            pstmt.setString(2, nameTextField.getText());
            pstmt.setString(3, ssidTextField.getText());
            pstmt.setString(4, dnameTextField.getText());
            pstmt.setDate(5, dob);
            pstmt.setString(6, genderTextField.getText());
            pstmt.setInt(7, new Integer(phoneTextField.getText()));
            pstmt.setString(8, emailTextField.getText());
            pstmt.setString(9,origUSN);
            
            System.out.println(pstmt);
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to update",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_student_edit.fxml"); 
        }
           
        MessageLabel.setText("Student Updated Succesfully");         
    }
    
    public void deleteButtonPushed(ActionEvent event) throws SQLException, IOException{
        
        // Store the old USN( Primary key) for our updation query
        // We take the usn from the student object by selecting the row and then delete it
        StudentDetails student = tableStudent.getSelectionModel().getSelectedItem();
        String origUSN = student.getUsn();
        
        String sqlDelete = "DELETE FROM student WHERE usn = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        
        pstmt.setString(1,origUSN);
        
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_student_edit.fxml"); 
        }
        
        
    }

    // We want to database to be connected as soon as the scene is loaded
    /*
     * FXCollections is the wrapper class for collections which has all the methods
     * of collection, but returns a ObservableList.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            dc = new DbConnection();
            conn = dc.connect();
            data = FXCollections.observableArrayList();

            // Execute Query and store the result in ResultSet
            ResultSet rs = conn.createStatement().executeQuery("SELECT * from student");
            while (rs.next()) {

                // Get string from DB
                data.add(new StudentDetails(rs.getString("usn"), rs.getString("name"),
                        rs.getString("ssid"), rs.getString("dname"), rs.getString("dob"),
                        rs.getString("gender"), (Integer)rs.getInt("phone_no"), rs.getString("email")
                )
                );

            }

        } catch (SQLException ex) {
            System.err.println("Error: " + ex);
        }
        
        // Set the cell value Factory to tableView
        /*
          This is used to specifiy the kind of values we will use to populate 
          our cell
        */
        columnUsn.setCellValueFactory(new PropertyValueFactory<>("usn"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnSsid.setCellValueFactory(new PropertyValueFactory<>("ssid"));
        columnDname.setCellValueFactory(new PropertyValueFactory<>("dname"));
        columnDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        tableStudent.setItems(null);
        tableStudent.setItems(data);


    }

}
