package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.Instructor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Professional_assignController {

    @FXML 
    private Button add_instructor_btn;
    @FXML 
    private Button assignInstructorsBtn;
    @FXML 
    private ComboBox<String> class_name;
    @FXML 
    private Button closeSessionBtn;
    @FXML 
    private TableColumn<Instructor, String> instructor_dni;
    @FXML 
    private TableColumn<Instructor, String> instructor_name;
    @FXML 
    private TableColumn<Instructor, String> instructor_surnames;
    @FXML 
    private TableColumn<Instructor, String> instructor_password;
    @FXML 
    private TableColumn<Instructor, String> instructor_email;
    @FXML 
    private TableColumn<Instructor, String> instructor_number;
    @FXML 
    private TableColumn<Instructor, String> instructor_address;
    @FXML 
    private TableColumn<Instructor, String> instructor_status;


    @FXML private TableColumn<Instructor, Void> edit;
    @FXML private TableColumn<Instructor, Void> delete;
    @FXML private TableView<Instructor> instructor_table;
    @FXML private AnchorPane mainBackground;
    @FXML private Button manageAppointmentsBtn;
    @FXML private Button manageClientsBtn;
    @FXML private Button edit_instructor_btn;
    @FXML private Button delete_instructor_btn;
    @FXML private Text rol;
    @FXML private Button showStatsBtn;
    @FXML private Text username;

    @FXML
    public void initialize() throws ClassNotFoundException {
        // Llenar ComboBox
        ObservableList<String> class_names = FXCollections.observableArrayList(
            "Spinning", "Ioga", "BodyPump", "Crossfit", "Zumba",
            "Pilates", "Boxing", "HITT", "Step", "BodyCombat",
            "Stretching", "Cardio"
        );
        class_name.setItems(class_names);

        // Configurar columnas visibles
        instructor_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        instructor_surnames.setCellValueFactory(new PropertyValueFactory<>("surnames"));
        instructor_dni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        instructor_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        instructor_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        instructor_number.setCellValueFactory(new PropertyValueFactory<>("phone"));

        instructor_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        instructor_status.setCellValueFactory(new PropertyValueFactory<>("status"));



        // Cargar datos iniciales
        cargarInstructores();
    }

    private void cargarInstructores() throws ClassNotFoundException {
        ObservableList<Instructor> lista = FXCollections.observableArrayList();

        String sql = "SELECT * FROM instructor";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                String surnames = rs.getString("surnames");
                String dni = rs.getString("dni");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String status = rs.getString("status");

                lista.add(new Instructor(id, name, surnames, dni, password, email, phone, address, status));
            }

            instructor_table.setItems(lista);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update_instructor(ActionEvent event) throws ClassNotFoundException {
        Instructor ins = instructor_table.getSelectionModel().getSelectedItem();

        if (ins != null) {
            System.out.println("Eliminant instructor: " + ins.getName());

            try (Connection conn = DatabaseConnection.getConnection()) {
                if (conn == null) {
                    System.out.println("❌No so s'ha pogut connectar a la base de dades");
                }
                
                String selectSql = "SELECT status FROM instructor WHERE dni = ?";
                PreparedStatement selectStmt = conn.prepareStatement(selectSql);
                selectStmt.setString(1, ins.getDni());
                ResultSet rs = selectStmt.executeQuery();
                
                if(rs.next()){
                    String currentStatus = rs.getString("status");
                    String newStatus;
                    if(currentStatus.equalsIgnoreCase("active")){
                        newStatus = "inactive";
                    }
                    else{
                        newStatus = "active";
                    }
                   
                    String updateSql = "UPDATE instructor SET status = ? WHERE dni = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setString(1, newStatus);
                    updateStmt.setString(2, ins.getDni());
                    int rowsAffected = updateStmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("✅ Instructor " + ins.getName() + " ahora está " + newStatus);
                        cargarInstructores(); // refrescar tabla
                    } 
                    
                }
                
                // Actualizar tabla
                cargarInstructores();

                System.out.println("✅Instructor eliminat correctament");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Selecciona l'instructor que vols desactivar");
            alerta.show();
        }
    }

    @FXML
    void edit_instructor(ActionEvent event) throws IOException {
        Instructor ins = instructor_table.getSelectionModel().getSelectedItem();

        if (ins != null) {
            System.out.println("📝 Editar instructor: " + ins.getName());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_instructor.fxml"));
            Parent root = loader.load();
            
            //Es demana el controlador de la vista que volem obrir per editar dades
            Edit_InstructorController ctrl = loader.getController();
            //Es pasen les dades de l'instructor al nou controlador
            ctrl.initData(ins);
            
            //Canviar l'escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Instructor");
            stage.show();
            
            
            
            
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Selecciona l'instructor que vols editar");
            alerta.show();
        }
    }

    @FXML
    void show_instructors(ActionEvent event) throws ClassNotFoundException {
        String selected_class = class_name.getValue();

        if (selected_class == null) {
            System.out.println("Debes seleccionar una clase.");
            return;
        }

        System.out.println("Mostrando instructores de la clase: " + selected_class);

        ObservableList<Instructor> instructors = FXCollections.observableArrayList();

        String sql = "SELECT DISTINCT i.ID, i.name, i.surnames, i.dni, i.password, i.email, i.phone, i.address, i.status FROM instructor i INNER JOIN classes c ON c.fk_id_instructor = i.ID WHERE c.name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, selected_class);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                String surnames = rs.getString("surnames");
                String dni = rs.getString("dni");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String status = rs.getString("status");

                instructors.add(new Instructor(id, name, surnames, dni, password, email, phone, address, status));
            }

            instructor_table.setItems(instructors);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Métodos vacíos por ahora (según necesidad futura)
    @FXML void add_instructor(ActionEvent event) throws IOException {
        App.setRoot("main_panell");

    }
    @FXML void assignInstructors(ActionEvent event) {
        
    }
    @FXML void closeSession(ActionEvent event) {
    }
    @FXML void manageAppointments(ActionEvent event) {
    }
    @FXML void manageClients(ActionEvent event) {
    }
    @FXML void showStats(ActionEvent event) {
    }
    @FXML void professional_administration(ActionEvent event) throws IOException {
        App.setRoot("main_panell");
    }
}
