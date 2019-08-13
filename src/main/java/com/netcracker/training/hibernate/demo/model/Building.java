package com.netcracker.training.hibernate.demo.model;

import com.netcracker.training.hibernate.demo.model.embeddables.BuildingId;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class Building {

    @EmbeddedId
    private BuildingId buildingId;

    private int stages;

}
