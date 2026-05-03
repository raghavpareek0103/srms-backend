package com.srms.backend.Controller;

import com.srms.backend.dto.ReportCardDto;
import com.srms.backend.pdf.ReportCardPdfGenerator;
import com.srms.backend.repository.ParentRepository;
import com.srms.backend.repository.StudentRepository;
import com.srms.backend.service.EmailService;
import com.srms.backend.service.ReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report-card")
public class ReportCardController {

    @Autowired private ReportCardService service;
    @Autowired private EmailService emailService;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ParentRepository parentRepository;

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping("/{studentId}")
    public ResponseEntity<byte[]> download(@PathVariable Long studentId) {
        ReportCardDto dto = service.generate(studentId);
        byte[] pdf = ReportCardPdfGenerator.generate(dto);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=report-card.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/email/{studentId}")
    public String email(@PathVariable Long studentId) {
        ReportCardDto dto = service.generate(studentId);
        byte[] pdf = ReportCardPdfGenerator.generate(dto);

        studentRepository.findById(studentId).ifPresent(student ->
                parentRepository.findById(student.getParentId()).ifPresent(parent ->
                        emailService.sendResultPdf(parent.getEmail(), pdf)
                )
        );
        return "Report Card emailed successfully";
    }
}