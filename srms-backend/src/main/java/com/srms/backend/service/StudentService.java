package com.srms.backend.service;

import com.srms.backend.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Page<Student> getAllStudents(Pageable pageable);
    List<Student> getAllStudents();
    Student getById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}