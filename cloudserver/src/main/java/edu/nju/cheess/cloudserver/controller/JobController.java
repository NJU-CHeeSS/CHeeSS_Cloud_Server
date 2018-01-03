package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.*;
import edu.nju.cheess.cloudserver.service.JobService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return null;
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
        return null;
    }

    /**
     * 根据条件搜索职位列表
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
    public Page<JobInfoBean> getJobByCondition(@RequestParam(value = "keyword") String keyword,
                                               @RequestParam(value = "order") String order,
                                               @RequestParam(value = "size") int size,
                                               @RequestParam(value = "page") int page,
                                               ConditionBean conditionBean) {
        return null;
    }

    /**
     * 推荐职位列表
     *
     * @return 列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/users/{userId}/jobs",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<JobInfoBean> getRecommendedJobs(@PathVariable Long userId) {
        return null;
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
        return null;
    }

    /**
     * 获得待遇分析信息
     *
     * @return 待遇分析信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/analyzeTreatment",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public TreatmentInfoBean analyzeTreatment() {
        return null;
    }

    /**
     * 获得待遇分析信息
     *
     * @return 待遇分析信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/jobs/compare",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public CompareResultBean compareJobs(Long jobId1, Long jobId2) {
        return null;
    }
}