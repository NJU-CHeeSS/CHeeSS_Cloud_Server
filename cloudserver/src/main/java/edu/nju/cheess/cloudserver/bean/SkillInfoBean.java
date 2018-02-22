package edu.nju.cheess.cloudserver.bean;

import java.util.List;

public class SkillInfoBean {

    private String industry;

    private String job;

    private List<SkillKeywordsBean> keywords;

    public SkillInfoBean() {
    }

    public SkillInfoBean(String industry, String job, List<SkillKeywordsBean> keywords) {
        this.industry = industry;
        this.job = job;
        this.keywords = keywords;
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

    public List<SkillKeywordsBean> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<SkillKeywordsBean> keywords) {
        this.keywords = keywords;
    }
}
