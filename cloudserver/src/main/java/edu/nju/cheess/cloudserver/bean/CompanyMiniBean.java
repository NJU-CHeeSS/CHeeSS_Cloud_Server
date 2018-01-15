package edu.nju.cheess.cloudserver.bean;

import java.util.List;

public class CompanyMiniBean {
    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业所属行业
     */
    private String industry;

    /**
     * 企业关键词
     */
    private List<String> keywords;

    public CompanyMiniBean(Long companyId, String name, String industry, List<String> keywords) {
        this.companyId = companyId;
        this.name = name;
        this.industry = industry;
        this.keywords = keywords;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
