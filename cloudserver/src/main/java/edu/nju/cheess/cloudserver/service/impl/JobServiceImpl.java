package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.service.JobService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    @Override
    public JobInfoBean getJobInfo(Long jobId) {
        Job job = jobDao.getJobById(jobId);
        return new JobInfoBean(job.getId(), job.getTitle(), job.getCompany(), job.getJobType(),
                (int) job.getLowMoney(), (int) job.getHighMoney(), job.getLocation(), job.getDate(), job.getEducation(),
                job.getTotalPeople(), job.getLowExperience(), job.getHighExperience());
    }

    @Override
    public Page<JobInfoBean> getJobByKeyword(String keyword, String order, int size, int page) {

        edu.nju.cheess.cloudserver.util.Page<JobInfoBean> res = new edu.nju.cheess.cloudserver.util.Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);

        // 默认按照日期倒序排序
        org.springframework.data.domain.Page<Job> jobPage = jobDao.getJobByCondition(keyword,
                new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, order == null ? "date" : order)));

        List<JobInfoBean> beans = new ArrayList<>();
        for (Job job : jobPage.getContent()) {
            beans.add(new JobInfoBean(job.getId(), job.getTitle(), job.getCompany(), job.getJobType(),
                    (int) job.getLowMoney(), (int) job.getHighMoney(), job.getLocation(), job.getDate(), job.getEducation(),
                    job.getTotalPeople(), job.getLowExperience(), job.getHighExperience()));
        }
        res.setResult(beans);
        res.setTotalCount((int) jobPage.getTotalElements());
        return res;
    }

    @Override
    public Page<JobInfoBean> getJobByCondition(String keyword, String order, int size, int page, ConditionBean conditionBean) {
        edu.nju.cheess.cloudserver.util.Page<JobInfoBean> res = new edu.nju.cheess.cloudserver.util.Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);

        // 默认按照日期倒序排序
        org.springframework.data.domain.Page<Job> jobPage = jobDao.getJobByCondition(keyword,
                new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, order == null ? "date" : order)));
        List<JobInfoBean> beans = new ArrayList<>();

        for (Job job : jobPage.getContent()) {
            if (conditionBean.getLocation() != null && conditionBean.getLocation().contains(job.getLocation()) &&
                    conditionBean.getEarlyReleaseDate() != null && conditionBean.getEarlyReleaseDate().isBefore(job.getDate()) &&
                    conditionBean.getLateReleaseDate() != null && conditionBean.getLateReleaseDate().isAfter(job.getDate()) &&
                    conditionBean.getDiploma() != null && conditionBean.getDiploma().contains(job.getEducation())) {
                JobInfoBean jobInfoBean = new JobInfoBean(job.getId(), job.getTitle(), job.getCompany(), job.getJobType(),
                        (int) job.getLowMoney(), (int) job.getHighMoney(), job.getLocation(), job.getDate(), job.getEducation(),
                        job.getTotalPeople(), job.getLowExperience(), job.getHighExperience());
                beans.add(jobInfoBean);
            }
        }
        res.setResult(beans);
        res.setTotalCount((int) jobPage.getTotalElements());
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

    @Override
    public SkillInfoBean analyseSkills(Long jobId) {
        return null;
    }
}
