package com.srms.backend.service.impl;

import com.srms.backend.entity.Parent;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.ParentRepository;
import com.srms.backend.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository repository;

    @Override
    public Parent addParent(Parent parent) {
        return repository.save(parent);
    }

    @Override
    public List<Parent> getAllParents() {
        return repository.findAll();
    }

    @Override
    public Parent getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found"));
    }

    @Override
    public Parent updateParent(Long id, Parent updated) {
        Parent existing = getById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setStudentId(updated.getStudentId());
        return repository.save(existing);
    }

    @Override
    public void deleteParent(Long id) {
        repository.deleteById(id);
    }
}