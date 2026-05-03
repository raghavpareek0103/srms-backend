package com.srms.backend.service;

import com.srms.backend.entity.Attendance;
import java.util.List;

public interface AttendanceService {
    Attendance markAttendance(Attendance attendance);
    List<Attendance> getByStudent(Long studentId);
    Attendance updateAttendance(Long id, Attendance attendance);
    void deleteAttendance(Long id);
}