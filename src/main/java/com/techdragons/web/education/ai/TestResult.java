package com.techdragons.web.education.ai;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "test_results")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "student_id")
    private Long studentId;

    private Double score; // Can be calculated based on DetailedAnswers

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetailedAnswer> detailedAnswers;

}
