package edu.nju.cheess.cloudserver.controller;

import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.bean.ResultMessageBean;
import edu.nju.cheess.cloudserver.service.CompanyService;
import edu.nju.cheess.cloudserver.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            value = "/{companyId}",
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public CompanyInfoBean getCompanyById(@PathVariable Long companyId) {
        return null;
    }

    /**
     * 搜索企业列表
     *
     * @param keyword 关键字
     * @param order   按什么排序
     * @param size    每页大小
     * @param page    第几页
     * @return 分页列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/search",
            params = {"keyword", "order", "size", "page"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<CompanyMiniBean> getCompanyByKeyword(@RequestParam(value = "keyword") String keyword,
                                                     @RequestParam(value = "order") String order,
                                                     @RequestParam(value = "size") int size,
                                                     @RequestParam(value = "page") int page) {
        return null;
    }

    /**
     * 关注企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return 关注结果
     */
    @ResponseBody
    @RequestMapping(
            value = "/follow",
            method = RequestMethod.GET)
    public ResultMessageBean follow(Long userId, Long companyId) {
        return null;
    }

    /**
     * 取关企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return 取关结果
     */
    @ResponseBody
    @RequestMapping(
            value = "/unfollow",
            method = RequestMethod.GET)
    public ResultMessageBean cancelFollow(Long userId, Long companyId) {
        return null;
    }

    /**
     * 获得是否关注企业
     *
     * @param userId    用户id
     * @param companyId 企业id
     * @return 是否关注企业
     */
    @ResponseBody
    @RequestMapping(
            value = "/checkFollow",
            method = RequestMethod.GET)
    public boolean checkFollow(Long userId, Long companyId) {

        return false;
    }

}
