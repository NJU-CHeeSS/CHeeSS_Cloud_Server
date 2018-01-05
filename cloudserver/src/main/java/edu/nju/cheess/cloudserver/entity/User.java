package edu.nju.cheess.cloudserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    /**
     * 用户名
     */
    @Basic
    @Column(name="user_name")
    private String username;

    /**
     * 密码
     */
    @Basic
    @Column(name="password")
    private String password;

    /**
     * 性别，男为1，女为0
     */
    @Basic
    @Column(name="sex")
    private Integer sex;

    /**
     * 城市
     */
    @Basic
    @Column(name="city")
    private String city;

    /**
     * 年龄
     */
    @Basic
    @Column(name="age")
    private int age;

    /**
     * 专业
     */
    @Basic
    @Column(name="major")
    private String major;

    /**
     * 学历
     */
    @Basic
    @Column(name="diploma")
    private String diploma;

    /**
     * 技能
     */
    @Basic
    @Column(name="skill")
    private String skill;

    /**
     * 工作经验
     */
    @Basic
    @Column(name="experience")
    private double experience;

    /**
     * 邮箱
     */
    @Basic
    @Column(name = "email")
    private String email;

    /**
     * 电话号码
     */
    @Basic
    @Column(name = "telephone")
    private String telephone;

//    /**
//     * 关注企业列表
//     */
//    @ManyToMany
//    @JoinTable(
//            name="followCompany",
//            joinColumns=@JoinColumn(name="user_id", referencedColumnName="ID"),
//            inverseJoinColumns=@JoinColumn(name="company_id", referencedColumnName="ID"))
//    private List<Company> followCompanies;


    public User() {
    }

    public User(String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, Integer sex, String city, int age, String major, String diploma, String skill, double experience) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.city=city;
        this.age=age;
        this.major = major;
        this.diploma = diploma;
        this.skill = skill;
        this.experience = experience;
    }

    public User(String username, String password, Integer sex, String city, int age, String major, String diploma, String skill, double experience) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.city=city;
        this.age=age;
        this.major = major;
        this.diploma = diploma;
        this.skill = skill;
        this.experience = experience;
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