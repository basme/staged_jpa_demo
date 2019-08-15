package com.netcracker.training.hibernate.demo.scenario;

import com.google.common.collect.Lists;
import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.fetching.Child;
import com.netcracker.training.hibernate.demo.model.fetching.Parent;
import com.netcracker.training.hibernate.demo.model.relations.Author;
import com.netcracker.training.hibernate.demo.model.relations.Book;
import com.netcracker.training.hibernate.demo.model.relations.ContactInfo;
import com.netcracker.training.hibernate.demo.model.relations.Page;
import com.netcracker.training.hibernate.demo.service.repo.AuthorRepo;
import com.netcracker.training.hibernate.demo.service.repo.PageRepo;
import com.netcracker.training.hibernate.demo.service.repo.ParentRepo;
import com.netcracker.training.hibernate.demo.service.util.Examples;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage6 {

    @Resource
    private AuthorRepo authorRepo;

    @Resource
    private ParentRepo parentRepo;

    @Test
    public void cascadesPropagateActionsToRelatedEntities() {
        Author azimov = Examples.azimov();
        ContactInfo azimovInfo = Examples.azimovInfo();
        Book nightfall = Examples.nightfall();
        Page folder = Examples.nightfallFolder();
        Page page = Examples.nightfallSimplePage();
        azimov.getBooks().add(nightfall);
        nightfall.getPages().addAll(Lists.newArrayList(folder, page));
        azimov.setContactInfo(azimovInfo);
        authorRepo.save(azimov);
    }

    @Test
    public void prepareFetchingData() {
        final List<Parent> parents = IntStream.range(0, 10).boxed().map(i -> {
            final Parent parent = new Parent(i, String.valueOf(i));
            parent.setChildren(
                    IntStream.range(0, 50).boxed().map(j -> new Child(i*100 + j, String.valueOf(j), parent)).collect(Collectors.toSet()));
            return parent;
        }).collect(Collectors.toList());
        parentRepo.saveAll(parents);
    }

    @Test
    @Transactional
    public void fetchTypeJoinCausesJoinsInHibernateSelect() {
        Parent parent = parentRepo.getOne(5);
        final Set<Child> children = parent.getChildren();
    }

    @Test
    @Transactional
    public void batchSizeCausesRelatedSetsBatching() {
        parentRepo.findAll().forEach(p -> {
            final Set<Child> children = p.getChildren();
        });
    }
}
