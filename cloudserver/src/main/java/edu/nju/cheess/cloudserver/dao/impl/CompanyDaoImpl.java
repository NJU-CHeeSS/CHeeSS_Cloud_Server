package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    @Autowired
    private HBaseHelper hBaseHelper;

    private static final String TABLE_NAME = "cloud:company";

    private Company getCompanyEntityByMap(Map<String, String> data) {
        if (data.get("rowKey") == null) {
            return null;
        }

        Company company = new Company();
        company.setId(Long.valueOf(data.get("rowKey")));
        company.setName(data.get("name"));
        company.setType(data.get("company_type"));
        company.setIndustry(data.get("industry"));
        company.setIntroduction(data.get("introduction"));
        company.setSize(data.get("size"));

        return company;
    }

    @Override
    public Company getCompanyById(Long id) {
        hBaseHelper.init();
        Map<String, String> data = hBaseHelper.getData(TABLE_NAME, String.valueOf(id));
        hBaseHelper.close();

        return getCompanyEntityByMap(data);
    }

    @Override
    public Page<Company> getCompanyByCondition(String keyword, Pageable pageable) {
        // TODO
        hBaseHelper.init();
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(TABLE_NAME, "info", "name", keyword);
        hBaseHelper.close();

        List<Company> companies = dataList.stream().map(this::getCompanyEntityByMap).collect(Collectors.toList());
        for (Company company : companies) {
            System.out.println(company.getId());
            System.out.println(company.getName());
            System.out.println();
        }

        return null;
    }

    @Override
    public List<Company> getCompanies(Pageable pageable) {
        hBaseHelper.init();
        List<Map<String, String>> mapList = hBaseHelper.getDataByPage(TABLE_NAME, pageable.getPageSize(), pageable.getPageNumber());
        hBaseHelper.close();

        return mapList.stream().map(this::getCompanyEntityByMap).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobs(Long id) {
        return null;
    }

    @Override
    public List<Company> getPopularCompanies() {
        return null;
    }
}
