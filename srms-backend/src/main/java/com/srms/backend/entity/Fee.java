package com.srms.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "fees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId;
    private String studentName;
    private double totalAmount;
    private double paidAmount;
    private double dueAmount;
    private LocalDate dueDate;
    private boolean feePaid;  // ← renamed from 'paid'
}