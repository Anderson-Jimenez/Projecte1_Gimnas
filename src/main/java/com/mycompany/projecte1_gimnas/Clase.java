/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projecte1_gimnas;

/**
 *
 * @author asix1
 */
public class Clase {
    private String class_name;
    private String instructor;
    private int aforament;

    public Clase(String class_name, String instructor, int aforament){
        this.class_name=class_name;
        this.instructor=instructor;
        this.aforament=aforament;
    }
    
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getAforament() {
        return aforament;
    }

    public void setAforament(int aforament) {
        this.aforament = aforament;
    }
}

