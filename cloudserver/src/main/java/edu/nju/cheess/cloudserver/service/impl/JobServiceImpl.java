package edu.nju.cheess.cloudserver.service.impl;

import com.hankcs.hanlp.HanLP;
import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.dao.JobDao;
import edu.nju.cheess.cloudserver.entity.Job;
import edu.nju.cheess.cloudserver.entity.User;
import edu.nju.cheess.cloudserver.repository.ApplyJobRepository;
import edu.nju.cheess.cloudserver.repository.UserRepository;
import edu.nju.cheess.cloudserver.service.CompanyService;
import edu.nju.cheess.cloudserver.service.JobService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobDao jobDao;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyService companyService;

    @Autowired
    ApplyJobRepository applyJobRepository;


    private JobInfoBean jobToJobInfoBean(Job job) {
        return new JobInfoBean(job.getId(), job.getTitle(), job.getCompany(), job.getJobType(),
                (int) job.getLowMoney(), (int) job.getHighMoney(), job.getLocation(), String.valueOf(job.getDate()).split("T")[0],
                job.getInformation(), job.getEducation(), job.getTotalPeople(), job.getLowExperience(), job.getHighExperience());
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
                new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, order.equals("") ? "date" : order)));

        List<JobInfoBean> beans = new ArrayList<>();
        for (Job job : jobPage) {
            beans.add(jobToJobInfoBean(job));
        }
        res.setResult(beans);
        res.setTotalCount(jobPage.size());
        return res;
    }

    @Override
    public Page<JobInfoBean> getJobByCondition(String order, int size, int page,
                                               String location, String diploma, String earlyReleaseDate, String property) {
        Page<JobInfoBean> res = new Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);

        // 默认按照日期倒序排序
        List<Job> jobList = jobDao.getJobByCondition("",
                new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, order.equals("") ? "date" : order)));
        List<JobInfoBean> beans = new ArrayList<>();

        for (Job job : jobList) {
            LocalDateTime time = LocalDateTime.now();
            switch (earlyReleaseDate) {
                case "近24小时":
                    time = time.minusDays(1);
                    break;
                case "上周":
                    time = time.minusWeeks(1);
                    break;
                case "上月":
                    time = time.minusMonths(1);
                    break;
            }
            if (!location.equals("不限") && location.equals(job.getLocation()) &&
                    !earlyReleaseDate.equals("不限") && time.isBefore(job.getDate()) &&
                    !diploma.equals("不限") && diploma.equals(job.getEducation()) &&
                    !property.equals("不限") && jobDao.getJobByJobType(getJobTypeList(property)).contains(job)) {

                beans.add(jobToJobInfoBean(job));
            }
        }
        res.setResult(beans);
        res.setTotalCount(jobList.size());
        return res;
    }

    @Override
    public Page<JobInfoBean> getRecommendedJobs(String order, int size, int page, Long userId) {
        Page<JobInfoBean> res = new Page<>();
        res.setOrder(order);
        res.setPage(page);

        User user = userRepository.findOne(userId);
        List<String> skills = Arrays.asList(user.getSkill().trim().split("[，,]"));

        List<Job> jobs = jobDao.getRecommendJobs(user.getCity(), user.getDiploma(), skills);
        Map<Long, Integer> jobApplyNumMap = new HashMap<>();
        jobs.forEach(j -> jobApplyNumMap.put(j.getId(), applyJobRepository.countByJobId(j.getId())));

        switch (order) {
            case "date":        // 日期逆序
                jobs.sort((j1, j2) -> j2.getDate().compareTo(j1.getDate()));
                break;
            case "low_money":   // 最低薪资正序
                jobs.sort(Comparator.comparingDouble(Job::getLowMoney));
                break;
            case "hot":         // 申请数逆序
                jobs.sort((j1, j2) -> jobApplyNumMap.get(j2.getId()) - jobApplyNumMap.get(j1.getId()));
                break;
            default:
                break;
        }

        // 分页
        int fromIndex = size * (page - 1);
        int toIndex = size * page;

        toIndex = toIndex > jobs.size() ? jobs.size() : toIndex;
        fromIndex = fromIndex > toIndex ? toIndex : fromIndex;

        jobs = jobs.subList(fromIndex, toIndex);
        res.setSize(jobs.size());
        res.setResult(jobs.stream().map(this::jobToJobInfoBean).collect(Collectors.toList()));
        return res;
    }

    @Override
    public SalaryInfoBean analyzeSalary(Long jobId) {
        Job job = jobDao.getJobById(jobId);

        return null;
    }

    @Override
    public List<JobInfoBean> getRelatedJobs(Long jobId) {
        Job job = jobDao.getJobById(jobId);
        List<JobInfoBean> relatedJobs = new ArrayList<>();
        int count = 0;

        for (String jobType : job.getJobType().split(" ")) {
            for (Job relatedJob : jobDao.getJobByJobType(getJobTypeList(jobType))) {
                if (!relatedJobs.contains(jobToJobInfoBean(relatedJob)) && !relatedJob.getId().equals(jobId)) {
                    relatedJobs.add(jobToJobInfoBean(relatedJob));
                    count++;
                }

                if (count == 10) {
                    return relatedJobs;
                }
            }
        }
        return relatedJobs;
    }

    @Override
    public TreatmentInfoBean analyzeTreatment(String jobType, String city) {
        List<Job> countryJobs = jobDao.getJobByJobType(getJobTypeList(jobType));
        List<Job> cityJobs = new ArrayList<>();

        /* 全国的职位 */
        double countryLowSum = 0.0, countryHighSum = 0.0;
        double countryLowest = countryJobs.get(0).getLowMoney(), countryHighest = 0.0;
        Set<String> areaSet = new HashSet<>();

        /* 该城市的职位 */
        double cityLowSum = 0.0, cityHighSum = 0.0;
        double cityLowest = Double.MAX_VALUE, cityHighest = 0.0;
        Set<String> sizeSet = new HashSet<String>() {{
            add("大型企业");
            add("中型企业");
            add("小型企业");
        }};

        Set<String> educationSet = new HashSet<>();
        int maxExperience = 0;

        for (Job job : countryJobs) {
            double lowMoney = job.getLowMoney();
            double highMoney = job.getHighMoney();

            countryLowSum += lowMoney;
            countryHighSum += highMoney;
            countryLowest = (countryLowest < lowMoney) ? countryLowest : lowMoney;
            countryHighest = (countryHighest > highMoney) ? countryHighest : highMoney;
            areaSet.add(getCity(job));

            if (getCity(job).equals(city)) {
                cityJobs.add(job);

                cityLowSum += lowMoney;
                cityHighSum += highMoney;
                cityLowest = (cityLowest < lowMoney) ? cityLowest : lowMoney;
                cityHighest = (cityHighest > highMoney) ? cityHighest : highMoney;

                educationSet.add(job.getEducation());

                int highExperience = job.getHighExperience();
                maxExperience = (highExperience > maxExperience) ? highExperience : maxExperience;
            }
        }
        cityLowest = cityLowest == Double.MAX_VALUE ? 0 : cityLowest;

        List<AreaSalaryBean> areaSalary = new ArrayList<>();
        for (String area : areaSet) {
            List<Integer> areaStatisticResult = getStatisticResult(countryJobs, area, "area");
            if (areaStatisticResult != null) {
                areaSalary.add(new AreaSalaryBean(area, areaStatisticResult.get(0), areaStatisticResult.get(1), areaStatisticResult.get(2)));
            }
        }

        List<TreatmentDistributionBean> distribution = getDistribution(cityJobs);

        List<SizeSalaryBean> sizeSalary = new ArrayList<>();
        for (String size : sizeSet) {
            List<Integer> sizeStatisticResult = getStatisticResult(cityJobs, size, "size");
            if (sizeStatisticResult != null) {
                sizeSalary.add(new SizeSalaryBean(size, sizeStatisticResult.get(0), sizeStatisticResult.get(1), sizeStatisticResult.get(2)));
            }
        }

        List<EducationSalaryBean> educationSalary = new ArrayList<>();
        for (String education : educationSet) {
            List<Integer> educationStatisticResult = getStatisticResult(cityJobs, education, "education");
            if (educationStatisticResult != null) {
                educationSalary.add(new EducationSalaryBean(education, educationStatisticResult.get(0), educationStatisticResult.get(1), educationStatisticResult.get(2)));
            }
        }

        List<ExperienceSalaryBean> experienceSalary = new ArrayList<>();
        for (int experience = 0; experience < maxExperience; experience++) {
            List<Integer> experienceStatisticResult = getStatisticResult(cityJobs, String.valueOf(experience), "experience");
            if (experienceStatisticResult != null) {
                experienceSalary.add(new ExperienceSalaryBean(String.valueOf(experience) + "年", experienceStatisticResult.get(0), experienceStatisticResult.get(1), experienceStatisticResult.get(2)));
            }
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

        return new CompareResultBean(job1.getTitle(), job2.getTitle(), job1.getCompany(), job2.getCompany(),
                companyService.getCompanyByName(job1.getCompany()).getKeywords(),
                companyService.getCompanyByName(job2.getCompany()).getKeywords(),
                job1.getJobType(), job2.getJobType(), (int) job1.getLowMoney(), (int) job2.getLowMoney(),
                (int) job1.getHighMoney(), (int) job2.getHighMoney(), job1.getLocation(), job2.getLocation(),
                job1.getTotalPeople(), job2.getTotalPeople(), getKeywordsByInformation(job1.getInformation()),
                getKeywordsByInformation(job2.getInformation()));
    }

    @Override
    public SkillInfoBean analyseSkills(String jobType) {
        final String[] REQUIRE_BEGINNER = {"任职资格：", "任职要求：", "岗位要求："};
        final String[] REMOVE_WORD={"欢迎","加入","相关","公司","一定","工作","具有","优先","能够","强烈",
                "享受","良好","较强","熟悉","企业","使用","优先","善于","擅长","帮主","悟空","现场","面试",
                "不限","性别","面试","良好","职业","具备"};
        List<Job> jobs = jobDao.getJobByJobType(getJobTypeList(jobType));
        List<String> keywords = new ArrayList<>();
        List<Integer> keywordsNum = new ArrayList<>();

        String information = "";
        String require = "";

        for (Job job : jobs) {
            information = job.getInformation();
            for (String beginner : REQUIRE_BEGINNER) {
                if (information.contains(beginner)) {
                    int startIndex = information.indexOf(beginner) + (beginner).length();
                    //找到下一个：
                    int endIndex = information.indexOf("：", startIndex);
                    require = information.substring(startIndex, endIndex);
                    break;
                }
            }
            List<String> jobKeywords = getKeywordsByInformation(require);
            for (String jobKeyword : jobKeywords) {
                boolean containRemove=false;
                for (String word :REMOVE_WORD){
                    //排除词汇
                    if (word.equals(jobKeyword)){
                        containRemove=true;
                    }
                }
                //跳过
                if (containRemove){
                    continue;
                }
                if (!keywords.contains(jobKeyword)) {
                    keywords.add(jobKeyword);
                    keywordsNum.add(1);
                } else {
                    int index = keywords.indexOf(jobKeyword);
                    keywordsNum.set(index, keywordsNum.get(index) + 1);
                }
            }
        }
        List<SkillKeywordsBean> skillKeywords = new ArrayList<>();
        for (int i = 0; i < keywords.size() && i < 10; i++) {
            skillKeywords.add(new SkillKeywordsBean(keywords.get(i), keywordsNum.get(i)));
        }

        return new SkillInfoBean(jobType, skillKeywords);
    }

    /**
     * 获得职位列表的统计数据
     *
     * @param jobs   职位列表
     * @param string 统计的关键词
     * @return 统计数据，平均值，最低值，最高值
     */
    private List<Integer> getStatisticResult(List<Job> jobs, String string, String type) {
        double lowSum = 0.0, highSum = 0.0;
        double lowest = Integer.MAX_VALUE, highest = 0.0;
        int num = 0;

        for (Job job : jobs) {
            boolean isSatisfied = false;

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

        if (num == 0) {
            return null;
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
     * @return 薪资分布结果
     */
    private List<TreatmentDistributionBean> getDistribution(List<Job> jobs) {
        List<TreatmentDistributionBean> distribution = new ArrayList<>();
        final int LEVEL = 5; // 分成5个级别
        int[] salaryRange = {0, 4000, 10000, 20000, 30000}; // 表驱动
        int[] salaryRangeNum = new int[LEVEL];

        for (Job job : jobs) {
            if (job.getLowMoney() >= salaryRange[LEVEL - 1]) {
                salaryRangeNum[LEVEL - 1]++;
                continue;
            }
            for (int i = 0; i < LEVEL - 1; i++) {
                if (job.getLowMoney() >= salaryRange[i] && job.getLowMoney() < salaryRange[i + 1]) {
                    salaryRangeNum[i]++;
                    break;
                }
            }
        }

        distribution.add(new TreatmentDistributionBean("4000元以下", salaryRangeNum[0]));
        distribution.add(new TreatmentDistributionBean("4000元-10000元", salaryRangeNum[1]));
        distribution.add(new TreatmentDistributionBean("10000元-20000元", salaryRangeNum[2]));
        distribution.add(new TreatmentDistributionBean("20000元-30000元", salaryRangeNum[3]));
        distribution.add(new TreatmentDistributionBean("30000元以上", salaryRangeNum[4]));
        return distribution;
    }

    /**
     * 获得职位城市
     *
     * @param job 职位
     * @return 城市名字
     */
    private String getCity(Job job) {
        return job.getLocation().split("-")[0];
    }

    /**
     * 通过职位information获得职位关键词
     *
     * @param information 职位信息
     * @return 职位关键词列表
     */
    private List<String> getKeywordsByInformation(String information) {
        //调用HanLP TextRank算法
        return HanLP.extractKeyword(information, 3);
    }

    /**
     * 得到职位类型列表
     *
     * @param jobType 职位类型
     * @return 职位类型列表
     */
    private List<String> getJobTypeList(String jobType) {
        List<String> jobTypeList = new ArrayList<>();
        Collections.addAll(jobTypeList, jobType.replace(" ", "").split("\\|"));

        return jobTypeList;
    }

    @Override
    public ResultMessageBean countApply(Long jobId) {
        return new ResultMessageBean(true, String.valueOf(applyJobRepository.countByJobId(jobId)));
    }
}
