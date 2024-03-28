package com.techdragons.web.education.individ.repository;

import com.techdragons.web.education.individ.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByAnswerListId(Long testResultId);
}
