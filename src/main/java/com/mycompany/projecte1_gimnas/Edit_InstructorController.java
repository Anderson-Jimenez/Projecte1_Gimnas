package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import com.mycompany.projecte1_gimnas.model.Instructor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Edit_InstructorController {

    @FXML
    private TextField addressLabel;

    @FXML
    private Button adm_professionals;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private ComboBox<?> class_name;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private TextField emailLabel;

    @FXML
    private Text fullnameLabel;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private TextField passwdLabel;

    @FXML
    private TextField phoneNumberLabel;
    
    @FXML
    private Button editTimeBtn;
    
    @FXML
    private Text rol;

    @FXML
    private Button showStatsBtn;

    @FXML
    private Button update_instructor_btn;

    @FXML
    private Text username;
    
    private Instructor instructor;

    public void initData(Instructor instructor) {
        this.instructor = instructor;
        // Mostrar los datos en los campos
        fullnameLabel.setText(instructor.getName() + " " + instructor.getSurnames());
        emailLabel.setText(instructor.getEmail());
        phoneNumberLabel.setText(instructor.getPhone());
        addressLabel.setText(instructor.getAddress());
        passwdLabel.setText(instructor.getPassword());
        
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"add_instructors");
    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        
    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"editTimetable");
    }
    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"user_management");

    }

    @FXML
    void professional_administration(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"professional_assign");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"estadistiques");
    }
    @FXML
    void show_instructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"main_panell");
    }


    @FXML
    void update_instructor(ActionEvent event) throws IOException, ClassNotFoundException {
        
        
        String sql = "UPDATE instructor SET email = ?, password = ?, phone = ?, address = ? WHERE id = ?";
             
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌No so s'ha pogut connectar a la base de dades");
            }

            Statement statement = conn.createStatement();
            PreparedStatement stmt = conn.prepareStatement(sql);
                    
            stmt.setString(1, emailLabel.getText());
            stmt.setString(2, passwdLabel.getText());
            stmt.setString(3, phoneNumberLabel.getText());
            stmt.setString(4, addressLabel.getText());
            stmt.setInt(5, instructor.getId());
            
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Instructor actualitzat correctament");

                //Volver a la vista principal de instructors
                FXMLLoader loader = new FXMLLoader(getClass().getResource("professional_assign.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("⚠️ No se ha actualizado ningún registro (¿ID incorrecto?)");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
