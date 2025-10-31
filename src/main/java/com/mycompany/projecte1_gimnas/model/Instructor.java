package com.mycompany.projecte1_gimnas.model;

public class Instructor {

    private int id;
    private String name;
    private String surnames;
    private String dni;
    private String password;
    private String email;
    private String phone;
    private String address;
    private String status;

    // 🔹 Constructor completo
    public Instructor(int id, String name, String surnames, String dni, String password,
                      String email, String phone, String address, String status) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.dni = dni;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }

    // =====================
    // 🔹 Getters y Setters
    // =====================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 🔹 Método útil para debug o mostrar en consola
    @Override
    public String toString() {
        return name + " " + surnames + " (" + dni + ")";
    }
}
