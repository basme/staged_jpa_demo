package com.netcracker.training.hibernate.demo.model;

import com.netcracker.training.hibernate.demo.model.embeddables.PassportData;
import com.netcracker.training.hibernate.demo.model.embeddables.PersonContacts;
import com.netcracker.training.hibernate.demo.model.enums.Gender;
import com.netcracker.training.hibernate.demo.model.enums.MaritalStatus;
import com.netcracker.training.hibernate.demo.service.converter.PassportDataConverter;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(generator = "personIdGenerator")
    @SequenceGenerator(name = "personIdGenerator", sequenceName = "person_id_sequence", initialValue = 100)
    private int id;

    @Column(name = "full_name")
    private String name;

    private int age;

    private BigInteger personalCode;

    @Transient
    private Object additionalInfo;

    @Embedded
    private PersonContacts contacts;

    @Enumerated
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Convert(converter = PassportDataConverter.class)
    private PassportData passportData;

}
