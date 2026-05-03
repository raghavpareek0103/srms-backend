package com.srms.backend.service;

import com.srms.backend.entity.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();
    Student getById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
}