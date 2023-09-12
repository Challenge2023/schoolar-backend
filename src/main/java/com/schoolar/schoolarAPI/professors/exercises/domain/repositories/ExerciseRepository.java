package com.schoolar.schoolarAPI.professors.exercises.domain.repositories;

import com.schoolar.schoolarAPI.professors.exercises.domain.schema.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> findAllByProfessor(String professor);
}
