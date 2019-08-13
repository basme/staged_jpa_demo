package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepo extends JpaRepository<Person, Integer> {
}
