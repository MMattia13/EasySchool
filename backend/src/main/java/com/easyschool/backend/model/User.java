package com.easyschool.backend.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@AllArgsConstructor
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

    @OneToOne(mappedBy = "user")
    private Credential credentials;

    @OneToMany
    private List<Score> score;

    @ManyToMany
    private List<Subject> subject;

    @ManyToMany
    private List<Exam> exams;

    @ManyToMany
    private List<Lesson> lesson;

    @OneToMany(mappedBy = "user")
    private List<File> file;

    @ManyToOne
    private Role role;
}
