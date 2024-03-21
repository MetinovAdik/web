package com.techdragons.web.education.ai.repository;

import com.techdragons.web.education.ai.PersonalizedCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalizedCourseRepository extends JpaRepository<PersonalizedCourse, Long> {
    List<PersonalizedCourse> findByStudentId(Long studentId);
}
