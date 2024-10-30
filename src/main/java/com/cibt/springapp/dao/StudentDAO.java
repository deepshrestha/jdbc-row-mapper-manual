package com.cibt.springapp.dao;

import java.util.List;

import com.cibt.springapp.models.Student;

public interface StudentDAO {

    List<Student> fetchAll();
    int insert(Student student);
    Student fetchById(int id);
    Student getLastInsertedId();
    int update(Student student);
    int delete(int id);
}