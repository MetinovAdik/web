package com.techdragons.web.education.individ.service;

import com.techdragons.web.education.individ.PersonalizedCourse;
import com.techdragons.web.education.individ.repository.PersonalizedCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalizedCourseService {

    private final PersonalizedCourseRepository personalizedCourseRepository;

    @Autowired
    public PersonalizedCourseService(PersonalizedCourseRepository personalizedCourseRepository) {
        this.personalizedCourseRepository = personalizedCourseRepository;
    }

    public PersonalizedCourse createPersonalizedCourse(Long studentId , String title , List<String> themes) {
        PersonalizedCourse personalizedCourse = new PersonalizedCourse();
        personalizedCourse.setStudentId(studentId);
        personalizedCourse.setTitle(title);
        personalizedCourse.setThemes(themes);
        return personalizedCourseRepository.save(personalizedCourse);
    }

    public List<PersonalizedCourse> getPersonalizedCoursesByStudentId(Long studentId) {
        return personalizedCourseRepository.findByStudentId(studentId);
    }

    // Additional logic to generate personalized lessons based on student weaknesses...
}
