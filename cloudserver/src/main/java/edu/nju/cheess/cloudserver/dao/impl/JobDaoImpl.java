package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.util.DateUtil;
import edu.nju.cheess.cloudserver.util.Diploma;
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
    public List<Job> getJobByJobType(List<String> jobType) {
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        jobType.forEach(t -> filterList
                .addFilter(new SingleColumnValueFilter(
                        Bytes.toBytes("info"), Bytes.toBytes("job_type"),
                        CompareFilter.CompareOp.EQUAL, new SubstringComparator(t)
        )));
        List<Map<String, String>> dataList = hBaseHelper.getDataByFilterList(TABLE_NAME, filterList);

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobByJobTypeAndCity(List<String> jobType, String city) {
        // 职位类型过滤列表
        FilterList jobTypeFilterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        jobType.forEach(t -> jobTypeFilterList
                .addFilter(new SingleColumnValueFilter(
                        Bytes.toBytes("info"), Bytes.toBytes("job_type"),
                        CompareFilter.CompareOp.EQUAL, new SubstringComparator(t)
        )));

        // 城市过滤器
        Filter cityFilter = new SingleColumnValueFilter(
                Bytes.toBytes("info"), Bytes.toBytes("location"),
                CompareFilter.CompareOp.EQUAL, new SubstringComparator(city));

        FilterList filterList = new FilterList();
        filterList.addFilter(jobTypeFilterList);
        filterList.addFilter(cityFilter);

        List<Map<String, String>> dataList = hBaseHelper.getDataByFilterList(TABLE_NAME, filterList);

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobByCondition(String keyword, String location, String education, String time, Pageable pageable) {
        FilterList filterList = new FilterList();
        if (location != null) {
            filterList.addFilter(new SingleColumnValueFilter(
                    Bytes.toBytes("info"), Bytes.toBytes("location"),
                    CompareFilter.CompareOp.EQUAL, new SubstringComparator(location)));
        }
        if (education != null) {
            filterList.addFilter(new SingleColumnValueFilter(
                    Bytes.toBytes("requirement"), Bytes.toBytes("education"),
                    CompareFilter.CompareOp.EQUAL, Bytes.toBytes(education)));
        }
        // 大于设置时间
        if (time != null) {
            filterList.addFilter(new SingleColumnValueFilter(
                    Bytes.toBytes("info"), Bytes.toBytes("date"),
                    CompareFilter.CompareOp.GREATER, Bytes.toBytes(time)
            ));
        }
        filterList.addFilter(new SingleColumnValueFilter(
                Bytes.toBytes("info"), Bytes.toBytes("title"),
                CompareFilter.CompareOp.EQUAL, new SubstringComparator(keyword)));

        List<Map<String, String>> dataList = hBaseHelper.getDataByFilterList(TABLE_NAME, filterList);

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getJobByCondition(String keyword, Pageable pageable) {
        return getJobByCondition(keyword, null, null, null, pageable);
    }

    @Override
    public List<Job> getJobs(Pageable pageable) {
        List<Map<String, String>> dataList = hBaseHelper.getDataByPage(TABLE_NAME, pageable.getPageSize(), pageable.getPageNumber());

        return dataList.stream().map(this::convertMapToJobEntity).collect(Collectors.toList());
    }

    @Override
    public List<Job> getRecommendJobs(String location, String diploma, List<String> skills) {
        FilterList skillFilterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        skills.forEach(s -> skillFilterList
                .addFilter(new SingleColumnValueFilter(
                        Bytes.toBytes("info"), Bytes.toBytes("information"),
                        CompareFilter.CompareOp.EQUAL, new SubstringComparator(s)
        )));

        Filter locationFilter = new SingleColumnValueFilter(
                Bytes.toBytes("info"), Bytes.toBytes("location"),
                CompareFilter.CompareOp.EQUAL, new SubstringComparator(location));

        FilterList filterList = new FilterList();
        filterList.addFilter(skillFilterList);
        filterList.addFilter(locationFilter);

        Diploma diplomaEnum = Diploma.stringToDiploma(diploma);

        List<Map<String, String>> dataList = hBaseHelper.getDataByFilterList(TABLE_NAME, filterList);

        return dataList.stream()
                .filter(d -> {
                    Diploma education = Diploma.stringToDiploma(d.get("education"));
                    return diplomaEnum == null || education == null || education.ordinal() <= diplomaEnum.ordinal();})
                .map(this::convertMapToJobEntity)
                .collect(Collectors.toList());
    }

}
