package com.srms.backend.service;

import com.srms.backend.dto.ReportCardDto;

public interface ReportCardService {
    ReportCardDto generate(Long studentId);
}