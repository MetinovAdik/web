package com.techdragons.web.education.controller;

import com.techdragons.web.education.Course;
import com.techdragons.web.education.dto.CourseDTO;
import com.techdragons.web.education.service.CourseService;
import com.techdragons.web.entity.User;
import com.techdragons.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final UserRepository userRepository;
    @Autowired
    public CourseController(CourseService courseService, UserRepository userRepository) {
        this.courseService = courseService;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseDTO> createCourse(@AuthenticationPrincipal UserDetails userDetails , @RequestBody CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setThemes(courseDTO.getThemes());
        Optional<User> user =  userRepository.findByEmail(userDetails.getUsername());
        course.setTeacherId(user.get().getId()); // Assuming Course has a teacherId field

        // Save the course
        Course savedCourse = courseService.saveCourse(course);

        // Convert back to DTO
        CourseDTO savedCourseDTO = new CourseDTO(savedCourse.getId(), savedCourse.getTitle(), savedCourse.getDescription(),savedCourse.getThemes());

        return new ResponseEntity<>(savedCourseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();


        List<CourseDTO> courseDTOs = courses.stream()
                .map(course -> new CourseDTO(course.getId(), course.getTitle(), course.getDescription(),course.getThemes()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(courseDTOs);
    }

    
}
