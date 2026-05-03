package com.srms.backend.service.impl;

import com.srms.backend.entity.Announcement;
import com.srms.backend.repository.AnnouncementRepository;
import com.srms.backend.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository repository;

    @Override
    public Announcement create(Announcement announcement) {
        announcement.setCreatedAt(LocalDateTime.now());
        return repository.save(announcement);
    }

    @Override
    public List<Announcement> getAll() {
        return repository.findAll();
    }
}