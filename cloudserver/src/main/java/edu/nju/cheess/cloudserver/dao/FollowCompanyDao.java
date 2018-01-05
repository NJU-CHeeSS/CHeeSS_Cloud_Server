package edu.nju.cheess.cloudserver.dao;

import edu.nju.cheess.cloudserver.entity.Company;

import java.util.List;

/**
 * Created by CLL on 18/1/5.
 */
public interface FollowCompanyDao {
    /**
     * 关注企业
     * @param username
     * @param companyID
     */
    void followCompany(String username,int companyID);

    /**
     * 获得关注的企业列表
     * @param username
     * @return
     */
    List<Company> getFollowCompanies(String username);
}
