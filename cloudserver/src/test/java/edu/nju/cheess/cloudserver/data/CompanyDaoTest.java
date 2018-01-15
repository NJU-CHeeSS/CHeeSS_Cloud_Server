package edu.nju.cheess.cloudserver.data;

import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTest {

    @Autowired
    public CompanyDao companyDao;

    @Test
    public void testGetCompanyById() {
        Company company = companyDao.getCompanyById(1L);
        System.out.println(company.getName());
        System.out.println(company.getIndustry());
        System.out.println(company.getType());
        System.out.println(company.getIntroduction());
        System.out.println(company.getSize());
    }

    @Test
    public void testSearch() {
        companyDao.getCompanyByCondition("广告有限公司", null);
    }

}
