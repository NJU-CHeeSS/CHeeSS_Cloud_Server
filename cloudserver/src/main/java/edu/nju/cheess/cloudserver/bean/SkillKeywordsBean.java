package edu.nju.cheess.cloudserver.bean;

public class SkillKeywordsBean {

    /**
     * 职位技能中出现的关键词
     */
    private String keywords;

    /**
     * 关键词在所有职位技能中出现的次数或量化指标
     */
    private Integer figure;

    public SkillKeywordsBean() {
    }

    public SkillKeywordsBean(String keywords, Integer figure) {
        this.keywords = keywords;
        this.figure = figure;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Integer getFigure() {
        return figure;
    }

    public void setFigure(Integer figure) {
        this.figure = figure;
    }
}
