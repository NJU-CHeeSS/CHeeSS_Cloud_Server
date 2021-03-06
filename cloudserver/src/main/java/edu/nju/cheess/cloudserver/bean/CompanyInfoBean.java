package edu.nju.cheess.cloudserver.bean;

import java.util.List;

public class CompanyInfoBean {

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 企业形式
     */
    private String type;

    /**
     * 企业所属行业
     */
    private String industry;

    /**
     * 企业规模
     */
    private String scale;

    /**
     * 企业介绍
     */
    private String introduction;

    /**
     * 企业关键词
     */
    private List<String> keywords;

    public CompanyInfoBean() {
    }

    public CompanyInfoBean(Long companyId, String name, String type, String industry, String scale, String introduction, List<String> keywords) {
        this.companyId = companyId;
        this.name = name;
        this.type = type;
        this.industry = industry;
        this.scale = scale;
        if (introduction.endsWith("展开")){
            introduction=introduction.replace("展开","");
        }
        this.introduction = introduction;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
