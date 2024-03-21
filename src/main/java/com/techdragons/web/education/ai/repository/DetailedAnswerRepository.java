package com.techdragons.web.education.ai.repository;

import com.techdragons.web.education.ai.DetailedAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailedAnswerRepository extends JpaRepository<DetailedAnswer, Long> {
    List<DetailedAnswer> findByTestResultId(Long testResultId);
}
