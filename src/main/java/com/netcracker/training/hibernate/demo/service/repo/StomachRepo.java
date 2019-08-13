package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.Stomach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StomachRepo extends JpaRepository<Stomach, Integer> {
}
