package com.techdragons.web.education.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private List<String> themes;
}