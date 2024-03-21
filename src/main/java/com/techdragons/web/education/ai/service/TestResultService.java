package com.techdragons.web.education.ai.service;

import com.techdragons.web.education.ai.TestResult;
import com.techdragons.web.education.ai.repository.TestResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestResultService {

    private final TestResultRepository testResultRepository;

    @Autowired
    public TestResultService(TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    public TestResult saveTestResult(TestResult testResult) {
        return testResultRepository.save(testResult);
    }

    public Optional<TestResult> getTestResultById(Long id) {
        return testResultRepository.findById(id);
    }

    public List<TestResult> getTestResultsByStudentId(Long studentId) {
        return testResultRepository.findByStudentId(studentId);
    }

    // Methods to calculate scores and analyze detailed answers...
}
