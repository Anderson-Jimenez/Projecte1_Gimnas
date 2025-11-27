package com.mycompany.projecte1_gimnas.model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InstructorCard extends HBox {

    private final Instructor instructor;

    public InstructorCard(Instructor instructor) {
        this.instructor = instructor;
        buildUI();
        // Hacer que la tarjeta ocupe todo el ancho disponible
        setMaxWidth(Double.MAX_VALUE);
    }

    private void buildUI() {
        setSpacing(15);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER_LEFT);
        setStyle(
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

        // Información del instructor
        Text fullName = new Text(instructor.getName() + " " + instructor.getSurnames());
        fullName.setFont(Font.font("System", FontWeight.BOLD, 18));

        Label statusLabel = new Label(instructor.getStatus());
        statusLabel.setFont(Font.font(14));
        statusLabel.setPadding(new Insets(4, 10, 4, 10));
        if (instructor.getStatus().equalsIgnoreCase("active")) {
            statusLabel.setStyle("-fx-background-color: #4CAF50; -fx-background-radius: 8; -fx-text-fill: white;");
        } else {
            statusLabel.setStyle("-fx-background-color: #b71c1c; -fx-background-radius: 8; -fx-text-fill: white;");
        }

        VBox vbox = new VBox(5, fullName, statusLabel);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(vbox, Priority.ALWAYS); // Permite que el VBox de texto crezca y empuje el botón a la derecha

        // Botón de modificar
        Button editBtn = new Button("Modificar");
        editBtn.setStyle(
                "-fx-background-color: #4A90E2;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8;" +
                "-fx-padding: 6 12;"
        );
        editBtn.setId("editInstructorBtn");

        // Spacer flexible
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(avatar, vbox, spacer, editBtn);

        // Hover effect
        setOnMouseEntered(e -> setStyle(
                "-fx-background-color: #f6f6f6;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #d3d3d3;"
        ));

        setOnMouseExited(e -> setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #e0e0e0;"
        ));

        // Click → popup
        setOnMouseClicked(e -> showInstructorDetails());
    }

    public Instructor getInstructor() {
        return instructor;
    }

    // Pop-up de información
    private void showInstructorDetails() {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Informació de l'instructor");
        dialog.getDialogPane().getButtonTypes().add(javafx.scene.control.ButtonType.CLOSE);

        ImageView avatar = new ImageView(new Image(
                getClass().getResource("/com/mycompany/projecte1_gimnas/img/user_icon.png").toExternalForm()
        ));
        avatar.setFitWidth(120);
        avatar.setFitHeight(120);
        avatar.setPreserveRatio(true);

        Label fullName = new Label(instructor.getName() + " " + instructor.getSurnames());
        fullName.setFont(Font.font("System", FontWeight.BOLD, 20));
        fullName.setStyle("-fx-text-fill: white;");

        VBox fields = new VBox(10);
        fields.setPadding(new Insets(15));
        fields.getChildren().addAll(
                styledField("DNI: " + instructor.getDni()),
                styledField("Correu: " + instructor.getEmail()),
                styledField("Telèfon: " + instructor.getPhone()),
                styledField("Adreça: " + instructor.getAddress()),
                styledField("Estat: " + instructor.getStatus())
        );

        VBox root = new VBox(18, avatar, fullName, fields);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        root.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #FFF, #5DA9FE);" +
                "-fx-padding: 20;"
        );

        dialog.getDialogPane().setContent(root);
        dialog.getDialogPane().setPrefWidth(380);
        dialog.getDialogPane().setStyle("-fx-background-color: transparent;");
        dialog.showAndWait();
    }

    private HBox styledField(String text) {
        Label label = new Label(text);
        label.setFont(Font.font(15));
        label.setStyle("-fx-text-fill: #003366;");

        HBox box = new HBox(label);
        box.setPadding(new Insets(10));
        box.setStyle(
                "-fx-background-color: rgba(255,255,255,0.9);" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 8, 0, 0, 2);"
        );
        return box;
    }
}
