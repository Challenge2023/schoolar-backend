package com.schoolar.schoolarAPI.professors.exams.service;

import com.schoolar.schoolarAPI.professors.exams.domain.dto.RegisterExamDTO;
import com.schoolar.schoolarAPI.professors.exams.domain.dto.UpdateExamDTO;
import com.schoolar.schoolarAPI.professors.exams.domain.repository.ExamRepository;
import com.schoolar.schoolarAPI.professors.exams.domain.schema.Exam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamsService {
    private final ExamRepository repository;

    @Autowired
    public ExamsService(ExamRepository repository) {
        this.repository = repository;
    }

    public List<Exam> getAllExams() { return repository.findAll(); }

    public Optional<Exam> getExamById(String id) { return repository.findById(id); }

    public List<Exam> getAllExamsByStudent(String student) { return repository.findAllByStudent(student); }

    public List<Exam> getAllExamsByProfessor(String professor) { return repository.findAllByProfessor(professor); }

    public Exam createExam(RegisterExamDTO data) {
        Exam exam = new Exam();
        exam.setStudent(data.student());
        exam.setProfessor(data.professor());
        exam.setQuestions(data.questions());
        exam.setResult(data.result());

        return repository.save(exam);
    }

    public Exam updateExam(String id, UpdateExamDTO data) {
        Optional<Exam> optionalExam = repository.findById(id);
        if(optionalExam.isPresent()) {
            Exam exam = optionalExam.get();
            if(data.questions() != null) exam.setQuestions(data.questions());
            if(data.result() != null) exam.setResult(data.result());

            return repository.save(exam);
        }
        return null;
    }

    public void deleteExam(String id) {
        repository.deleteById(id);
    }
}
