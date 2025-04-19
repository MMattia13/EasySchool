package com.easyschool.backend.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
// @Table(name = "school.file")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String date;
    private String time;
    private String duration;
    private String type; // written, oral, practical
    private int score; 

    @ManyToMany(mappedBy = "exams")
    private List<User> users;

//     @ManytoOne  (mappedBy = "exam")
//     private List<Class> classes; 
}
