package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

    @FXML
    private AnchorPane login_panell;

    @FXML
    private PasswordField password_input;

    @FXML
    private TextField username_input;

    /*
    @FXML
    private void connection_button(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();

        if (conn == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
        }

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name, password FROM administrator");

            boolean loginCorrecte = false;

            while (rs.next()) {
                String usuariBBDD = rs.getString("name");
                String contrasenyaBBDD = rs.getString("password");

                if (username_input.getText().equals(usuariBBDD) && 
                    password_input.getText().equals(contrasenyaBBDD)) {
                    loginCorrecte = true;
                }
            }

            if (loginCorrecte) {
                System.out.println("Benvingut al gym!");
                AppUtils.changeWindow(event, "main_panell");
            } else {
                AppUtils.showAlert("Error dades", "L'usuari o la contrasenya son incorrectes!", Alert.AlertType.INFORMATION);
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    */
    @FXML
    private void connection_button(ActionEvent event) throws ClassNotFoundException {
        // 1. Intentem connectar
        Connection conn = DatabaseConnection.getConnection();

        // 2. Si falla la connexió, sortim de la funció per evitar el NullPointerException
        if (conn == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
        }

        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT name, password FROM administrators");
            boolean loginCorrecte = false;
            String inputUser = username_input.getText();
            String inputPass = password_input.getText();

            while (rs.next()) {
                String usuariBBDD = rs.getString("name");
                String hashBBDD = rs.getString("password"); // El que comença per $2y$
                String hashCorregit = hashBBDD.replaceFirst("^\\$2y\\$", "\\$2a\\$");
                
                // Verifiquem l'usuari i comprovem el hash amb BCrypt
                if (inputUser.equals(usuariBBDD) && org.mindrot.jbcrypt.BCrypt.checkpw(inputPass, hashCorregit)) {
                    loginCorrecte = true;
                }
            }

            if (loginCorrecte) {
                System.out.println("Benvingut al gym!");
                AppUtils.changeWindow(event, "main_panell");
            } else {
                AppUtils.showAlert("Error dades", "L'usuari o la contrasenya són incorrectes!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
