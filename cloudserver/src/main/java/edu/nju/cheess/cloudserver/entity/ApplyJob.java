package edu.nju.cheess.cloudserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "apply_job")
public class ApplyJob {
    private Long id;
    private Long userId;
    private Integer jobId;

    public ApplyJob() {}

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "job_id", nullable = false)
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
