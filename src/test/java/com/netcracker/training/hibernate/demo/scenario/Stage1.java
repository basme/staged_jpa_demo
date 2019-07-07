package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.InitialTestEntity;
import com.netcracker.training.hibernate.demo.service.repo.InitialTestEntityRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage1 {

    @Resource
    private InitialTestEntityRepo initialTestEntityRepo;

    @Test
    public void test() {
        InitialTestEntity entity = new InitialTestEntity(UUID.randomUUID(), "Some content");
        initialTestEntityRepo.save(entity);
        initialTestEntityRepo.findAll();
    }

}
