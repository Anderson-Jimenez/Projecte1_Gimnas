package com.mycompany.projecte1_gimnas.model;

public class Instructor {

    private String surnames;
    private String name;
    private String dni;

    // Constructor
    public Instructor(String surnames, String name, String dni) {
        this.surnames = surnames;
        this.name = name;
        this.dni = dni;
    }

    // Getters (requeridos para que PropertyValueFactory funcione)
    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getDNI() {
        return dni;
    }

    // Setters opcionales (por si luego quieres modificar los datos)
    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }
}
