package com.netcracker.training.hibernate.demo.model.relations;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Collection;

@Data
@EqualsAndHashCode(of = {"authorId", "name"})
@ToString(of = {"authorId", "name"})
@Entity
public class Author {

    @Id
    @GeneratedValue
    private BigInteger authorId;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts")
    private ContactInfo contactInfo;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "authorship",
            joinColumns = @JoinColumn(name = "author"),
            inverseJoinColumns = @JoinColumn(name = "book")
    )
    private Collection<Book> books;

}
