package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.fetching.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepo extends JpaRepository<Parent, Integer> {
}
