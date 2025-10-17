package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class LoginController {

    @FXML
    private AnchorPane login_panell;

    @FXML
    private PasswordField password_input;

    @FXML
    private TextField username_input;

    @FXML
    private void connection_button() throws IOException, SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();

        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
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
                System.out.println("✅ Benvingut al gym!");
                App.setRoot("main_panell");
            } else {
                System.out.println("❌ Usuari o contrasenya incorrectes.");
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
