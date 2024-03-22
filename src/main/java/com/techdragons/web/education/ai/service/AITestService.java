package com.techdragons.web.education.ai.service;

import com.techdragons.web.education.ai.AITest;
import com.techdragons.web.education.ai.repository.AITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AITestService {

    private final AITestRepository aiTestRepository;

    @Autowired
    public AITestService(AITestRepository aiTestRepository) {
        this.aiTestRepository = aiTestRepository;
    }

    public AITest createTest(AITest test) {
        return aiTestRepository.save(test);
    }

    public Optional<AITest> getTestById(Long id) {
        return aiTestRepository.findById(id);
    }

    public List<AITest> getAllTests() {
        return aiTestRepository.findAll();
    }
}
