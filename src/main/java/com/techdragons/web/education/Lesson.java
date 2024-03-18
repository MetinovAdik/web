package com.techdragons.web.education;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lessons")
@Setter
@Getter
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    private String title;
    private String descriptionOne;
    private String descriptionTwo;
    private String exercise;
    private String exerciseAnswer;
}
