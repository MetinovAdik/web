package com.techdragons.web.education.individ.service;

import com.techdragons.web.education.individ.Answer;
import com.techdragons.web.education.individ.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Answer saveDetailedAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    public List<Answer> getDetailedAnswersByTestResultId(Long testResultId) {
        return answerRepository.findByAnswerListId(testResultId);
    }
}
