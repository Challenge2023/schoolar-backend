package com.schoolar.schoolarAPI.subjects.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "subjects")
@Getter
@Setter
public class Subject {
    private UUID id;
    private String code;
    private String description;
}
