package com.skrg.sekoraga.service.dto;

import java.time.LocalDate;
import java.time.Instant;

public class AdUserActivityLogDTO {
    private Integer logId;
    private Long userId;
    private Integer scheduleId;
    private LocalDate activityDate;
    private String notes;
    private Boolean verifiedByTeacher;
    private Instant createdDate;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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
