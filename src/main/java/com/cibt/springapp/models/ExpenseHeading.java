package com.cibt.springapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpenseHeading {

    private int id;
    private String headingTitle;

    public ExpenseHeading(){
    }
    
    public ExpenseHeading(int id, String headingTitle){ // POJO class ( Plain Old Java Object)
        this.id = id;
        this.headingTitle = headingTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadingTitle() {
        return headingTitle;
    }

    public void setHeadingTitle(String headingTitle) {
        this.headingTitle = headingTitle;
    }
    
}