package com.techdragons.web.education.repository;

import com.techdragons.web.education.Enrollment;
import com.techdragons.web.education.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository  extends JpaRepository<Enrollment, Long> {
}
