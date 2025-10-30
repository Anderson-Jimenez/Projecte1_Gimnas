package com.mycompany.projecte1_gimnas.model;

public class Instructor {

    private String name;
    private String surnames;
    private String dni;

    public Instructor(String name, String surnames, String dni) {
        this.name = name;
        this.surnames = surnames;
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getDni() {      // 🔹 Este getter es obligatorio
        return dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
