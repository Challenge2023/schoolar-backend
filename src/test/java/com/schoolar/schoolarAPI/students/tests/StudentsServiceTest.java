package com.schoolar.schoolarAPI.students.tests;

import com.schoolar.schoolarAPI.students.domain.dto.RegisterStudentDTO;
import com.schoolar.schoolarAPI.students.domain.dto.UpdateStudentDTO;
import com.schoolar.schoolarAPI.students.domain.repositories.StudentRepository;
import com.schoolar.schoolarAPI.students.domain.schema.Student;
import com.schoolar.schoolarAPI.students.domain.schema.StudentSubjects;
import com.schoolar.schoolarAPI.students.services.StudentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class StudentsServiceTest {
    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentsService studentsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should be able to retrieve all students")
    void getAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student());
        given(studentRepository.findAll()).willReturn(students);

        List<Student> expectedStudents = studentsService.getAllStudents();

        assertEquals(expectedStudents, students);
        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("Should be able to retrieve an student with the passed Id")
    void getStudentById() {
        Student student = new Student();
        student.setId("64fdd97b2e09ea0629753091");
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
        Optional<Student> expectedStudent = studentsService.getStudentById(student.getId());
        verify(studentRepository).findById(student.getId());
    }

    @Test
    @DisplayName("Should be able to update student data")
    void updateStudent() {
        Student student = new Student();
        student.setId("64fdd97b2e09ea062975309");
        student.setName("Lucas Amadeu");

        UpdateStudentDTO newStudent = new UpdateStudentDTO("Vitor Mantovani", null, null, null, null);

        given(studentRepository.findById(student.getId())).willReturn(Optional.of(student));

        studentsService.updateStudent(student.getId(), newStudent);

        verify(studentRepository).findById(student.getId());
    }
    @Test
    @DisplayName("Should be able to create a student")
    void createStudent() {
        StudentSubjects studentSubjects = new StudentSubjects("64fdd97b2e09ea0629753091", "MATH101", 85.0, 2);
        ArrayList<StudentSubjects> subjects = new ArrayList<>();
        subjects.add(studentSubjects);
        RegisterStudentDTO studentDTO = new RegisterStudentDTO("Lucas Amadeu", "lucas@escola.com", "123456", "7th grade", "s0059935", subjects);

        Student student = new Student();
        student.setName(studentDTO.name());
        student.setEmail(studentDTO.email());
        student.setPassword(studentDTO.password());
        student.setSchoolGrade(studentDTO.schoolGrade());
        student.setEnrollment(studentDTO.enrollment());
        student.setSubjects(studentDTO.subjects());

        when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);

        Student createdStudent = studentsService.createStudent(studentDTO);

        assertEquals(createdStudent.getEnrollment(), studentDTO.enrollment());
        verify(studentRepository, times(1)).save(any());
    }
}
