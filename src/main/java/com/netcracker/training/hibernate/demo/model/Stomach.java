package com.netcracker.training.hibernate.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Stomach {

    private int id;

    private int capacity;
    private int actualUtilization;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getActualUtilization() {
        return actualUtilization;
    }

    public void setActualUtilization(int actualUtilization) {
        if(actualUtilization > capacity) throw new IllegalArgumentException("А ты налей и отойди");
        this.actualUtilization = actualUtilization;
    }
}
