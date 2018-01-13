package edu.nju.cheess.cloudserver.entity;

import javax.persistence.*;

/**
 * Created by CLL on 18/1/5.
 */
@Entity
@Table(name = "follow_company")
@IdClass(FollowCompanyKey.class)
public class FollowCompany {
    @Id
    @Column(name = "user_id")
    private Long userID;

    @Id
    @Column(name = "company_id")
    private Long companyID;

    public FollowCompany() {
    }

    public FollowCompany(Long userID, Long companyID) {
        this.userID = userID;
        this.companyID = companyID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }
}
