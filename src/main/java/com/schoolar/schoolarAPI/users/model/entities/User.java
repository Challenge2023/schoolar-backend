package com.schoolar.schoolarAPI.users.model.entities;

import com.schoolar.schoolarAPI.constants.UserTypes;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private UserTypes type;
    private String enrollment;
    private LocalDate birthDate;
    private String schoolGrade = null;
    private List<StudentSubjects> studentSubjects = null;
    private List<ProfessorSubjects> subjects = null;
    private List<Classrooms> classrooms = null;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(type.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StudentSubjects {
        private String subjectCode;
        private Double average;
        private Integer absences;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfessorSubjects {
        private String code;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Classrooms {
        private String classNumber;
        private Integer weeklyLessons;
    }

    public User() {
        this.id = UUID.randomUUID();
    }

}
