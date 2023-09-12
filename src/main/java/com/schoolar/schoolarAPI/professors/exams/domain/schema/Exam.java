package com.schoolar.schoolarAPI.professors.exams.domain.schema;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "exams")
public class Exam {
    @Id
    private String id;
    private String student;
    private String professor;
    private String questions;
    private Integer result;

    public Exam() {}

    public Exam(String id, String student, String professor, String questions, Integer result) {
        this.id = id;
        this.student = student;
        this.professor = professor;
        this.questions = questions;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public Exam setId(String id) {
        this.id = id;
        return this;
    }

    public String getStudent() {
        return student;
    }

    public Exam setStudent(String student) {
        this.student = student;
        return this;
    }

    public String getProfessor() {
        return professor;
    }

    public Exam setProfessor(String professor) {
        this.professor = professor;
        return this;
    }

    public String getQuestions() {
        return questions;
    }

    public Exam setQuestions(String questions) {
        this.questions = questions;
        return this;
    }

    public Integer getResult() {
        return result;
    }

    public Exam setResult(Integer result) {
        this.result = result;
        return this;
    }
}
