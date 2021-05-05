package com.netcracker.training.hibernate.demo.scenario;

import com.google.common.collect.Lists;
import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.bean.PageBean;
import com.netcracker.training.hibernate.demo.model.relations.Author;
import com.netcracker.training.hibernate.demo.model.relations.Book;
import com.netcracker.training.hibernate.demo.model.relations.ContactInfo;
import com.netcracker.training.hibernate.demo.model.relations.Page;
import com.netcracker.training.hibernate.demo.service.repo.AuthorRepo;
import com.netcracker.training.hibernate.demo.service.repo.BookRepo;
import com.netcracker.training.hibernate.demo.service.util.Examples;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsArrayContaining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage7 {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private AuthorRepo authorRepo;

    @Resource
    private BookRepo bookRepo;

    @Test
    public void prepareData() {
        Author azimov = Examples.azimov();
        Author silverberg = Examples.silverberg();
        ContactInfo azimovInfo = Examples.azimovInfo();
        ContactInfo silverbergInfo = Examples.silverbergInfo();
        Book nightfall = Examples.nightfall();
        Book endOfEternity = Examples.endOfEternity();
        Book nebula = Examples.nebula();
        Page folder = Examples.nightfallFolder();
        Page page = Examples.nightfallSimplePage();
        folder.setBook(nightfall);
        page.setBook(nightfall);
        nightfall.getPages().addAll(Lists.newArrayList(folder, page));
        Book savedNightfall = bookRepo.save(nightfall);
        Book savedNebula = bookRepo.save(nebula);
        Book savedEndOfEternity = bookRepo.save(endOfEternity);
        azimov.getBooks().addAll(Lists.newArrayList(nightfall, endOfEternity));
        silverberg.getBooks().addAll(Lists.newArrayList(nightfall, nebula));
        azimov.setContactInfo(azimovInfo);
        silverberg.setContactInfo(silverbergInfo);
        authorRepo.save(azimov);
        authorRepo.save(silverberg);
    }

    @Test
    @Transactional
    public void jpqlQueryLoadsManagedEntity() {
        Query query = em.createQuery("SELECT b from Book b where b.title = ?1");
        query.setParameter(1, "Nightfall");
        List<Book> result = query.getResultList();
        assertNotNull(result);
        assertThat(result, hasSize(1));
        Book book = result.iterator().next();
        assertThat(book.getTitle(), is("Nightfall"));
        assertThat(book.getAuthors(), hasSize(2));
    }

    @Test
    @Transactional
    public void projectionLoadsOnlyNeededFields() {
        Query query = em.createQuery("SELECT p.number, p.isTechnical from Page p");
        List<Object> result = query.getResultList();
        assertThat(result, hasItem(Matchers.isA(Object[].class)));
        assertThat(result, hasSize(2));
        assertThat(result, hasItem(IsArrayContaining.hasItemInArray(is(1))));
    }

    @Test
    @Transactional
    public void projectionConstructsBean() {
        Query query = em.createQuery("SELECT new com.netcracker.training.hibernate.demo.model.bean.PageBean(p.number, p.isTechnical) from Page p");
        List<Object> result = query.getResultList();
        assertThat(result, hasItem(Matchers.isA(PageBean.class)));
        assertThat(result, hasSize(2));
        assertThat(result, hasItem(hasProperty("number", is(56))));
    }

    @Test
    @Transactional
    public void joinsWorkStrange() {
        Query query = em.createQuery("SELECT a, b from Book b, Author a");
        List result = query.getResultList();

        query = em.createQuery("SELECT b from Book b join b.authors");
        result = query.getResultList();

        query = em.createQuery("SELECT b from Book b left join b.authors");
        result = query.getResultList();

        query = em.createQuery("SELECT b from Book b join b.authors a on a.name = 'Isaac Asimov'");
        result = query.getResultList();

        query = em.createQuery("SELECT b from Book b join fetch b.authors");
        result = query.getResultList();
    }

    @Test
    public void groupingAndOrderingWorksStrange() {
        Query query = em.createQuery("SELECT b, count(a) from Book b left join b.authors a group by b");
        List result = query.getResultList();

        query = em.createQuery("SELECT b, count(a) from Book b left join b.authors a group by b having count(a) > 1");
        result = query.getResultList();

        query = em.createQuery("SELECT b from Book b join fetch b.authors a order by a.name desc");
        result = query.getResultList();
    }

    @Test
    @Transactional
    public void updateWorks() {
        Query query = em.createQuery("update Book b set title = 'Nightfell' where b.title = 'Nightfall'");
        query.executeUpdate();
        assertTrue(bookRepo.findAll().stream().anyMatch(b -> b.getTitle().equals("Nightfell")));
    }

    @Test
    public void namedNativeQueryWorks() {
        Query query = em.createNamedQuery("page.get");
        List<Object> result = query.getResultList();
        assertThat(result, hasItem(Matchers.isA(Object[].class)));
        assertThat(result, hasSize(2));
        assertThat(result, hasItem(IsArrayContaining.hasItemInArray(Matchers.isA(PageBean.class))));
        assertThat(result, hasItem(IsArrayContaining.hasItemInArray(Matchers.isA(BigInteger.class))));
        assertThat(result, hasItem(IsArrayContaining.hasItemInArray(Matchers.isA(Page.class))));
    }




}
