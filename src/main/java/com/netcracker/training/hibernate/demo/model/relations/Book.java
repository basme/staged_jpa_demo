package com.netcracker.training.hibernate.demo.model.relations;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.Collection;

@Data
@EqualsAndHashCode(of = {"bookId", "title"})
@ToString(of = {"bookId", "title"})
@Entity
public class Book {

    @Id
    @GeneratedValue
    private BigInteger bookId;

    private String title;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Collection<Page> pages;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Collection<Author> authors;

}
