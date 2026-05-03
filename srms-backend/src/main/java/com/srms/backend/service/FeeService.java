package com.srms.backend.service;

import com.srms.backend.entity.Fee;
import java.util.List;

public interface FeeService {
    Fee createFee(Fee fee);
    List<Fee> getByStudent(Long studentId);
    Fee updateFee(Long id, Fee fee);
    void deleteFee(Long id);
}