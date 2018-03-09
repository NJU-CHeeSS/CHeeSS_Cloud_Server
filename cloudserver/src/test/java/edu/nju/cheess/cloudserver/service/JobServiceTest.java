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

//    @Test
//    public void treatmentTest(){
//        jobService.analyzeTreatment("物流","上海");
//    }

//    @Test
//    public void testRecommendJobs() {
//        Page<JobInfoBean> jobPage = jobService.getRecommendedJobs("date", 10, 2, 1L);
//        jobPage.getResult().forEach(j -> System.out.println(j.getCompanyName()));
//    }

//    @Test
//    public void testGetJobByCondition() {
//        Page<JobInfoBean> jobs = jobService.getJobByCondition("low_money", 10, 2, "南京", "本科", "不限", "主管|总监|经理");
//        System.out.println(jobs.getTotalCount());
//        jobs.getResult().forEach(j -> System.out.println(j.getCompanyName() + " " + j.getMinSalary()));
//    }
}
