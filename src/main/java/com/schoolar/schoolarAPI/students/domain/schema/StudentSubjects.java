package com.schoolar.schoolarAPI.students.domain.schema;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "student_subjects")
public class StudentSubjects {
    @Id
    private String id;
    private String code;
    private Double averageGrade;
    private Integer absences;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt = null;

    public StudentSubjects() {}

    public StudentSubjects(String id, String code, Double averageGrade, Integer absences) {
        this.id = id;
        this.code = code;
        this.averageGrade = averageGrade;
        this.absences = absences;
    }

    public String getId() {
        return id;
    }

    public StudentSubjects setId(String id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public StudentSubjects setCode(String code) {
        this.code = code;
        return this;
    }

    public Double getAverageGrade() {
        return averageGrade;
    }

    public StudentSubjects setAverageGrade(Double averageGrade) {
        this.averageGrade = averageGrade;
        return this;
    }

    public Integer getAbsences() {
        return absences;
    }

    public StudentSubjects setAbsences(Integer absences) {
        this.absences = absences;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public StudentSubjects setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public StudentSubjects setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public StudentSubjects setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
