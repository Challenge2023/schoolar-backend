package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.students.domain.dto.RegisterStudentDTO;
import com.schoolar.schoolarAPI.students.domain.dto.UpdateStudentDTO;
import com.schoolar.schoolarAPI.students.domain.schema.Student;
import com.schoolar.schoolarAPI.students.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentsController {
    private final StudentsService studentsService;

    @Autowired
    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping("/david")
    public String davidChato() { return "Hello David"; }
    @GetMapping("")
    public List<Student> getAllStudents() {
        return studentsService.getAllStudents();
    }

    @GetMapping("/id")
    public Optional<Student> getStudentById(@RequestHeader("id") String id) {
        return studentsService.getStudentById(id);
    }

    @GetMapping("/enrollment")
    public Optional<Student> getStudentByEnrollment(@RequestHeader("enrollment") String enrollment) {
        return studentsService.getStudentByEnrollment(enrollment);
    }

    @PostMapping("/register")
    public Student createStudent(@RequestBody RegisterStudentDTO data) {
        return studentsService.createStudent(data);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestHeader("id") String id, @RequestBody UpdateStudentDTO data) {
        return studentsService.updateStudent(id, data);
    }

    @DeleteMapping("/delete")
    public void deleteStudent(@RequestHeader("id") String id) {
        studentsService.deleteStudent(id);
    }
}
