package com.netcracker.training.hibernate.demo.model.relations;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(of = {"contactId", "mail", "phone"})
@ToString(of = {"contactId", "mail", "phone"})
@Entity
public class ContactInfo {

    @Id
    @GeneratedValue
    private BigInteger contactId;

    private String mail;
    private String phone;

    @OneToOne(mappedBy = "contactInfo")
    private Author author;

}
