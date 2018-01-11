package edu.nju.cheess.cloudserver.data;

import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobDaoTest {

    @Autowired
    public JobDao jobDao;

    @Test
    public void testGetJobById() {
        Job job = jobDao.getJobById(1L);
        System.out.println(job.getTitle());
    }

}
