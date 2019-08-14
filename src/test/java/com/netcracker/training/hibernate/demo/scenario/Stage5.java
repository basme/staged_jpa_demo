package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.relations.Author;
import com.netcracker.training.hibernate.demo.model.relations.Book;
import com.netcracker.training.hibernate.demo.model.relations.ContactInfo;
import com.netcracker.training.hibernate.demo.model.relations.Page;
import com.netcracker.training.hibernate.demo.service.repo.AuthorRepo;
import com.netcracker.training.hibernate.demo.service.repo.BookRepo;
import com.netcracker.training.hibernate.demo.service.repo.ContactInfoRepo;
import com.netcracker.training.hibernate.demo.service.repo.PageRepo;
import com.netcracker.training.hibernate.demo.service.util.Examples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage5 {

    @Resource
    private AuthorRepo authorRepo;

    @Resource
    private ContactInfoRepo contactInfoRepo;

    @Resource
    private BookRepo bookRepo;

    @Resource
    private PageRepo pageRepo;

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void relatedEntityShouldBeSavedFirst() {
        Author azimov = Examples.azimov();
        ContactInfo azimovContact = Examples.azimovInfo();
        azimov.setContactInfo(azimovContact);
        authorRepo.save(azimov);
    }

    @Test
    public void hostSavesOneToOneRelation() {
        Author azimov = Examples.azimov();
        ContactInfo azimovContact = Examples.azimovInfo();
        azimov.setContactInfo(azimovContact);
        final ContactInfo savedContact = contactInfoRepo.save(azimovContact);
        final Author savedAuthor = authorRepo.save(azimov);
        assertThat(savedAuthor.getContactInfo(), is(azimovContact));
        assertNull(azimovContact.getAuthor());
        assertNull(savedContact.getAuthor());
        assertSame(azimovContact, savedContact);
    }

    @Test
    public void inverseDoesNotSaveOneToOneRelation() {
        Author azimov = Examples.azimov();
        ContactInfo azimovContact = Examples.azimovInfo();
        azimovContact.setAuthor(azimov);
        Author savedAuthor = authorRepo.save(azimov);
        ContactInfo savedContact = contactInfoRepo.save(azimovContact);
        assertNull(savedAuthor.getContactInfo());
    }

    @Test
    public void hostSavesManyToOneRelation() {
        Book nightfall = Examples.nightfall();

        Page folder = Examples.nightfallFolder();
        Page page = Examples.nightfallSimplePage();

        Collection<Page> pages = new HashSet<>();
        pages.add(page);
        pages.add(folder);
        nightfall.setPages(pages);

        folder.setBook(nightfall);
        page.setBook(nightfall);

        final Book savedBook = bookRepo.save(nightfall);
        final Page savedFolder = pageRepo.save(folder);
        final Page savedPage = pageRepo.save(page);

        assertEquals(savedPage.getBook(), savedBook);
    }

    @Test
    @Transactional
    public void oneSideLoadsOneToManyRelation() {
        final Book book = bookRepo.findAll().stream().findAny().get();
        assertThat(book.getPages(), hasSize(2));
    }

    @Test
    public void oneSideSavesManyToManyRelations() {
        Book nightfall = Examples.nightfall();
        Book nebula = Examples.nebula();
        Book endOfEternity = Examples.endOfEternity();

        Author azimov = Examples.azimov();
        Author silverberg = Examples.silverberg();

        authorRepo.saveAll(Arrays.asList(azimov, silverberg));
        bookRepo.saveAll(Arrays.asList(nightfall, nebula, endOfEternity));

        azimov.getBooks().addAll(Arrays.asList(nightfall, endOfEternity));
        silverberg.getBooks().addAll(Arrays.asList(nightfall, nebula));

        nebula.getAuthors().addAll(Arrays.asList(silverberg));
        endOfEternity.getAuthors().addAll(Arrays.asList(azimov));
        nightfall.getAuthors().addAll(Arrays.asList(azimov, silverberg));

        authorRepo.save(azimov);
        authorRepo.save(silverberg);
    }

    @Test
    public void defaultOneToOneRelationAllowsNulls() {
        Author azimov = Examples.azimov();
        final Author savedAuthor = authorRepo.save(azimov);
        assertNull(savedAuthor.getContactInfo());
    }

}
