package com.cibt.springapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private int id;
    private String studentName;
    private String studentAddress;
    private String studentEmail;

    public Student() {
    }

    public Student(int id, String studentName, String studentAddress, String studentEmail) {
        this.id = id;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
        this.studentEmail = studentEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentEmail() {
        return studentEmail;
    }
    
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
    
}