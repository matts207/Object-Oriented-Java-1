/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finaldbapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
//REM Removed - potential use but unneeded here
//import java.sql.*;
//import javafx.geometry.HPos;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.BorderPane;

/**
 *
 * @author dupont/scott
 */
//public class JavaFXDB {
public class finaldbapp extends Application {

    //Declare connection
    //NOTE - Requires the MySQL JDBC driver has been added to Libraries
    private Connection conn;

    //Declare textfields for form
    private TextField tfCourseID = new TextField();
    private TextField tfSubjectID = new TextField();
    private TextField tfCourseNumber = new TextField();
    private TextField tfTitle = new TextField();
    private TextField tfNumberOfCredits = new TextField();

    //Declare textArea for display
    private TextArea taShowRecords = new TextArea();

    //Declare labels
    private Label lbCourseID = new Label("Course ID:");
    private Label lbSubjectID = new Label("Subject ID:");
    private Label lbCourseNumber = new Label("Course Number:");
    private Label lbTitle = new Label("Title:");
    private Label lbNumberOfCredits = new Label("# Credits:");
    private Label lbStatus = new Label("Action:");
    private Label lbBlank = new Label(" ");

    //Declare buttons
    private Button btGo = new Button("Go");
    private Button btCLS = new Button("Clear");
    private ChoiceBox boxCRUD = new ChoiceBox(FXCollections.observableArrayList("Search", "Insert", "Update", "Delete"));
    private ChoiceBox tableBox = new ChoiceBox(FXCollections.observableArrayList("course"));
    private ArrayList<String> a = new ArrayList();


    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        //Call method defined below that creates connection to DB
        initializeDB();

        //Create UI
        //Personal placement choices of labels and textfields here.
        //Room for experimentation.
        
        
        btGo.setOnAction(e -> searchRecord());

        
        boxCRUD.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            switch(newValue.intValue()) {
                case 0:
                    btGo.setOnAction(e -> searchRecord());
                    break;
                case 1:
                    btGo.setOnAction(e -> insertRecord());
                    break;
                case 2:
                    btGo.setOnAction(e -> updateRecord());
                    break;
                case 3:
                    btGo.setOnAction(e -> deleteRecord());
                    break;
            }
        });
        
        
        
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #C0C9C0;");
        boxCRUD.setPrefWidth(153);
        boxCRUD.setValue("Search");
        btGo.setPrefWidth(140);
        btCLS.setPrefWidth(140);
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.add(lbStatus, 1, 1);
        gridPane.add(boxCRUD, 2, 1, 2, 1);
        gridPane.add(lbCourseID, 1, 2, 1, 1);
        gridPane.add(tfCourseID, 2, 2, 1, 1);
        gridPane.add(lbSubjectID, 1, 3, 1, 1);
        gridPane.add(tfSubjectID, 2, 3, 1, 1);
        gridPane.add(lbCourseNumber, 3, 1, 1, 1);
        gridPane.add(tfCourseNumber, 4, 1, 1, 1);
        gridPane.add(lbTitle, 3, 2, 1, 1);
        gridPane.add(tfTitle, 4, 2, 1, 1);
        gridPane.add(lbNumberOfCredits, 3, 3, 1, 1);
        gridPane.add(tfNumberOfCredits, 4, 3, 1, 1);
        gridPane.add(btGo, 4, 6, 3, 1);
        gridPane.add(btCLS, 4, 9, 3, 1);
        gridPane.add(taShowRecords, 1, 4, 3, 8);
        gridPane.setAlignment(Pos.TOP_LEFT);

        // Create event handlers for buttons
        btCLS.setOnAction(e -> clearFields());

        // Create a scene and place it in the stage
        Scene scene = new Scene(gridPane, 580, 370);
        primaryStage.setTitle("Courses DB Demonstration App"); // Set title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        showRecords();  //showRecords called on init and any method that changes DB     
    }

    //This method is for the delete button. Run a prepared statement 
    //to delete active record.
    private void deleteRecord() {
        String queryString = "delete from Course where courseID = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, tfCourseID.getText());
            preparedStatement.executeUpdate();
            showAlert("Delete worked!");
            //After db change, update list at bottom
            showRecords();
        } catch (SQLException e2) {
            showAlert("Delete Failed");
            //clearFields(); //optional clear form on delete fail
        }
    }

    //This method is for the insert button. Run a prepared statement 
    //to insert values in form.
    private void insertRecord() {
        String queryString = "insert into Course (courseId, subId, courseNumber, title, numOfCredits) values (?,?,?,?,?);";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, tfCourseID.getText());
            preparedStatement.setString(2, tfSubjectID.getText());
            //Little extra work here, convert string for int DB field tfCourseNumber
            preparedStatement.setInt(3, Integer.parseInt(tfCourseNumber.getText()));
            preparedStatement.setString(4, tfTitle.getText());
            //Little extra work here, convert string for int DB field tfNumberOfCredits
            preparedStatement.setInt(5, Integer.parseInt(tfNumberOfCredits.getText()));
            preparedStatement.executeUpdate();
            showAlert("Insert worked!");
            //After db change, update list at bottom
            showRecords();
        } catch (SQLException e2) {
            showAlert("Insert Failed");
            //clearFields(); //optional clear form on delete fail
        }
    }

    private void updateRecord() {
        String queryString = "update Course set courseId = ?, subId = ?, courseNumber = ?, title = ?, numOfCredits = ? where courseID = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, tfCourseID.getText());
            preparedStatement.setString(2, tfSubjectID.getText());
            //Little extra work here, convert string for int DB field tfCourseNumber
            preparedStatement.setInt(3, Integer.parseInt(tfCourseNumber.getText()));
            preparedStatement.setString(4, tfTitle.getText());
            //Little extra work here, convert string for int DB field tfNumberOfCredits
            preparedStatement.setInt(5, Integer.parseInt(tfNumberOfCredits.getText()));
            preparedStatement.setString(6, tfCourseID.getText());
            preparedStatement.executeUpdate();
            showAlert("Update worked!");
            //After db change, update list at bottom
            showRecords();
        } catch (SQLException e2) {
            showAlert("Update Failed");
            //clearFields(); //optional clear form on update fail
        }
    }

    private void searchRecord() {
        //type a courseID and search
        String queryString = "Select * from Course where courseId = ?;";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            preparedStatement.setString(1, tfCourseID.getText());
            ResultSet rset = preparedStatement.executeQuery();
            if (rset.next()) {
                tfCourseID.setText(rset.getString("courseID"));
                tfSubjectID.setText(rset.getString("subID"));
                tfCourseNumber.setText(String.valueOf(rset.getInt("courseNumber")));
                tfTitle.setText(rset.getString("title"));
                tfNumberOfCredits.setText(rset.getString("numOfCredits"));
            } else {
                showAlert("No Record Found with ID of " + tfCourseID.getText());
                clearFields();
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            showAlert("Search Failed");
        }
    }

    private void showRecords() {
        //Utility method - update list at bottom of form whenever any update 
        //is called, and on program load
        //TableView would be a good replacement for this logic, 
        //but was more complicated. Keeping this simple. Might
        //add an order by though.
        String queryString = "Select courseID, title, numOfCredits, subjectAbbreviation from course INNER JOIN subject ON course.subid = subject.subjectId";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(queryString);
            ResultSet rset2 = preparedStatement.executeQuery();
            String output = "";
            //Counter here - loop through the retrieved records.
            //If we finish the loop without changing count,
            //DB table was empty
            System.out.println(rset2);
            
            int count = 0;
            taShowRecords.setText("");
            while (rset2.next()) {
                count += 1;
                output = output + rset2.getString("courseID") + " " + rset2.getString("subjectAbbreviation") + " " + rset2.getString("title") + ":   " + rset2.getString("numOfCredits") + " Credits" + "\n";
                
            }
            taShowRecords.setText(output);
            //If count remained 0, no records, so display an error. 
            if (count == 0) {
                taShowRecords.setText("No records found.");
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            showAlert("Insert Failed");
            //clearFields();
        }
    }

    private void clearFields() {
        //Utility method - want to clear the textFields from a few different spots
        tfCourseID.setText("");
        tfSubjectID.setText("");
        tfCourseNumber.setText("");
        tfTitle.setText("");
        tfNumberOfCredits.setText("");
    }

    private void showAlert(String message) {
        //Utility method - wanted easy way to trigger alert box
        //Warning - I set the message up in 3 different spots. 
        //Just pick one - this should be tweaked.
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(message);
        alert.setHeaderText(message);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Here's where we build the connection to the DB. 
    //Double check DB name, user name, and pass!
    private void initializeDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/projectdb", "projectuser", "projectuser");
        } catch (ClassNotFoundException | SQLException ex) {
            showAlert("Connection failed - check DB is created");
        }
    }

    //This kicks off the JavaFX main form 
    public static void main(String[] args) {
        Application.launch(args);
    }
}
