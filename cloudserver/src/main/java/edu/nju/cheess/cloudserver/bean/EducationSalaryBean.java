package edu.nju.cheess.cloudserver.bean;

public class EducationSalaryBean {

    private String education;

    private Integer averageSalary;

    private Integer lowSalary;

    private Integer highSalary;

    public EducationSalaryBean() {
    }

    public EducationSalaryBean(String education, Integer averageSalary, Integer lowSalary, Integer highSalary) {
        this.education = education;
        this.averageSalary = averageSalary;
        this.lowSalary = lowSalary;
        this.highSalary = highSalary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
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
