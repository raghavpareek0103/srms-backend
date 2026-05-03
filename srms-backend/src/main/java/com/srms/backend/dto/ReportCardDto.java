package com.srms.backend.dto;

import com.srms.backend.entity.Attendance;
import com.srms.backend.entity.Fee;
import com.srms.backend.entity.Result;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportCardDto {

    private String studentId;
    private String studentName;
    private String className;

    private Result result;
    private List<Attendance> attendanceList;
    private List<Fee> fees;
}
