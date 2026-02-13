package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

public class Instructor_AddController {

    @FXML
    private Button addInstructor;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private Button editTimeBtn;

    @FXML
    private TextField instructorAddress;

    @FXML
    private TextField instructorDNI;

    @FXML
    private TextField instructorEmail;

    @FXML
    private TextField instructorName;

    @FXML
    private PasswordField instructorPassword;

    @FXML
    private TextField instructorPhone;

    @FXML
    private TextField instructorSurnames;

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
    private Text username;

    @FXML
    void addInstructorrBtn(ActionEvent event) throws IOException, ClassNotFoundException {
        boolean flag=false;
        String username =instructorName.getText();
        String dni=instructorDNI.getText();

        try (Connection conn1 = DatabaseConnection.getConnection()) {

            String sql = "SELECT dni FROM instructors WHERE dni = ?";
            PreparedStatement stmt = conn1.prepareStatement(sql);
            stmt.setString(1, dni);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                flag = true; //si en aquesta consulta hi ha resultats, significa que ja existeix un dni
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si existeix el dni, mostra una alarma
        if (flag) {
            AppUtils.showAlert("Usuari ja registrat", "Ja existeix un usuari amb aquest DNI",Alert.AlertType.ERROR );
        }

        // Recollir dades formulari
        String surnames = instructorSurnames.getText();
        String plainPassword = instructorPassword.getText();
        
        String hashedPassword=BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        
        String email = instructorEmail.getText();
        String phone = instructorPhone.getText();
        String address = instructorAddress.getText();
        String status = "ACTIVE";

        // INSERT correcto en la tabla users
        try (Connection conn2 = DatabaseConnection.getConnection()) {

            String sql = "INSERT INTO instructors (surnames, name, dni, password, email, phone, address, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn2.prepareStatement(sql);
            stmt.setString(1, surnames);
            stmt.setString(2, username);
            stmt.setString(3, dni);
            stmt.setString(4, hashedPassword);
            stmt.setString(5, email);
            stmt.setString(6, phone);
            stmt.setString(7, address);
            stmt.setString(8, status);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Cambiar de ventana tras insertar
        //AppUtils.showAlert("Usuari ja registrat", "Ja existeix un usuari amb aquest DNI",Alert.AlertType.ERROR );
        AppUtils.changeWindow(event, "class_select");
    }

    @FXML
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "login");
    }

    @FXML
    void editTimetable(ActionEvent event) {

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
