package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Integer> {
}
