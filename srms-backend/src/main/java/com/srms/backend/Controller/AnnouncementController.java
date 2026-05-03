package com.srms.backend.Controller;

import com.srms.backend.entity.Announcement;
import com.srms.backend.entity.Attendance;
import com.srms.backend.entity.Parent;
import com.srms.backend.repository.ParentRepository;
import com.srms.backend.service.AnnouncementService;
import com.srms.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    @Autowired private AnnouncementService service;
    @Autowired private EmailService emailService;
    @Autowired private ParentRepository parentRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Announcement create(@RequestBody Announcement announcement) {
        Announcement saved = service.create(announcement);

        try {
            List<Parent> parents = parentRepository.findAll();
            parents.forEach(parent ->
                    emailService.sendAnnouncementMail(
                            parent.getEmail(),
                            saved.getTitle(),
                            saved.getMessage())
            );
        } catch (Exception e) {
            System.out.println("Email failed: " + e.getMessage());
        }
        return saved;
    }

    @PreAuthorize("hasRole('PARENT')")
    @GetMapping
    public List<Announcement> getAll() {
        return service.getAll();
    }
}