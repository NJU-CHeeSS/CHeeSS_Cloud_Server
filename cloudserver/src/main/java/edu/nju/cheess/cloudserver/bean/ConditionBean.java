package edu.nju.cheess.cloudserver.bean;

public class ConditionBean {
    /**
     * 工作地点
     */
    private String location;

    /**
     * 最早发布日期
     */
    private String earlyReleaseDate;

    /**
     * 学历要求
     */
    private String diploma;

    /**
     * 工作性质
     */
    private String property;

    public ConditionBean() {
    }

    public ConditionBean(String location, String earlyReleaseDate, String diploma, String property) {
        this.location = location;
        this.earlyReleaseDate = earlyReleaseDate;
        this.diploma = diploma;
        this.property = property;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEarlyReleaseDate() {
        return earlyReleaseDate;
    }

    public void setEarlyReleaseDate(String earlyReleaseDate) {
        this.earlyReleaseDate = earlyReleaseDate;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
