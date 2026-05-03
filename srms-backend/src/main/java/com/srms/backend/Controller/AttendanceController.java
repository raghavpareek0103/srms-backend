package com.srms.backend.Controller;

import com.srms.backend.entity.Attendance;
import com.srms.backend.repository.ParentRepository;
import com.srms.backend.repository.StudentRepository;
import com.srms.backend.service.AttendanceService;
import com.srms.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired private AttendanceService service;
    @Autowired private EmailService emailService;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ParentRepository parentRepository;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public Attendance mark(@RequestBody Attendance attendance) {
        Attendance saved = service.markAttendance(attendance);
        if (!saved.isAttended()) {
            studentRepository.findById(saved.getStudentId()).ifPresent(student ->
                    parentRepository.findById(student.getParentId()).ifPresent(parent ->
                            emailService.sendAbsentMail(parent.getEmail(), saved.getStudentName())
                    )
            );
        }
        return saved;
    }

    @PreAuthorize("hasAnyRole('PARENT','STUDENT')")
    @GetMapping("/{studentId}")
    public List<Attendance> get(@PathVariable Long studentId) {
        return service.getByStudent(studentId);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{id}")
    public Attendance update(@PathVariable Long id, @RequestBody Attendance attendance) {
        return service.updateAttendance(id, attendance);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteAttendance(id);
        return ResponseEntity.ok("Attendance deleted successfully");
    }
}