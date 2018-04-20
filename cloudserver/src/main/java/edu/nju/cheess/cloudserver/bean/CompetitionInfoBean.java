package edu.nju.cheess.cloudserver.bean;

import java.util.Map;

public class CompetitionInfoBean {

    private Map<String, Long> competitorSkills;

    public CompetitionInfoBean() {
    }

    public CompetitionInfoBean(Map<String, Long> competitorSkills) {
        this.competitorSkills = competitorSkills;
    }

    public Map<String, Long> getCompetitorSkills() {
        return competitorSkills;
    }

    public void setCompetitorSkills(Map<String, Long> competitorSkills) {
        this.competitorSkills = competitorSkills;
    }
}
