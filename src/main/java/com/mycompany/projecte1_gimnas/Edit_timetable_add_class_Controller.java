package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Edit_timetable_add_class_Controller {
    
    @FXML
    public void initialize() throws ClassNotFoundException {
        ObservableList<String> days = FXCollections.observableArrayList("dilluns", "dimarts", "dimecres", "dijous", "divendres", "dissabte");
        day.setItems(days);
        
        ObservableList<String> hours = FXCollections.observableArrayList("06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00");
        hour.setItems(hours);
        
        ObservableList<String> typeClasses = FXCollections.observableArrayList("Spinning", "Ioga","BodyPump","Crossfit","Zumba","Pilates","Stretching","Cardio","BodyCombat","HIIT","Boxing","Step");
        typeClass.setItems(typeClasses);
        
        ObservableList<Integer> instructors = FXCollections.observableArrayList();
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM instructor");

            while (rs.next()) {
                int instructorT = rs.getInt("ID");   
                instructors.add(instructorT);
            }
            
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        instructor.setItems(instructors);
        
        ObservableList<Integer> capacitys = FXCollections.observableArrayList(16,20,32);
        capacity.setItems(capacitys);
    }
        
    @FXML
    private Button addClass;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private ComboBox<Integer> capacity;
    
    @FXML
    private Button closeSessionBtn;

    @FXML
    private ComboBox<String> day;

    @FXML
    private Button editTimeBtn;

    @FXML
    private ComboBox<String> hour;

    @FXML
    private ComboBox<Integer> instructor;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Text role;

    @FXML
    private Button showStatsBtn;

    @FXML
    private ComboBox<String> typeClass;

    @FXML
    private Text username;

    @FXML
    void addClass(ActionEvent event) throws IOException, ClassNotFoundException {
        String selected_day=day.getValue();
        String selected_hour=hour.getValue();
        String selected_class=typeClass.getValue();
        int selected_instructor=instructor.getValue();
        int selectedCapacity=capacity.getValue();
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {
                String sql="INPUT";
                PreparedStatement stmt=conn.prepareStatement(sql);
                stmt.setString(1,selected_day);
                
                ResultSet rs = stmt.executeQuery();
            
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        App.setRoot("editTimetable");
    }

    @FXML
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void capacitySelect(ActionEvent event) {

    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void daySelect(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        App.setRoot("editTimetable");
    }

    @FXML
    void hourSelect(ActionEvent event) {

    }

    @FXML
    void instructorSelect(ActionEvent event) {

    }

    @FXML
    void manageAppointments(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) {

    }

    @FXML
    void showStats(ActionEvent event) {

    }

    @FXML
    void typeClassSelect(ActionEvent event) {

    }
}