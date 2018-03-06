package edu.nju.cheess.cloudserver.repository;

import edu.nju.cheess.cloudserver.entity.FollowCompany;
import edu.nju.cheess.cloudserver.entity.FollowCompanyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by CLL on 18/1/5.
 */
public interface FollowCompanyRepository extends JpaRepository<FollowCompany, FollowCompanyKey> {

    /**
     * 获得关注的企业ID列表
     * @param userID
     * @return
     */
    @Query("select fc.companyID from FollowCompany fc where fc.userID=?1")
    List<Integer> findCompanyIDs(Long userID);

    /**
     * 获得受关注的企业id
     * @return
     */
    @Query("select fc.companyID from FollowCompany fc")
    List<Integer> findPopularCompanyIDs();

    /**
     * 企业id及其关注用户数
     * @return
     */
    @Query("select fc.companyID as id, count (fc.userID) as num from FollowCompany fc group by fc.companyID")
    Object[][] findCompanyFollowNum();
}
