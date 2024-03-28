package com.techdragons.web.education.controller;

import com.techdragons.web.education.Enrollment;
import com.techdragons.web.education.individ.service.PersonalizedCourseService;
import com.techdragons.web.education.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final PersonalizedCourseService personalizedCourseService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, PersonalizedCourseService personalizedCourseService) {
        this.enrollmentService = enrollmentService;
        this.personalizedCourseService = personalizedCourseService;
    }

    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<?> enrollInCourse(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long courseId) throws IOException {
        Enrollment enrollment = enrollmentService.enrollInCourse(courseId,userDetails.getUsername());

        return new ResponseEntity<>("Enrolled and personalized course created successfully.", HttpStatus.CREATED);
    }
}
