package com.schoolar.schoolarAPI.students.services;

import com.schoolar.schoolarAPI.students.domain.dto.RegisterStudentSubjectDTO;
import com.schoolar.schoolarAPI.students.domain.repositories.StudentSubjectRepository;
import com.schoolar.schoolarAPI.students.domain.schema.StudentSubjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsSubjectsService {
    @Autowired
    private final StudentSubjectRepository repository;

    public StudentsSubjectsService(StudentSubjectRepository studentSubjectRepository) {
        this.repository = studentSubjectRepository;
    }

    public List<StudentSubjects> getAllSubjects() { return repository.findAll(); }

    public Optional<StudentSubjects> getSubjectById(String id) { return  repository.findById(id); }

    public StudentSubjects createSubject(RegisterStudentSubjectDTO data) {
            StudentSubjects subject = new StudentSubjects();
            subject.setCode(data.code());
            subject.setAverageGrade(data.avarageGrade());
            subject.setAbsences(data.absences());

            return repository.save(subject);
    }

    public void deleteSubject(String id) {
        repository.deleteById(id);
    }
}
