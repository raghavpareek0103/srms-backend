package com.srms.backend.service;

import com.srms.backend.entity.Parent;
import java.util.List;

public interface ParentService {
    Parent addParent(Parent parent);
    List<Parent> getAllParents();
    Parent getById(Long id);
    Parent updateParent(Long id, Parent parent);
    void deleteParent(Long id);
}