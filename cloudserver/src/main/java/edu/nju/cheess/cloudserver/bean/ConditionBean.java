package edu.nju.cheess.cloudserver.bean;

import java.time.LocalDateTime;
import java.util.List;

public class ConditionBean {
    /**
     * 工作地点
     */
    private List<String> location;

    /**
     * 最早发布日期
     */
    private LocalDateTime earlyReleaseDate;

    /**
     * 最晚发布日期
     */
    private LocalDateTime lateReleaseDate;

    /**
     * 学历要求
     */
    private List<String> diploma;

    public ConditionBean(List<String> location, LocalDateTime earlyReleaseDate, LocalDateTime lateReleaseDate, List<String> diploma) {
        this.location = location;
        this.earlyReleaseDate = earlyReleaseDate;
        this.lateReleaseDate = lateReleaseDate;
        this.diploma = diploma;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public LocalDateTime getEarlyReleaseDate() {
        return earlyReleaseDate;
    }

    public void setEarlyReleaseDate(LocalDateTime earlyReleaseDate) {
        this.earlyReleaseDate = earlyReleaseDate;
    }

    public LocalDateTime getLateReleaseDate() {
        return lateReleaseDate;
    }

    public void setLateReleaseDate(LocalDateTime lateReleaseDate) {
        this.lateReleaseDate = lateReleaseDate;
    }

    public List<String> getDiploma() {
        return diploma;
    }

    public void setDiploma(List<String> diploma) {
        this.diploma = diploma;
    }

}
