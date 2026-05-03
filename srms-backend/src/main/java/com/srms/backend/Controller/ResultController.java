package com.srms.backend.Controller;

import com.srms.backend.entity.Result;
import com.srms.backend.pdf.ResultPdfGenerator;
import com.srms.backend.repository.ParentRepository;
import com.srms.backend.repository.StudentRepository;
import com.srms.backend.service.EmailService;
import com.srms.backend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired private ResultService service;
    @Autowired private EmailService emailService;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ParentRepository parentRepository;

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping
    public Result create(@RequestBody Result result) {
        Result saved = service.addResult(result);

        try {
            byte[] pdf = ResultPdfGenerator.generate(saved);
            studentRepository.findById(saved.getStudentId()).ifPresent(student ->
                    parentRepository.findById(student.getParentId()).ifPresent(parent ->
                            emailService.sendResultPdf(parent.getEmail(), pdf)
                    )
            );
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
        return saved;
    }
    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Result result) {
        return service.updateResult(id, result);
    }
    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/{studentId}")
    public Result view(@PathVariable Long studentId) {
        return service.getByStudent(studentId);
    }
}