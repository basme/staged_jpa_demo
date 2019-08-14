package com.netcracker.training.hibernate.demo.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "employees")
@Immutable
public class Employee extends Person {

    private String employeeCode;

}
