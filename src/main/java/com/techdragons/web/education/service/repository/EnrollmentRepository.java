package com.techdragons.web.education.service.repository;

import com.techdragons.web.education.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository  extends JpaRepository<Enrollment, Long> {
}
