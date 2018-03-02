package edu.nju.cheess.cloudserver.service;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.util.Page;

import java.util.List;

public interface JobService {

    /**
     * 获得职位信息
     *
     * @param jobId 职位id
     * @return 职位信息
     */
    JobInfoBean getJobInfo(Long jobId);

    /**
     * 直接搜索职位列表
     *
     * @param keyword 关键字
     * @param order   按什么排序
     * @param size    每页大小
     * @param page    第几页
     * @return 分页列表
     */
    Page<JobInfoBean> getJobByKeyword(String keyword, String order, int size, int page);

    /**
     * 根据条件搜索职位列表
     *
     * @param order         按什么排序
     * @param size          每页大小
     * @param page          第几页
     * @param conditionBean 搜索条件
     * @return 分页列表
     */
    Page<JobInfoBean> getJobByCondition(String order, int size, int page, ConditionBean conditionBean);

    /**
     * 推荐职位列表
     *
     * @return 列表
     */
    List<JobInfoBean> getRecommendedJobs(Long userId);

    /**
     * 获得职位薪水信息
     *
     * @param jobId 职位id
     * @return 职位薪水信息
     */
    SalaryInfoBean analyzeSalary(Long jobId);

    /**
     * 获得待遇分析信息
     *
     * @return 待遇分析信息
     */
    TreatmentInfoBean analyzeTreatment(String jobType, String city);

    /**
     * 职位比较
     *
     * @return 职位比较信息
     */
    CompareResultBean compareJobs(Long jobId1, Long jobId2);

    /**
     * 获得职位技能需求信息
     *
     * @return 职位技能需求信息
     */
    SkillInfoBean analyseSkills(String jobType);
}
