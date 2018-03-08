package edu.nju.cheess.cloudserver.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by CLL on 18/3/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyServiceTest {
    @Autowired
    CompanyService companyService;

    @Test
    public void relatedCompanyTest(){
        companyService.getRelatedCompanies((long) 1);
    }
}
