package com.techdragons.web.education.ai.service;

import com.techdragons.web.education.ai.PersonalizedLesson;
import com.techdragons.web.education.ai.repository.PersonalizedLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalizedLessonService {

    private final PersonalizedLessonRepository personalizedLessonRepository;

    @Autowired
    public PersonalizedLessonService(PersonalizedLessonRepository personalizedLessonRepository) {
        this.personalizedLessonRepository = personalizedLessonRepository;
    }

    public PersonalizedLesson createPersonalizedLesson(PersonalizedLesson lesson) {
        return personalizedLessonRepository.save(lesson);
    }

    public List<PersonalizedLesson> getPersonalizedLessonsByCourseId(Long courseId) {
        return personalizedLessonRepository.findByCourseId(courseId);
    }

    // Additional methods as needed, possibly including logic for generating lesson content using AI...
}
