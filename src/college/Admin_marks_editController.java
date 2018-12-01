/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package college;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_marks_editController implements Initializable {
    
    private ObservableList<CourseDetails> data;
    private DbConnection dc;
    private Connection conn;
    
    private String semsec = MarksInitData.semsec;
    private String cid = MarksInitData.cid;

    @FXML
    private Button homeButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button backButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField usnTextField;
    @FXML
    private TextField courseTextField;
    @FXML
    private TextField deptTextField;
    @FXML
    private TextField t1TextField;
    @FXML
    private TextField t2TextField;
    @FXML
    private TextField t3TextField;
    @FXML
    private TextField totalTextField;
    @FXML
    private TableView<MarksDetails> MarksTable;
    @FXML
    private TableColumn<MarksDetails, String> columnUsn;
    @FXML
    private TableColumn<MarksDetails, String> columnName;
    @FXML
    private TableColumn<MarksDetails, String> columnCourse;
    @FXML
    private TableColumn<MarksDetails, String> columnDept;
    @FXML
    private TableColumn<MarksDetails, Integer> columnT1;
    @FXML
    private TableColumn<MarksDetails, Integer> columnT2;
    @FXML
    private TableColumn<MarksDetails, Integer> columnT3;
    @FXML
    private TableColumn<MarksDetails, Integer> columnTotal;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dc = new DbConnection();
        conn = dc.connect();
        data = FXCollections.observableArrayList();
        
        // Get all the students present in the particular semsec
    }    

    @FXML
    private void homeButtonPushed(ActionEvent event) {
    }

    @FXML
    private void updateButtonPushed(ActionEvent event) {
    }

    @FXML
    private void deleteButtonPushed(ActionEvent event) {
    }

    @FXML
    private void backButtonPushed(ActionEvent event) {
    }

    @FXML
    private void clearButtonPushed(ActionEvent event) {
    }
    
}
