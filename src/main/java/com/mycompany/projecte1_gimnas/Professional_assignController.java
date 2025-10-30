package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.Instructor;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.text.Text;

public class Professional_assignController {

    // ===== Labels de clases =====
    @FXML private Label Spinning;
    @FXML private Label Ioga;
    @FXML private Label BodyPump;
    @FXML private Label Crossfit;
    @FXML private Label Zumba;
    @FXML private Label Pilates;
    @FXML private Label Boxing;
    @FXML private Label HITT;
    @FXML private Label Step;
    @FXML private Label BodyCombat;
    @FXML private Label Stretching;
    @FXML private Label Cardio;

    // ===== Contenedores de cada clase =====
    @FXML private StackPane spinning_box;
    @FXML private StackPane ioga_box;
    @FXML private StackPane body_pump_box;
    @FXML private StackPane crossfit_box;
    @FXML private StackPane zumba_box;
    @FXML private StackPane pilates_box;
    @FXML private StackPane boxing_box;
    @FXML private StackPane hitt_box;
    @FXML private StackPane step_box;
    @FXML private StackPane body_combat_box;
    @FXML private StackPane stretching_box;
    @FXML private StackPane cardio_box;

    // Contenedor principal (donde pondremos la tabla)
    @FXML private AnchorPane mainBackground;

    
    // ADMINISTRACION BARRA NAVEGACION Y AÑADIR INSTRUCTOR

    @FXML
    void add_instructor(ActionEvent event) {
        
    }

    @FXML
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void manageAppointments(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) {

    }

    @FXML
    void professional_administration(ActionEvent event) throws IOException {
        App.setRoot("main_panell");
    }

    @FXML
    void showStats(ActionEvent event) {

    }
    
    
    
    
    
    // ===== EVENTOS DE CLICK EN CLASES ===== //


    @FXML void show_spinning_instructor(MouseEvent event) {
        showInstructorTable(Spinning.getText()); 
    }
    
    @FXML void show_zumba_instructor(MouseEvent event) {
        showInstructorTable(Zumba.getText()); 
    }
    
    @FXML void show_crossfit_instructor(MouseEvent event) {
        showInstructorTable(Crossfit.getText()); 
    }
    
    @FXML void show_body_combat_instructor(MouseEvent event) {
        showInstructorTable(BodyCombat.getText()); 
    }
    
    @FXML void show_body_pump_instructor(MouseEvent event) {
        showInstructorTable(BodyPump.getText()); 
    }
    
    @FXML void show_boxing_instructor(MouseEvent event) {
        showInstructorTable(Boxing.getText()); 
    }
    
    @FXML void show_hitt_instructor(MouseEvent event) {
        showInstructorTable(HITT.getText()); 
    }
    
    @FXML void show_pilates_instructor(MouseEvent event) {
        showInstructorTable(Pilates.getText()); 
    }
    
    @FXML void show_step_instructor(MouseEvent event) {
        showInstructorTable(Step.getText()); 
    }
    
    @FXML void show_stretching_instructor(MouseEvent event) {
        showInstructorTable(Stretching.getText()); 
    }
    
    @FXML void show_cardio_instructor(MouseEvent event) {
        showInstructorTable(Cardio.getText()); 
    }
    
    @FXML void show_yoga_instructor(MouseEvent event) {
        showInstructorTable(Ioga.getText()); 
    }

    // ======================================
    // ===== LÓGICA PARA MOSTRAR TABLA ======
    // ======================================

    private void showInstructorTable(String className) {
        // Ocultar todos los cuadros de clases
        spinning_box.setVisible(false);
        ioga_box.setVisible(false);
        body_pump_box.setVisible(false);
        crossfit_box.setVisible(false);
        zumba_box.setVisible(false);
        pilates_box.setVisible(false);
        boxing_box.setVisible(false);
        hitt_box.setVisible(false);
        step_box.setVisible(false);
        body_combat_box.setVisible(false);
        stretching_box.setVisible(false);
        cardio_box.setVisible(false);

        // Crear una tabla simple de instructores
        TableView<Instructor> table = new TableView<>();

        TableColumn<Instructor, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Instructor, String> emailCol = new TableColumn<>("Surnames");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("Surnames"));

        TableColumn<Instructor, String> phoneCol = new TableColumn<>("DNI");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("dni"));

        table.getColumns().addAll(nameCol, emailCol, phoneCol);

        // Datos de ejemplo (puedes cambiarlos por los de tu base de datos)
        ObservableList<Instructor> data = FXCollections.observableArrayList(
            new Instructor("Garcia", "Laura", "612345678"),
            new Instructor("Pérez", "Carlos", "698765432")
        );
        table.setItems(data);

        /* Botón para volver a las clases
        Button backBtn = new Button("Volver");
        backBtn.setOnAction(e -> showClasses());*/


        System.out.println("Mostrando tabla de instructores de " + className);
    }

    private void showClasses() {
        // Al volver, limpiamos el contenido y mostramos los cuadros
        mainBackground.getChildren().clear();

        spinning_box.setVisible(true);
        ioga_box.setVisible(true);
        body_pump_box.setVisible(true);
        crossfit_box.setVisible(true);
        zumba_box.setVisible(true);
        pilates_box.setVisible(true);
        boxing_box.setVisible(true);
        hitt_box.setVisible(true);
        step_box.setVisible(true);
        body_combat_box.setVisible(true);
        stretching_box.setVisible(true);
        cardio_box.setVisible(true);
    }
}
