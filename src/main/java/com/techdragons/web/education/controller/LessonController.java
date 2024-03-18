package com.techdragons.web.education.controller;

import com.techdragons.web.education.dto.LessonDTO;
import com.techdragons.web.education.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/add")
    public ResponseEntity<LessonDTO> addLesson(@RequestBody LessonDTO lessonDTO) {
        // Assuming you have a method in your service to handle this:
        LessonDTO savedLessonDTO = lessonService.createAndAddLessonToCourse(lessonDTO);

        // Assuming createAndAddLessonToCourse returns a LessonDTO
        return new ResponseEntity<>(savedLessonDTO, HttpStatus.CREATED);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonDTO>> getLessonsByCourse(@PathVariable Long courseId) {
        // Use the service to get a list of Lesson entities by courseId
        List<LessonDTO> lessonsDTO = lessonService.getAllLessonsOfCourse(courseId);

        // Assuming getAllLessonsOfCourse returns a List<LessonDTO>
        return ResponseEntity.ok(lessonsDTO);
    }
}
