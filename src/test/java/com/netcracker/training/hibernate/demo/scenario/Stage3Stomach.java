package com.netcracker.training.hibernate.demo.scenario;

import com.netcracker.training.hibernate.demo.config.HibernateConfig;
import com.netcracker.training.hibernate.demo.model.Stomach;
import com.netcracker.training.hibernate.demo.service.repo.StomachRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class Stage3Stomach {

    @Resource
    private StomachRepo stomachRepo;

    @Test
    public void boomHappens() {
        Stomach stomach = new Stomach();
        stomach.setId(1);
        stomach.setCapacity(10);
        stomach.setActualUtilization(5);
        stomachRepo.save(stomach);

        stomach = stomachRepo.findById(1).get();
    }

}
