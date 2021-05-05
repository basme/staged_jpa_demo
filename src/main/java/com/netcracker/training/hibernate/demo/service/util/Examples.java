package com.netcracker.training.hibernate.demo.service.util;

import com.netcracker.training.hibernate.demo.model.relations.Author;
import com.netcracker.training.hibernate.demo.model.relations.Book;
import com.netcracker.training.hibernate.demo.model.relations.ContactInfo;
import com.netcracker.training.hibernate.demo.model.relations.Page;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by daba0216 on 17.04.2018.
 */
public class Examples {

    public static final BigInteger AZIMOV_ID = BigInteger.valueOf(12345l);
    public static final BigInteger SILVERBERG_ID = BigInteger.valueOf(12346l);

    public static Author azimov() {
        Author azimov = new Author();
        azimov.setAuthorId(AZIMOV_ID);
        azimov.setName("Isaac Azimov");
        azimov.setBooks(new ArrayList<>());
        return azimov;
    }

    public static Author silverberg() {
        Author silverberg = new Author();
        silverberg.setAuthorId(SILVERBERG_ID);
        silverberg.setName("Robert Silverberg");
        silverberg.setBooks(new ArrayList<>());
        return silverberg;
    }

    public static ContactInfo azimovInfo() {
        ContactInfo azimovInfo = new ContactInfo();
        azimovInfo.setMail("azimov@test.com");
        azimovInfo.setPhone("55512345678");
        return azimovInfo;
    }

    public static ContactInfo silverbergInfo() {
        ContactInfo silverbergInfo = new ContactInfo();
        silverbergInfo.setMail("silverberg@test.com");
        silverbergInfo.setPhone("5554365734567");
        return silverbergInfo;
    }

    public static Book nightfall() {
        Book book = new Book();
        book.setTitle("Nightfall");
        book.setAuthors(new ArrayList<>());
        book.setPages(new ArrayList<>());
        return book;
    }

    public static Book endOfEternity() {
        Book book = new Book();
        book.setTitle("End Of Eternity");
        book.setAuthors(new ArrayList<>());
        book.setPages(new ArrayList<>());
        return book;
    }

    public static Book nebula() {
        Book book = new Book();
        book.setTitle("Nebula");
        book.setAuthors(new ArrayList<>());
        book.setPages(new ArrayList<>());
        return book;
    }

    public static Page nightfallFolder() {
        Page page = new Page();
        page.setNumber(1);
        page.setTechnical(true);
        return page;
    }

    public static Page nightfallSimplePage() {
        Page page = new Page();
        page.setNumber(56);
        page.setTechnical(false);
        return page;
    }

}
