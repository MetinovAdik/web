package com.techdragons.web.education.individ.repository;

import com.techdragons.web.education.individ.AnswerList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerListRepository extends JpaRepository<AnswerList, Long> {
    List<AnswerList> findByStudentId(Long studentId);
}
