package com.techdragons.web.education.ai.repository;

import com.techdragons.web.education.ai.PersonalizedLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalizedLessonRepository extends JpaRepository<PersonalizedLesson, Long> {
    List<PersonalizedLesson> findByCourseId(Long courseId);
}
