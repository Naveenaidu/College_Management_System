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
public class Admin_course_editController implements Initializable {
    
    private ObservableList<CourseDetails> data;
    private DbConnection dc;
    private Connection conn;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;
    @FXML
    private Button homeButton;
    @FXML
    private TextField cidTextField;
    @FXML
    private TextField dnameTextField;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField creditsTextField;
    @FXML
    private TableView<CourseDetails> tableCourses;
    @FXML private TableColumn<TeacherDetails, String> columnPointer;

    @FXML
    private TableColumn<CourseDetails, String> columnCid;
    @FXML
    private TableColumn<CourseDetails, String> columnDname;
    @FXML
    private TableColumn<CourseDetails, Integer> columnCredits;
    @FXML
    private TableColumn<CourseDetails, String> columnTitle;

    @FXML
    private Button clearButton;
    

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
            ResultSet rs = conn.createStatement().executeQuery("SELECT * from course");
            while (rs.next()) {

                // Get string from DB
                data.add(new CourseDetails(rs.getString("cid"), rs.getString("title"),
                        rs.getString("dname"),(Integer)rs.getInt("credits")
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
        columnCid.setCellValueFactory(new PropertyValueFactory<>("cid"));
        columnDname.setCellValueFactory(new PropertyValueFactory<>("dname"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnCredits.setCellValueFactory(new PropertyValueFactory<>("credits"));
        tableCourses.setItems(null);
        tableCourses.setItems(data);
    }    

    @FXML
    private void updateButtonPushed(ActionEvent event) throws SQLException, IOException {
        
        // Store the old USN( Primary key) for our updation query
        /*
        We take the usn from the student object by selecting the row and then
        update it
        */
        CourseDetails course = tableCourses.getSelectionModel().getSelectedItem();
        String origCID = course.getCid();
                
        String sqlUpdate = "UPDATE course" + " SET cid = ? " + ",title = ?" + ",dname = ?"
                            +  ",credits = ?" + " WHERE cid = ?";
        
        System.out.println(sqlUpdate);
        
                            
                
        PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
        
        // Enter the values in the query
            pstmt.setString(1, cidTextField.getText());
            pstmt.setString(2, titleTextField.getText());
            pstmt.setString(3, dnameTextField.getText());
            pstmt.setInt(4, new Integer(creditsTextField.getText()));
            pstmt.setString(5,origCID);
            
            System.out.println(pstmt);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_course_edit.fxml"); 
        }        
    }

    @FXML
    private void deleteButtonPushed(ActionEvent event) throws SQLException, IOException {
        
        // Store the old USN( Primary key) for our updation query
        // We take the usn from the student object by selecting the row and then delete it
        CourseDetails course = tableCourses.getSelectionModel().getSelectedItem();
        String origCID = course.getCid();
        
        String sqlDelete = "DELETE FROM course WHERE cid = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlDelete);
        
        pstmt.setString(1,origCID);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the record",
                      ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            pstmt.execute();
            conn.close();
            changeScene(event, "admin_course_edit.fxml"); 
        }
        
        
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

    @FXML
    private void tableCourse_MouseClicked(MouseEvent event) throws SQLException, IOException {
        CourseDetails course = tableCourses.getSelectionModel().getSelectedItem();
        cidTextField.setText(course.getCid());
        dnameTextField.setText(course.getDname());
        titleTextField.setText(course.getTitle());
        creditsTextField.setText(course.getCredits().toString());

    }

    @FXML
    private void clearButtonPushed(ActionEvent event) throws SQLException, IOException{
        conn.close();
        changeScene(event, "admin_course_edit.fxml");
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
}
