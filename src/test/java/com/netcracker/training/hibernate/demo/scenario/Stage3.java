package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.Building;
import com.netcracker.training.hibernate.demo.model.Person;
import com.netcracker.training.hibernate.demo.model.embeddables.BuildingId;
import com.netcracker.training.hibernate.demo.model.embeddables.PassportData;
import com.netcracker.training.hibernate.demo.model.embeddables.PersonContacts;
import com.netcracker.training.hibernate.demo.model.enums.Gender;
import com.netcracker.training.hibernate.demo.model.enums.MaritalStatus;
import com.netcracker.training.hibernate.demo.service.repo.BuildingRepo;
import com.netcracker.training.hibernate.demo.service.repo.PersonRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage3 {

    private static final int PERSON_ID_ONE = 1;

    private static final Date ISSUE_DATE = new Date();

    @Resource
    private PersonRepo personRepo;

    @Resource
    private BuildingRepo buildingRepo;

    @Test
    public void enumsAreProcessed() {
        final Person person = personRepo.findById(PERSON_ID_ONE).get();
        person.setGender(Gender.MALE);
        person.setMaritalStatus(MaritalStatus.MARRIED);
        personRepo.save(person);
    }

    @Test
    public void datesAreProcessed() throws ParseException {
        final Person person = personRepo.findById(PERSON_ID_ONE).get();
        person.setCreatedAt(new Date());
        person.setDateOfBirth(new SimpleDateFormat("dd-MM-yyyy").parse("22-11-1992"));
        personRepo.save(person);
    }

    @Test
    public void embeddablesAreProcessed() {
        final Person person = personRepo.findById(PERSON_ID_ONE).get();
        final PersonContacts contacts = new PersonContacts();
        contacts.setEmail("dan.bashmakov@netcracker.com");
        contacts.setPhone("1234567890");
        contacts.setPostAddress("Some address");
        person.setContacts(contacts);
        personRepo.save(person);
    }

    @Test
    public void converterSaves() {
        final Person person = personRepo.findById(PERSON_ID_ONE).get();
        person.setPassportData(new PassportData(1234, 567890, ISSUE_DATE, "Some desk"));
        personRepo.save(person);
    }

    @Test
    public void converterLoads() {
        final Person person = personRepo.findById(PERSON_ID_ONE).get();
        assertEquals(person.getPassportData().getIssuedBy(), "The best desk ever");
    }

    @Test
    public void compositeKeyProcessed() {
        BuildingId buildingId = new BuildingId("Lenin st.", 10);
        Building building = new Building();
        building.setBuildingId(buildingId);
        building.setStages(5);
        buildingRepo.save(building);
    }

    @Test
    public void keyIsGenerated() {
        Person person = new Person();
        person.setName("John Doe");
        personRepo.save(person);
    }

}
