package com.schoolar.schoolarAPI.students.domain.repositories;

import com.schoolar.schoolarAPI.students.domain.schema.StudentSubjects;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentSubjectRepository extends MongoRepository<StudentSubjects, String> {
}
