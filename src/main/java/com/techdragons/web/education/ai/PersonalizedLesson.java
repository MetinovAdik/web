package com.techdragons.web.education.ai;

import com.techdragons.web.education.Course;
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
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
    private String title;
    private String descriptionOne;
    private String descriptionTwo;
    private String exercise;
    private String exerciseAnswer;
}
