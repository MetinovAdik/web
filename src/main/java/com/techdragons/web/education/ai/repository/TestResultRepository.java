package com.techdragons.web.education.ai.repository;

import com.techdragons.web.education.ai.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    List<TestResult> findByStudentId(Long studentId);
}
