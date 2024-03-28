package com.techdragons.web.education.individ;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "answer_lists")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @OneToMany(mappedBy = "answerList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;

}
