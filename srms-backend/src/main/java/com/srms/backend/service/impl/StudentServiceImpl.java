package com.srms.backend.service.impl;

import com.srms.backend.entity.Student;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.StudentRepository;
import com.srms.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public Student addStudent(Student student) {
        if (repository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new IllegalArgumentException(
                    "Student with roll number " + student.getRollNumber() + " already exists");
        }
        return repository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    @Override
    public Student getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    @Override
    public Student updateStudent(Long id, Student updated) {
        Student existing = getById(id);
        existing.setName(updated.getName());
        existing.setRollNumber(updated.getRollNumber());
        existing.setEmail(updated.getEmail());
        existing.setClassName(updated.getClassName());
        existing.setSection(updated.getSection());
        existing.setParentId(updated.getParentId());
        return repository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}