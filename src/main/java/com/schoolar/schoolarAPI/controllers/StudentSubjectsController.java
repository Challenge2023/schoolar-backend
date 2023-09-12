package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.students.domain.dto.RegisterStudentSubjectDTO;
import com.schoolar.schoolarAPI.students.domain.schema.StudentSubjects;
import com.schoolar.schoolarAPI.students.services.StudentsSubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class StudentSubjectsController {
    private final StudentsSubjectsService subjectsService;

    @Autowired
    public StudentSubjectsController(StudentsSubjectsService service) {
        this.subjectsService = service;
    }

    @GetMapping("")
    public List<StudentSubjects> getAllSubjects() { return subjectsService.getAllSubjects(); }

    @GetMapping("/id")
    public Optional<StudentSubjects> getSubjectById(@RequestHeader("id") String id) {
        return subjectsService.getSubjectById(id);
    }

    @PostMapping("/register")
    public StudentSubjects createSubject(@RequestBody RegisterStudentSubjectDTO data) {
        return subjectsService.createSubject(data);
    }

    @DeleteMapping("/delete")
    public void deleteSubject(@RequestHeader("id") String id) {
        subjectsService.deleteSubject(id);
    }

}
