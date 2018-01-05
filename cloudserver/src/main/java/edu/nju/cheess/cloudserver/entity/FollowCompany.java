package edu.nju.cheess.cloudserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by CLL on 18/1/5.
 */
@Entity
@Table(name = "followCompany")
public class FollowCompany {
    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private int userID;

    /**
     * 公司ID
     */
    @Column(name = "company_id")
    private int companyID;

    public FollowCompany(int userID, int companyID) {
        this.userID = userID;
        this.companyID = companyID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
}
