package com.srms.backend.service.impl;

import com.srms.backend.dto.ReportCardDto;
import com.srms.backend.entity.Attendance;
import com.srms.backend.entity.Fee;
import com.srms.backend.entity.Result;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.AttendanceRepository;
import com.srms.backend.repository.FeeRepository;
import com.srms.backend.repository.ResultRepository;
import com.srms.backend.service.ReportCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportCardServiceImpl implements ReportCardService {

    private final ResultRepository resultRepo;
    private final AttendanceRepository attendanceRepo;
    private final FeeRepository feeRepo;

    @Override
    public ReportCardDto generate(Long studentId) {

        Result result = resultRepo.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));

        List<Attendance> attendance = attendanceRepo.findByStudentId(studentId);
        List<Fee> fees = feeRepo.findByStudentId(studentId);

        return ReportCardDto.builder()
                .studentId(String.valueOf(studentId))
                .studentName(result.getStudentName())
                .className(result.getClassName())
                .result(result)
                .attendanceList(attendance)
                .fees(fees)
                .build();
    }
}