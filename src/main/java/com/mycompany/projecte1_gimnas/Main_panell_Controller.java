package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Main_panell_Controller {

    @FXML
    private Button assignInstructorsBtn;
    @FXML
    private Text rol;
    
    @FXML
    private Text username;
    
    @FXML
    private Button closeSessionBtn;

    @FXML
    private Button editTimeBtn;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Button showStatsBtn;

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        App.setRoot("professional_assign");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        App.setRoot("editTimetable");
    }

    @FXML
    void manageAppointments(ActionEvent event) {
        
    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        App.setRoot("user_management");
    }

    @FXML
    void showStats(ActionEvent event) {

    }

}
