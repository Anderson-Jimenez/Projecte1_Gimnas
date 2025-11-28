package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.User;
import com.mycompany.projecte1_gimnas.model.AppUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Edit_UserController {

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
    private ComboBox<String> userStatus;

    @FXML
    private Text username;
    
    private User user;
    
    @FXML
    void addUserBtn(ActionEvent event) {

    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "class_select");
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
        AppUtils.changeWindow(event,"user_managment");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"estadistiques");
    }
    public void initData(User user) {
        this.user = user;
        userName.setText(user.getName());
        userSurnames.setText(user.getSurnames());
        userDNI.setText(user.getDni());
        userEmail.setText(user.getMail());
        userPhone.setText(user.getPhone());
        userIBAN.setText(user.getIban());
        userAddress.setText(user.getAddress());
        userStatus.setValue(user.getStatus());
    }
    @FXML
    public void initialize() throws ClassNotFoundException {
        ObservableList<String> status = FXCollections.observableArrayList("ACTIVE", "INACTIVE");
        userStatus.setItems(status);
    }
    @FXML
    private void updateUser(ActionEvent event) {
        if (user == null) {
            AppUtils.showAlert("Error", "No hay usuario cargado", Alert.AlertType.ERROR);
        }

        String sql = "UPDATE users SET name = ?, surnames = ?, dni = ?, mail = ?, phone = ?, IBAN = ?, address = ?, password = ?, status = ? WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                AppUtils.showAlert("Error", "No se ha podido conectar con la base de datos", Alert.AlertType.ERROR);
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName.getText());
            stmt.setString(2, userSurnames.getText());
            stmt.setString(3, userDNI.getText());
            stmt.setString(4, userEmail.getText());
            stmt.setString(5, userPhone.getText());
            stmt.setString(6, userIBAN.getText());
            stmt.setString(7, userAddress.getText());
            //Control·lar si la contrassenya es buida o no
            if (userPassword.getText().isEmpty()) {
                stmt.setString(8, user.getPassword());
            } else {
                stmt.setString(8, userPassword.getText());
            }
            stmt.setString(9, userStatus.getValue());
            stmt.setInt(10, user.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                AppUtils.showAlert("Correcte", "L'instructor s'ha actualitzat correctament", Alert.AlertType.INFORMATION);
                AppUtils.changeWindow(event, "user_management");
            } else {
                AppUtils.showAlert("Avís", "No s'han modificat les dades", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            e.printStackTrace();
            AppUtils.showAlert("Error", "Hi ha hagut un error en editar l'usuari", Alert.AlertType.ERROR);
        }
    }
    
}