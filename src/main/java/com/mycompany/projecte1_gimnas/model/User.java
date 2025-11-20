/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projecte1_gimnas.model;

public class User {

    private int id;
    private String surnames;
    private String name;
    private String dni;
    private String password;
    private String mail;
    private String phone;
    private String iban;
    private String address;
    private String status;

    public User() {
    }

    public User(int id, String surnames, String name, String dni, String password,
                String mail, String phone, String iban, String address, String status) {
        this.id = id;
        this.surnames = surnames;
        this.name = name;
        this.dni = dni;
        this.password = password;
        this.mail = mail;
        this.phone = phone;
        this.iban = iban;
        this.address = address;
        this.status = status;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
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

    @Override
    public String toString() {
        return name + " " + surnames + " (" + dni + ")";
    }
}
