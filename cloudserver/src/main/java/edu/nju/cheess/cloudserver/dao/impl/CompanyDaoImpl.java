package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.Job;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private final HBaseHelper hBaseHelper;

    private final JobDaoImpl jobDao;

    private static final String TABLE_NAME = "cloud:company";

    @Autowired
    public CompanyDaoImpl(HBaseHelper hBaseHelper, JobDaoImpl jobDao) {
        this.hBaseHelper = hBaseHelper;
        this.jobDao = jobDao;
    }

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
    public List<Company> getCompanyByCondition(String keyword, Pageable pageable) {
        hBaseHelper.init();
        // 子串比较器
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "name",
                new SubstringComparator(keyword));
        hBaseHelper.close();

        return dataList.stream().map(this::getCompanyEntityByMap).collect(Collectors.toList());
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
        hBaseHelper.init();
        // 获取企业名称
        Map<String, String> data = hBaseHelper.getData(TABLE_NAME, String.valueOf(id), "info", "name");
        String companyName = data.get("name");

        List<Map<String, String>> mapList = hBaseHelper.getDataByColumnValue(JobDaoImpl.TABLE_NAME, "info", "company", companyName);
        hBaseHelper.close();

        return mapList.stream().map(jobDao::getJobEntityByMap).collect(Collectors.toList());
    }

    @Override
    public List<Company> getPopularCompanies() {
        // TODO
        return null;
    }

    @Override
    public List<Company> getCompanyByType(String type) {
        hBaseHelper.init();
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "company_type", type);
        hBaseHelper.close();

        return dataList.stream().map(this::getCompanyEntityByMap).collect(Collectors.toList());
    }

    @Override
    public List<Company> getCompanyByIndustry(String industry) {
        hBaseHelper.init();
        // 单个公司通常涉及众多产业，采用子串比较器
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "industry",
                new SubstringComparator(industry));
        hBaseHelper.close();

        return dataList.stream().map(this::getCompanyEntityByMap).collect(Collectors.toList());
    }

}
