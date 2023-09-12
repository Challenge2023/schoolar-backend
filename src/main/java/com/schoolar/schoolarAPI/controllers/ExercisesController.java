package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.professors.exercises.domain.dto.RegisterExerciseDTO;
import com.schoolar.schoolarAPI.professors.exercises.domain.dto.UpdateExerciseDTO;
import com.schoolar.schoolarAPI.professors.exercises.domain.schema.Exercise;
import com.schoolar.schoolarAPI.professors.exercises.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExercisesController {
    private final ExerciseService service;

    @Autowired
    public ExercisesController(ExerciseService service) { this.service = service; }

    @GetMapping("")
    public List<Exercise> getAllExercises() { return service.getAllExercises(); }

    @GetMapping("/id")
    public Optional<Exercise> getExerciseById(@RequestHeader("id") String id) {
        return service.getExerciseById(id);
    }

    @GetMapping("/professor")
    public List<Exercise> getAllExercisesByProfessor(@RequestHeader("professor") String professor) {
        return service.getAllExercisesByProfessor(professor);
    }

    @PostMapping("/register")
    public Exercise createExercise(@RequestBody RegisterExerciseDTO data) {
        return service.createExercise(data);
    }

    @PutMapping("/update")
    public Exercise updateExercise(@RequestHeader("id") String id, @RequestBody UpdateExerciseDTO data) {
        return service.updateExercise(id, data);
    }

    @DeleteMapping("/delete")
    public void deleteExercise(@RequestHeader("id") String id) {
        service.deleteExercise(id);
    }
}
