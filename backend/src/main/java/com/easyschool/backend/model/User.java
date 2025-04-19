package com.easyschool.backend.model;

import java.util.List;

import org.hibernate.annotations.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
// @Table(name = "school.user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    private String name;
    private String surname;
    private String birthdate;
    private String role;
    
    @OneToMany(mappedBy = "user")
    private List<Credential> credentials;

    
    @ManyToOne
    private List<Score> scores;

    @ManyToMany
    private List<Subject> subjects;
    
    @ManyToMany (mappedBy = "user")
    private List<Lesson> lessons;
    
    @OneToMany(mappedBy = "user")
    private List<File> files;

}
