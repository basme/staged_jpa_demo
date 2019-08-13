package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.Building;
import com.netcracker.training.hibernate.demo.model.embeddables.BuildingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepo extends JpaRepository<Building, BuildingId> {
}
