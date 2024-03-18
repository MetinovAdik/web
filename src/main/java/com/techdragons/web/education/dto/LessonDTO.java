package com.techdragons.web.education.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LessonDTO {
    private String title;
    private String descriptionOne;
    private String descriptionTwo;
    private String exercise;
    private String exerciseAnswer;
    private Long courseId;
}