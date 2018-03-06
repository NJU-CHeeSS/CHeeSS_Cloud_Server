package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.CompanyAnalyseBean;
import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.bean.JobInfoBean;
import edu.nju.cheess.cloudserver.service.CompanyService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    /**
     * 获得企业信息
     *
     * @param companyId 企业id
     * @return 企业信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/id/{companyId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public CompanyInfoBean getCompanyById(@PathVariable Long companyId) {

        return companyService.getCompanyById(companyId);
    }

    /**
     * 获得企业信息
     *
     * @param companyName 企业名称
     * @return 企业信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/name/{companyName}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public CompanyInfoBean getCompanyByName(@PathVariable String companyName) {

        return companyService.getCompanyByName(companyName);
    }

    /**
     * 搜索企业列表
     *
     * @param keyword 关键字
     * @param size    每页大小
     * @param page    第几页
     * @return 分页列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/search",
            params = {"keyword", "size", "page"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<CompanyMiniBean> getCompanyByKeyword(@RequestParam(value = "keyword") String keyword,
                                                     @RequestParam(value = "size") int size,
                                                     @RequestParam(value = "page") int page) {
        return companyService.findCompanyByKeyword(keyword, size, page);
    }

    /**
     * 获得企业职位列表
     *
     * @param companyId 企业id
     * @return 企业职位列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/jobs",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<JobInfoBean> getJobs(@PathVariable Long companyId) {

        return companyService.getJobs(companyId);
    }

    /**
     * 获得热门企业
     *
     * @return 热门企业列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/popular",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<CompanyMiniBean> getPopularCompanies() {

        return companyService.getPopularCompanies();
    }

    /**
     * 获得相关企业
     *
     * @param companyId 企业id
     * @return 相关企业信息
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/relate",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<CompanyMiniBean> getRelatedCompanies(@PathVariable Long companyId) {

        return companyService.getRelatedCompanies(companyId);
    }

    /**
     * 获得同类行业企业排行
     *
     * @param industry 行业名
     * @return 同类行业企业排行
     */
    @ResponseBody
    @RequestMapping(
            value = "/rank/{industry}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public List<CompanyMiniBean> getCompaniesRank(@PathVariable String industry) {

        return companyService.getCompaniesRank(industry);
    }

    /**
     * 企业水平分析
     *
     * @param companyId 企业id
     * @return 企业水平分析
     */
    @ResponseBody
    @RequestMapping(
            value = "/{companyId}/analyse",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public CompanyAnalyseBean getCompanyAnalyse(@PathVariable Long companyId) {

        return companyService.getCompanyAnalyse(companyId);
    }
}
