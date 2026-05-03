package com.srms.backend.Controller;

import com.srms.backend.entity.Teacher;
import com.srms.backend.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired private TeacherService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) {
        return service.create(teacher);
    }

    @PreAuthorize("hasAnyRole('ADMIN','TEACHER')")
    @GetMapping
    public List<Teacher> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return service.update(id, teacher);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Teacher deleted successfully");
    }
}