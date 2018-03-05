package edu.nju.cheess.cloudserver.bean;

/**
 * 企业分析数据
 */
public class CompanyAnalyseBean {
    private double maxSalary;
    private double minSalary;
    private double averageSalary;

    public CompanyAnalyseBean(double maxSalary, double minSalary, double averageSalary) {
        this.maxSalary = maxSalary;
        this.minSalary = minSalary;
        this.averageSalary = averageSalary;
    }

    public CompanyAnalyseBean() {
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
