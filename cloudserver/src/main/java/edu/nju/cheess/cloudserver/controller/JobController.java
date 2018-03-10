package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.service.JobService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 获得职位信息
     *
     * @param jobId 职位id
     * @return 职位信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/{jobId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public JobInfoBean getJobInfo(@PathVariable Long jobId) {

        return jobService.getJobInfo(jobId);
    }

    /**
     * 直接搜索职位列表
     *
     * @param keyword 关键字
     * @param order   按什么排序
     * @param size    每页大小
     * @param page    第几页
     * @return 分页列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs",
            params = {"keyword", "order", "size", "page"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<JobInfoBean> getJobByKeyword(@RequestParam(value = "keyword") String keyword,
                                             @RequestParam(value = "order") String order,
                                             @RequestParam(value = "size") int size,
                                             @RequestParam(value = "page") int page) {

        return jobService.getJobByKeyword(keyword, order, size, page);
    }

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
     * @return 职位列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/search",
            params = {"order", "size", "page", "location", "diploma", "earlyReleaseDate", "property"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<JobInfoBean> getJobByCondition(@RequestParam(value = "order") String order,
                                               @RequestParam(value = "size") int size,
                                               @RequestParam(value = "page") int page,
                                               @RequestParam(value = "location") String location,
                                               @RequestParam(value = "diploma") String diploma,
                                               @RequestParam(value = "earlyReleaseDate") String earlyReleaseDate,
                                               @RequestParam(value = "property") String property) {

        return jobService.getJobByCondition(order, size, page, location, diploma, earlyReleaseDate, property);
    }

    /**
     * 推荐职位列表
     *
     * @param order 按什么排序
     * @param size  每页大小
     * @param page  第几页
     * @return 推荐职位列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/users/{userId}/jobs",
            params = {"order", "size", "page"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<JobInfoBean> getRecommendedJobs(@RequestParam(value = "order") String order,
                                                @RequestParam(value = "size") int size,
                                                @RequestParam(value = "page") int page,
                                                @PathVariable Long userId) {

        return jobService.getRecommendedJobs(order, size, page, userId);
    }

    /**
     * 获得职位薪水信息
     *
     * @param jobId 职位id
     * @return 职位薪水信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/{jobId}/analyzeSalary",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public SalaryInfoBean analyzeSalary(@PathVariable Long jobId) {

        return jobService.analyzeSalary(jobId);
    }

    /**
     * 获得相关职业
     *
     * @param jobId 职位id
     * @return 相关职位信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/{jobId}/relate",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<JobInfoBean> getRelatedCompanies(@PathVariable Long jobId) {

        return jobService.getRelatedJobs(jobId);
    }

    /**
     * 获得待遇分析信息
     *
     * @return 待遇分析信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/analyzeTreatment",
            params = {"jobType", "city"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public TreatmentInfoBean analyzeTreatment(@RequestParam(value = "jobType") String jobType,
                                              @RequestParam(value = "city") String city) {

        return jobService.analyzeTreatment(jobType, city);
    }

    /**
     * 获得职位对比信息
     *
     * @return 职位对比信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/compare",
            params = {"jobId1", "jobId2"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public CompareResultBean compareJobs(@RequestParam(value = "jobId1") Long jobId1,
                                         @RequestParam(value = "jobId2") Long jobId2) {

        return jobService.compareJobs(jobId1, jobId2);
    }

    /**
     * 获得职位技能需求信息
     *
     * @return 职位技能需求信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/skill",
            params = {"jobType"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public SkillInfoBean analyseSkills(@RequestParam(value = "jobType") String jobType) {

        return jobService.analyseSkills(jobType);
    }

    /**
     * 查看职位申请人数
     *
     * @param jobId 职位id
     * @return (true, 申请人数)
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/apply",
            params = {"jobId"},
            method = RequestMethod.GET)
    public ResultMessageBean getApplyNum(@RequestParam(value = "jobId") Long jobId) {

        return jobService.countApply(jobId);
    }
}
