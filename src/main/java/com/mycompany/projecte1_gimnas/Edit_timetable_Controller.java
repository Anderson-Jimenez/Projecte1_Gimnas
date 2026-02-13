package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.Clase;
import com.mycompany.projecte1_gimnas.model.AppUtils;

import com.mycompany.projecte1_gimnas.model.Instructor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Edit_timetable_Controller {

    private String day;
    
    @FXML
    public void initialize() throws ClassNotFoundException {            
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
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
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int instructorT = rs.getInt("instructor_id");
                    int capacityT = rs.getInt("capacity");
                    java.sql.Date sqlDate = rs.getDate("date");
                    LocalDate date = sqlDate != null ? sqlDate.toLocalDate() : null;
                    
                    String startT = rs.getString("start_time");
                    String endT = rs.getString("end_time");

                    clases.add(new Clase(id, name, instructorT, capacityT, date, startT, endT));
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
    private Button editClass;
    
    @FXML
    private Button addClass;
    
    @FXML
    private Button deleteClass;
    
    @FXML
    private Text username;
    
    @FXML
    private Text role;
    
    @FXML
    private Button checkInfo;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    void checkInfo(ActionEvent event) throws IOException {
        Clase clase = class_table.getSelectionModel().getSelectedItem();

        if (clase != null) {
            System.out.println("Editar classe: " + clase.getClass_name());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkInfo.fxml"));
            Parent root = loader.load();
            
            //Es demana el controlador de la vista que volem obrir per editar dades
            classInfoController ctrl = loader.getController();
            //Es pasen les dades de l'instructor al nou controlador
            ctrl.initData(clase);

            //Canviar l'escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Informacio Clase");
            stage.show();
            

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Selecciona la classe que vols editar");
            alerta.show();
        }
    }
      
    @FXML
    void addClass(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "editTimetable-addClass");
    }

    @FXML
    void editClass(ActionEvent event) throws IOException {
        Clase clase = class_table.getSelectionModel().getSelectedItem();

        if (clase != null) {
            System.out.println("📝 Editar classe: " + clase.getClass_name());
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editClass.fxml"));
            Parent root = loader.load();
            
            //Es demana el controlador de la vista que volem obrir per editar dades
            EditClassController ctrl = loader.getController();
            //Es pasen les dades de l'instructor al nou controlador
            ctrl.initData(clase);

            //Canviar l'escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Classe");
            stage.show();
            

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Selecciona la classe que vols editar");
            alerta.show();
        }
    }
    
    @FXML
    void deleteClass(ActionEvent event) throws ClassNotFoundException {
        Clase selectedClass=class_table.getSelectionModel().getSelectedItem();
        if(selectedClass!=null){
            System.out.println(selectedClass.getClass_name());
            
            Connection conn = DatabaseConnection.getConnection();
        
            if (conn == null) {
                System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
            }

            try {

                Statement statement = conn.createStatement();

                try {
                    String sql="DELETE FROM classes WHERE name= ? AND date=? AND start_time=?";
                    
                    PreparedStatement stmt=conn.prepareStatement(sql);
                    stmt.setString(1,selectedClass.getClass_name());
                    
                    if (selectedClass.getDate() != null){
                        stmt.setDate(2, java.sql.Date.valueOf(selectedClass.getDate()));
                    }
                     
                    stmt.setString(3,selectedClass.getStart_time());

                    stmt.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            loadAllClasses();
        }
    }
    
    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "class_select");
    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "login");
    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "main_panell");

    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "user_management");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "estadistiques");
    }
    
    /*======================================Funcions====================================*/

    public void changeEditTimetableStyle(){
        class_table.getStyleClass().remove("hidden");
        addClass.getStyleClass().remove("hidden");
        editClass.getStyleClass().remove("hidden");
        deleteClass.getStyleClass().remove("hidden");
        checkInfo.getStyleClass().remove("hidden");
    }
    
    private void updateTable(String dateString) throws ClassNotFoundException{
                
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {
            ObservableList<Clase> clases = FXCollections.observableArrayList();

            try {
                String sql="SELECT * FROM classes WHERE date= ?";
                PreparedStatement stmt=conn.prepareStatement(sql);
                
                LocalDate localDate = LocalDate.parse(dateString);
                stmt.setDate(1,java.sql.Date.valueOf(localDate));
                
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String instructor_query="SELECT name FROM instructor INNER JOIN classes ON instructor.ID = ?";
                    int instructorT = rs.getInt("instructor_id");
                    int capacityT = rs.getInt("capacity");
                    java.sql.Date sqlDate = rs.getDate("date");
                    LocalDate date = sqlDate != null ? sqlDate.toLocalDate() : null;
                    String startT = rs.getString("start_time");
                    String endT = rs.getString("end_time");

                    clases.add(new Clase(id, name, instructorT, capacityT, date, startT, endT));
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
    void selectDate(ActionEvent event) throws ClassNotFoundException{
        LocalDate selectedDate =datePicker.getValue();
        
        if(selectedDate!=null){
            day=selectedDate.toString();
            changeEditTimetableStyle();
            updateTable(day);
        }
    }
    
    
    private void loadAllClasses() throws ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();

        if (conn == null) {
            System.out.println("No s'ha pogut connectar amb la base de dades.");
        }

        try {
            ObservableList<Clase> clases = FXCollections.observableArrayList();

            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM classes");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int instructorT = rs.getInt("instructor_id");
                    int capacityT = rs.getInt("capacity");
                    java.sql.Date sqlDate = rs.getDate("date");
                    LocalDate date = sqlDate != null ? sqlDate.toLocalDate() : null;

                    String startT = rs.getString("start_time");
                    String endT = rs.getString("end_time");

                    clases.add(new Clase(id, name, instructorT, capacityT, date, startT, endT));
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
}