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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class Admin_teacher_editController implements Initializable {
    
    private ObservableList<TeacherDetails> data;
    private DbConnection dc;
    private Connection conn;

    @FXML private Button homeButton;
    @FXML private Button backButton;
    @FXML private Button clearButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;

    @FXML private TextField sidTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField dnameTextField;
    @FXML private TextField genderTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField emailTextField;
    @FXML private TextField salaryTextField;
    
    // We tell the table that it is expecting a Teacher Object
    @FXML private TableView<TeacherDetails> tableTeacher;
    
    /*
     * TableColumn<S,T> 
     * S -> The type of the TableView generic type (i.e. S == TableView<S>)
     * S basically is the parent class    
     * T -> The type of content in each cell
     */
    @FXML private TableColumn<TeacherDetails, String> columnPointer;
    @FXML private TableColumn<TeacherDetails, String> columnSid;
    @FXML private TableColumn<TeacherDetails, String> columnName;
    @FXML private TableColumn<TeacherDetails, String> columnDname;
    @FXML private TableColumn<TeacherDetails, String> columnGender;
    @FXML private TableColumn<TeacherDetails, Integer> columnPhone;
    @FXML private TableColumn<TeacherDetails, String> columnEmail;
    @FXML private TableColumn<TeacherDetails, Integer> columnSalary;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            dc = new DbConnection();
            conn = dc.connect();
            data = FXCollections.observableArrayList();

            // Execute Query and store the result in ResultSet
            ResultSet rs = conn.createStatement().executeQuery("SELECT * from staff");
            while (rs.next()) {

                // Get string from DB
                data.add(new TeacherDetails(rs.getString("sid"), rs.getString("name"),
                        rs.getString("dname"),rs.getString("gender"),(Integer)rs.getInt("phone_no"),
                        rs.getString("email"), (Integer)rs.getInt("salary")
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
        columnSid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnDname.setCellValueFactory(new PropertyValueFactory<>("dname"));
        columnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        
        tableTeacher.setItems(null);
        tableTeacher.setItems(data);
    }  
    
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
    @FXML
    public void homeButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_homepage.fxml");
    }

    // Back Button Pushed
    @FXML
    public void backButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_teacher.fxml");
    }

    // Clear Button Pushed
    @FXML
    public void clearButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event, "admin_teacher_edit.fxml");
    }
    
    // When user mouse selects a particular row. Update the corresponding values
    // When you select a table row, it returns a Student Object. You can do whatever
    // you want with it
    public void tableTeacher_MouseClicked(MouseEvent event){
        TeacherDetails teacher = tableTeacher.getSelectionModel().getSelectedItem();
        sidTextField.setText(teacher.getSid());
        nameTextField.setText(teacher.getName());
        dnameTextField.setText(teacher.getDname());
        genderTextField.setText(teacher.getGender());
        phoneTextField.setText(teacher.getPhone().toString());
        emailTextField.setText(teacher.getEmail());
        salaryTextField.setText(teacher.getPhone().toString());
        
    }
    
    @FXML
    public void updateButtonPushed(ActionEvent event) throws SQLException, ParseException, IOException{
        
        // Store the old USN( Primary key) for our updation query
        /*
        We take the usn from the student object by selecting the row and then
        update it
        */
        TeacherDetails teacher = tableTeacher.getSelectionModel().getSelectedItem();
        String origSID = teacher.getSid();
                
        String sqlUpdate = "UPDATE staff" + " SET sid = ? " + ",name = ?" + ",dname = ?"
                            + ",gender = ?" + ",phone_no = ?" + ",email = ?" +",salary = ?"+
                            " WHERE sid = ?";
        
        System.out.println(sqlUpdate);
        
                            
                
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        
        // Enter the values in the query
            pstmt.setString(1, sidTextField.getText());
            pstmt.setString(2, nameTextField.getText());
            pstmt.setString(3, dnameTextField.getText());
            pstmt.setString(4, genderTextField.getText());
            pstmt.setInt(5, new Integer(phoneTextField.getText()));
            pstmt.setString(6, emailTextField.getText());
            pstmt.setInt(7, new Integer(salaryTextField.getText()));
            pstmt.setString(8,origSID);
            
            System.out.println(pstmt);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_teacher_edit.fxml"); 
        }
        
    }
    
    @FXML
    public void deleteButtonPushed(ActionEvent event) throws SQLException, IOException{
        
        // Store the old USN( Primary key) for our updation query
        // We take the usn from the student object by selecting the row and then delete it
        TeacherDetails teacher = tableTeacher.getSelectionModel().getSelectedItem();
        String origSID = teacher.getSid();
        
        String sqlDelete = "DELETE FROM staff WHERE sid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        
        pstmt.setString(1,origSID);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_teacher_edit.fxml"); 
        }
        
        
    }

    
    
}
