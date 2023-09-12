package com.schoolar.schoolarAPI.professors.exams.domain.repository;

import com.schoolar.schoolarAPI.professors.exams.domain.schema.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExamRepository extends MongoRepository<Exam, String> {
    List<Exam> findAllByStudent(String student);

    List<Exam> findAllByProfessor(String professor);
}
