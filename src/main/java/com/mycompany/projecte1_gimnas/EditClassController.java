package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.Clase;
import com.mycompany.projecte1_gimnas.model.Instructor;
import com.mycompany.projecte1_gimnas.model.AppUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditClassController {

    private Clase classe;
    private String savedHour;
    
    public void initData(Clase classe) {
        this.classe=classe;
        
        savedHour=classe.getStart_time();
        
        // Mostrar los datos en los campos
        typeClass.setValue(classe.getClass_name());
        instructor.setValue(classe.getInstructor());
        day.setValue(classe.getDate());
        hour.setValue(classe.getStart_time());
        capacitySlider.setValue(classe.getAforament());
    }
    
    @FXML
    public void initialize() throws ClassNotFoundException {
        ObservableList<String> days = FXCollections.observableArrayList("dilluns", "dimarts", "dimecres", "dijous", "divendres", "dissabte");
        day.setItems(days);
        
        ObservableList<String> hours = FXCollections.observableArrayList("06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
        hour.setItems(hours);
        
        ObservableList<String> typeClasses = FXCollections.observableArrayList("Spinning", "Ioga","BodyPump","Crossfit","Zumba","Pilates","Stretching","Cardio","BodyCombat","HIIT","Boxing","Step");
        typeClass.setItems(typeClasses);
        
        ObservableList<Integer> instructors = FXCollections.observableArrayList();
        
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM instructor");

            while (rs.next()) {
                int instructorT = rs.getInt("ID");   
                instructors.add(instructorT);
            }
            
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        instructor.setItems(instructors);
    }
    
    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private Slider capacitySlider;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private ComboBox<String> day;

    @FXML
    private Button editTimeBtn;

    @FXML
    private ComboBox<String> hour;

    @FXML
    private ComboBox<Integer> instructor;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Text role;

    @FXML
    private Button showStatsBtn;

    @FXML
    private ComboBox<String> typeClass;

    @FXML
    private Button updateClass;

    @FXML
    private Text username;

    
    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "class_select");
    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "login");
    }

    @FXML
    void daySelect(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"editTimetable");
    }

    @FXML
    void hourSelect(ActionEvent event) {

    }

    @FXML
    void instructorSelect(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"user_management");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"estadistiques");
    }

    @FXML
    void typeClassSelect(ActionEvent event) {
        
    }

    @FXML
    void updateClass(ActionEvent event) throws ClassNotFoundException, IOException {
        boolean flag=false;
        String selected_day=day.getValue();
        String selected_hour=hour.getValue();
            
        Connection conn1 = DatabaseConnection.getConnection();
        
        if (conn1 == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }

        try {
                String sql="SELECT * FROM classes WHERE date=?";
                PreparedStatement stmt=conn1.prepareStatement(sql);
                stmt.setString(1,selected_day);
                
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next() && flag==false) {
                    String hour = rs.getString("start_time");
                    if(hour.equals(selected_hour)==true){
                        if(hour.equals(savedHour)){
                            flag=false;
                        }
                        else{
                            flag=true;
                        }
                    }
            
            
                }
            
            conn1.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        if(flag==true){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Ja existeix una classe en aquesta hora");
            a.show();
        }
        else{            
            List<String> list= Arrays.asList("06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00","24:00");
            
            int end_hour_int=list.indexOf(selected_hour);
            end_hour_int=end_hour_int+1;
            String end_hour=list.get(end_hour_int);        

            // Insert de classes a la taula d'horaris
        
            Connection conn2 = DatabaseConnection.getConnection();
        
            if (conn2 == null) {
                System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
            }

            try {
                String sql = "UPDATE classes SET name = ?, date = ?, start_time = ?, end_time = ?, capacity = ? WHERE id = ?";
                
                Statement statement = conn2.createStatement();
                PreparedStatement stmt = conn2.prepareStatement(sql);
            
                stmt.setString(1, typeClass.getValue());
                stmt.setString(2, day.getValue());
                stmt.setString(3, hour.getValue());
                stmt.setString(4, end_hour);
                stmt.setInt(5, (int) capacitySlider.getValue());
                stmt.setInt(6, classe.getId());

                int rows = stmt.executeUpdate();
                conn2.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            fxmlLoader(event,"editTimetable");
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