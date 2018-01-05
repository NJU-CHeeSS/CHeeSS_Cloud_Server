package edu.nju.cheess.cloudserver.entity;

import java.io.Serializable;

/**
 * Created by CLL on 18/1/5.
 */
public class FollowCompanyKey implements Serializable {
    private Long userID;
    private int companyID;

    public FollowCompanyKey() {
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

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((userID == null) ? 0 : userID.hashCode());
        result = PRIME * result + ((companyID == 0) ? 0 : companyID);
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final FollowCompanyKey other = (FollowCompanyKey) obj;
        if(userID == null){
            if(other.userID != null){
                return false;
            }
        }else if(!userID.equals(other.userID)){
            return false;
        }
        if(companyID==other.companyID){
            return true;
        }
        else {
            return false;
        }
    }
}
