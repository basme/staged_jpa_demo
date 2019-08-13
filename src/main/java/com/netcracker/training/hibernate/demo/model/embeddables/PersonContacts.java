package com.netcracker.training.hibernate.demo.model.embeddables;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class PersonContacts {

    private String phone;
    private String email;
    private String postAddress;

}
