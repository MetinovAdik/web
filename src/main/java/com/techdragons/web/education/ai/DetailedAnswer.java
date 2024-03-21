package com.techdragons.web.education.ai;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detailed_answers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetailedAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_result_id", nullable = false)
    private TestResult testResult;

    private String theme;
    private Boolean correct;
    private String courseTitle;
}
