package com.srms.backend.service.impl;

import com.srms.backend.entity.Attendance;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.AttendanceRepository;
import com.srms.backend.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository repository;

    @Override
    public Attendance markAttendance(Attendance attendance) {
        return repository.save(attendance);
    }

    @Override
    public List<Attendance> getByStudent(Long studentId) {
        return repository.findByStudentId(studentId);
    }

    @Override
    public Attendance updateAttendance(Long id, Attendance updated) {
        Attendance existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found"));
        existing.setAttended(updated.isAttended());  // ← uses new field name
        existing.setDate(updated.getDate());
        return repository.save(existing);
    }

    @Override
    public void deleteAttendance(Long id) {
        repository.deleteById(id);
    }
}