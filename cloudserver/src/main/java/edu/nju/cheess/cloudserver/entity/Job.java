package edu.nju.cheess.cloudserver.entity;

import java.time.LocalDateTime;

public class Job {

    private Long id;
    private String title;
    private String location;
    private String company;
    private double lowMoney;
    private double highMoney;
    private int lowExperience;
    private int highExperience;
    private LocalDateTime date;
    private String information;
    private String education;
    private String totalPeople;
    private String jobType;
    private String companyLink;

    public Job() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getLowMoney() {
        return lowMoney;
    }

    public void setLowMoney(double lowMoney) {
        this.lowMoney = lowMoney;
    }

    public double getHighMoney() {
        return highMoney;
    }

    public void setHighMoney(double highMoney) {
        this.highMoney = highMoney;
    }

    public int getLowExperience() {
        return lowExperience;
    }

    public void setLowExperience(int lowExperience) {
        this.lowExperience = lowExperience;
    }

    public int getHighExperience() {
        return highExperience;
    }

    public void setHighExperience(int highExperience) {
        this.highExperience = highExperience;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(String totalPeople) {
        this.totalPeople = totalPeople;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getCompanyLink() {
        return companyLink;
    }

    public void setCompanyLink(String companyLink) {
        this.companyLink = companyLink;
    }
}
