package com.techdragons.web.education.individ.repository;

import com.techdragons.web.education.individ.PersonalizedLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalizedLessonRepository extends JpaRepository<PersonalizedLesson, Long> {
}
