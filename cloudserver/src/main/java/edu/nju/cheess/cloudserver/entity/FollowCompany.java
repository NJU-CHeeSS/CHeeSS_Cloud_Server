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
    private int companyID;

    public FollowCompany() {
    }

    public FollowCompany(Long userID, int companyID) {
        this.userID = userID;
        this.companyID = companyID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
}
