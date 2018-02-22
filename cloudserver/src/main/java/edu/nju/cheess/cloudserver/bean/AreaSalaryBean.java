package edu.nju.cheess.cloudserver.bean;

public class AreaSalaryBean {

    private String area;

    private Integer averageSalary;

    private Integer lowSalary;

    private Integer highSalary;

    public AreaSalaryBean() {
    }

    public AreaSalaryBean(String area, Integer averageSalary, Integer lowSalary, Integer highSalary) {
        this.area = area;
        this.averageSalary = averageSalary;
        this.lowSalary = lowSalary;
        this.highSalary = highSalary;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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
