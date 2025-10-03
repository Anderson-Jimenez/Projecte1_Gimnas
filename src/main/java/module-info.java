module com.mycompany.projecte1_gimnas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.projecte1_gimnas to javafx.fxml;
    exports com.mycompany.projecte1_gimnas;
}
