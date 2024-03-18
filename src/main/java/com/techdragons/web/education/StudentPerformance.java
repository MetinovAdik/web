package com.techdragons.web.education;

import com.techdragons.web.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "student_performance")
public class StudentPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;

    private Double grade;

    @Column(name = "registry_time")
    private LocalDateTime registryTime;

    public StudentPerformance() {
        // No-args constructor
    }

    public StudentPerformance(User student, Course course, Double grade, LocalDateTime registryTime) {
        this.student = student;
        this.course = course;
        this.grade = grade;
        this.registryTime = registryTime;
    }


}
