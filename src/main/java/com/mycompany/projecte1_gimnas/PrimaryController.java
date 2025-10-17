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

public class PrimaryController {

    @FXML
    private AnchorPane loginPanell;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private void connectionButton() throws IOException, SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String connectionQuery = "SELECT * FROM administrator";
        
        try {
            if (conn != null) {
                Statement statement = conn.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectionQuery);

                while (queryOutput.next()) {
                    usernameInput.setText(queryOutput.getString("name"));
                }

                conn.close();
            } else {
                System.out.println("❌ No se pudo establecer la conexión con la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageView imageView = new ImageView(new Image(getClass().getResource("/img/bg_principal.jpeg").toExternalForm()));
        App.setRoot("main_panell");
    }
}
