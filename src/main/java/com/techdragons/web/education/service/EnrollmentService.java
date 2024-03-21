package com.techdragons.web.education.service;

import com.techdragons.web.education.Enrollment;
import com.techdragons.web.education.ai.PersonalizedCourse;
import com.techdragons.web.education.ai.service.PersonalizedCourseService;
import com.techdragons.web.education.ai.service.TestGenerationService;
import com.techdragons.web.education.repository.CourseRepository;
import com.techdragons.web.education.repository.EnrollmentRepository;
import com.techdragons.web.entity.User;
import com.techdragons.web.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final PersonalizedCourseService personalizedCourseService;
    private final TestGenerationService testGenerationService;
    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository, PersonalizedCourseService personalizedCourseService, TestGenerationService testGenerationService) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.personalizedCourseService = personalizedCourseService;
        this.testGenerationService = testGenerationService;
    }
    public Enrollment enrollInCourse(Long courseId, String studentEmail) throws IOException {
        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new IllegalArgumentException("Student not found with email: " + studentEmail));
        var course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

        // Create and save the enrollment
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollmentRepository.save(enrollment);

        // Create a personalized course
        PersonalizedCourse personalizedCourse = personalizedCourseService.createPersonalizedCourse(student.getId(),course.getTitle(),course.getThemes());

        // Generate initial test for the course themes
        testGenerationService.generateTestForCourse(courseId, course.getTitle(), course.getThemes());

        return enrollment;
    }
}
