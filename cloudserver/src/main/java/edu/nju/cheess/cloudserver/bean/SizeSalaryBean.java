package edu.nju.cheess.cloudserver.bean;

public class SizeSalaryBean {

    private String size;

    private Integer averageSalary;

    private Integer lowSalary;

    private Integer highSalary;

    public SizeSalaryBean() {
    }

    public SizeSalaryBean(String size, Integer averageSalary, Integer lowSalary, Integer highSalary) {
        this.size = size;
        this.averageSalary = averageSalary;
        this.lowSalary = lowSalary;
        this.highSalary = highSalary;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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
