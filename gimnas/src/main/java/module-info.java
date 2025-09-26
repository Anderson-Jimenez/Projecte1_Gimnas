module com.mycompany.gimnas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.gimnas to javafx.fxml;
    exports com.mycompany.gimnas;
}
