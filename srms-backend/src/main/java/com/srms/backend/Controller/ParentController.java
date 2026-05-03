package com.srms.backend.Controller;

import com.srms.backend.entity.Parent;
import com.srms.backend.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {

    @Autowired private ParentService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Parent create(@RequestBody Parent parent) {
        return service.addParent(parent);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Parent> getAll() {
        return service.getAllParents();
    }

    @PreAuthorize("hasAnyRole('ADMIN','PARENT')")
    @GetMapping("/{id}")
    public Parent get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Parent update(@PathVariable Long id, @RequestBody Parent parent) {
        return service.updateParent(id, parent);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteParent(id);
        return ResponseEntity.ok("Parent deleted successfully");
    }
}