package edu.nju.cheess.cloudserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "apply_job")
public class ApplyJob {
    private Long id;
    private Long userId;
    private Long jobId;

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
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
