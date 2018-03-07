package edu.nju.cheess.cloudserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by CLL on 18/3/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobServiceTest {
    @Autowired
    JobService jobService;

    @Test
    public void skillTest(){
        System.out.print(jobService.analyseSkills("物流").getKeywords().size());
    }

    @Test
    public void treatmentTest(){
        jobService.analyzeTreatment("物流","上海");
    }
}
