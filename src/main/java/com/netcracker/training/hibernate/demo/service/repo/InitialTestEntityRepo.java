package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.InitialTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InitialTestEntityRepo extends JpaRepository<InitialTestEntity, UUID> {
}
