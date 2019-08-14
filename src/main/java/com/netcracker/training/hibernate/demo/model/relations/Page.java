package com.netcracker.training.hibernate.demo.model.relations;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigInteger;

@Data
@EqualsAndHashCode(of = {"pageId", "number", "isTechnical"})
@ToString(of = {"pageId", "number", "isTechnical"})
@Entity
public class Page {

    @Id
    @GeneratedValue
    private BigInteger pageId;

    private Integer number;
    private boolean isTechnical;

    @ManyToOne
    @JoinColumn(name = "bookid")
    private Book book;

}
