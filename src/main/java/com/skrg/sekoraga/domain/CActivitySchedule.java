package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c_activity_schedule")
public class CActivitySchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Column(name = "day_of_week", length = 100, nullable = false)
    private String dayOfWeek;

    @Column(name = "activity_name", length = 100, nullable = false)
    private String activityName;

    @Column(name = "point_value")
    private Integer pointValue = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AdUser user;

    // Default constructor required by JPA
    public CActivitySchedule() {
    }

    // Getters and Setters
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getPointValue() {
        return pointValue;
    }

    public void setPointValue(Integer pointValue) {
        this.pointValue = pointValue;
    }

    public AdUser getUser() {
        return user;
    }

    public void setUser(AdUser user) {
        this.user = user;
    }

    public CActivitySchedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }
}
