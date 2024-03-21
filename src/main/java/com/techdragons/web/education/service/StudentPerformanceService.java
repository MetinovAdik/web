package com.techdragons.web.education.service;

import com.techdragons.web.education.StudentPerformance;
import com.techdragons.web.education.repository.StudentPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPerformanceService {

    private final StudentPerformanceRepository studentPerformanceRepository;

    @Autowired
    public StudentPerformanceService(StudentPerformanceRepository studentPerformanceRepository) {
        this.studentPerformanceRepository = studentPerformanceRepository;
    }

    public List<StudentPerformance> findAllPerformances() {
        return studentPerformanceRepository.findAll();
    }

    public StudentPerformance findPerformanceById(Long id) {
        return studentPerformanceRepository.findById(id).orElse(null);
    }

    public StudentPerformance savePerformance(StudentPerformance studentPerformance) {
        return studentPerformanceRepository.save(studentPerformance);
    }
}
