package edu.nju.cheess.cloudserver.service.impl;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.repository.UserRepository;
import edu.nju.cheess.cloudserver.service.CompanyService;
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

    @Autowired
    CompanyService companyService;

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
    public TreatmentInfoBean analyzeTreatment(String jobType, String city) {
        List<Job> countryJobs = jobDao.getJobByJobType(jobType);
        List<Job> cityJobs = jobDao.getJobByJobTypeAndCity(jobType, city);

        /* 全国的职位 */
        double countryLowSum = 0.0, countryHighSum = 0.0, countryLowest = Integer.MAX_VALUE, countryHighest = 0.0; // 会有工资比Integer.MAX_VALUE还高吗
        List<String> areaList = new ArrayList<>();

        for (Job countryJob : countryJobs) {
            double countryLow = countryJob.getLowMoney();
            double countryHigh = countryJob.getHighMoney();

            countryLowSum += countryLow;
            countryHighSum += countryHigh;
            countryLowest = (countryLowest < countryLow) ? countryLowest : countryLow;
            countryHighest = (countryHighest > countryHigh) ? countryHighest : countryHigh;

            String area = getCity(countryJob);
            if (!areaList.contains(area)) {
                areaList.add(area);
            }
        }

        List<AreaSalaryBean> areaSalary = new ArrayList<>();
        for (String area : areaList) {
            List<Integer> areaStatisticResult = getStatisticResult(countryJobs, area, "area");
            areaSalary.add(new AreaSalaryBean(area, areaStatisticResult.get(0), areaStatisticResult.get(1), areaStatisticResult.get(2)));
        }


        /* 该城市的职位 */
        double cityLowSum = 0.0, cityHighSum = 0.0, cityLowest = Integer.MAX_VALUE, cityHighest = 0.0;
        List<String> sizeList = new ArrayList<>();
        List<String> educationList = new ArrayList<>();
        int maxExperience = 0;

        for (Job cityJob : cityJobs) {
            double cityLow = cityJob.getLowMoney();
            double cityHigh = cityJob.getHighMoney();

            cityLowSum += cityLow;
            cityHighSum += cityHigh;
            cityLowest = (cityLowest < cityLow) ? cityLowest : cityLow;
            cityHighest = (cityHighest > cityHigh) ? cityHighest : cityHigh;

            String size = companyService.getCompanySize(cityJob.getCompany());
            if (!sizeList.contains(size)) {
                sizeList.add(size);
            }

            String education = cityJob.getEducation();
            if (!educationList.contains(education)) {
                educationList.add(education);
            }

            int highExperience = cityJob.getHighExperience();
            maxExperience = (highExperience > maxExperience) ? highExperience : maxExperience;
        }

        List<TreatmentDistributionBean> distribution = getDistribution(cityJobs, cityLowest, cityHighest);

        List<SizeSalaryBean> sizeSalary = new ArrayList<>();
        for (String size : sizeList) {
            List<Integer> sizeStatisticResult = getStatisticResult(cityJobs, size, "size");
            sizeSalary.add(new SizeSalaryBean(size, sizeStatisticResult.get(0), sizeStatisticResult.get(1), sizeStatisticResult.get(2)));
        }

        List<EducationSalaryBean> educationSalary = new ArrayList<>();
        for (String education : educationList) {
            List<Integer> educationStatisticResult = getStatisticResult(cityJobs, education, "education");
            sizeSalary.add(new SizeSalaryBean(education, educationStatisticResult.get(0), educationStatisticResult.get(1), educationStatisticResult.get(2)));
        }

        List<ExperienceSalaryBean> experienceSalary = new ArrayList<>();
        for (int experience = 0; experience < maxExperience; experience++) {
            List<Integer> experienceStatisticResult = getStatisticResult(cityJobs, String.valueOf(experience), "experience");
            sizeSalary.add(new SizeSalaryBean(String.valueOf(experience) + "年", experienceStatisticResult.get(0), experienceStatisticResult.get(1), experienceStatisticResult.get(2)));
        }

        return new TreatmentInfoBean(jobType, city,
                (int) (cityHighSum + cityLowSum) / 2 / cityJobs.size(), (int) cityLowest, (int) cityHighest,
                (int) (countryHighSum + countryLowSum) / 2 / countryJobs.size(), (int) countryLowest, (int) countryHighest,
                distribution, areaSalary, sizeSalary, educationSalary, experienceSalary);
    }

    @Override
    public CompareResultBean compareJobs(Long jobId1, Long jobId2) {
        Job job1 = jobDao.getJobById(jobId1);
        Job job2 = jobDao.getJobById(jobId2);

        return null;
    }

    @Override
    public SkillInfoBean analyseSkills(String jobType) {
        return null;
    }

    /**
     * 获得职位列表的统计数据
     *
     * @param jobs   职位列表
     * @param string 统计的关键词
     * @return 统计数据，平均值，最低值，最高值
     */
    private List<Integer> getStatisticResult(List<Job> jobs, String string, String type) {
        double lowSum = 0.0, highSum = 0.0, lowest = Integer.MAX_VALUE, highest = 0.0;
        int num = 0;

        boolean isSatisfied = false;
        for (Job job : jobs) {

            if (type.equals("experience")) {
                int lowExperience = job.getLowExperience();
                int highExperience = job.getHighExperience();
                int experience = Integer.valueOf(string);

                if (experience >= lowExperience && experience <= highExperience) {
                    isSatisfied = true;
                }

            } else {
                String judgeString = "";

                switch (type) {
                    case "size":
                        judgeString = companyService.getCompanySize(job.getCompany());
                        break;
                    case "area":
                        judgeString = getCity(job);
                        break;
                    case "education":
                        judgeString = job.getEducation();
                        break;
                }

                if (judgeString.equals(string)) {
                    isSatisfied = true;
                }
            }

            if (isSatisfied) {
                double low = job.getLowMoney();
                double high = job.getHighMoney();

                lowSum += low;
                highSum += high;
                lowest = (lowest < low) ? lowest : low;
                highest = (highest > high) ? highest : high;
                num++;
            }
        }

        List<Integer> res = new ArrayList<>();
        res.add((int) (lowSum + highSum) / 2 / num);
        res.add((int) lowest);
        res.add((int) highest);

        return res;
    }

    /**
     * 获得薪资分布结果
     *
     * @param lowestRaw  最低薪资
     * @param highestRaw 最高薪资
     * @return 薪资分布结果
     */
    private List<TreatmentDistributionBean> getDistribution(List<Job> jobs, double lowestRaw, double highestRaw) {
        List<TreatmentDistributionBean> distribution = new ArrayList<>();

        // 最低薪资和最高薪资归一化
        int lowestBitNum = String.valueOf((int) lowestRaw).length();
        int lowest = (int) ((int) (lowestRaw / Math.pow(10, lowestBitNum - 1)) * Math.pow(10, lowestBitNum - 1));

        int highestBitNum = String.valueOf((int) highestRaw).length();
        int highest = (int) ((int) (highestRaw / Math.pow(10, highestBitNum - 1) + 1) * Math.pow(10, highestBitNum - 1));

        final int LEVEL = 5; // 分成5个级别

        int gap = (highest - lowest) / LEVEL;
        int[] salaryRange = new int[LEVEL]; // 表驱动
        int[] salaryRangeNum = new int[LEVEL];
        for (int i = 0; i < LEVEL; i++) {
            salaryRange[i] = lowest + gap * i;
            salaryRangeNum[i] = 0;
        }

        for (Job job : jobs) {
            for (int i = 0; i < LEVEL - 1; i++) {
                if (job.getLowMoney() >= salaryRange[i] && job.getLowMoney() < salaryRange[i + 1]) {
                    salaryRangeNum[i]++;
                }
            }

            if (job.getLowMoney() >= salaryRange[LEVEL - 1]) {
                salaryRangeNum[LEVEL - 1]++;
            }
        }

        for (int i = 0; i < LEVEL; i++) {
            distribution.add(new TreatmentDistributionBean(String.valueOf(lowest + gap * i) +
                    "-" + String.valueOf(lowest + gap * (i + 1)) + "元", salaryRangeNum[i]));
        }

        return distribution;
    }

    /**
     * 获得职位城市
     *
     * @param job 职位
     * @return 城市名字
     */
    private String getCity(Job job) {
        String location = job.getLocation();
        String area;
        if (location.contains("-")) {
            area = location.split("-")[0];
        } else {
            area = location;
        }

        return area;
    }
}
