package com.techdragons.web.education.service;

import com.techdragons.web.education.Course;
import com.techdragons.web.education.Lesson;
import com.techdragons.web.education.dto.LessonDTO;
import com.techdragons.web.education.repository.CourseRepository;
import com.techdragons.web.education.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public LessonService(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    public List<Lesson> findAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson findLessonById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    public Lesson saveLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public LessonDTO createAndAddLessonToCourse(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setDescriptionOne(lessonDTO.getDescriptionOne());
        lesson.setDescriptionTwo(lessonDTO.getDescriptionTwo());
        lesson.setExercise(lessonDTO.getExercise());
        lesson.setExerciseAnswer(lessonDTO.getExerciseAnswer());
        Course course = courseRepository.findById(lessonDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        lesson.setCourse(course);
        Lesson savedLesson = lessonRepository.save(lesson);
        return convertToDTO(savedLesson);
    }
    public List<LessonDTO> getAllLessonsOfCourse(Long courseId) {
        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);
        return lessons.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public LessonDTO convertToDTO(Lesson lesson) {
        LessonDTO dto = new LessonDTO();
        dto.setTitle(lesson.getTitle());
        dto.setDescriptionOne(lesson.getDescriptionOne());
        dto.setDescriptionTwo(lesson.getDescriptionTwo());
        dto.setExercise(lesson.getExercise());
        dto.setExerciseAnswer(lesson.getExerciseAnswer());
        dto.setCourseId(lesson.getCourse().getId());
        return dto;
    }
}
