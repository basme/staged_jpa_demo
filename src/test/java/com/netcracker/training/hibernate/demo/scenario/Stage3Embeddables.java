package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.Person;
import com.netcracker.training.hibernate.demo.model.embeddables.PersonContacts;
import com.netcracker.training.hibernate.demo.service.repo.PersonRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage3Embeddables {

    private static final int PERSON_ID_TWO = 2;

    @Resource
    private PersonRepo personRepo;

    @Test
    public void embeddablesAreNullsWhenNoFieldsFilled() {
        Person person = new Person();
        person.setId(PERSON_ID_TWO);
        personRepo.save(person);
        person = personRepo.findById(PERSON_ID_TWO).get();
        assertNull(person.getContacts());

        person.setContacts(new PersonContacts());
        personRepo.save(person);
        person = personRepo.findById(PERSON_ID_TWO).get();
        assertNull(person.getContacts());

        PersonContacts contacts = new PersonContacts();
        contacts.setEmail("");
        person.setContacts(contacts);
        personRepo.save(person);
        person = personRepo.findById(PERSON_ID_TWO).get();
        assertNotNull(person.getContacts());
    }

}
