package edu.nju.cheess.cloudserver.repository;

import java.util.List;

/**
 * Created by CLL on 18/1/5.
 */
public interface FollowCompanyRepository {
    /**
     * 关注企业
     * @param username
     * @param companyID
     */
    void followCompany(String username,int companyID);

    /**
     * 获得关注的企业ID列表
     * @param username
     * @return
     */
    List<String> getFollowCompanyIDs(String username);
}
