package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.util.DateUtil;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JobDaoImpl implements JobDao {

    public static final String TABLE_NAME = "cloud:job";

    private final HBaseHelper hBaseHelper;

    @Autowired
    public JobDaoImpl(HBaseHelper hBaseHelper) {
        this.hBaseHelper = hBaseHelper;
    }

    public Job convertMapToJobEntity(Map<String, String> data) {
        if (data.get("rowKey") == null) {
            return null;
        }

        Job job = new Job();

        job.setId(Long.valueOf(data.get("rowKey")));
        job.setTitle(data.get("title"));
        job.setLocation(data.get("location"));
        job.setCompany(data.get("company"));
        job.setLowMoney(Double.parseDouble(data.get("low_money")));
        job.setHighMoney(Double.parseDouble(data.get("high_money")));
        job.setLowExperience(Integer.parseInt(data.get("low_experience")));
        job.setHighExperience(Integer.parseInt(data.get("high_experience")));
        job.setDate(DateUtil.getLocalDateTime(data.get("date").split("\\.")[0]));
        job.setInformation(data.get("information"));
        job.setEducation(data.get("education"));
        job.setTotalPeople(data.get("total_people"));
        job.setJobType(data.get("job_type"));
        job.setCompanyLink(data.get("company_link"));

        return job;
    }

    @Override
    public Job getJobById(Long id) {
        Map<String, String> data = hBaseHelper.getData(TABLE_NAME, String.valueOf(id));

        return convertMapToJobEntity(data);

    }

    @Override
    public List<Job> getJobByJobType(String jobType) {
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(
                TABLE_NAME, "info", "job_type", new SubstringComparator(jobType));

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobByJobTypeAndCity(String jobType, String city) {
        FilterList filterList = new FilterList();
        // 职位类型子串过滤器
        SubstringComparator jobTypeComparator = new SubstringComparator(jobType);
        Filter jobTypeFilter = new SingleColumnValueFilter(
                Bytes.toBytes("info"), Bytes.toBytes("job_type"), CompareFilter.CompareOp.EQUAL, jobTypeComparator);

        // 城市过滤器
        SubstringComparator cityComparator = new SubstringComparator(city);
        Filter cityFilter = new SingleColumnValueFilter(
                Bytes.toBytes("info"), Bytes.toBytes("location"), CompareFilter.CompareOp.EQUAL, cityComparator);

        filterList.addFilter(jobTypeFilter);
        filterList.addFilter(cityFilter);

        List<Map<String, String>> dataList = hBaseHelper.getDataByFilterList(TABLE_NAME, filterList);

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobByCondition(String keyword, Pageable pageable) {
        List<Map<String, String>> dataList = hBaseHelper.getDataByColumnValue(TABLE_NAME, "info", "name", keyword);

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobs(Pageable pageable) {
        List<Map<String, String>> dataList = hBaseHelper.getDataByPage(TABLE_NAME, pageable.getPageSize(), pageable.getPageNumber());

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

}
