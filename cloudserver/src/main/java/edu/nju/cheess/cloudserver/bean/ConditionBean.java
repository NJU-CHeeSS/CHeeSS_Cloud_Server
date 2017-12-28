package edu.nju.cheess.cloudserver.bean;

import java.time.LocalDate;

public class ConditionBean {
    /**
     * 工作地点
     */
    private String location;

    /**
     * 发布日期
     */
    private LocalDate releaseDate;

    /**
     * 学历要求
     */
    private String diploma;

    /**
     * 专业
     */
    private String major;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 技能
     */
    private String skill;

    /**
     * 关键词
     */
    private String keyword;

    public ConditionBean(String location, LocalDate releaseDate, String diploma, String major, Integer age, String skill, String keyword) {
        this.location = location;
        this.releaseDate = releaseDate;
        this.diploma = diploma;
        this.major = major;
        this.age = age;
        this.skill = skill;
        this.keyword = keyword;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
