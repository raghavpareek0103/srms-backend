package com.srms.backend.service;

import com.srms.backend.entity.Announcement;
import java.util.List;

public interface AnnouncementService {
    Announcement create(Announcement announcement);
    List<Announcement> getAll();
}