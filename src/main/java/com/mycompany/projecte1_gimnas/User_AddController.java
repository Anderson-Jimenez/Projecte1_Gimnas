package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class User_AddController {

    @FXML
    private Button addUser;

    @FXML
    private Button assignInstructorsBtn;

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
    private Text role;

    @FXML
    private Button showStatsBtn;

    @FXML
    private TextField userAddress;

    @FXML
    private TextField userDNI;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userIBAN;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userPhone;

    @FXML
    private TextField userSurnames;

    @FXML
    void addUserBtn(ActionEvent event) throws IOException, ClassNotFoundException {
        boolean flag=false;
        String username =userName.getText();
        String iban = userIBAN.getText();
        String dni=userDNI.getText();

        try (Connection conn1 = DatabaseConnection.getConnection()) {

            String sql = "SELECT dni FROM users WHERE dni = ? OR IBAN = ?";
            PreparedStatement stmt = conn1.prepareStatement(sql);
            stmt.setString(1, dni);
            stmt.setString(2, iban);


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                flag = true; //si en aquesta consulta hi ha resultats, significa que ja existeix un dni
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Si existeix el dni, mostra una alarma
        if (flag) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Ja existeix un usuari amb aquest DNI o IBAN");
            a.show();
        }

        // Recollir dades formulari
        String surnames = userSurnames.getText();
        String password = userPassword.getText();
        
        String email = userEmail.getText();
        String phone = userPhone.getText();
        String address = userAddress.getText();
        String status = "ACTIVE";

        // INSERT correcto en la tabla users
        try (Connection conn2 = DatabaseConnection.getConnection()) {

            String sql = "INSERT INTO users (surnames, name, dni, password, mail, phone, IBAN, address, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn2.prepareStatement(sql);
            stmt.setString(1, surnames);
            stmt.setString(2, username);
            stmt.setString(3, dni);
            stmt.setString(4, password);
            stmt.setString(5, email);
            stmt.setString(6, phone);
            stmt.setString(7, iban);
            stmt.setString(8, address);
            stmt.setString(9, status);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Cambiar de ventana tras insertar
        AppUtils.changeWindow(event, "user_management");
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "class_select");
    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"edit_timtable");
    }


    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"user_managment");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"estadistiques");
    }

}
