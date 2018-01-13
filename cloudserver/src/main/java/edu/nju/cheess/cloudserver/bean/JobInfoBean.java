package edu.nju.cheess.cloudserver.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobInfoBean {

    /**
     * 职位id
     */
    private Long jobId;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 职位所属公司名称
     */
    private String companyName;

    /**
     * 职位性质
     */
    private String property;

    /**
     * 最低月薪
     */
    private Integer minSalary;

    /**
     * 最高月薪
     */
    private Integer maxSalary;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 发布日期
     */
    private LocalDateTime releaseDate;

    /**
     * 学历要求
     */
    private String diploma;

    /**
     * 招聘人数
     */
    private String peopleNum;

    /**
     * 最低工作经验年数
     */
    private Integer minExperience;

    /**
     * 最高工作经验年数
     */
    private Integer maxExperience;

    public JobInfoBean(Long jobId, String jobName, String companyName, String property, Integer minSalary, Integer maxSalary, String location, LocalDateTime date, String diploma, String peopleNum, Integer minExperience, Integer maxExperience) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.companyName = companyName;
        this.property = property;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.releaseDate = date;
        this.diploma = diploma;
        this.peopleNum = peopleNum;
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(String peopleNum) {
        this.peopleNum = peopleNum;
    }

    public Integer getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(Integer minExperience) {
        this.minExperience = minExperience;
    }

    public Integer getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(Integer maxExperience) {
        this.maxExperience = maxExperience;
    }

}
