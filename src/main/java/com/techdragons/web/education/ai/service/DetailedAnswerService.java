package com.techdragons.web.education.ai.service;

import com.techdragons.web.education.ai.DetailedAnswer;
import com.techdragons.web.education.ai.repository.DetailedAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailedAnswerService {

    private final DetailedAnswerRepository detailedAnswerRepository;

    @Autowired
    public DetailedAnswerService(DetailedAnswerRepository detailedAnswerRepository) {
        this.detailedAnswerRepository = detailedAnswerRepository;
    }

    public DetailedAnswer saveDetailedAnswer(DetailedAnswer detailedAnswer) {
        return detailedAnswerRepository.save(detailedAnswer);
    }

    public List<DetailedAnswer> getDetailedAnswersByTestResultId(Long testResultId) {
        return detailedAnswerRepository.findByTestResultId(testResultId);
    }

    // Methods for analyzing detailed answers to determine student understanding...
}
