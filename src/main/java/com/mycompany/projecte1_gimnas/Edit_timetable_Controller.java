package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Edit_timetable_Controller {

    @FXML
    public void initialize() throws ClassNotFoundException {
        ObservableList<String> days = FXCollections.observableArrayList("dilluns", "dimarts", "dimecres", "dijous", "divendres", "dissabte");

        day.setItems(days);
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        class_name.setCellValueFactory(new PropertyValueFactory<>("class_name"));
        instructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("aforament"));
        start_time.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        end_time.setCellValueFactory(new PropertyValueFactory<>("end_time"));

        try {

            Statement statement = conn.createStatement();

            ObservableList<Clase> clases = FXCollections.observableArrayList();

            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM classes");

                while (rs.next()) {
                    String name = rs.getString("name");
                    int instructorT = rs.getInt("fk_id_instructor");
                    int capacityT = rs.getInt("capacity");
                    String date = rs.getString("date");
                    String startT = rs.getString("start_time");
                    String endT = rs.getString("end_time");

                    clases.add(new Clase(name, instructorT, capacityT, date, startT, endT));
                }
                class_table.setItems(clases);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private ComboBox<String> day;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private TableColumn<Clase, Integer> capacity;

    @FXML
    private TableColumn<Clase, String> class_name;

    @FXML
    private TableView<Clase> class_table;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private Button editTimeBtn;

    @FXML
    private TableColumn<Clase, String> end_time;

    @FXML
    private TableColumn<Clase, Integer> instructor;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Button showStatsBtn;

    @FXML
    private TableColumn<Clase, String> start_time;

    @FXML
    private TableColumn<?, ?> delete;

    @FXML
    private TableColumn<?, ?> edit;
    
    @FXML
    private Button addClass;
    
    @FXML
    private Text username;
    
    @FXML
    private Text role;
    
    @FXML
    void addClass(ActionEvent event) throws IOException {
        App.setRoot("editTimetable-addClass");
    }
    
    @FXML 
    void updateTable(ActionEvent event) throws ClassNotFoundException{
        String selected_day = day.getValue();
        System.out.println(selected_day);
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {

            Statement statement = conn.createStatement();

            ObservableList<Clase> clases = FXCollections.observableArrayList();

            try {
                String sql="SELECT * FROM classes WHERE date= ?";
                PreparedStatement stmt=conn.prepareStatement(sql);
                stmt.setString(1,selected_day);
                
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    String instructor_query="SELECT name FROM instructor INNER JOIN classes ON instructor.ID = ?";
                    int instructorT = rs.getInt("fk_id_instructor");
                    int capacityT = rs.getInt("capacity");
                    String date = rs.getString("date");
                    String startT = rs.getString("start_time");
                    String endT = rs.getString("end_time");

                    clases.add(new Clase(name, instructorT, capacityT, date, startT, endT));
                }
                class_table.setItems(clases);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    @FXML
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        App.setRoot("main_panell");

    }

    @FXML
    void manageAppointments(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) {

    }

    @FXML
    void showStats(ActionEvent event) {

    }

}
