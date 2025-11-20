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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


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
        AppUtils.changeWindow(event, "add_user");
    }

    @FXML
    void assignInstructors(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event,"professional_assign");
    }

    @FXML
    void closeSession(ActionEvent event) {

    }

    @FXML
    void editTimetable(ActionEvent event) throws IOException {
        AppUtils.changeWindow(event, "editTimetable");
    }

    @FXML
    void editUser(ActionEvent event) {

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
        container.setPadding(new Insets(20)); //donar padding a cada tarjeta

        List<User> users = getUsersFromDatabase(); //importa els usuaris de la base de dades

        for (User user : users) {
            //if (user.getStatus().equalsIgnoreCase("ACTIVE"))
            container.getChildren().add(new UserCard(user)); //afegeix al contenidor d'usuaris una tarjeta nova amb les dades de cada usuari
        }

        usersScroll.setContent(container);
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

                // Crear objeto User EXACTO según tu tabla
                User user = new User(id,surnames,name,dni,password,mail,phone,iban,address,status);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /*
        
    */
}
