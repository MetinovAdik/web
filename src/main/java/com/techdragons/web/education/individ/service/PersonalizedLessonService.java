package com.techdragons.web.education.individ.service;

import com.techdragons.web.education.individ.PersonalizedLesson;
import com.techdragons.web.education.individ.repository.PersonalizedLessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
