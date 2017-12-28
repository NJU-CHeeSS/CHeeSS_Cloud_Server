package edu.nju.cheess.cloudserver.entity;

import java.time.LocalDate;
import java.util.List;

public class User {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别，男为1，女为0
     */
    private Integer sex;

    /**
     * 城市列表
     */
    private List<String> cities;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 专业
     */
    private String major;

    /**
     * 学历
     */
    private String diploma;

    /**
     * 技能
     */
    private String skill;

    /**
     * 工作经验
     */
    private String experience;

    /**
     * 关注企业列表
     */
    private List<Company> followCompanies;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password, Integer sex, List<String> cities, LocalDate birthday, String major, String diploma, String skill, String experience, List<Company> followCompanies) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.cities = cities;
        this.birthday = birthday;
        this.major = major;
        this.diploma = diploma;
        this.skill = skill;
        this.experience = experience;
        this.followCompanies = followCompanies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public List<Company> getFollowCompanies() {
        return followCompanies;
    }

    public void setFollowCompanies(List<Company> followCompanies) {
        this.followCompanies = followCompanies;
    }
}