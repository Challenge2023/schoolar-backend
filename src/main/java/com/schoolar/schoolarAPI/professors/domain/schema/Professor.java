package com.schoolar.schoolarAPI.professors.domain.schema;

import com.schoolar.schoolarAPI.professors.types.Classrooms;
import com.schoolar.schoolarAPI.professors.types.ProfessorSubjects;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "professors")
public class Professor {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String enrollment;
    private List<ProfessorSubjects> subjects;
    private List<Classrooms> classrooms;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt = null;

    public Professor() {}
    public Professor(String id, String name, String email, String password, String enrollment, List<ProfessorSubjects> subjects, List<Classrooms> classrooms, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.enrollment = enrollment;
        this.subjects = subjects;
        this.classrooms = classrooms;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public String getId() {
        return id;
    }

    public Professor setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Professor setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Professor setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Professor setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public Professor setEnrollment(String enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public List<ProfessorSubjects> getSubjects() {
        return subjects;
    }

    public Professor setSubjects(List<ProfessorSubjects> subjects) {
        this.subjects = subjects;
        return this;
    }

    public List<Classrooms> getClassrooms() {
        return classrooms;
    }

    public Professor setClassrooms(List<Classrooms> classrooms) {
        this.classrooms = classrooms;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Professor setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Professor setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public Professor setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
