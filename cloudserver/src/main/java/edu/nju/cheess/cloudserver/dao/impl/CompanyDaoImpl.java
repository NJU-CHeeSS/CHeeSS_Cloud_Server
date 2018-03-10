package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.CompanyDao;
import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.repository.FollowCompanyRepository;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CompanyDaoImpl implements CompanyDao {

    private final HBaseHelper hBaseHelper;

    private final JobDaoImpl jobDao;

    private final FollowCompanyRepository followCompanyRepository;

    private static final String TABLE_NAME = "cloud:company";

    @Autowired
    public CompanyDaoImpl(HBaseHelper hBaseHelper, JobDaoImpl jobDao, FollowCompanyRepository followCompanyRepository) {
        this.hBaseHelper = hBaseHelper;
        this.jobDao = jobDao;
        this.followCompanyRepository = followCompanyRepository;
    }

    private Company convertMapToCompanyEntity(Map<String, String> data) {
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
        Map<String, String> data = hBaseHelper.getData(TABLE_NAME, String.valueOf(id));

        return convertMapToCompanyEntity(data);
    }

    @Override
    public Company getCompanyByName(String name) {
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "name", name);
        if (dataList.isEmpty()) {
            return null;
        }
        return convertMapToCompanyEntity(dataList.get(0));
    }

    @Override
    public List<Company> getCompanyByCondition(String keyword, Pageable pageable) {
        // 子串比较器
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "name",
                new SubstringComparator(keyword));

        return dataList.stream().map(this::convertMapToCompanyEntity).collect(Collectors.toList());
    }

    @Override
    public List<Company> getCompanies(Pageable pageable) {
        List<Map<String, String>> mapList = hBaseHelper.getDataByPage(TABLE_NAME, pageable.getPageSize(), pageable.getPageNumber());

        return mapList.stream().map(this::convertMapToCompanyEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobs(Long id) {
        // 获取企业名称
        Map<String, String> data = hBaseHelper.getData(TABLE_NAME, String.valueOf(id), "info", "name");
        String companyName = data.get("name");

        List<Map<String, String>> mapList = hBaseHelper.getDataByColumnValue(JobDaoImpl.TABLE_NAME, "info", "company", companyName);

        return mapList.stream().map(jobDao::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Company> getPopularCompanies() {
//        List<Integer> followCompanies = followCompanyRepository.findPopularCompanyIDs();
        Map<Long, Long> popularMap = new TreeMap<>();
        Object[][] companyPopular = followCompanyRepository.findCompanyFollowNum();
        for (int i = 0; i < companyPopular.length; i++) {
            int id = (int)companyPopular[i][0];
            long num = (long)companyPopular[i][1];
            popularMap.put((long) id,(long)companyPopular[i][1]);
        }
        List<Map.Entry<Long,Long>> list = new ArrayList<>(popularMap.entrySet());
        //然后通过比较器来实现排序
        //降序排序
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        List<Company> result = new ArrayList<>();
        for (Map.Entry<Long,Long> followCompany : list) {
            //不超过10个
            if (result.size()>=10){
                break;
            }
            result.add(this.getCompanyById(followCompany.getKey()));
        }
        return result;
//        return followCompanies.stream().map(company-> this.getCompanyById(company.longValue())).collect(Collectors.toList());
    }

    @Override
    public List<Company> getCompanyByType(String type) {
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "company_type", type);

        return dataList.stream().map(this::convertMapToCompanyEntity).collect(Collectors.toList());
    }

    @Override
    public List<Company> getCompanyByIndustry(String industry) {
        // 单个公司通常涉及众多产业，采用子串比较器
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "industry",
                new SubstringComparator(industry));

        return dataList.stream().map(this::convertMapToCompanyEntity).collect(Collectors.toList());
    }

    @Override
    public List<Company> getCompanyByUser(Long userId) {
        // 获取用户关注企业id列表
        List<Integer> companyIds = followCompanyRepository.findCompanyIDs(userId);

        return companyIds.stream()
                .map(id -> convertMapToCompanyEntity(hBaseHelper.getData(TABLE_NAME, String.valueOf(id))))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
