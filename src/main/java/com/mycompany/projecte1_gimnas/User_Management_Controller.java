package com.mycompany.projecte1_gimnas;

import com.mycompany.projecte1_gimnas.model.User;
import com.mycompany.projecte1_gimnas.model.UserCard;
import com.mycompany.projecte1_gimnas.model.AppUtils;
import java.io.IOException;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class User_Management_Controller {

    @FXML
    private Button addUserbtn;
    
    @FXML
    private ScrollPane usersScroll;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private Button editTimeBtn;

    @FXML
    private Button editUser;

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
    private Text username;
    
    @FXML
    void addUser(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "user_add");
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"class_select");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "editTimetable");
    }
    
    @FXML
    void manageAppointments(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"user_management");
    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "estadistiques");
    }

       
    public void initialize() throws ClassNotFoundException {
        loadUserCards();
    }
    
    public void loadUserCards() throws ClassNotFoundException {
    
        VBox container = new VBox(15); 
        container.setPadding(new Insets(20)); 
        container.setFillWidth(true); // Permite que las tarjetas ocupen todo el ancho

        List<User> users = getUsersFromDatabase();

        for (User user : users) {
            UserCard card = new UserCard(user); 

            Button editBtn = (Button) card.lookup("#editBtn");
            if (editBtn != null) {
                editBtn.setOnAction(e -> openUserEditView(user, e));
            }

            container.getChildren().add(card);
        }

        usersScroll.setContent(container);
        usersScroll.setFitToWidth(true); // Ajusta el contenido al ancho del ScrollPane
    }

    public List<User> getUsersFromDatabase() throws ClassNotFoundException {
        
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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

                User user = new User(id,surnames,name,dni,password,mail,phone,iban,address,status);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    private void openUserEditView(User user, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("edit_user.fxml"));
            Parent root = loader.load();

            // Obtenim el control·lador de la pagina que volem
            Edit_UserController ctrl = loader.getController();

            // Pasar les dades de l'usuari
            ctrl.initData(user);

            // Cambiar scene
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Usuario");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
