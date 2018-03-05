package edu.nju.cheess.cloudserver.bean;

import java.util.List;

public class CompareResultBean {
    /**
     * 职位名称
     */
    private String job1Name;
    private String job2Name;

    /**
     * 职位所属公司名称
     */
    private String company1Name;
    private String company2Name;

    /**
     * 企业关键词
     */
    private List<String> company1Keywords;
    private List<String> company2Keywords;

    /**
     * 职位性质
     */
    private String job1Property;
    private String job2Property;

    /**
     * 最低月薪
     */
    private Integer job1MinSalary;
    private Integer job2MinSalary;

    /**
     * 最高月薪
     */
    private Integer job1MaxSalary;
    private Integer job2MaxSalary;

    /**
     * 工作地点
     */
    private String job1Location;
    private String job2Location;

    /**
     * 招聘人数
     */
    private String job1PeopleNum;
    private String job2PeopleNum;

    /**
     * 职位关键词
     */
    private List<String> job1Keywords;
    private List<String> job2Keywords;

    public CompareResultBean() {
    }

    public CompareResultBean(String job1Name, String job2Name, String company1Name, String company2Name, List<String> company1Keywords, List<String> company2Keywords, String job1Property, String job2Property, Integer job1MinSalary, Integer job2MinSalary, Integer job1MaxSalary, Integer job2MaxSalary, String job1Location, String job2Location, String job1PeopleNum, String job2PeopleNum, List<String> job1Keywords, List<String> job2Keywords) {
        this.job1Name = job1Name;
        this.job2Name = job2Name;
        this.company1Name = company1Name;
        this.company2Name = company2Name;
        this.company1Keywords = company1Keywords;
        this.company2Keywords = company2Keywords;
        this.job1Property = job1Property;
        this.job2Property = job2Property;
        this.job1MinSalary = job1MinSalary;
        this.job2MinSalary = job2MinSalary;
        this.job1MaxSalary = job1MaxSalary;
        this.job2MaxSalary = job2MaxSalary;
        this.job1Location = job1Location;
        this.job2Location = job2Location;
        this.job1PeopleNum = job1PeopleNum;
        this.job2PeopleNum = job2PeopleNum;
        this.job1Keywords = job1Keywords;
        this.job2Keywords = job2Keywords;
    }

    public String getJob1Name() {
        return job1Name;
    }

    public void setJob1Name(String job1Name) {
        this.job1Name = job1Name;
    }

    public String getJob2Name() {
        return job2Name;
    }

    public void setJob2Name(String job2Name) {
        this.job2Name = job2Name;
    }

    public String getCompany1Name() {
        return company1Name;
    }

    public void setCompany1Name(String company1Name) {
        this.company1Name = company1Name;
    }

    public String getCompany2Name() {
        return company2Name;
    }

    public void setCompany2Name(String company2Name) {
        this.company2Name = company2Name;
    }

    public List<String> getCompany1Keywords() {
        return company1Keywords;
    }

    public void setCompany1Keywords(List<String> company1Keywords) {
        this.company1Keywords = company1Keywords;
    }

    public List<String> getCompany2Keywords() {
        return company2Keywords;
    }

    public void setCompany2Keywords(List<String> company2Keywords) {
        this.company2Keywords = company2Keywords;
    }

    public String getJob1Property() {
        return job1Property;
    }

    public void setJob1Property(String job1Property) {
        this.job1Property = job1Property;
    }

    public String getJob2Property() {
        return job2Property;
    }

    public void setJob2Property(String job2Property) {
        this.job2Property = job2Property;
    }

    public Integer getJob1MinSalary() {
        return job1MinSalary;
    }

    public void setJob1MinSalary(Integer job1MinSalary) {
        this.job1MinSalary = job1MinSalary;
    }

    public Integer getJob2MinSalary() {
        return job2MinSalary;
    }

    public void setJob2MinSalary(Integer job2MinSalary) {
        this.job2MinSalary = job2MinSalary;
    }

    public Integer getJob1MaxSalary() {
        return job1MaxSalary;
    }

    public void setJob1MaxSalary(Integer job1MaxSalary) {
        this.job1MaxSalary = job1MaxSalary;
    }

    public Integer getJob2MaxSalary() {
        return job2MaxSalary;
    }

    public void setJob2MaxSalary(Integer job2MaxSalary) {
        this.job2MaxSalary = job2MaxSalary;
    }

    public String getJob1Location() {
        return job1Location;
    }

    public void setJob1Location(String job1Location) {
        this.job1Location = job1Location;
    }

    public String getJob2Location() {
        return job2Location;
    }

    public void setJob2Location(String job2Location) {
        this.job2Location = job2Location;
    }

    public String getJob1PeopleNum() {
        return job1PeopleNum;
    }

    public void setJob1PeopleNum(String job1PeopleNum) {
        this.job1PeopleNum = job1PeopleNum;
    }

    public String getJob2PeopleNum() {
        return job2PeopleNum;
    }

    public void setJob2PeopleNum(String job2PeopleNum) {
        this.job2PeopleNum = job2PeopleNum;
    }

    public List<String> getJob1Keywords() {
        return job1Keywords;
    }

    public void setJob1Keywords(List<String> job1Keywords) {
        this.job1Keywords = job1Keywords;
    }

    public List<String> getJob2Keywords() {
        return job2Keywords;
    }

    public void setJob2Keywords(List<String> job2Keywords) {
        this.job2Keywords = job2Keywords;
    }
}
