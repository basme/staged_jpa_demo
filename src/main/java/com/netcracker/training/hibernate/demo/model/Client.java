package com.netcracker.training.hibernate.demo.model;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@Table(name = "clients")
public class Client extends Person {

    private float satisfactionRate;

    @Lob
    @Basic
    private HashMap<String, String> questionnaire;

}
