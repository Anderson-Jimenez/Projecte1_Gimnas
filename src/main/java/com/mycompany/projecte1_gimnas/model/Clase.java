package com.mycompany.projecte1_gimnas.model;

import java.time.LocalDate;


public class Clase {
    private int id;
    private String class_name;
    private int instructor;
    private int aforament;
    private LocalDate date;
    private String start_time;
    private String end_time;
    
    public Clase(int id,String class_name, int instructor, int aforament, LocalDate date, String start_time, String end_time) {
        this.id = id;
        this.class_name = class_name;
        this.instructor = instructor;
        this.aforament = aforament;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    
    public int getId() {
        return id;
    }

    public void setClass_name(int id) {
        this.id = id;
    }
    
    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getInstructor() {
        return instructor;
    }

    public void setInstructor(int instructor) {
        this.instructor = instructor;
    }

    public int getAforament() {
        return aforament;
    }

    public void setAforament(int aforament) {
        this.aforament = aforament;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
