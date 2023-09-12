package com.schoolar.schoolarAPI.professors.exercises.services;

import com.schoolar.schoolarAPI.professors.exercises.domain.dto.RegisterExerciseDTO;
import com.schoolar.schoolarAPI.professors.exercises.domain.dto.UpdateExerciseDTO;
import com.schoolar.schoolarAPI.professors.exercises.domain.repositories.ExerciseRepository;
import com.schoolar.schoolarAPI.professors.exercises.domain.schema.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    private final ExerciseRepository repository;

    @Autowired
    public ExerciseService(ExerciseRepository repository) { this.repository = repository; }

    public List<Exercise> getAllExercises() { return this.repository.findAll(); }

    public List<Exercise> getAllExercisesByProfessor(String professor) { return repository.findAllByProfessor(professor); }

    public Optional<Exercise> getExerciseById(String id) { return repository.findById(id); }

    public Exercise createExercise(RegisterExerciseDTO data) {
        Exercise exercise = new Exercise();
        exercise.setProfessor(data.professor());
        exercise.setDescription(data.description());

        return repository.save(exercise);
    }

    public Exercise updateExercise(String id, UpdateExerciseDTO data) {
        Optional<Exercise> optionalExercise = repository.findById(id);
        if(optionalExercise.isPresent()) {
            Exercise exercise = optionalExercise.get();
            if(data.description() != null) exercise.setDescription(data.description());

            return repository.save(exercise);
        }
        return null;
    }

    public void deleteExercise(String id) {
        repository.deleteById(id);
    }
}
