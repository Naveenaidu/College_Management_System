/*
TODO:- Change the menu options of course based on department
1. We can assosciate any course to any student. That means a CS student can take
   Mechanical course
*/




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
import javafx.scene.control.ChoiceBox;
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
public class Admin_student_course_registController implements Initializable {

    private ObservableList<StudentCourseRegistDetails> data;
    private DbConnection dc;
    private Connection conn;
    
    @FXML
    private Button clearButton;
    @FXML
    private Button backButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button homeButton;
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usnTextField;
    @FXML
    private ChoiceBox courseChoiceBox;
    @FXML
    private ChoiceBox dnameChoiceBox;
    
    @FXML
    private TableView<StudentCourseRegistDetails> tableStudentRegist;
    
    @FXML
    private TableColumn<StudentCourseRegistDetails, String> columnPointer;
    @FXML
    private TableColumn<StudentCourseRegistDetails, String> columnUsn;
    @FXML
    private TableColumn<StudentCourseRegistDetails, String> columnName;
    @FXML
    private TableColumn<StudentCourseRegistDetails, String> columnDept;
    @FXML
    private TableColumn<?, ?> columnCid;
    @FXML
    private TableColumn<?, ?> columnTitle;
    
    

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
            ResultSet rs = conn.createStatement().executeQuery("SELECT s.name,s.usn,s.dname,c.cid,c.title " + 
                                            "FROM student s,course c, student_course_registrar scr " + 
                                            "WHERE s.usn = scr.usn and c.cid = scr.cid");



            while (rs.next()) {

                // Get string from DB
                data.add(new StudentCourseRegistDetails(rs.getString("name"), rs.getString("usn"),
                        rs.getString("dname"),rs.getString("cid"),rs.getString("title")
                         )
                       );

            }
            
            // Initialize the values into the  Department ChoiceBox
            /*
            To do that query all the department and add them
            */
            
            ResultSet deptResult = conn.createStatement().executeQuery("SELECT * FROM department");
            //courseChoiceBox.getItems().add("Hello");
            
            while(deptResult.next()){
                dnameChoiceBox.getItems().add(deptResult.getString("dname"));
            }
            
            ResultSet courseResult = conn.createStatement().executeQuery("SELECT * FROM course");
            //courseChoiceBox.getItems().add("Hello");
            
            while(courseResult.next()){
                courseChoiceBox.getItems().add(courseResult.getString("title"));
            }
            
            
            
            // Set the courses depending on the department. Get the department value
            // and then set the courses.
            
            //ResultSet courseResult = conn.createStatement().executeQuery("SELECT ");
            

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
        columnDept.setCellValueFactory(new PropertyValueFactory<>("dname"));
        columnCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        
        tableStudentRegist.setItems(null);
        tableStudentRegist.setItems(data);
    }


    @FXML
    private void updateButtonPushed(ActionEvent event) throws SQLException, IOException {
        // Store the old USN( Primary key) for our updation query
        /*
        We take the usn from the student object by selecting the row and then
        update it
        */
        StudentCourseRegistDetails student_regis  = tableStudentRegist.getSelectionModel().getSelectedItem();
        String origUSN = student_regis.getUsn();
        String cid_num = null;
        
        // Creating a SQL statement to get the cid of the course selected
        String courseName = courseChoiceBox.getValue().toString();
        ResultSet courseNameResult =conn.createStatement().executeQuery("SELECT cid FROM course WHERE title ='" 
                                                                  + courseName+"'");
        System.out.println(courseNameResult);
        while(courseNameResult.next()){
            cid_num = courseNameResult.getString("cid");
        }
        
        // Query to update the values. The table had only `usn` and `cid`
        String sqlUpdate = "UPDATE student_course_registrar" + " SET usn = ? " + ",cid = ?" + "WHERE usn = ?"  ;
        
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        
        pstmt.setString(1, origUSN);
        pstmt.setString(2,cid_num);
        pstmt.setString(3,origUSN);
        
        System.out.println(pstmt);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_student_course_regist.fxml"); 
        }      
        
    }
    
    @FXML
    private void clearButtonPushed(ActionEvent event) throws SQLException, IOException {
        conn.close();
        changeScene(event, "admin_student_course_regist.fxml");
    }

    @FXML
    private void backButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event,"admin_homepage.fxml");
    }

    @FXML
    private void deleteButtonPushed(ActionEvent event) throws SQLException, IOException {
        // Store the old USN( Primary key) for our updation query
        // We take the usn from the student object by selecting the row and then delete it
        StudentCourseRegistDetails student_regis  = tableStudentRegist.getSelectionModel().getSelectedItem();
        String origUSN = student_regis.getUsn();
        
        String sqlDelete = "DELETE FROM student_course_registrar WHERE usn = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        
        pstmt.setString(1,origUSN);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_student_course_regist.fxml"); 
        }
    }

    @FXML
    private void addButtonPushed(ActionEvent event) throws SQLException, IOException {
        
        // Store the old USN( Primary key) for our updation query
        /*
        We take the usn from the student object by selecting the row and then
        update it
        */
        String cid_num = null;
        
        // Creating a SQL statement to get the cid of the course selected
        String courseName = courseChoiceBox.getValue().toString();
        ResultSet courseNameResult =conn.createStatement().executeQuery("SELECT cid FROM course WHERE title ='" 
                                                                  + courseName+"'");
        System.out.println(courseNameResult);
        while(courseNameResult.next()){
            cid_num = courseNameResult.getString("cid");
        }
        
        // MySql query
        String addQuery = "insert into student_course_registrar values(?,?)";
        
        // Create a preparedStatement 
        PreparedStatement pstmt = conn.prepareStatement(addQuery);
        
        pstmt.setString(1,usnTextField.getText());
        pstmt.setString(2,cid_num);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to add the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                try{
                    pstmt.execute();
                    System.out.println("Executed the statement");
                }
                catch(SQLException e){
                    Alert ErrorAlert = new Alert(Alert.AlertType.CONFIRMATION, "Their is a problem in the query,Please change it",
                      ButtonType.CANCEL);
                    ErrorAlert.showAndWait();
                    if (ErrorAlert.getResult() == ButtonType.CANCEL){
                        changeScene(event,"admin_student_course_regist.fxml");
                    }
                    
                }
                conn.close();
                changeScene(event,"admin_student_course_regist.fxml");
             }  
        
    }

    @FXML
    private void homeButtonPushed(ActionEvent event) throws IOException, SQLException {
        conn.close();
        changeScene(event,"admin_homepage.fxml");
    }

    @FXML
    private void tableStudentRegist_MouseClicked(MouseEvent event) {
        StudentCourseRegistDetails student_regis = tableStudentRegist.getSelectionModel().getSelectedItem();
        nameTextField.setText(student_regis.getName());
        usnTextField.setText(student_regis.getUsn());
        
        dnameChoiceBox.setValue(student_regis.getDname());
        courseChoiceBox.setValue(student_regis.getCourse());
        
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
