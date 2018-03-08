package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.Job;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyDao {

    /**
     * 获得企业信息
     *
     * @param id    企业id
     * @return      企业信息
     */
    Company getCompanyById(Long id);

    /**
     * 获得企业信息
     *
     * @param name  企业名称
     * @return      企业信息
     */
    Company getCompanyByName(String name);

    /**
     * 根据关键词和条件分页获得企业列表
     *
     * @param keyword   关键词
     * @param pageable  分页信息
     * @return 企业信息列表
     */
    List<Company> getCompanyByCondition(String keyword, Pageable pageable);

    /**
     * 获取企业
     *
     * @param pageable  分页信息
     * @return          企业列表
     */
    List<Company> getCompanies(Pageable pageable);

    /**
     * 获得企业职位列表
     *
     * @param id 企业id
     * @return 职位列表
     */
    List<Job> getJobs(Long id);

    /**
     * 获得数据库中关注度比较高的几个企业
     *
     * @return 企业列表
     */
    List<Company> getPopularCompanies();

    /**
     * 获得某一类别的企业
     * @return
     */
    List<Company> getCompanyByType(String type);

    /**
     * 获得某一行业的企业（数据库中一个公司的多个行业由/隔开）
     * @param industry
     * @return
     */
    List<Company> getCompanyByIndustry(String industry);

    /**
     * 获得用户关注的公司列表
     *
     * @param id        用户id
     * @return          公司列表
     */
    List<Company> getCompanyByUser(Long id);
}
