package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    private Text username;
    
    private User user;
    
    @FXML
    void addUserBtn(ActionEvent event) {

    }

    @FXML
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void closeSession(ActionEvent event) {

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
    public void initData(User user) {
        userName.setText(user.getName());
        userSurnames.setText(user.getSurnames());
        userDNI.setText(user.getDni());
        userEmail.setText(user.getMail());
        userPhone.setText(user.getPhone());
        userIBAN.setText(user.getIban());
        userAddress.setText(user.getAddress());
    }
    @FXML
    private void updateUser(ActionEvent event) {
        if (user == null) {
            System.out.println("❌ No hay usuario cargado");
            return;
        }

        String sql = "UPDATE users SET name = ?, surnames = ?, dni = ?, mail = ?, phone = ?, IBAN = ?, address = ?, password = ? WHERE ID = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ No se pudo conectar a la base de datos");
                return;
            }

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName.getText());
            stmt.setString(2, userSurnames.getText());
            stmt.setString(3, userDNI.getText());
            stmt.setString(4, userEmail.getText());
            stmt.setString(5, userPhone.getText());
            stmt.setString(6, userIBAN.getText());
            stmt.setString(7, userAddress.getText());
            stmt.setString(8, userPassword.getText()); // si quieres actualizar la contraseña
            stmt.setInt(9, user.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Usuario actualizado correctamente");

                // Volver a la ventana principal
                FXMLLoader loader = new FXMLLoader(getClass().getResource("user_management.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Gestión de Usuarios");
                stage.show();
            } else {
                System.out.println("⚠️ No se actualizó ningún registro");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
