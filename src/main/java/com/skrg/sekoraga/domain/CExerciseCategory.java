package com.skrg.sekoraga.domain;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c_exercise_category")
public class CExerciseCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id")
    private CAttachment attachment;

    // Getters and Setters
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(CAttachment attachment) {
        this.attachment = attachment;
    }
}
