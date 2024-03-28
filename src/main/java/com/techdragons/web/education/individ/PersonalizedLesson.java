package com.techdragons.web.education.individ;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personalized_lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalizedLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "personalized_course_id")
    private Long personalizedCourseId;
    private String theme;
    private String theory;
    private String practicalExample;
    private String exercise;
    private String exerciseAnswer;
}
