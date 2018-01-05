package edu.nju.cheess.cloudserver.bean;

import edu.nju.cheess.cloudserver.entity.Company;

import java.util.List;

public class UserInfoBean {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 性别，男为1，女为0
     */
    private Integer sex;

    /**
     * 城市
     */
    private  String city;

    /**
     * 年龄
     */
    private int age;

    /**
     * 专业
     */
    private String major;

    /**
     * 学历
     */
    private String diploma;

    /**
     * 技能(多个skill用,分隔)
     */
    private String skill;

    /**
     * 工作经验
     */
    private double experience;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 关注企业列表
     */
    private List<Company> followCompanies;

    public UserInfoBean(Long userId, String username, Integer sex, String city, int age, String major, String diploma, String skill, double experience, List<Company> followCompanies) {
        this.userId = userId;
        this.username = username;
        this.sex = sex;
        this.city=city;
        this.age=age;
        this.major = major;
        this.diploma = diploma;
        this.skill = skill;
        this.experience = experience;
        this.followCompanies = followCompanies;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public List<Company> getFollowCompanies() {
        return followCompanies;
    }

    public void setFollowCompanies(List<Company> followCompanies) {
        this.followCompanies = followCompanies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
