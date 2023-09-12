package com.schoolar.schoolarAPI.students.services;

import com.schoolar.schoolarAPI.students.domain.dto.RegisterStudentDTO;
import com.schoolar.schoolarAPI.students.domain.dto.UpdateStudentDTO;
import com.schoolar.schoolarAPI.students.domain.repositories.StudentRepository;
import com.schoolar.schoolarAPI.students.domain.schema.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(String id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByEnrollment(String enrollment) {
        return studentRepository.findStudentByEnrollment(enrollment);
    }

    public Student createStudent(RegisterStudentDTO data) {
        Student student = new Student();
        student.setName(data.name());
        student.setEmail(data.email());
        student.setPassword(data.password());
        student.setSchoolGrade(data.schoolGrade());
        student.setEnrollment(data.enrollment());
        student.setSubjects(data.subjects());

        return studentRepository.save(student);
    }

    public Student updateStudent(String id, UpdateStudentDTO data) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if(data.name() != null) student.setName(data.name());
            if(data.email() != null) student.setEmail(data.email());
            if(data.password() != null) student.setPassword(data.password());
            if(data.schoolGrade() != null) student.setSchoolGrade(data.schoolGrade());
            if(data.subjects() != null) student.setSubjects(data.subjects());

            return studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
