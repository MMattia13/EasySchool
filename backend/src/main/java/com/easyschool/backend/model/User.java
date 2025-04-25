package com.easyschool.backend.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "school_user") 
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String surname;
    
    private String birthdate;


    @OneToMany(mappedBy = "user")
    private List<Credential> credentials;

    @OneToMany
    private List<Score> score;

    @ManyToMany
    private List<Subject> subject;

    @ManyToMany
    private List<Lesson> lesson;

    @OneToMany(mappedBy = "user")
    private List<File> file;
}
