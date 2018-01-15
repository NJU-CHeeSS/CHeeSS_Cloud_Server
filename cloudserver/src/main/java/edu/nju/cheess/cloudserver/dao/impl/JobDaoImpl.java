package edu.nju.cheess.cloudserver.dao.impl;

import edu.nju.cheess.cloudserver.dao.HBaseHelper;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JobDaoImpl implements JobDao {

    private static final String TABLE_NAME = "cloud:job";

    @Autowired
    private HBaseHelper hBaseHelper;

    private Job getJobEntityByMap(Map<String, String> data) {
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
        hBaseHelper.init();
        Map<String, String> data = hBaseHelper.getData(TABLE_NAME, String.valueOf(id));
        hBaseHelper.close();

        return getJobEntityByMap(data);

    }

    @Override
    public Page<Job> getJobByCondition(String keyword, Pageable pageable) {
        return null;
    }

    @Override
    public List<Job> getJobs(Pageable pageable) {
        hBaseHelper.init();
        List<Map<String, String>> dataList = hBaseHelper.getDataByPage(TABLE_NAME, pageable.getPageSize(), pageable.getPageNumber());
        hBaseHelper.close();

        return dataList.stream().map(this::getJobEntityByMap).collect(Collectors.toList());
    }

}
