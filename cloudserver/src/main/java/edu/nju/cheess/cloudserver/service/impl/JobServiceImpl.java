package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.service.JobService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Override
    public JobInfoBean getJobInfo(Long jobId) {
        return null;
    }

    @Override
    public Page<JobInfoBean> getJobByKeyword(String keyword, String order, int size, int page) {
        return null;
    }

    @Override
    public Page<JobInfoBean> getJobByCondition(String keyword, String order, int size, int page, ConditionBean conditionBean) {
        return null;
    }

    @Override
    public List<JobInfoBean> getRecommendedJobs(Long userId) {
        return null;
    }

    @Override
    public SalaryInfoBean analyzeSalary(Long jobId) {
        return null;
    }

    @Override
    public TreatmentInfoBean analyzeTreatment() {
        return null;
    }

    @Override
    public CompareResultBean compareJobs(Long jobId1, Long jobId2) {
        return null;
    }
}
