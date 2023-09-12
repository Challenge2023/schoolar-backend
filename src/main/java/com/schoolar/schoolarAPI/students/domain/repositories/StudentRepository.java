package com.schoolar.schoolarAPI.students.domain.repositories;

import com.schoolar.schoolarAPI.students.domain.schema.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findStudentByEnrollment(String enrollment);
}
