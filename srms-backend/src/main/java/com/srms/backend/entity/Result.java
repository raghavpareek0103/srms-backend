package com.srms.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String studentName;
    private String className;
    private Integer maths;
    private Integer science;
    private Integer english;
    private Integer total;
    private Double percentage;
    private String grade;
}