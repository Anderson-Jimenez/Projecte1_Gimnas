package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.AppUtils;
import com.mycompany.projecte1_gimnas.model.Clase;
import com.mycompany.projecte1_gimnas.model.User;
import com.mycompany.projecte1_gimnas.model.UserCardReservas;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class classInfoController {
    private int classID;
    private Clase classe;
    
    private String actualCapacitText;
    private int actualCapacit;
    private int capacityCounter=0;
    private int aforament;
    private String aforamentText;
    
    
    public void initData(Clase clase){
        this.classe=clase;
        classID=clase.getId();
        aforament=classe.getAforament();
        aforamentText=String.valueOf(aforament);
        
        className.setText(classe.getClass_name());
        if(classe.getDate() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            day.setText(classe.getDate().format(formatter));
        }
        
        startTime.setText(classe.getStart_time());
        endTime.setText(classe.getEnd_time());
        capacity.setText(aforamentText);
        
        System.out.println("S'inicia la data");
        
        try{
            loadUserCards();
        }
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    private Text capacity;

    @FXML
    private Text currentCapacity;
    
    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private Button back;

    @FXML
    private ProgressBar capacityBar;

    @FXML
    private Text className;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private Text day;

    @FXML
    private Button editTimeBtn;

    @FXML
    private Text endTime;

    @FXML
    private Text instructorName;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private Text role;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button showStatsBtn;

    @FXML
    private Text startTime;

    @FXML
    private Text username;

    @FXML
    void addClass(ActionEvent event) {
        
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "class_select");
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"edit_timetable");        
    }

    @FXML
    void closeSession(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "login");
    }

    @FXML
    void deleteClass(ActionEvent event) {
        
    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"edit_timetable");
    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"user_management");       
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"estadistiques");   
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void fxmlLoader(ActionEvent event, String pagina) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pagina+".fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    
    public void loadUserCards() throws ClassNotFoundException, SQLException {
        
        VBox container = new VBox(15);
        container.setPadding(new Insets(20));

        ObservableList<User> users = getUsersFromDatabase();

        for (User user : users) {
            container.getChildren().add(new UserCardReservas(user));
        }

        scrollPane.setContent(container);
    }
    public ObservableList<User> getUsersFromDatabase() throws ClassNotFoundException, SQLException {
        capacityCounter=0;
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users INNER JOIN reservation ON reservation.fk_id_user = users.id WHERE reservation.fk_id_class = ?";

        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement stmt=conn.prepareStatement(sql);

            stmt.setInt(1,classID);
            System.out.println("La id de la clase: "+classID);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
                int id = rs.getInt("ID");
                String surnames = rs.getString("surnames");
                String name = rs.getString("name");
                String dni = rs.getString("dni");
                String password = rs.getString("password");
                String mail = rs.getString("mail");
                String phone = rs.getString("phone");
                String iban = rs.getString("IBAN");
                String address = rs.getString("address");
                String status = rs.getString("status");

                capacityCounter+=1;
                // Crear objeto User EXACTO según tu tabla
                User user = new User(id,surnames,name,dni,password,mail,phone,iban,address,status);

                users.add(user);
                
            }
            
            actualCapacit=aforament-capacityCounter;
            actualCapacitText=String.valueOf(actualCapacit);
            currentCapacity.setText(actualCapacitText);
            
            System.out.println("Aqui haurien de sortir els usuaris: "+users);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
 
}