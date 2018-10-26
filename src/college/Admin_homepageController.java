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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author theprophet
 */
public class Admin_homepageController implements Initializable {

    @FXML private Label welcomeLabel;
    @FXML private Label userNameLabel;
    
    @FXML private Button backButton;
    @FXML private Button homeButton;
    @FXML private Button studentButton;
    @FXML private Button staffButton;
    @FXML private Button coursesButton;
    @FXML private Button examButton;
    @FXML private Button marksButton;
    @FXML private Button attendanceButton;
    @FXML private Button studentCourseRegistButton;
    @FXML private Button staffCourseRegistButton;
    
    // This method is used to change the scene. Pass the resource file from
    // the button pressed.
    public void changeScene(ActionEvent event,String resource) throws IOException{
        Parent adminHomeParent = FXMLLoader.load(getClass().getResource(resource));
        Scene adminHomeScene = new Scene(adminHomeParent);
            
        //Now we need to get the information of the Stage
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            
        window.setScene(adminHomeScene);
        window.show();
    }
    
    // Home Button Pushed
    public void homeButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_homepage.fxml");
    }
    
    // Back Button Pushed
    public void backButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"FXMLDocument.fxml");
    }
    
    // Student Button Pushed
    public void studentButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_student.fxml");
    }
    
    // Staff Button Pushed
    public void staffButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_teacher.fxml");
    }
    
    // Courses Button Pushed
    public void coursesButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_courses.fxml");
    }
    
    // Exam Button Pushed
    public void examButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_exams.fxml");
    }
    
    // Marks Button Pushed
    public void marksButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_marks.fxml");
    }
    
    // Attendance Button Pushed
    public void attendanceButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_attendance.fxml");
    }
    
    // Student_course_regist Button Pushed
    public void studentCoureseRegistButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_student_course_regist.fxml");
    }
    
    // staff_course_regist Button Pushed
    public void staffCoureseRegistButtonPushed(ActionEvent event) throws IOException{
        changeScene(event,"admin_staff_course_regist.fxml");
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
