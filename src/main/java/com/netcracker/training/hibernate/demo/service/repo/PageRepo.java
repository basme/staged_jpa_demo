package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.relations.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * Created by daba0216 on 17.04.2018.
 */
public interface PageRepo extends JpaRepository<Page, BigInteger> {
}
