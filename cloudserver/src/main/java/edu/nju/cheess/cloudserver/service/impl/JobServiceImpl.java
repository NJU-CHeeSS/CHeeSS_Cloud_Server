package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    private JobInfoBean jobToJobInfoBean(Job job) {
        return new JobInfoBean(job.getId(), job.getTitle(), job.getCompany(), job.getJobType(),
                (int) job.getLowMoney(), (int) job.getHighMoney(), job.getLocation(), job.getDate(), job.getEducation(),
                job.getTotalPeople(), job.getLowExperience(), job.getHighExperience());
    }

    @Override
    public JobInfoBean getJobInfo(Long jobId) {
        Job job = jobDao.getJobById(jobId);
        return jobToJobInfoBean(job);
    }

    @Override
    public Page<JobInfoBean> getJobByKeyword(String keyword, String order, int size, int page) {

        Page<JobInfoBean> res = new Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);

        // 默认按照日期倒序排序
        List<Job> jobPage = jobDao.getJobByCondition(keyword,
                new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, order == null ? "date" : order)));

        List<JobInfoBean> beans = new ArrayList<>();
        for (Job job : jobPage) {
            beans.add(jobToJobInfoBean(job));
        }
        res.setResult(beans);
        res.setTotalCount(jobPage.size());
        return res;
    }

    @Override
    public Page<JobInfoBean> getJobByCondition(String keyword, String order, int size, int page, ConditionBean conditionBean) {
        Page<JobInfoBean> res = new Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);

        // 默认按照日期倒序排序
        List<Job> jobList = jobDao.getJobByCondition(keyword,
                new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, order == null ? "date" : order)));
        List<JobInfoBean> beans = new ArrayList<>();

        for (Job job : jobList) {
            if (conditionBean.getLocation() != null && conditionBean.getLocation().contains(job.getLocation()) &&
                    conditionBean.getEarlyReleaseDate() != null && conditionBean.getEarlyReleaseDate().isBefore(job.getDate()) &&
                    conditionBean.getLateReleaseDate() != null && conditionBean.getLateReleaseDate().isAfter(job.getDate()) &&
                    conditionBean.getDiploma() != null && conditionBean.getDiploma().contains(job.getEducation())) {
                beans.add(jobToJobInfoBean(job));
            }
        }
        res.setResult(beans);
        res.setTotalCount(jobList.size());
        return res;
    }

    @Override
    public List<JobInfoBean> getRecommendedJobs(Long userId) {
        User user = userRepository.findOne(userId);

        return null;
    }

    @Override
    public SalaryInfoBean analyzeSalary(Long jobId) {
        Job job = jobDao.getJobById(jobId);

        return null;
    }

    @Override
    public TreatmentInfoBean analyzeTreatment() {
        return null;
    }

    @Override
    public CompareResultBean compareJobs(Long jobId1, Long jobId2) {
        Job job1 = jobDao.getJobById(jobId1);
        Job job2 = jobDao.getJobById(jobId2);

        return null;
    }

    @Override
    public SkillInfoBean analyseSkills(Long jobId) {
        Job job = jobDao.getJobById(jobId);

        return null;
    }
}
