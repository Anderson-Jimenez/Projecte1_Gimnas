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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

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
    private TableColumn<Instructor, Void> edit;

    @FXML
    private TableColumn<Instructor, Void> delete;

    @FXML
    private TableView<Instructor> instructor_table;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Text rol;

    @FXML
    private Button showStatsBtn;

    @FXML
    private Text username;

    @FXML
    public void initialize() throws ClassNotFoundException {
        // Llenar ComboBox
        ObservableList<String> class_names = FXCollections.observableArrayList(
            "Spinning", "Ioga", "BodyPump", "Crossfit", "Zumba",
            "Pilates", "Boxing", "HITT", "Step", "BodyCombat",
            "Stretching", "Cardio"
        );
        class_name.setItems(class_names);

        // Configurar columnas
        instructor_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        instructor_surnames.setCellValueFactory(new PropertyValueFactory<>("surnames"));
        instructor_dni.setCellValueFactory(new PropertyValueFactory<>("dni"));

        // Agregar botones Editar y Eliminar
        agregarBotonesEditarYEliminar();

        // Cargar datos
        cargarInstructores();
    }

    private void cargarInstructores() throws ClassNotFoundException {
        ObservableList<Instructor> lista = FXCollections.observableArrayList();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name, surnames, dni FROM instructor")) {

            while (rs.next()) {
                String name = rs.getString("name");
                String surnames = rs.getString("surnames");
                String dni = rs.getString("dni");
                lista.add(new Instructor(name, surnames, dni));
            }

            instructor_table.setItems(lista);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void agregarBotonesEditarYEliminar() {
        // Botón Editar
        edit.setCellFactory(col -> new TableCell<Instructor, Void>() {
            private final Button btn = new Button("Editar");
            {
                btn.setOnAction(e -> {
                    Instructor ins = getTableView().getItems().get(getIndex());
                    System.out.println("📝 Editar instructor: " + ins.getName());
                    // Aquí abrirías tu formulario de edición
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        // Botón Eliminar
        delete.setCellFactory(col -> new TableCell<Instructor, Void>() {
            private final Button btn = new Button("Eliminar");
            {
                btn.setOnAction(e -> {
                    Instructor ins = getTableView().getItems().get(getIndex());
                    eliminarInstructor(ins);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void eliminarInstructor(Instructor ins) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM instructor WHERE dni = ?")) {

            stmt.setString(1, ins.getDni());
            stmt.executeUpdate();
            instructor_table.getItems().remove(ins);
            System.out.println("❌ Instructor eliminado: " + ins.getName());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void add_instructor(ActionEvent event) {}

    @FXML
    void assignInstructors(ActionEvent event) {}

    @FXML
    void closeSession(ActionEvent event) {}

    @FXML
    void manageAppointments(ActionEvent event) {}

    @FXML
    void manageClients(ActionEvent event) {}

    @FXML
    void professional_administration(ActionEvent event) throws IOException {
        App.setRoot("main_panell");
    }

    @FXML
    void show_instructors(ActionEvent event) throws ClassNotFoundException {
        String selected_class = class_name.getValue();
        System.out.println(selected_class);

        Connection conn = DatabaseConnection.getConnection();
        if (conn == null) {
            System.out.println("❌ No se pudo conectar a la base de datos.");
        }

        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT DISTINCT i.name, i.surnames, i.dni " +
                "FROM instructor i " +
                "INNER JOIN classes c ON c.fk_id_instructor = i.ID " +
                "WHERE c.name = ?")) {

            stmt.setString(1, selected_class);
            ResultSet rs = stmt.executeQuery();

            ObservableList<Instructor> instructors = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("name");
                String surnames = rs.getString("surnames");
                String dni = rs.getString("dni");
                instructors.add(new Instructor(name, surnames, dni));
            }

            instructor_table.setItems(instructors);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showStats(ActionEvent event) {}
}
