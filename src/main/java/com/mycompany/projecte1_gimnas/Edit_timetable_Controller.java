package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Edit_timetable_Controller {
    Connection conn = DatabaseConnection.getConnection();
    
    
    @FXML
    private TableView<Clase> class_table;
    
    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private TableColumn<Clase, ?> capacity;

    @FXML
    private TableColumn<Clase, ?> class_name;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private TableColumn<Clase, ?> edit;

    @FXML
    private Button editTimeBtn;

    @FXML
    private TableColumn<Clase, ?> instructor;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Button showStatsBtn;

    @FXML
    private TableColumn<Clase, ?> time;

    @FXML
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        App.setRoot("main_panell");
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

}
