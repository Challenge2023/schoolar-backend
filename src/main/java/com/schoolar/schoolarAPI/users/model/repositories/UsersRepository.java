package com.schoolar.schoolarAPI.users.model.repositories;

import com.schoolar.schoolarAPI.users.dtos.FindByTypeDTO;
import com.schoolar.schoolarAPI.users.dtos.RegisterUserDTO;
import com.schoolar.schoolarAPI.users.dtos.UpdateUserDTO;
import com.schoolar.schoolarAPI.users.interfaces.IUsersRepository;
import com.schoolar.schoolarAPI.users.model.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UsersRepository implements IUsersRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public User register(RegisterUserDTO data) {
        User user = new User();
        user.setName(data.getName());
        user.setPassword(data.getPassword());
        user.setEmail(data.getEmail());
        user.setType(data.getType());
        user.setEnrollment(data.getEnrollment());
        user.setBirthDate(data.getBirthDate());
        if (data.getSchoolGrade() != null) user.setSchoolGrade(data.getSchoolGrade());
        if (data.getStudentSubjects() != null) user.setStudentSubjects(data.getStudentSubjects());
        if (data.getSubjects() != null) user.setSubjects(data.getSubjects());
        if (data.getClassrooms() != null) user.setClassrooms(data.getClassrooms());

        return mongoTemplate.save(user);
    }

    @Override
    public List<User> findAll() {
        Query query = new Query(Criteria.where("deletedAt").is(null));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public List<User> findByType(FindByTypeDTO type) {
        Query query = new Query(
                Criteria.where("type").is(type.getType())
                        .and("deletedAt").is(null)
        );
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public Optional<User> findByEnrollment(String enrollment) {
        Query query = new Query(
                Criteria.where("enrollment").is(enrollment)
                        .and("deletedAt").is(null)
        );
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Query query = new Query(
                Criteria.where("email").is(email)
                        .and("deletedAt").is(null)
        );
        return Optional.ofNullable(mongoTemplate.findOne(query, User.class));
    }

    @Override
    public User update(String enrollment, UpdateUserDTO data) {
        Query query = new Query(Criteria.where("enrollment").is(enrollment));
        Update update = new Update();

        if (data.getName() != null) update.set("name", data.getName());

        if (data.getPassword() != null) update.set("password", data.getPassword());

        if (data.getSchoolGrade() != null) update.set("schoolGrade", data.getSchoolGrade());

        if (data.getStudentSubjects() != null) update.set("studentSubjects", data.getStudentSubjects());

        if (data.getSubjects() != null) update.set("subjects", data.getSubjects());

        if (data.getClassrooms() != null) update.set("classrooms", data.getClassrooms());

        return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), User.class);
    }

    @Override
    public void delete(String enrollment) {
        Query query = new Query(Criteria.where("enrollment").is(enrollment));
        Update update = new Update().set("deletedAt", LocalDateTime.now());
        mongoTemplate.updateFirst(query, update, User.class);
    }
}
