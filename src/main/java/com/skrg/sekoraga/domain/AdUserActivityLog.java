package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Instant;

@Entity
@Table(name = "ad_user_activity_log")
public class AdUserActivityLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AdUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private CActivitySchedule schedule;

    @Column(name = "activity_date", nullable = false)
    private LocalDate activityDate = LocalDate.now();

    @Column(name = "notes")
    private String notes;

    @Column(name = "verified_by_teacher")
    private Boolean verifiedByTeacher = false;

    @Column(name = "created_date")
    private Instant createdDate = Instant.now();

    // Getters and Setters
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public AdUser getUser() {
        return user;
    }

    public void setUser(AdUser user) {
        this.user = user;
    }

    public CActivitySchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(CActivitySchedule schedule) {
        this.schedule = schedule;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getVerifiedByTeacher() {
        return verifiedByTeacher;
    }

    public void setVerifiedByTeacher(Boolean verifiedByTeacher) {
        this.verifiedByTeacher = verifiedByTeacher;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}
