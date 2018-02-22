package edu.nju.cheess.cloudserver.bean;

public class ExperienceSalaryBean {

    private String experience;

    private Integer averageSalary;

    private Integer lowSalary;

    private Integer highSalary;

    public ExperienceSalaryBean() {
    }

    public ExperienceSalaryBean(String experience, Integer averageSalary, Integer lowSalary, Integer highSalary) {
        this.experience = experience;
        this.averageSalary = averageSalary;
        this.lowSalary = lowSalary;
        this.highSalary = highSalary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(Integer averageSalary) {
        this.averageSalary = averageSalary;
    }

    public Integer getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(Integer lowSalary) {
        this.lowSalary = lowSalary;
    }

    public Integer getHighSalary() {
        return highSalary;
    }

    public void setHighSalary(Integer highSalary) {
        this.highSalary = highSalary;
    }
}
