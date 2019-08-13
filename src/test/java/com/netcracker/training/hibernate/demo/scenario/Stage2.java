package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.Person;
import com.netcracker.training.hibernate.demo.service.repo.PersonRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage2 {

    private static final int PERSON_ID = 1;

    @Resource
    private PersonRepo personRepo;

    @Test
    public void newEntityCreated() {
        Person person = new Person();
        person.setId(PERSON_ID);
        person.setName("Dan");
        person.setAge(26);
        person.setPersonalCode(BigInteger.valueOf(123457984567453l));
        final List<Person> noPersons = personRepo.findAll();
        assertEquals(noPersons.size(), 0);
        personRepo.save(person);
        final List<Person> allPersons = personRepo.findAll();
        assertEquals(allPersons.size(), 1);
        final Optional<Person> found = personRepo.findById(PERSON_ID);
        assertTrue(found.isPresent());
        assertEquals(found.get(), person);
    }

    @Test
    public void newEntityPersisted() {
        final Optional<Person> found = personRepo.findById(PERSON_ID);
        assertTrue(found.isPresent());
        assertEquals(found.get().getId(), PERSON_ID);
    }

}
