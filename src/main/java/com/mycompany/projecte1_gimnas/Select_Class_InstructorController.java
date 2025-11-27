package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import com.mycompany.projecte1_gimnas.model.Instructor;
import com.mycompany.projecte1_gimnas.model.InstructorCard;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Select_Class_InstructorController {
    
    @FXML
    private Label BodyCombat;

    @FXML
    private Label BodyPump;

    @FXML
    private Label Boxing;

    @FXML
    private Label Cardio;

    @FXML
    private Label Crossfit;

    @FXML
    private Label HITT;

    @FXML
    private Label Ioga;

    @FXML
    private Label Pilates;

    @FXML
    private Label Spinning;

    @FXML
    private Label Step;

    @FXML
    private Label Stretching;

    @FXML
    private Label Zumba;

    @FXML
    private Button add_instructor_btn;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private StackPane body_combat_box;

    @FXML
    private StackPane body_pump_box;

    @FXML
    private StackPane boxing_box;

    @FXML
    private StackPane cardio_box;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private StackPane crossfit_box;

    @FXML
    private Button editTimeBtn;

    @FXML
    private StackPane hitt_box;

    @FXML
    private StackPane ioga_box;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private StackPane pilates_box;

    @FXML
    private Text rol;

    @FXML
    private Button showStatsBtn;

    @FXML
    private StackPane spinning_box;

    @FXML
    private StackPane step_box;

    @FXML
    private StackPane stretching_box;

    @FXML
    private Text username;
    
    @FXML
    private ScrollPane instructorScroll;
    
    @FXML
    private StackPane zumba_box;
    
    private String selected_class;
    
    @FXML
    void add_instructor(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "add_instructor");
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "class_select");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "editTimetable");
    }

    @FXML
    void manageAppointments(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "");
    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "user_management");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "estadistiques");
    }

    @FXML
    void show_body_combat_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = BodyCombat.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_body_pump_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = BodyPump.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_boxing_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Boxing.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_cardio_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Cardio.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_crossfit_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Crossfit.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_hitt_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = HITT.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_pilates_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Pilates.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_spinning_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Spinning.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_step_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Step.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_stretching_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Stretching.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_yoga_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Ioga.getText();
        see_intructors(selected_class);
    }

    @FXML
    void show_zumba_instructor(MouseEvent event) throws ClassNotFoundException {
        selected_class = Zumba.getText();
        see_intructors(selected_class);
    }
    
    private void see_intructors(String class_name) throws ClassNotFoundException{
        String selected_class = class_name;
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {

            Statement statement = conn.createStatement();

            ObservableList<Instructor> instructors = FXCollections.observableArrayList();

            
                String sql="SELECT DISTINCT i.ID, i.name, i.surnames, i.dni, i.password, i.email, i.phone, i.address, i.status FROM instructor i INNER JOIN classes c ON i.ID = c.fk_id_instructor WHERE c.name = ?";
                PreparedStatement stmt=conn.prepareStatement(sql);
                stmt.setString(1,selected_class);
                
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String surnames = rs.getString("surnames");
                    
                    String name = rs.getString("name");
                    String dni = rs.getString("dni");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String status = rs.getString("status");

                    instructors.add(new Instructor(id, surnames,name,dni,password,email,phone,address,status));
                }

            conn.close();
            VBox container = new VBox(15);
            container.setPadding(new Insets(20));
            container.setFillWidth(true);

            for (Instructor instructor : instructors) {
                InstructorCard card = new InstructorCard(instructor);
                container.getChildren().add(card);
            }

            // Asegúrate de que el ScrollPane ajuste el contenido
            instructorScroll.setContent(container);
            instructorScroll.setFitToWidth(true);
            
            instructorScroll.getStyleClass().remove("hidden");
            hideAllClassBoxes();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void hideAllClassBoxes() {
        body_combat_box.getStyleClass().add("hidden");
        body_pump_box.getStyleClass().add("hidden");
        boxing_box.getStyleClass().add("hidden");
        cardio_box.getStyleClass().add("hidden");
        crossfit_box.getStyleClass().add("hidden");
        hitt_box.getStyleClass().add("hidden");
        ioga_box.getStyleClass().add("hidden");
        pilates_box.getStyleClass().add("hidden");
        spinning_box.getStyleClass().add("hidden");
        step_box.getStyleClass().add("hidden");
        stretching_box.getStyleClass().add("hidden");
        zumba_box.getStyleClass().add("hidden");
    }
}
