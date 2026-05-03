package com.srms.backend.service.impl;

import com.srms.backend.entity.Teacher;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.TeacherRepository;
import com.srms.backend.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    @Override
    public Teacher create(Teacher teacher) {
        return repository.save(teacher);
    }

    @Override
    public List<Teacher> getAll() {
        return repository.findAll();
    }

    @Override
    public Teacher update(Long id, Teacher updated) {
        Teacher existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setSubject(updated.getSubject());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}