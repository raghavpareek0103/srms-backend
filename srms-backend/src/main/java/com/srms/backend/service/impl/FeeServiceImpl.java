package com.srms.backend.service.impl;

import com.srms.backend.entity.Fee;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.FeeRepository;
import com.srms.backend.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeServiceImpl implements FeeService {

    private final FeeRepository repository;

    @Override
    public Fee createFee(Fee fee) {
        double due = fee.getTotalAmount() - fee.getPaidAmount();
        fee.setDueAmount(due);
        fee.setFeePaid(due <= 0);  // ← changed
        return repository.save(fee);
    }

    @Override
    public List<Fee> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    @Override
    public Fee updateFee(Long id, Fee updated) {
        Fee existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found"));
        existing.setTotalAmount(updated.getTotalAmount());
        existing.setPaidAmount(updated.getPaidAmount());
        double due = updated.getTotalAmount() - updated.getPaidAmount();
        existing.setDueAmount(due);
        existing.setFeePaid(due <= 0);  // ← changed
        existing.setDueDate(updated.getDueDate());
        return repository.save(existing);
    }

    @Override
    public void deleteFee(Long id) {
        repository.deleteById(id);
    }
}