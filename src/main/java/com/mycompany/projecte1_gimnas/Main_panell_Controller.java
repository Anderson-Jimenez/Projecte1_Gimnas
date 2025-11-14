package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        fxmlLoader(event, "professional_assign");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        fxmlLoader(event, "editTimetable");
    }

    @FXML
    void manageAppointments(ActionEvent event) {
        
    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        App.setRoot("user_management");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        fxmlLoader(event, "estadistiques");
    }
    
    public void fxmlLoader(ActionEvent event, String pagina) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pagina+".fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
