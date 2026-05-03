package com.srms.backend.service;

import com.srms.backend.entity.Teacher;
import java.util.List;

public interface TeacherService {
    Teacher create(Teacher teacher);
    List<Teacher> getAll();
    Teacher update(Long id, Teacher teacher);
    void delete(Long id);
}