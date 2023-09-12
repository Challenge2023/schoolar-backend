package com.schoolar.schoolarAPI.professors.services;

import com.schoolar.schoolarAPI.professors.domain.dto.RegisterProfessorDTO;
import com.schoolar.schoolarAPI.professors.domain.dto.UpdateProfessorDTO;
import com.schoolar.schoolarAPI.professors.domain.repositories.ProfessorRepository;
import com.schoolar.schoolarAPI.professors.domain.schema.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;

    @Autowired
    public ProfessorService(ProfessorRepository repository) { this.repository = repository; }

    public List<Professor> getAllProfessors() { return  repository.findAll(); }

    public Optional<Professor> getProfessorById(String id) { return repository.findById(id); }

    public Optional<Professor> getProfessorByEnrollment(String enrollment) {
        return repository.findProfessorByEnrollment(enrollment);
    }

    public Professor createProfessor(RegisterProfessorDTO data) {
        Professor professor = new Professor();
        professor.setName(data.name());
        professor.setEmail(data.email());
        professor.setPassword(data.password());
        professor.setEnrollment(data.enrollment());
        professor.setSubjects(data.subjects());
        professor.setClassrooms(data.classrooms());

        return repository.save(professor);
    }

    public Professor updateProfessor(String id, UpdateProfessorDTO data) {
        Optional<Professor> optionalProfessor = repository.findById(id);
        if (optionalProfessor.isPresent()) {
            Professor professor = optionalProfessor.get();
            if(data.name() != null) professor.setName(data.name());
            if(data.email() != null) professor.setEmail(data.email());
            if(data.password() != null) professor.setPassword(data.password());
            if(data.subjects() != null) professor.setSubjects(data.subjects());
            if(data.classrooms() != null) professor.setClassrooms(data.classrooms());

            return repository.save(professor);
        }
        return null;
    }

    public void deleteProfessor(String id) {
        repository.deleteById(id);
    }
}
