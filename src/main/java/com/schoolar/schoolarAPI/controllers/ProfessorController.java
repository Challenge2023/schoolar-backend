package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.professors.domain.dto.RegisterProfessorDTO;
import com.schoolar.schoolarAPI.professors.domain.dto.UpdateProfessorDTO;
import com.schoolar.schoolarAPI.professors.domain.schema.Professor;
import com.schoolar.schoolarAPI.professors.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Professor> getAllProfessors() { return service.getAllProfessors(); }

    @GetMapping("/id")
    public Optional<Professor> getProfessorById(@RequestHeader("id") String id) {
        return service.getProfessorById(id);
    }

    @GetMapping("/enrollment")
    public Optional<Professor> getProfessorByEnrollment(@RequestHeader("enrollment") String enrollment) {
        return service.getProfessorByEnrollment(enrollment);
    }

    @PostMapping("/register")
    public Professor createProfessor(@RequestBody RegisterProfessorDTO data) {
        return service.createProfessor(data);
    }

    @PutMapping("/update")
    public Professor updateProfessor(@RequestHeader("id") String id, @RequestBody UpdateProfessorDTO data) {
        return service.updateProfessor(id, data);
    }

    @DeleteMapping("/delete")
    public void deleteProfessor(@RequestHeader("id") String id) {
        service.deleteProfessor(id);
    }

}
