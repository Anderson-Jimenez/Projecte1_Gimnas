package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Professional_assignController {

    @FXML
    private Label Spinning;

    @FXML
    private Button add_instructor_btn;

    @FXML
    private Button adm_professionals;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private Label body_combat;

    @FXML
    private Label body_pump;

    @FXML
    private Label boxing;

    @FXML
    private Label cardio;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private Label crossfit;

    @FXML
    private Label hitt;

    @FXML
    private Label ioga;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Label pilates;

    @FXML
    private Text rol;

    @FXML
    private Button showStatsBtn;

    @FXML
    private Label step;

    @FXML
    private Text username;

    @FXML
    private Label zumba;

    @FXML
    void add_instructor(ActionEvent event) throws IOException {
        App.setRoot("main_panell");
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        App.setRoot("main_panell");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void manageAppointments(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) {

    }

    @FXML
    void professional_administration(ActionEvent event) {

    }

    @FXML
    void showStats(ActionEvent event) {

    }

    @FXML
    void show_body_combat_instructor(MouseEvent event) {

    }

    @FXML
    void show_body_pump_instructor(MouseEvent event) {

    }

    @FXML
    void show_boxing_instructor(MouseEvent event) {

    }

    @FXML
    void show_cardio_instructor(MouseEvent event) {

    }

    @FXML
    void show_crossfit_instructor(MouseEvent event) {

    }

    @FXML
    void show_hitt_instructor(MouseEvent event) {

    }

    @FXML
    void show_pilates_instructor(MouseEvent event) {

    }

    @FXML
    void show_spinning_instructor(MouseEvent event) {

    }

    @FXML
    void show_step_instructor(MouseEvent event) {

    }

    @FXML
    void show_stretching_instructor(MouseEvent event) {

    }

    @FXML
    void show_yoga_instructor(MouseEvent event) {

    }

    @FXML
    void show_zumba_instructor(MouseEvent event) {

    }

}
