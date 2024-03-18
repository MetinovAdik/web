package com.techdragons.web.education;

import com.techdragons.web.entity.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_lesson_performances")
public class StudentLessonPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private Lesson lesson;

    private String exerciseSubmission;
    private Boolean exerciseCorrect;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean completed = false;
    public Long getId() {
        return id;
    }
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getExerciseSubmission() {
        return exerciseSubmission;
    }

    public void setExerciseSubmission(String exerciseSubmission) {
        this.exerciseSubmission = exerciseSubmission;
    }

    public Boolean getExerciseCorrect() {
        return exerciseCorrect;
    }

    public void setExerciseCorrect(Boolean exerciseCorrect) {
        this.exerciseCorrect = exerciseCorrect;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getDurationInMinutes() {
        if (startTime != null && endTime != null) {
            return java.time.Duration.between(startTime, endTime).toMinutes();
        }
        return null;
    }
}