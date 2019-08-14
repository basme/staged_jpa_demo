package com.netcracker.training.hibernate.demo.service.repo;

import com.netcracker.training.hibernate.demo.model.relations.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

/**
 * Created by daba0216 on 17.04.2018.
 */
public interface ContactInfoRepo extends JpaRepository<ContactInfo, BigInteger> {
}
