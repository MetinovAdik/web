package com.techdragons.web.education.repository;

import com.techdragons.web.education.StudentPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPerformanceRepository extends JpaRepository<StudentPerformance, Long> {
}
