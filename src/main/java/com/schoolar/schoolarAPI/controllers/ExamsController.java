package com.schoolar.schoolarAPI.controllers;

import com.schoolar.schoolarAPI.professors.exams.domain.dto.GenerateContentDTO;
import com.schoolar.schoolarAPI.professors.exams.domain.dto.RegisterExamDTO;
import com.schoolar.schoolarAPI.professors.exams.domain.dto.UpdateExamDTO;
import com.schoolar.schoolarAPI.professors.exams.domain.schema.Exam;
import com.schoolar.schoolarAPI.professors.exams.service.ExamsService;
import com.schoolar.schoolarAPI.shared.api.services.ContentCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exam")
public class ExamsController {
    private final ExamsService service;
    private final ContentCreatorService contentService;

    @Autowired
    public ExamsController(ExamsService service, ContentCreatorService contentService) { this.service = service; this.contentService = contentService; }

    @GetMapping("")
    public List<Exam> getAllExams() { return service.getAllExams(); }

    @GetMapping("/student")
    public List<Exam> getAllExamsByStudent(@RequestHeader("student") String student) { return service.getAllExamsByStudent(student); }

    @GetMapping("/professor")
    public List<Exam> getAllExamsByProfessor(@RequestHeader("professor") String professor) {
        return service.getAllExamsByProfessor(professor);
    }

    @GetMapping("/id")
    public Optional<Exam> getExamById(@RequestHeader("id") String id) {
        return service.getExamById(id);
    }

    @PostMapping("/register")
    public Exam createExam(@RequestBody RegisterExamDTO data) {
        return service.createExam(data);
    }

    @PostMapping("/content/generate")
    public Mono<String> generateContent(@RequestBody GenerateContentDTO data) {
        return contentService.generateContent(data.questionsNumber(), data.theme(), data.subject());
    }

    @PutMapping("/update")
    public Exam updateExam(@RequestHeader("id") String id, @RequestBody UpdateExamDTO data) {
        return service.updateExam(id, data);
    }

    @DeleteMapping("/delete")
    public void deleteExam(@RequestHeader("id") String id) {
        service.deleteExam(id);
    }
}
