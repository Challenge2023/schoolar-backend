package com.schoolar.schoolarAPI.professors.domain.repositories;

import com.schoolar.schoolarAPI.professors.domain.schema.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
    Optional<Professor> findProfessorByEnrollment(String enrollment);
}
