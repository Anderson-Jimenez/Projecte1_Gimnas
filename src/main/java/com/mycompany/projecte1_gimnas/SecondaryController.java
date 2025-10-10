package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SecondaryController {

    
    @FXML
    private Button secondaryButton;

    @FXML
    private Label usernameLabel;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}