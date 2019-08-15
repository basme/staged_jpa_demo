package com.netcracker.training.hibernate.demo.model.fetching;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "parent")
public class Child {

    public Child(Integer id, String content, Parent parent) {
        this.id = id;
        this.content = content;
        this.parent = parent;
    }

    @Id
    private Integer id;

    private String content;

    @ManyToOne
    private Parent parent;

}
