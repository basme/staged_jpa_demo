package com.netcracker.training.hibernate.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigInteger;

@Entity
@Data
@NoArgsConstructor
@Table(name = "persons")
public class Person {

    @Id
    private int id;

    @Column(name = "full_name")
    private String name;

    private int age;

    private BigInteger personalCode;

    @Transient
    private Object additionalInfo;

}
