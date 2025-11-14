/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projecte1_gimnas.model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import com.mycompany.projecte1_gimnas.model.User;


public class UserCard extends HBox {

    private final User user;

    public UserCard(User user) {
        this.user = user;
        buildUI();
    }

    private void buildUI() {
        setSpacing(15);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER_LEFT);
        setStyle("" +
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #e0e0e0;" +
                "-fx-border-width: 1;"
        );

        // Avatar
        ImageView avatar = new ImageView(new Image(
                getClass().getResource("/com/mycompany/projecte1_gimnas/img/user_icon.png").toExternalForm()
        ));
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        avatar.setPreserveRatio(true);

        // Nombre + apellidos
        Text fullName = new Text(user.getName() + " " + user.getSurnames());
        fullName.setFont(Font.font("System", FontWeight.BOLD, 18));

        // Status
        Label statusLabel = new Label(user.getStatus());
        statusLabel.setFont(Font.font(14));
        statusLabel.setPadding(new Insets(4, 10, 4, 10));
        statusLabel.setStyle("-fx-background-radius: 8; -fx-text-fill: white;");

        if (user.getStatus().equalsIgnoreCase("active"))
            statusLabel.setStyle(statusLabel.getStyle() + "-fx-background-color: #4CAF50;");
        else
            statusLabel.setStyle(statusLabel.getStyle() + "-fx-background-color: #b71c1c;");

        VBox vbox = new VBox(5, fullName, statusLabel);

        getChildren().addAll(avatar, vbox);

        // Opcional: efecto hover
        setOnMouseEntered(e -> setStyle(
                "-fx-background-color: #f6f6f6; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #d3d3d3;"
        ));

        setOnMouseExited(e -> setStyle(
                "-fx-background-color: white; -fx-background-radius: 12; -fx-border-radius: 12; -fx-border-color: #e0e0e0;"
        ));
    }

    public User getUser() {
        return user;
    }
}

