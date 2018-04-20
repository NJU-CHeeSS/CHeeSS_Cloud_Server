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
     * @param order            按什么排序
     * @param size             每页大小
     * @param page             第几页
     * @param location         地点
     * @param diploma          学历
     * @param earlyReleaseDate 发布时间
     * @param property         职位性质
     * @return 分页列表
     */
    Page<JobInfoBean> getJobByCondition(String order, int size, int page,
                                        String location, String diploma, String earlyReleaseDate, String property);

    /**
     * 推荐职位列表
     *
     * @return 列表
     */
    Page<JobInfoBean> getRecommendedJobs(String order, int size, int page, Long userId);

    /**
     * 获得职位薪水信息
     *
     * @param jobId 职位id
     * @return 职位薪水信息
     */
    SalaryInfoBean analyzeSalary(Long jobId);

    /**
     * 获得相关职位信息
     *
     * @param jobId 职位id
     * @return 相关企业列表
     */
    List<JobInfoBean> getRelatedJobs(Long jobId);

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

    /**
     * 获取职位申请人数
     *
     * @param jobId     职位id
     * @return          结果
     */
    ResultMessageBean countApply(Long jobId);

    /**
     * 分析岗位竞争对手情况
     *
     * @param jobId     职位id
     * @return          Result
     */
    CompetitionInfoBean analyseCompetitors(Long jobId);

}
