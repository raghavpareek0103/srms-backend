package com.srms.backend.service.impl;

import com.srms.backend.entity.Result;
import com.srms.backend.exception.ResourceNotFoundException;
import com.srms.backend.repository.ResultRepository;
import com.srms.backend.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository repository;

    @Override
    public Result addResult(Result result) {
        if (repository.findByStudentId(result.getStudentId()).isPresent()) {
            throw new IllegalArgumentException(
                    "Result for student ID " + result.getStudentId() + " already exists. Use update instead.");
        }
        return calculateAndSave(result);
    }

    @Override
    public List<Result> getAllResults() {
        return repository.findAll();
    }

    @Override
    public Result getByStudent(Long studentId) {
        return repository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }

    @Override
    public Result updateResult(Long id, Result updated) {
        Result existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
        existing.setMaths(updated.getMaths());
        existing.setScience(updated.getScience());
        existing.setEnglish(updated.getEnglish());
        return calculateAndSave(existing);
    }

    private Result calculateAndSave(Result result) {
        int total = result.getMaths() + result.getScience() + result.getEnglish();
        double percentage = (total / 300.0) * 100;

        result.setTotal(total);
        result.setPercentage(percentage);

        if (percentage >= 90) result.setGrade("A+");
        else if (percentage >= 75) result.setGrade("A");
        else if (percentage >= 60) result.setGrade("B");
        else if (percentage >= 45) result.setGrade("C");
        else result.setGrade("F");

        return repository.save(result);
    }
}