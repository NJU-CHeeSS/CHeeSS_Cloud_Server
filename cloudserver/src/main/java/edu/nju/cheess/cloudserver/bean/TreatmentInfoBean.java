package edu.nju.cheess.cloudserver.bean;

import java.util.List;

public class TreatmentInfoBean {

    private String industry;

    private String job;

    private String city;

    private Integer cityAverage;

    private Integer cityLow;

    private Integer cityHigh;

    private Integer countryAverage;

    private Integer countryLow;

    private Integer countryHigh;

    private List<TreatmentDistributionBean> distribution;

    private List<AreaSalaryBean> areaSalary;

    private List<SizeSalaryBean> sizeSalary;

    private List<EducationSalaryBean> educationSalary;

    private List<ExperienceSalaryBean> experienceSalary;

    private Integer maleSalary;

    private Integer femaleSalary;

    public TreatmentInfoBean() {
    }

    public TreatmentInfoBean(String industry, String job, String city, Integer cityAverage, Integer cityLow, Integer cityHigh, Integer countryAverage, Integer countryLow, Integer countryHigh, List<TreatmentDistributionBean> distribution, List<AreaSalaryBean> areaSalary, List<SizeSalaryBean> sizeSalary, List<EducationSalaryBean> educationSalary, List<ExperienceSalaryBean> experienceSalary, Integer maleSalary, Integer femaleSalary) {
        this.industry = industry;
        this.job = job;
        this.city = city;
        this.cityAverage = cityAverage;
        this.cityLow = cityLow;
        this.cityHigh = cityHigh;
        this.countryAverage = countryAverage;
        this.countryLow = countryLow;
        this.countryHigh = countryHigh;
        this.distribution = distribution;
        this.areaSalary = areaSalary;
        this.sizeSalary = sizeSalary;
        this.educationSalary = educationSalary;
        this.experienceSalary = experienceSalary;
        this.maleSalary = maleSalary;
        this.femaleSalary = femaleSalary;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityAverage() {
        return cityAverage;
    }

    public void setCityAverage(Integer cityAverage) {
        this.cityAverage = cityAverage;
    }

    public Integer getCityLow() {
        return cityLow;
    }

    public void setCityLow(Integer cityLow) {
        this.cityLow = cityLow;
    }

    public Integer getCityHigh() {
        return cityHigh;
    }

    public void setCityHigh(Integer cityHigh) {
        this.cityHigh = cityHigh;
    }

    public Integer getCountryAverage() {
        return countryAverage;
    }

    public void setCountryAverage(Integer countryAverage) {
        this.countryAverage = countryAverage;
    }

    public Integer getCountryLow() {
        return countryLow;
    }

    public void setCountryLow(Integer countryLow) {
        this.countryLow = countryLow;
    }

    public Integer getCountryHigh() {
        return countryHigh;
    }

    public void setCountryHigh(Integer countryHigh) {
        this.countryHigh = countryHigh;
    }

    public List<TreatmentDistributionBean> getDistribution() {
        return distribution;
    }

    public void setDistribution(List<TreatmentDistributionBean> distribution) {
        this.distribution = distribution;
    }

    public List<AreaSalaryBean> getAreaSalary() {
        return areaSalary;
    }

    public void setAreaSalary(List<AreaSalaryBean> areaSalary) {
        this.areaSalary = areaSalary;
    }

    public List<SizeSalaryBean> getSizeSalary() {
        return sizeSalary;
    }

    public void setSizeSalary(List<SizeSalaryBean> sizeSalary) {
        this.sizeSalary = sizeSalary;
    }

    public List<EducationSalaryBean> getEducationSalary() {
        return educationSalary;
    }

    public void setEducationSalary(List<EducationSalaryBean> educationSalary) {
        this.educationSalary = educationSalary;
    }

    public List<ExperienceSalaryBean> getExperienceSalary() {
        return experienceSalary;
    }

    public void setExperienceSalary(List<ExperienceSalaryBean> experienceSalary) {
        this.experienceSalary = experienceSalary;
    }

    public Integer getMaleSalary() {
        return maleSalary;
    }

    public void setMaleSalary(Integer maleSalary) {
        this.maleSalary = maleSalary;
    }

    public Integer getFemaleSalary() {
        return femaleSalary;
    }

    public void setFemaleSalary(Integer femaleSalary) {
        this.femaleSalary = femaleSalary;
    }
}
