package com.mycompany.projecte1_gimnas.model;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppUtils {
    
    public static void changeWindow(ActionEvent event, String page) throws IOException {
        FXMLLoader loader = new FXMLLoader(AppUtils.class.getResource("/com/mycompany/projecte1_gimnas/" + page + ".fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    // Puedes agregar más métodos utilitarios aquí
    public static void showError(String mensaje) {
        // Implementación para mostrar diálogos de error
    }
}