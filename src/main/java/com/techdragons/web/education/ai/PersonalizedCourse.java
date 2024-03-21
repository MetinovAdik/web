package com.techdragons.web.education.ai;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personalized_courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalizedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private Long studentId;
    private Long teacherId;
    private String title;
    private List<String> themes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personalized_course_id")
    private List<PersonalizedLesson> lessons = new ArrayList<>();

}
