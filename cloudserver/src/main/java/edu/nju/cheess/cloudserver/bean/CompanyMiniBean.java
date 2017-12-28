package edu.nju.cheess.cloudserver.bean;

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

    public CompanyMiniBean(Long id, String name, String industry) {
        this.companyId = id;
        this.name = name;
        this.industry = industry;
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
}
