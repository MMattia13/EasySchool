package com.easyschool.backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
// @Table(name = "school.file")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String date;
    private String time;

    @OneToMany(mappedBy = "lesson")
    private List<Subject> subject;

    @ManyToOne
    private Class classes;
    
    @ManyToMany(mappedBy = "lesson")
    private List<User> users;
}
