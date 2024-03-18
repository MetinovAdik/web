package com.techdragons.web.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name="performances")
@AllArgsConstructor
@NoArgsConstructor
public class StudentPerformance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long student;
    private Double grade;
    private String registry_time;
    private List<String> goodPerformanceLessonsList;
    private List<String> badPerformanceLessonsList;
    private String attendance;
}
