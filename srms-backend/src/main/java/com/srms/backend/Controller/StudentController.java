package com.srms.backend.Controller;

import com.srms.backend.entity.Student;
import com.srms.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/students")
@PreAuthorize("hasRole('ADMIN')")
public class StudentController {

    @Autowired private StudentService service;

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping
    public ResponseEntity<Page<Student>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseEntity.ok(service.getAllStudents(pageable));
    }

    @GetMapping("/all")
    public List<Student> getAllWithoutPagination() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/me/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public Student getMyProfile(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}