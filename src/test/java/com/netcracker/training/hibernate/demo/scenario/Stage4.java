package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.Client;
import com.netcracker.training.hibernate.demo.model.Employee;
import com.netcracker.training.hibernate.demo.model.Person;
import com.netcracker.training.hibernate.demo.service.repo.ClientRepo;
import com.netcracker.training.hibernate.demo.service.repo.PersonRepo;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage4 {

    @Resource
    private PersonRepo personRepo;

    @Resource
    private ClientRepo clientRepo;

    @Test
    public void polymorphicQueryReturnsAllInheritors() {
        Employee employee = new Employee();
        employee.setName("Some Employee");
        employee.setEmployeeCode("daba0216");
        personRepo.save(employee);

        Client client = new Client();
        client.setName("Some Client");
        client.setSatisfactionRate(1.22f);
        personRepo.save(client);

        final List<Person> allPersons = personRepo.findAll();
        assertThat(allPersons, hasItem(hasProperty("name", is("Some Employee"))));
        assertThat(allPersons, hasItem(hasProperty("name", is("Some Client"))));
        assertThat(allPersons, hasItem(hasProperty("name", is("Dan"))));
    }

    @Test
    public void specificQueryReturnsOnlySpecificEntities() {
        final List<Client> allClients = clientRepo.findAll();
        assertThat(allClients, hasSize(1));
        assertThat(allClients, hasItem(hasProperty("name", is("Some Client"))));
    }

    @Test
    public void lobsAreSerialized() {
        Client client = new Client();
        client.setName("Lob client");
        HashMap<String, String> qestionnaire = new HashMap<>();
        qestionnaire.put("Q1", "A1");
        client.setQuestionnaire(qestionnaire);
        clientRepo.save(client);
    }

}
