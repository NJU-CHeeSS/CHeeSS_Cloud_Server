package edu.nju.cheess.cloudserver.bean;

import java.util.List;

public class SkillInfoBean {

    private String job;

    private List<SkillKeywordsBean> keywords;

    public SkillInfoBean() {
    }

    public SkillInfoBean(String job, List<SkillKeywordsBean> keywords) {
        this.job = job;
        this.keywords = keywords;
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
