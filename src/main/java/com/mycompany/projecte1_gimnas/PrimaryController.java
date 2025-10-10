package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrimaryController {

    @FXML
    private Button primaryButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private void connectionButton() throws IOException, SQLException, ClassNotFoundException {
        // Obtener la conexión desde tu clase DatabaseConnection
        Connection conn = DatabaseConnection.getConnection();

        String connectionQuery = "SELECT * FROM administrator";

        try {
            if (conn != null) {
                Statement statement = conn.createStatement();
                ResultSet queryOutput = statement.executeQuery(connectionQuery);

                while (queryOutput.next()) {
                    usernameLabel.setText(queryOutput.getString("name"));
                }

                conn.close();
            } else {
                System.out.println("❌ No se pudo establecer la conexión con la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        App.setRoot("main_panell");

    }
}
