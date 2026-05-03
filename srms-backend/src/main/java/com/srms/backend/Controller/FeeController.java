package com.srms.backend.Controller;

import com.srms.backend.entity.Fee;
import com.srms.backend.repository.ParentRepository;
import com.srms.backend.repository.StudentRepository;
import com.srms.backend.service.EmailService;
import com.srms.backend.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fees")
public class FeeController {

    @Autowired private FeeService service;
    @Autowired private EmailService emailService;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ParentRepository parentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Fee create(@RequestBody Fee fee) {
        Fee saved = service.createFee(fee);

    if  (!saved.isFeePaid()) {    studentRepository.findById(saved.getStudentId()).ifPresent(student ->
                    parentRepository.findById(student.getParentId()).ifPresent(parent ->
                            emailService.sendFeeDueMail(parent.getEmail(),
                                    saved.getStudentName(), saved.getDueAmount())
                    )
            );
        }
        return saved;
    }

    @PreAuthorize("hasAnyRole('PARENT','STUDENT')")
    @GetMapping("/{studentId}")
    public List<Fee> get(@PathVariable Long studentId) {
        return service.getByStudent(studentId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Fee update(@PathVariable Long id, @RequestBody Fee fee) {
        return service.updateFee(id, fee);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteFee(id);
        return ResponseEntity.ok("Fee deleted successfully");
    }
}