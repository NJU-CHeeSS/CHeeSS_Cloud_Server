package edu.nju.cheess.cloudserver.service;

import edu.nju.cheess.cloudserver.bean.CompanyAnalyseBean;
import edu.nju.cheess.cloudserver.bean.CompanyInfoBean;
import edu.nju.cheess.cloudserver.bean.CompanyMiniBean;
import edu.nju.cheess.cloudserver.bean.JobInfoBean;
import edu.nju.cheess.cloudserver.util.Page;

import java.util.List;

public interface CompanyService {

    /**
     * 获得企业信息
     *
     * @param companyId 企业id
     * @return 企业信息
     */
    CompanyInfoBean getCompanyById(Long companyId);

    /**
     * 获得企业信息
     *
     * @param name 企业名称
     * @return 企业信息
     */
    CompanyInfoBean getCompanyByName(String name);

    /**
     * 搜索企业列表
     *
     * @param keyword 关键字
     * @param size    每页大小
     * @param page    第几页
     * @return 分页列表
     */
    Page<CompanyMiniBean> findCompanyByKeyword(String keyword, int size, int page);


    /**
     * 获得企业职位列表
     *
     * @param companyId 企业id
     * @return 职位列表
     */
    List<JobInfoBean> getJobs(Long companyId);


    /**
     * 获得数据库中关注度比较高的几个企业
     *
     * @return 企业列表
     */
    List<CompanyMiniBean> getPopularCompanies();


    /**
     * 获得相关企业
     *
     * @param companyId 企业Id
     * @return 相关企业列表
     */
    List<CompanyMiniBean> getRelatedCompanies(Long companyId);


    /**
     * 获得同类行业企业排行
     *
     * @param industry 行业名
     * @return 相关企业列表
     */
    List<CompanyMiniBean> getCompaniesRank(String industry);


    /**
     * 企业水平分析(最高薪资，最低薪资，平均薪资)
     *
     * @param companyId 企业Id
     * @return 企业水平分析
     */
    CompanyAnalyseBean getCompanyAnalyse(Long companyId);


    /**
     * 通过企业名称获得企业规模
     *
     * @param name 企业名称
     * @return 企业规模
     */
    String getCompanySize(String name);
}
