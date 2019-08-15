package com.netcracker.training.hibernate.demo.model.fetching;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "children")
public class Parent {

    public Parent(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    @Id
    private Integer id;

    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    @Cascade(CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = 5)
    private Set<Child> children;

}
