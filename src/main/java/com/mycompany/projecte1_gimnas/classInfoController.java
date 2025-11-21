package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.User;
import com.mycompany.projecte1_gimnas.model.UserCard;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    
    public void initData(Clase classe) {
        this.classe=classe;
        classID=classe.getId();
        
        className.setText(classe.getClass_name());
        day.setText(classe.getDate());
        startTime.setText(classe.getStart_time());
        endTime.setText(classe.getEnd_time());
        
    }
    
    public void initialize() throws ClassNotFoundException, SQLException {
        loadUserCards();
    }

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
    void assignInstructors(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        fxmlLoader(event,"editTimetable");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void deleteClass(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) {

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

        List<User> users = getUsersFromDatabase();

        for (User user : users) {
            container.getChildren().add(new UserCard(user));
        }

        scrollPane.setContent(container);
    }
    public List<User> getUsersFromDatabase() throws ClassNotFoundException, SQLException {
        
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users INNER JOIN reservation ON reservation.fk_id_user = users.id WHERE reservation.fk_id_class = ?";

        try (Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement stmt=conn.prepareStatement(sql);
            
            stmt.setInt(1,classID);
                
            ResultSet rs = stmt.executeQuery(sql);

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

                // Crear objeto User EXACTO según tu tabla
                User user = new User(id,surnames,name,dni,password,mail,phone,iban,address,status);

                users.add(user);
            }
        

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
 
}