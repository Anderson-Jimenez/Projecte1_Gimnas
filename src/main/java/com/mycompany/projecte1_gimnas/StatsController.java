package com.mycompany.projecte1_gimnas;

import java.io.IOException;
import java.sql.Connection;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.StackedBarChart;

import javafx.scene.chart.PieChart;
import javafx.scene.control.cell.PropertyValueFactory;


public class StatsController{
    private int Dilluns;
    private int Dimarts;
    private int Dimecres;
    private int Dijous;
    private int Divendres;
    private int Dissabte;

    @FXML
    private Button assignInstructorsBtn;

    @FXML
    private Button closeSessionBtn;

    @FXML
    private Button editTimeBtn;

    @FXML
    private AnchorPane mainBackground;

    @FXML
    private Button manageAppointmentsBtn;

    @FXML
    private Button manageClientsBtn;

    @FXML
    private PieChart pieChart2;

    @FXML
    private Text role;

    @FXML
    private Button showStatsBtn;
    
    @FXML
    private Text username;

    @FXML
    private StackedBarChart<String, Number> stackedBarChart;

       
    /*
    public void generatePieChart(){
        Connection conn = DatabaseConnection.getConnection();
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }
        try {

            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT * FROM instructor");
            
            while (rs.next()) {
                int instructorT = rs.getInt("ID");   
                instructors.add(instructorT);
            }
                            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
    }
    */
    
    @FXML
    public void initialize() throws ClassNotFoundException, SQLException {

        ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                new PieChart.Data("Ignasi", 30),
                new PieChart.Data("Anderson", 70)
            );
        
        pieChart2.getData().addAll(pieChartData);
        
        XYChart.Series s1 = new XYChart.Series<>();
        s1.setName("Spinning");
        barCharGenerate("Spinning", s1);
  
        XYChart.Series s2 = new XYChart.Series<>();
        s2.setName("Ioga");
        barCharGenerate("Ioga", s2);
        
        XYChart.Series s3 = new XYChart.Series<>();
        s3.setName("BodyPump");
        barCharGenerate("BodyPump", s3);
        
        XYChart.Series s4 = new XYChart.Series<>();
        s4.setName("Crossfit");
        barCharGenerate("Crossfit", s4);
        
        XYChart.Series s5 = new XYChart.Series<>();
        s5.setName("Zumba");
        barCharGenerate("Zumba", s5);
        
        XYChart.Series s6 = new XYChart.Series<>();
        s6.setName("Pilates");
        barCharGenerate("Pilates", s6);
        
        XYChart.Series s7 = new XYChart.Series<>();
        s7.setName("Boxing");
        barCharGenerate("Boxing", s7);
        
        XYChart.Series s8 = new XYChart.Series<>();
        s8.setName("HIIT");
        barCharGenerate("HIIT", s8);
        
        XYChart.Series s9 = new XYChart.Series<>();
        s9.setName("Step");
        barCharGenerate("Step", s9);
        
        XYChart.Series s10 = new XYChart.Series<>();
        s10.setName("BodyCombat");
        barCharGenerate("BodyCombat", s10);
        
        XYChart.Series s11 = new XYChart.Series<>();
        s11.setName("Streching");
        barCharGenerate("Streching", s11);
        
        XYChart.Series s12 = new XYChart.Series<>();
        s12.setName("Cardio");
        barCharGenerate("Cardio", s12);
        
        stackedBarChart.getData().addAll(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12);
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
        fxmlLoader(event, "editTimetable");
    }

    @FXML
    void manageAppointments(ActionEvent event) {

    }

    @FXML
    void manageClients(ActionEvent event) {

    }

    @FXML
    void showStats(ActionEvent event) throws IOException {
        fxmlLoader(event, "main_panell");
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void fxmlLoader(ActionEvent event, String pagina) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(pagina+".fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void barCharGenerate(String className, XYChart.Series s) throws ClassNotFoundException, SQLException{
        Connection conn = DatabaseConnection.getConnection();
        
        Dilluns=0;
        Dimarts=0;
        Dimecres=0;
        Dijous=0;
        Divendres=0;
        Dissabte=0;
        
        if (conn == null) {
            System.out.println("❌ No s'ha pogut connectar amb la base de dades.");
        }
        try {

            Statement stmt1 = conn.createStatement();
            ResultSet rs1 = stmt1.executeQuery("SELECT COUNT(*) FROM classes WHERE date='dilluns' && name='"+className+"'");
            if(rs1.next()){
                Dilluns=rs1.getInt(1);
            }
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT COUNT(*) FROM classes WHERE date='dimarts' && name='"+className+"'");
            if(rs2.next()){
                Dimarts=rs2.getInt(1);
            }
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) FROM classes WHERE date='dimecres' && name='"+className+"'");
            if(rs3.next()){
                Dimecres=rs3.getInt(1);
            }
            Statement stmt4 = conn.createStatement();
            ResultSet rs4 = stmt4.executeQuery("SELECT COUNT(*) FROM classes WHERE date='dijous' && name='"+className+"'");
            if(rs4.next()){
                Dijous=rs4.getInt(1);
            }
            Statement stmt5 = conn.createStatement();
            ResultSet rs5 = stmt5.executeQuery("SELECT COUNT(*) FROM classes WHERE date='divendres' && name='"+className+"'");
            if(rs5.next()){
                Divendres=rs5.getInt(1);
            }
            Statement stmt6 = conn.createStatement();
            ResultSet rs6 = stmt1.executeQuery("SELECT COUNT(*) FROM classes WHERE date='dissabte' && name='"+className+"'");
            if(rs6.next()){
                Dissabte=rs6.getInt(1);
            }
                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        
        s.getData().add(new XYChart.Data<>("Dilluns", Dilluns));
        s.getData().add(new XYChart.Data<>("Dimarts", Dimarts));
        s.getData().add(new XYChart.Data<>("Dimecres", Dimecres));
        s.getData().add(new XYChart.Data<>("Dijous", Dijous));
        s.getData().add(new XYChart.Data<>("Divendres", Divendres));
        s.getData().add(new XYChart.Data<>("Dissabte", Dissabte));
    }
}
