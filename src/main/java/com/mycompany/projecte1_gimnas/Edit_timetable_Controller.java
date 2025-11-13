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
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int instructorT = rs.getInt("fk_id_instructor");
                    int capacityT = rs.getInt("capacity");
                    String date = rs.getString("date");
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
    private Button addDilluns;

    @FXML
    private Button addDimarts;

    @FXML
    private Button addDimecres;
    
    @FXML
    private Button addDijous;

    @FXML
    private Button addDivendres;
    
    @FXML
    private Button addDissabte;

    @FXML
    private Button back;
    
    @FXML
    void back(ActionEvent event) {
        back.getStyleClass().add("hidden");
        class_table.getStyleClass().add("hidden");
        addClass.getStyleClass().add("hidden");
        editClass.getStyleClass().add("hidden");
        deleteClass.getStyleClass().add("hidden");
        addDilluns.getStyleClass().remove("hidden");
        addDimarts.getStyleClass().remove("hidden");
        addDimecres.getStyleClass().remove("hidden");
        addDijous.getStyleClass().remove("hidden");
        addDivendres.getStyleClass().remove("hidden");
        addDissabte.getStyleClass().remove("hidden");
    }
    
    @FXML
    void addDilluns(ActionEvent event) throws ClassNotFoundException {
        day="dilluns";
        changeEditTimetableStyle();
        updateTable(day);
    }
    
    @FXML
    void addDimarts(ActionEvent event) throws ClassNotFoundException {
        day="dimarts";
        changeEditTimetableStyle();
        updateTable(day);
    }

    @FXML
    void addDimecres(ActionEvent event) throws ClassNotFoundException {
        day="dimecres";
        changeEditTimetableStyle();
        updateTable(day);
    }
    
    @FXML
    void addDijous(ActionEvent event) throws ClassNotFoundException {
        day="dijous";
        changeEditTimetableStyle();
        updateTable(day);
    }
    
    @FXML
    void addDivendres(ActionEvent event) throws ClassNotFoundException {
        day="divendres";
        changeEditTimetableStyle();
        updateTable(day);
    }

    @FXML
    void addDissabte(ActionEvent event) throws ClassNotFoundException {
        day="dissabte";
        changeEditTimetableStyle();
        updateTable(day);
    }
      
    @FXML
    void addClass(ActionEvent event) throws IOException {
        fxmlLoader(event, "editTimetable-addClass");
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
                    stmt.setString(2,selectedClass.getDate());
                    stmt.setString(3,selectedClass.getStart_time());

                    stmt.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {
            ObservableList<Clase> clases = FXCollections.observableArrayList();

            try {                
                String selected_day = day;
                String sql=null;
                
                                
                if(selected_day==null){
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText("Selecciona un dia, aixo es provisional");
                    a.show();
                }
                else{
                    sql="SELECT * FROM classes WHERE date=?";
                    PreparedStatement stmt=conn.prepareStatement(sql);
                    stmt.setString(1, selected_day);
                    
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int instructorT = rs.getInt("fk_id_instructor");
                        int capacityT = rs.getInt("capacity");
                        String date = rs.getString("date");
                        String startT = rs.getString("start_time");
                        String endT = rs.getString("end_time");

                        clases.add(new Clase(id, name, instructorT, capacityT, date, startT, endT));
                    }
                    class_table.setItems(clases);
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        fxmlLoader(event, "professional_assign");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        fxmlLoader(event, "main_panell");

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
    
    /*======================================Funcions====================================*/

    public void changeEditTimetableStyle(){
        back.getStyleClass().remove("hidden");
        class_table.getStyleClass().remove("hidden");
        addClass.getStyleClass().remove("hidden");
        editClass.getStyleClass().remove("hidden");
        deleteClass.getStyleClass().remove("hidden");
        addDilluns.getStyleClass().add("hidden");
        addDimarts.getStyleClass().add("hidden");
        addDimecres.getStyleClass().add("hidden");
        addDijous.getStyleClass().add("hidden");
        addDivendres.getStyleClass().add("hidden");
        addDissabte.getStyleClass().add("hidden");
    }
    
    private void updateTable(String day) throws ClassNotFoundException{
        String selected_day = day;
        
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
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String instructor_query="SELECT name FROM instructor INNER JOIN classes ON instructor.ID = ?";
                    int instructorT = rs.getInt("fk_id_instructor");
                    int capacityT = rs.getInt("capacity");
                    String date = rs.getString("date");
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
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void fxmlLoader(ActionEvent event, String pagina) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pagina+".fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
