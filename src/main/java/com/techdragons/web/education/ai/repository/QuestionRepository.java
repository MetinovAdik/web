package com.techdragons.web.education.ai.repository;

import com.techdragons.web.education.ai.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTestId(Long testId);
}
