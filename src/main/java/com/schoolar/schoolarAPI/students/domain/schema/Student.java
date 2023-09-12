package com.schoolar.schoolarAPI.students.domain.schema;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "students")
public class Student{
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String schoolGrade;
    private String enrollment;
    private List<StudentSubjects> subjects;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public Student(){}

    public Student(String id, String name, String email, String password, String schoolGrade, String enrollment, List<StudentSubjects> subjects, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.schoolGrade = schoolGrade;
        this.enrollment = enrollment;
        this.subjects = subjects;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public Student setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
        return this;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public Student setEnrollment(String enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public List<StudentSubjects> getSubjects() {
        return subjects;
    }

    public Student setSubjects(List<StudentSubjects> subjects) {
        this.subjects = subjects;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Student setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Student setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Student setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }


}
