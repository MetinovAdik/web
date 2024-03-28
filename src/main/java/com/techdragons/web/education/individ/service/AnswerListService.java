package com.techdragons.web.education.individ.service;

import com.techdragons.web.education.individ.AnswerList;
import com.techdragons.web.education.individ.repository.AnswerListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerListService {

    private final AnswerListRepository answerListRepository;

    @Autowired
    public AnswerListService(AnswerListRepository answerListRepository) {
        this.answerListRepository = answerListRepository;
    }

    public AnswerList saveTestResult(AnswerList answerList) {
        return answerListRepository.save(answerList);
    }

    public Optional<AnswerList> getTestResultById(Long id) {
        return answerListRepository.findById(id);
    }

    public List<AnswerList> getTestResultsByStudentId(Long studentId) {
        return answerListRepository.findByStudentId(studentId);
    }

    // Methods to calculate scores and analyze detailed answers...
}
