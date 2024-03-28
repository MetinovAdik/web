package com.techdragons.web.education.individ.service;

import com.techdragons.web.education.individ.Test;
import com.techdragons.web.education.individ.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public Optional<Test> getTestById(Long id) {
        return testRepository.findById(id);
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
}
