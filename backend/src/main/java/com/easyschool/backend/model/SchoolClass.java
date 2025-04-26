package com.easyschool.backend.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "school_class") 
public class SchoolClass {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column
  private String description;

  @ManyToOne
  private Program program;

  @OneToMany(mappedBy = "schoolclasses")
  private List<Lesson> lesson;

  @OneToMany(mappedBy = "schoolclasses")
  private List<Exam> exam;
  
}
