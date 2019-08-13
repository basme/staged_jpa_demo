package com.netcracker.training.hibernate.demo.model.embeddables;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PassportData {

    private int ser;
    private int no;
    private Date issued;
    private String issuedBy;

}
