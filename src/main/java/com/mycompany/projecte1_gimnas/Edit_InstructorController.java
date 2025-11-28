package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.mycompany.projecte1_gimnas.model.Instructor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Edit_InstructorController {

    @FXML
    private TextField addressLabel;

    @FXML
    private Button adm_professionals;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private ComboBox<?> class_name;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private TextField emailLabel;

    @FXML
    private Text fullnameLabel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private TextField passwdLabel;

    @FXML
    private TextField phoneNumberLabel;
    
    @FXML
    private Button editTimeBtn;
    
    @FXML
    private Text rol;

    @FXML
    private Button showStatsBtn;

    @FXML
    private Button update_instructor_btn;

    @FXML
    private Text username;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    private Instructor instructor;

    public void initData(Instructor instructor) {
        this.instructor = instructor;
        // Mostrar los datos en los campos
        fullnameLabel.setText(instructor.getName() + " " + instructor.getSurnames());
        emailLabel.setText(instructor.getEmail());
        phoneNumberLabel.setText(instructor.getPhone());
        addressLabel.setText(instructor.getAddress());
        passwdLabel.setText(instructor.getPassword());
        statusCombo.setValue(instructor.getStatus());

    }
    @FXML
    public void initialize() throws ClassNotFoundException {
        ObservableList<String> status = FXCollections.observableArrayList("ACTIVE", "INACTIVE", "DE BAIXA");
        statusCombo.setItems(status);
    }
    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"add_instructors");
    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "login");
    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"editTimetable");
    }
    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"user_management");

    }

    @FXML
    void professional_administration(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"professional_assign");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"estadistiques");
    }
    @FXML
    void show_instructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"main_panell");
    }


    @FXML
    void update_instructor(ActionEvent event) throws IOException, ClassNotFoundException {

        String sql = "UPDATE instructor SET email = ?, password = ?, phone = ?, address = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, emailLabel.getText());
            stmt.setString(2, passwdLabel.getText());
            stmt.setString(3, phoneNumberLabel.getText());
            stmt.setString(4, addressLabel.getText());
            stmt.setString(5, statusCombo.getValue());
            stmt.setInt(6, instructor.getId());

            int rows = stmt.executeUpdate();

            if(rows > 0) {
                AppUtils.showAlert("Actualització completada","L'instructor s'ha modificat correctament",Alert.AlertType.INFORMATION);
                AppUtils.changeWindow(event, "class_select");
            } 
            else{
                AppUtils.showAlert("Error Actualitzar dades","No s'ha modificat l'instructor",Alert.AlertType.ERROR);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
