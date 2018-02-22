package edu.nju.cheess.cloudserver.bean;

public class TreatmentDistributionBean {

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 处于此薪资范围的工作数量
     */
    private int number;

    public TreatmentDistributionBean() {
    }

    public TreatmentDistributionBean(String salary, int number) {
        this.salary = salary;
        this.number = number;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
