package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.Company;
import edu.nju.cheess.cloudserver.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyDao {

    /**
     * 获得企业信息
     *
     * @param id 企业id
     * @return 企业信息
     */
    Company getCompanyById(Long id);

    /**
     * 根据关键词和条件分页获得企业列表
     *
     * @param keyword   关键词
     * @param pageable  分页信息
     * @return 企业信息列表
     */
    Page<Company> getCompanyByCondition(String keyword, Pageable pageable);

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

}
