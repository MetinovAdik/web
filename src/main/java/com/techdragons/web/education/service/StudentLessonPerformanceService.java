package com.techdragons.web.education.service;

import com.techdragons.web.education.StudentLessonPerformance;
import com.techdragons.web.education.repository.StudentLessonPerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentLessonPerformanceService {

    private final StudentLessonPerformanceRepository studentLessonPerformanceRepository;
    @Autowired
    public StudentLessonPerformanceService(StudentLessonPerformanceRepository studentLessonPerformanceRepository) {
        this.studentLessonPerformanceRepository = studentLessonPerformanceRepository;
    }

    public List<StudentLessonPerformance> findAllPerformances() {
        return studentLessonPerformanceRepository.findAll();
    }

    public StudentLessonPerformance findPerformanceById(Long id) {
        return studentLessonPerformanceRepository.findById(id).orElse(null);
    }

    public StudentLessonPerformance savePerformance(StudentLessonPerformance studentLessonPerformance) {
        return studentLessonPerformanceRepository.save(studentLessonPerformance);
    }
}
