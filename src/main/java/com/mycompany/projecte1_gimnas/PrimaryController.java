package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        //App.setRoot("secondary");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/mycompany/myapp/check-view.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Verificación de conexión");
        stage.setScene(scene);
        stage.show();
    }
}
